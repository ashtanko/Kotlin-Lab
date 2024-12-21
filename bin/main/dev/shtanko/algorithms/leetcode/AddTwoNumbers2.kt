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

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.Stack

/**
 * 445. Add Two Numbers II
 * @see <a href="https://leetcode.com/problems/add-two-numbers-ii/">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/add-two-numbers-ii")
fun interface AddTwoNumbers2 {
    operator fun invoke(list1: ListNode?, list2: ListNode?): ListNode?
}

/**
 * Approach 1: Using Stack
 */
class AddTwoNumbers2Stack : AddTwoNumbers2 {
    /**
     * Adds two numbers represented as linked lists in reverse order.
     *
     * @param list1 The first linked list representing a number.
     * @param list2 The second linked list representing a number.
     * @return The linked list representing the sum of l1 and l2.
     */
    override fun invoke(list1: ListNode?, list2: ListNode?): ListNode? {
        val stack1: Stack<Int> = Stack<Int>()
        val stack2: Stack<Int> = Stack<Int>()
        var node1 = list1
        var node2 = list2
        while (node1 != null) {
            stack1.push(node1.value)
            node1 = node1.next
        }
        while (node2 != null) {
            stack2.push(node2.value)
            node2 = node2.next
        }

        var totalSum = 0
        var carryOver = 0
        var resultNode = ListNode()
        while (!stack1.empty() || !stack2.empty()) {
            if (!stack1.empty()) totalSum += stack1.pop()
            if (!stack2.empty()) totalSum += stack2.pop()
            resultNode.value = totalSum % DECIMAL
            carryOver = totalSum / DECIMAL
            val headNode = ListNode(carryOver)
            headNode.next = resultNode
            resultNode = headNode
            totalSum = carryOver
        }

        return if (carryOver == 0) resultNode.next else resultNode
    }
}

/**
 * Approach 2: Reverse Given Linked Lists
 */
class AddTwoNumbers2Reverse : AddTwoNumbers2 {
    /**
     * Adds two numbers represented by linked lists in reverse order.
     *
     * @param list1 The first linked list representing a number in reverse order.
     * @param list2 The second linked list representing a number in reverse order.
     * @return The sum of the two numbers as a linked list in reverse order.
     */
    override fun invoke(list1: ListNode?, list2: ListNode?): ListNode? {
        var reversedList1: ListNode? = list1?.reverseList()
        var reversedList2: ListNode? = list2?.reverseList()

        var totalSum = 0
        var carryOver = 0
        var resultNode = ListNode()
        while (reversedList1 != null || reversedList2 != null) {
            if (reversedList1 != null) totalSum += reversedList1.value
            if (reversedList2 != null) totalSum += reversedList2.value
            resultNode.value = totalSum % DECIMAL
            carryOver = totalSum / DECIMAL
            val headNode = ListNode(carryOver)
            headNode.next = resultNode
            resultNode = headNode
            totalSum = carryOver
            reversedList1 = reversedList1?.next
            reversedList2 = reversedList2?.next
        }

        return if (carryOver == 0) resultNode.next else resultNode
    }
}
