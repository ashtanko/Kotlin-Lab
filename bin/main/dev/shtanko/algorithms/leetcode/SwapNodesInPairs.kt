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
 * 24. Swap Nodes in Pairs
 * @see <a href="https://leetcode.com/problems/swap-nodes-in-pairs">Source</a>
 */
fun interface SwapNodesInPairs {
    operator fun invoke(head: ListNode?): ListNode?
}

class SwapNodesInPairsSimple : SwapNodesInPairs {
    override fun invoke(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        val ans = ListNode.empty()
        ans.next = head
        var curr: ListNode? = ans
        while (curr?.next != null && curr.next?.next != null) {
            val t1 = curr.next
            val t2 = curr.next?.next
            curr.next = t2
            t1?.next = t2?.next
            t2?.next = t1
            curr = curr.next?.next
        }
        return ans.next
    }
}
