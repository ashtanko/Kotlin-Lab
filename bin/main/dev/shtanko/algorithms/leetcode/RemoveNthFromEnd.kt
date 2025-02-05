/*
 * Copyright 2021 Oleksii Shtanko
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
 * Remove Nth Node From End of List
 * @see <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/">Source</a>
 */
fun interface RemoveNthFromEnd {
    operator fun invoke(head: ListNode?, n: Int): ListNode?
}

/**
 * Approach 1: Two pass algorithm
 * Time complexity : O(L)
 * Space complexity : O(1)
 */
class RemoveNthFromEndTwoPass : RemoveNthFromEnd {
    override operator fun invoke(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head
        var length = 0
        var first = head
        while (first != null) {
            length++
            first = first.next
        }
        length -= n
        first = dummy
        while (length > 0) {
            length--
            first = first?.next
        }
        first!!.next = first.next?.next
        return dummy.next
    }
}

/**
 * Approach 2: One pass algorithm
 * Time complexity : O(L)
 * Space complexity : O(1)
 */
class RemoveNthFromEndOnePass : RemoveNthFromEnd {
    override operator fun invoke(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head
        var first: ListNode? = dummy
        var second: ListNode? = dummy
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (i in 1..n + 1) {
            first = first?.next
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next
            second = second?.next
        }
        second!!.next = second.next?.next
        return dummy.next
    }
}
