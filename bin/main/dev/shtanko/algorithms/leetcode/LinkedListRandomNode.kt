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

import kotlin.random.Random

/**
 * 382. Linked List Random Node
 * @see <a href="https://leetcode.com/problems/linked-list-random-node/">Source</a>
 */
class LinkedListRandomNode(val head: ListNode) {
    fun getRandom(): Int {
        var c: ListNode? = head
        var r: Int? = c?.value
        var i = 1
        while (c?.next != null) {
            c = c.next
            if (Random.nextInt(i + 1) == i) {
                r = c?.value
            }
            i++
        }

        return r ?: i
    }
}
