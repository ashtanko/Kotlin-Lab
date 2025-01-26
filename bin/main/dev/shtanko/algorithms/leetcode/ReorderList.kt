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
 * 143. Reorder List
 * @see <a href="https://leetcode.com/problems/reorder-list/">Source</a>
 */
fun interface ReorderList {
    operator fun invoke(head: ListNode?)
}

class ReorderListImpl : ReorderList {
    override fun invoke(head: ListNode?) {
        // if head will be null or head.next will be null simply return
        if (head?.next == null) return

        // finding middle element
        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        // reversing the second half of the list
        val newNode: ListNode? = slow?.next?.reverseList()
        // breaking the list from the middle
        slow?.next = null
        // merging both list
        // first half list pointer
        var curr = head
        // second half list pointer
        var dummy: ListNode? = newNode
        while (dummy != null) {
            // pointer to store next element of curr(1st half list)
            val temp = curr?.next
            // link element of 1st half to that of second half
            curr?.next = dummy
            // pointer to store next element of dummy(2nd half list)
            val temp2 = dummy.next
            // link the rest of the first half list
            dummy.next = temp
            // increment curr and dummy pointer to do the same thing again and again util we reach end of
            // any one list or both list
            curr = temp
            dummy = temp2
        }
    }
}
