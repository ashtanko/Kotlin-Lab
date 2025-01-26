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

import java.util.PriorityQueue

/**
 * 23. Merge k Sorted Lists
 * @see <a href="https://leetcode.com/problems/merge-k-sorted-lists/">Source</a>
 */
fun interface MergeKLists {
    operator fun invoke(lists: Array<ListNode?>): ListNode?
}

class MergeKListsPQ : MergeKLists {
    override operator fun invoke(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null
        val queue = PriorityQueue<ListNode>(
            lists.size,
            Comparator { o1, o2 ->
                return@Comparator when {
                    o1.value < o2.value -> -1
                    o1.value == o2.value -> 0
                    else -> 1
                }
            },
        )

        val dummy = ListNode(0)
        var tail: ListNode? = dummy

        for (node in lists) {
            queue.addIfNotNull(node)
        }

        while (queue.isNotEmpty()) {
            tail?.next = queue.poll()
            tail = tail?.next
            val next = tail?.next
            if (next.isNotNull()) {
                queue.add(next)
            }
        }

        return dummy.next
    }
}
