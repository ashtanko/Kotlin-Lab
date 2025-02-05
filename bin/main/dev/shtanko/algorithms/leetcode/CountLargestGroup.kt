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

import dev.shtanko.algorithms.DECIMAL

private const val MAX_ARR_SIZE = 37

fun Int.countLargestGroup(): Int {
    val map = IntArray(MAX_ARR_SIZE)
    var maxCount = 0
    var res = 0

    for (i in 1..this) {
        var num = i
        var sum = 0
        while (num > 0) {
            sum += num % DECIMAL
            num /= DECIMAL
        }
        ++map[sum]
        if (maxCount < map[sum]) {
            maxCount = map[sum]
            res = 1
        } else if (maxCount == map[sum]) {
            res++
        }
    }
    return res
}
