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

package dev.shtanko.algorithms.templates

import dev.shtanko.algorithms.leetcode.ListNode

fun linkedListFastAndSlowPointer(head: ListNode): Int {
    var slow: ListNode? = head
    var fast: ListNode? = head
    val ans = 0
    while (fast?.next != null) {
        // do logic
        slow = slow?.next
        fast = fast.next?.next
    }
    return ans
}
