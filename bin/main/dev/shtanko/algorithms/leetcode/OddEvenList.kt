/*
 * Copyright 2022 Oleksii Shtanko
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
 * 328. Odd Even Linked List
 * @see <a href="https://leetcode.com/problems/odd-even-linked-list">Source</a>
 */
fun interface OddEvenList {
    operator fun invoke(head: ListNode): ListNode
}

class OddEvenListImpl : OddEvenList {
    override operator fun invoke(head: ListNode): ListNode {
        var odd: ListNode? = head
        var even = head.next
        val evenHead = even
        while (even?.next != null) {
            odd?.next = even.next
            odd = odd?.next
            even.next = odd?.next
            even = even.next
        }
        odd?.next = evenHead
        return head
    }
}
