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
 * 1721. Swapping Nodes in a Linked List
 * @see <a href="https://leetcode.com/problems/swapping-nodes-in-a-linked-list">Source</a>
 */
fun interface SwapNodes {
    operator fun invoke(head: ListNode?, k: Int): ListNode?
}

class SwapNodesTwoPointers : SwapNodes {
    override operator fun invoke(head: ListNode?, k: Int): ListNode? {
        var fast = head
        var slow = head

        // Put fast (k-1) nodes after slow
        for (i in 0 until k - 1) {
            fast = fast?.next
        }

        // Save the node for swapping
        val first: ListNode? = fast

        // Move until the end of the list
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next
        }

        // Save the second node for swapping
        // Note that the pointer second isn't necessary: we could use slow for swapping as well
        // However, having second improves readability
        val second: ListNode? = slow

        // Swap values
        val temp: Int = first?.value ?: 0
        first?.value = second?.value ?: 0
        second?.value = temp

        return head
    }
}
