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
 * 725. Split Linked List in Parts
 * @see <a href="https://leetcode.com/problems/split-linked-list-in-parts">Source</a>
 */
fun interface SplitLinkedListInParts {
    /**
     * This function splits a linked list into a specified number of parts.
     * @param head The head of the linked list.
     * @param k The number of parts to split the linked list into.
     * @return An array of linked lists representing the parts.
     */
    operator fun invoke(head: NullableListNode?, k: Int): Array<NullableListNode?>
}

/**
 * Approach #1: Create New Lists
 */
class SplitLinkedListInPartsCopy : SplitLinkedListInParts {
    /**
     * This function splits a linked list into a specified number of parts by creating new lists.
     * @param head The head of the linked list.
     * @param k The number of parts to split the linked list into.
     * @return An array of linked lists representing the parts.
     */
    override fun invoke(head: NullableListNode?, k: Int): Array<NullableListNode?> {
        var currentNode: NullableListNode? = head
        var totalNodes = 0
        while (currentNode != null) {
            currentNode = currentNode.next
            totalNodes++
        }

        val partSize = totalNodes / k
        val remainder = totalNodes % k

        val result = arrayOfNulls<NullableListNode>(k)
        currentNode = head
        for (i in 0 until k) {
            val dummyHead = NullableListNode(0)
            var writer: NullableListNode? = dummyHead
            for (j in 0 until partSize + if (i < remainder) 1 else 0) {
                writer?.next = currentNode?.value?.let { NullableListNode(it) }
                writer = writer?.next
                if (currentNode != null) currentNode = currentNode.next
            }
            result[i] = dummyHead.next
        }
        return result
    }
}

/**
 * Approach #2: Split Input List
 */
class SplitLinkedListInPartsInput : SplitLinkedListInParts {
    /**
     * This function splits a linked list into a specified number of parts by splitting the input list.
     * @param head The head of the linked list.
     * @param k The number of parts to split the linked list into.
     * @return An array of linked lists representing the parts.
     */
    override fun invoke(head: NullableListNode?, k: Int): Array<NullableListNode?> {
        var currentNode: NullableListNode? = head
        var totalNodes = 0
        while (currentNode != null) {
            currentNode = currentNode.next
            totalNodes++
        }

        val partSize = totalNodes / k
        val remainder = totalNodes % k

        val result = arrayOfNulls<NullableListNode>(k)
        currentNode = head
        for (i in 0 until k) {
            val partHead = currentNode
            for (j in 0 until partSize + (if (i < remainder) 1 else 0) - 1) {
                if (currentNode != null) currentNode = currentNode.next
            }
            if (currentNode != null) {
                val previousNode: NullableListNode = currentNode
                currentNode = currentNode.next
                previousNode.next = null
            }
            result[i] = partHead
        }
        return result
    }
}
