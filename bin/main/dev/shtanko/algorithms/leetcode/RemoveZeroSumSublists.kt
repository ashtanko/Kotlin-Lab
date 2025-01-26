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
 * 1171. Remove Zero Sum Consecutive Nodes from Linked List
 * @see <a href="https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/">Source</a>
 */
fun interface RemoveZeroSumSublists {
    operator fun invoke(head: ListNode?): ListNode?
}

class RemoveZeroSumSublistsMap : RemoveZeroSumSublists {
    override operator fun invoke(head: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var cur: ListNode? = dummy
        dummy.next = head
        var prefix = 0
        val m: MutableMap<Int, ListNode> = HashMap()
        while (cur != null) {
            prefix += cur.value
            if (m.containsKey(prefix)) {
                cur = m[prefix]?.next
                var p: Int = prefix + (cur?.value ?: 0)
                while (p != prefix) {
                    m.remove(p)
                    cur = cur?.next
                    p += cur?.value ?: 0
                }
                m[prefix]?.next = cur!!.next
            } else {
                m[prefix] = cur
            }
            cur = cur?.next
        }
        return dummy.next
    }
}

class RemoveZeroSumSublistsTwoPasses : RemoveZeroSumSublists {
    override operator fun invoke(head: ListNode?): ListNode? {
        var prefix = 0
        val dummy = ListNode(0)
        dummy.next = head
        val seen: MutableMap<Int, ListNode> = HashMap()
        seen[0] = dummy
        var i: ListNode? = dummy
        while (i != null) {
            prefix += i.value
            seen[prefix] = i
            i = i.next
        }
        prefix = 0
        i = dummy
        while (i != null) {
            prefix += i.value
            i.next = seen[prefix]?.next
            i = i.next
        }
        return dummy.next
    }
}
