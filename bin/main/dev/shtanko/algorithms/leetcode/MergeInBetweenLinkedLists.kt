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

/**
 * 1669. Merge In Between Linked Lists
 * @see <a href="https://leetcode.com/problems/merge-in-between-linked-lists">Source</a>
 */
fun interface MergeInBetweenLinkedLists {
    operator fun invoke(firstList: ListNode?, start: Int, end: Int, secondList: ListNode?): ListNode?
}

class MergeInBetweenLinkedListsArray : MergeInBetweenLinkedLists {
    override fun invoke(firstList: ListNode?, start: Int, end: Int, secondList: ListNode?): ListNode? {
        val mergeArray: MutableList<Int> = ArrayList()
        // Add list1 node values before `a` to the array.
        var index = 0
        var current1: ListNode? = firstList
        while (index < start) {
            current1?.value?.let { mergeArray.add(it) }
            current1 = current1?.next
            index++
        }

        // Add list2 node values to the array
        var current2: ListNode? = secondList
        while (current2 != null) {
            mergeArray.add(current2.value)
            current2 = current2.next
        }

        // Find node b + 1
        while (index < end + 1) {
            current1 = current1!!.next
            index++
        }

        // Add list1 node values after `b` to the array.
        while (current1 != null) {
            mergeArray.add(current1.value)
            current1 = current1.next
        }

        // Build a linked list with the result by iterating over the array
        // in reverse order and inserting new nodes to the front of the list
        var resultList: ListNode? = null
        for (i in mergeArray.indices.reversed()) {
            val newNode = ListNode(mergeArray[i], resultList)
            resultList = newNode
        }
        return resultList
    }
}

class MergeInBetweenLinkedListsTwoPointer : MergeInBetweenLinkedLists {
    override fun invoke(firstList: ListNode?, start: Int, end: Int, secondList: ListNode?): ListNode? {
        var startNode: ListNode? = secondList
        var endNode: ListNode? = firstList

        // Set startNode to node a - 1 and endNode to node b
        for (index in 0 until end) {
            if (index == start - 1) {
                startNode = endNode
            }
            endNode = endNode?.next
        }

        // Connect the startNode node to secondList
        startNode?.next = secondList

        // Find the tail of secondList
        while (startNode?.next != null) {
            startNode = startNode.next
        }

        // Set the tail of secondList to endNode.next
        startNode?.next = endNode?.next
        endNode?.next = null

        return firstList
    }
}
