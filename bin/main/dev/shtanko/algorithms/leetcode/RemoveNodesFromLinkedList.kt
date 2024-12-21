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

import java.util.Stack

/**
 * 2487. Remove Nodes From Linked List
 * @see <a href="https://leetcode.com/problems/remove-nodes-from-linked-list/">Remove Nodes From Linked List</a>
 */
fun interface RemoveNodesFromLinkedList {
    operator fun invoke(head: ListNode?): ListNode?
}

class RemoveNodesFromLinkedListStack : RemoveNodesFromLinkedList {
    override fun invoke(head: ListNode?): ListNode? {
        val stack: Stack<ListNode> = Stack()
        var current = head

        // Add nodes to the stack
        while (current != null) {
            stack.push(current)
            current = current.next
        }

        current = stack.pop()
        var maximum: Int = current.value
        var resultList: ListNode? = ListNode(maximum)

        // Remove nodes from the stack and add to result
        while (stack.isNotEmpty()) {
            current = stack.pop()
            // Current should not be added to the result
            if (current.value < maximum) {
                continue
            } else {
                val newNode = ListNode(current.value)
                newNode.next = resultList
                resultList = newNode
                maximum = current.value
            }
        }

        return resultList
    }
}

class RemoveNodesFromLinkedListRecursive : RemoveNodesFromLinkedList {
    override fun invoke(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        val next = invoke(head.next)
        return if (next != null && next.value > head.value) {
            next
        } else {
            head.next = next
            head
        }
    }
}

class RemoveNodesFromLinkedListReverse : RemoveNodesFromLinkedList {
    override fun invoke(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        val reversed = reverse(head)
        var current = reversed
        var maximum = current.value
        var resultList: ListNode? = ListNode(maximum)

        while (current.next != null) {
            current = current.next!!
            if (current.value < maximum) {
                continue
            } else {
                val newNode = ListNode(current.value)
                newNode.next = resultList
                resultList = newNode
                maximum = current.value
            }
        }

        return resultList
    }

    private fun reverse(head: ListNode): ListNode {
        var current = head
        var previous: ListNode? = null
        var next: ListNode?

        while (current.next != null) {
            next = current.next
            current.next = previous
            previous = current
            current = next!!
        }

        current.next = previous
        return current
    }
}
