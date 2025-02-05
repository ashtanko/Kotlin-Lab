/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.utils

import dev.shtanko.algorithms.leetcode.ListNode

class ListNodeComparator {
    fun areEqual(listNode1: ListNode?, listNode2: ListNode?): Boolean {
        var node1 = listNode1
        var node2 = listNode2

        while (node1 != null && node2 != null) {
            if (node1.value != node2.value) {
                return false
            }
            node1 = node1.next
            node2 = node2.next
        }

        // If one of the lists still has nodes left, then they are not equal
        return !(node1 != null || node2 != null)
    }
}

fun assertListNodeEquals(expected: ListNode?, actual: ListNode?): Boolean {
    val comparator = ListNodeComparator()
    return comparator.areEqual(expected, actual)
}
