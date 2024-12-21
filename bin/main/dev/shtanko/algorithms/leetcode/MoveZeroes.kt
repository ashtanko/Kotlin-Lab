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

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative
 * order of the non-zero elements.
 */
fun IntArray.moveZeroes() {
    var pos = 0
    for (i in indices) {
        if (this[i] != 0) {
            if (i != pos) {
                this[pos] = this[i]
                this[i] = 0
            }
            pos++
        }
    }
}
