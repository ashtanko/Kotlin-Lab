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

fun IntArray.searchRange(target: Int): IntArray {
    var high = this.size - 1
    var low = 0
    val rs = intArrayOf(-1, -1)

    // base case
    if (this.isEmpty()) {
        return rs
    }

    // left side
    while (low < high) {
        val local = high - low
        val mid = low + local / 2
        if (target > this[mid]) {
            low = mid + 1
        } else {
            high = mid
        }
    }
    if (target == this[low]) {
        rs[0] = low
    } else {
        rs[0] = -1
    }

    // right side
    high = this.size - 1
    while (low < high) {
        val local = high - low
        val m = low + local / 2
        val mid = m + 1
        if (target < this[mid]) {
            high = mid - 1
        } else {
            low = mid
        }
    }
    if (target == this[high]) {
        rs[1] = high
    } else {
        rs[1] = -1
    }

    return rs
}
