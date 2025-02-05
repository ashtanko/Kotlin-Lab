/*
 * Copyright 2023 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import java.util.Stack
import kotlin.math.max

/**
 * 2130. Maximum Twin Sum of a Linked List
 * @see <a href="https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/">Source</a>
 */
fun interface MaxTwinSum {
    fun pairSum(head: ListNode?): Int
}

/**
 * Approach 1: Using List Of Integers
 */
class MaxTwinSumIntList : MaxTwinSum {
    override fun pairSum(head: ListNode?): Int {
        var current = head
        val values: MutableList<Int> = ArrayList()

        while (current != null) {
            values.add(current.value)
            current = current.next
        }

        var i = 0
        var j = values.size - 1
        var maximumSum = 0
        while (i < j) {
            maximumSum = max(maximumSum, values[i] + values[j])
            i++
            j--
        }

        return maximumSum
    }
}

/**
 * Approach 2: Using Stack
 */
class MaxTwinSumStack : MaxTwinSum {
    override fun pairSum(head: ListNode?): Int {
        var current = head
        val st: Stack<Int> = Stack<Int>()

        while (current != null) {
            st.push(current.value)
            current = current.next
        }

        current = head
        val size: Int = st.size
        var count = 1
        var maximumSum = 0
        while (count <= size / 2) {
            maximumSum = max(maximumSum, (current?.value ?: 0) + st.peek())
            current = current?.next
            st.pop()
            count++
        }

        return maximumSum
    }
}

/**
 * Approach 3: Reverse Second Half In Place
 */
class MaxTwinSumReverse : MaxTwinSum {
    override fun pairSum(head: ListNode?): Int {
        var slow = head
        var fast = head

        // Get middle of the linked list.
        while (fast?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }

        // Reverse second half of the linked list.
        var nextNode: ListNode?
        var prev: ListNode? = null
        while (slow != null) {
            nextNode = slow.next
            slow.next = prev
            prev = slow
            slow = nextNode
        }

        var start = head
        var maximumSum = 0
        while (prev != null) {
            maximumSum = max(maximumSum, (start?.value ?: 0) + prev.value)
            prev = prev.next
            start = start?.next
        }

        return maximumSum
    }
}
