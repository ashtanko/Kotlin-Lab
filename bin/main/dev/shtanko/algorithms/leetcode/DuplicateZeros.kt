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

fun IntArray.duplicateZeros() {
    var countZero = 0
    for (element in this) {
        if (element == 0) countZero++
    }
    val len: Int = this.size + countZero
    // We just need O(1) space if we scan from back
    // i point to the original array, j point to the new location
    // We just need O(1) space if we scan from back
    // i point to the original array, j point to the new location

    var i: Int = this.size - 1
    var j = len - 1
    while (i < j) {
        if (this[i] != 0) {
            if (j < this.size) this[j] = this[i]
        } else {
            if (j < this.size) this[j] = this[i]
            j--
            if (j < this.size) this[j] = this[i] // copy twice when hit '0'
        }
        i--
        j--
    }
}
