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
 * 876. Middle of the Linked List
 * @see <a href="https://leetcode.com/problems/middle-of-the-linked-list/">Source</a>
 */
fun interface MiddleNode {
    operator fun invoke(head: ListNode?): ListNode?
}

class MiddleNodePointers : MiddleNode {
    override operator fun invoke(head: ListNode?): ListNode? {
        var slow: ListNode? = head
        var fast: ListNode? = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow
    }
}
