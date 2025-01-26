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
 * 61. Rotate List
 * @see <a href="https://leetcode.com/problems/rotate-list">Source</a>
 */
fun interface RotateList {
    operator fun invoke(head: ListNode?, k: Int): ListNode?
}

/**
 * Time Limit Exceeded
 */
class RotateListBruteForce : RotateList {
    override operator fun invoke(head: ListNode?, k: Int): ListNode? {
        var node = head
        if (node?.next == null) return node
        for (i in 0 until k) {
            var temp: ListNode? = node
            while (temp?.next?.next != null) temp = temp.next
            val end: ListNode? = temp?.next
            temp?.next = null
            end?.next = node
            node = end
        }
        return node
    }
}

class RotateListOptimized : RotateList {
    override operator fun invoke(head: ListNode?, k: Int): ListNode? {
        var node = head
        if (node?.next == null || k == 0) {
            return node
        }
        var curr = node
        var len = 1
        while (curr?.next != null) {
            len++
            curr = curr.next
        }
        curr?.next = node

        val ll = len - k % len

        for (i in 0 until ll) { // 3 times because curr. next  is at head but curr is still at end of the linkedlist
            curr = curr?.next
        }
        node = curr?.next
        curr?.next = null

        return node
    }
}
