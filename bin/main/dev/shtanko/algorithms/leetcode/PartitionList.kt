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
 * 86. Partition List
 * @see <a href="https://leetcode.com/problems/partition-list">Source</a>
 */
fun interface PartitionList {
    operator fun invoke(head: ListNode?, x: Int): ListNode?
}

class PartitionListOnePass : PartitionList {
    override operator fun invoke(head: ListNode?, x: Int): ListNode? {
        val smallerHead = ListNode(0)
        val biggerHead = ListNode(0)
        var smaller: ListNode? = smallerHead
        var bigger: ListNode? = biggerHead
        var h = head
        while (h != null) {
            if (h.value < x) {
                smaller?.next = h
                smaller = smaller?.next
            } else {
                bigger?.next = h
                bigger = bigger?.next
            }
            h = h.next
        }
        // no need for extra check because of fake heads
        // no need for extra check because of fake heads
        smaller?.next = biggerHead.next // join bigger after smaller

        bigger?.next = null // cut off anything after bigger

        return smallerHead.next
    }
}
