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

/**
 * 92. Reverse Linked List II
 * @see <a href="https://leetcode.com/problems/reverse-linked-list-ii/">Source</a>
 */
fun interface ReverseLinkedList2 {
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode?
}

class ReverseLinkedList2Recursive : ReverseLinkedList2 {

    // Object level variables since we need the changes
    // to persist across recursive calls and Java is pass by value.
    private var stop = false
    private var left: ListNode? = null

    private fun recurseAndReverse(head: ListNode?, m: Int, n: Int) {
        // base case. Don't proceed any further
        var right: ListNode? = head
        if (n == 1) {
            return
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right?.next

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            left = left?.next
        }

        // Recurse with m and n reduced.
        recurseAndReverse(right, m - 1, n - 1)

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (left == right || right?.next == left) {
            stop = true
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!stop) {
            val leftValue: Int = left?.value ?: 0
            this.left?.value = right?.value ?: 0
            right?.value = leftValue
            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            left = left?.next
        }
    }

    override fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (head == null) return null
        this.left = head
        this.recurseAndReverse(head, left, right)
        return head
    }
}

class ReverseLinkedList2Iterative : ReverseLinkedList2 {
    override fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        var m = left
        var n = right
        var node: ListNode? = head
        // Move the two pointers until they reach the proper starting point
        // in the list.
        var cur = node
        var prev: ListNode? = null
        while (m > 1) {
            prev = cur
            cur = cur?.next
            m--
            n--
        }

        // The two pointers that will fix the final connections.
        val con = prev
        val tail = cur

        // Iteratively reverse the nodes until n becomes 0.
        var third: ListNode?
        while (n > 0) {
            third = cur?.next
            cur?.next = prev
            prev = cur
            cur = third
            n--
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev
        } else {
            node = prev
        }

        tail?.next = cur
        return node
    }
}
