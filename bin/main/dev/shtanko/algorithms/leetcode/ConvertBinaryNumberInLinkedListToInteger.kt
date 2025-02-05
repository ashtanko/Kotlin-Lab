/*
 * Copyright 2020 Oleksii Shtanko
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

// Convert Binary Number in a Linked List to Integer
fun interface BinaryNumberToIntStrategy {
    operator fun invoke(head: ListNode?): Int
}

// Approach 1: Binary Representation
class BinaryNumberToIntBinary : BinaryNumberToIntStrategy {
    override operator fun invoke(head: ListNode?): Int {
        var h: ListNode = head ?: return 0
        var num: Int = h.value
        while (h.next != null) {
            num = num * 2 + h.next!!.value
            h = h.next!!
        }
        return num
    }
}

// Approach 2: Bit Manipulation
class BinaryNumberToIntBit : BinaryNumberToIntStrategy {
    override operator fun invoke(head: ListNode?): Int {
        var h: ListNode = head ?: return 0
        var num: Int = h.value
        while (h.next != null) {
            num = num shl 1 or h.next!!.value
            h = h.next!!
        }
        return num
    }
}
