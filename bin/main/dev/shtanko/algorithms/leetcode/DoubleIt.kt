/*
 * Copyright 2024 Oleksii Shtanko
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
import java.util.Stack

/**
 * 2816. Double a Number Represented as a Linked List
 * @see <a href="https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/">Source</a>
 */
fun interface DoubleIt {
    operator fun invoke(head: ListNode?): ListNode?
}

class DoubleItReversing : DoubleIt {
    override fun invoke(head: ListNode?): ListNode? {
        val reversedListNode = head?.reverseList()

        var carryOver = 0
        var currentNode = reversedListNode
        var previousNode: ListNode? = null

        while (currentNode != null) {
            val doubledValue: Int = currentNode.value * 2 + carryOver
            currentNode.value = doubledValue % DECIMAL
            carryOver = if (doubledValue > 9) {
                1
            } else {
                0
            }
            previousNode = currentNode
            currentNode = currentNode.next
        }

        if (carryOver != 0) {
            val extraNode = ListNode(carryOver)
            previousNode?.next = extraNode
        }

        return reversedListNode?.reverseList()
    }
}

class DoubleItStack : DoubleIt {
    override fun invoke(head: ListNode?): ListNode? {
        val nodeStack: Stack<ListNode> = Stack()
        var currentNode = head

        while (currentNode != null) {
            nodeStack.push(currentNode)
            currentNode = currentNode.next
        }

        var carryOver = 0
        var previousNode: ListNode? = null

        while (nodeStack.isNotEmpty()) {
            val node = nodeStack.pop()
            val doubledValue = node.value * 2 + carryOver
            node.value = doubledValue % DECIMAL
            carryOver = if (doubledValue > 9) {
                1
            } else {
                0
            }
            node.next = previousNode
            previousNode = node
        }

        if (carryOver != 0) {
            val extraNode = ListNode(carryOver)
            extraNode.next = previousNode
            return extraNode
        }

        return previousNode
    }
}

class DoubleItRecursive : DoubleIt {
    override fun invoke(head: ListNode?): ListNode? {
        var currentNode = head
        val carry: Int = doubleNodeValue(currentNode)
        if (carry != 0) {
            currentNode = ListNode(carry, currentNode)
        }

        return currentNode
    }

    private fun doubleNodeValue(node: ListNode?): Int {
        if (node == null) return 0
        val doubledValue: Int = node.value * 2 + doubleNodeValue(node.next)
        node.value = doubledValue % DECIMAL
        return doubledValue / DECIMAL
    }
}

class DoubleItTwoPointers : DoubleIt {
    override fun invoke(head: ListNode?): ListNode? {
        val reversedHead = head?.reverseList()
        var curr = reversedHead
        var carry = 0

        while (curr != null) {
            val twiceOfVal = curr.value * 2 + carry
            carry = twiceOfVal / DECIMAL
            curr.value = twiceOfVal % DECIMAL
            curr = curr.next
        }
        if (carry > 0) {
            val newHead = ListNode(carry)
            var tail = reversedHead
            while (tail?.next != null) {
                tail = tail.next
            }
            tail?.next = newHead
        }
        return reversedHead?.reverseList()
    }
}
