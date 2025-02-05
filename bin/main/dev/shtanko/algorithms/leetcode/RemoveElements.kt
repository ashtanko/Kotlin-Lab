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
 * 203. Remove Linked List Elements
 * @see <a href="https://leetcode.com/problems/remove-linked-list-elements/">Source</a>
 */
fun interface RemoveElements {
    operator fun invoke(head: ListNode?, value: Int): ListNode?
}

class RemoveElementsIterative : RemoveElements {
    override operator fun invoke(head: ListNode?, value: Int): ListNode? {
        if (head == null) {
            return null
        }

        val dummy = ListNode()
        dummy.next = head
        var cur: ListNode? = dummy

        while (cur?.next != null) {
            if (cur.next?.value == value) {
                cur.next = cur.next?.next
                // Here cannot move cur to cur.next as we need to validate the next node.
            } else {
                cur = cur.next
            }
        }
        return dummy.next
    }
}

class RemoveElementsRecursive : RemoveElements {
    override operator fun invoke(head: ListNode?, value: Int): ListNode? {
        if (head == null) {
            return null
        }

        // Once removeElements call is done, right side of the list is solved.
        val rightSideHead: ListNode? = invoke(head.next, value)
        if (head.value == value) {
            return rightSideHead
        }
        head.next = rightSideHead
        return head
    }
}
