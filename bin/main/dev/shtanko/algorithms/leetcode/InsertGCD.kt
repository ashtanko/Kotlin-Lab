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

import dev.shtanko.algorithms.math.gcd

/**
 * 2807. Insert Greatest Common Divisors in Linked List
 * @see <a href="https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/">Source</a>
 */
fun interface InsertGCD {
    operator fun invoke(head: ListNode?): ListNode?
}

data object InsertGCDSolution : InsertGCD {
    override fun invoke(head: ListNode?): ListNode? {
        // If the list contains only one node, return the head as no insertion is needed
        if (head?.next == null) return head

        // Initialize pointers to traverse the list
        var node1 = head
        var node2 = head.next

        // Traverse the linked list
        while (node2 != null) {
            val gcdValue = gcd(node1?.value ?: 0, node2.value)
            val gcdNode = ListNode(gcdValue)

            // Insert the GCD node between node1 and node2
            node1?.next = gcdNode
            gcdNode.next = node2

            // Move to the next pair of nodes
            node1 = node2
            node2 = node2.next
        }

        return head
    }
}
