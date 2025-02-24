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
 * 3217. Delete Nodes From Linked List Present in Array
 * @see <a href="https://leetcode.com/problems/delete-nodes-and-return-forest/">Source</a>
 */
fun interface ModifiedList {
    operator fun invoke(nums: IntArray, head: ListNode?): ListNode?
}

data object ModifiedListHashSet : ModifiedList {
    override fun invoke(
        nums: IntArray,
        head: ListNode?,
    ): ListNode? {
        // Create a HashSet for efficient lookup of values in nums
        val valuesToRemove = HashSet<Int>().apply {
            nums.forEach { add(it) }
        }

        // Handle the case where the head node needs to be removed
        var currentHead = head
        while (currentHead != null && valuesToRemove.contains(currentHead.value)) {
            currentHead = currentHead.next
        }

        // If the list is empty after removing head nodes, return null
        if (currentHead == null) {
            return null
        }

        // Iterate through the list, removing nodes with values in the set
        var current = currentHead
        while (current?.next != null) {
            if (valuesToRemove.contains(current.next?.value)) {
                // Skip the next node by updating the pointer
                current.next = current.next!!.next
            } else {
                // Move to the next node
                current = current.next
            }
        }

        return currentHead
    }
}
