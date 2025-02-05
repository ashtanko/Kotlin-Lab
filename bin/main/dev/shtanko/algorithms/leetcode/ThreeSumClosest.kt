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

import kotlin.math.abs

fun IntArray.threeSumClosest(target: Int): Int {
    var result = this[0] + this[1] + this[this.size - 1]
    sort()
    for (i in 0 until this.size - 2) {
        var start = i + 1
        var end = this.size - 1
        while (start < end) {
            val sum = this[i] + this[start] + this[end]
            if (sum > target) {
                end--
            } else {
                start++
            }
            if (abs(sum - target) < abs(result - target)) {
                result = sum
            }
        }
    }

    return result
}
