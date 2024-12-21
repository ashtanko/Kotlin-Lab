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

package dev.shtanko.algorithms.leetcode

import kotlin.math.ceil

/**
 * 2870. Minimum Number of Operations to Make Array Empty
 * @see <a href="https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty">Source</a>
 */
fun interface MinOpToMakeArrayEmpty {
    operator fun invoke(nums: IntArray): Int
}

val minOpToMakeArrayEmptyCounting = MinOpToMakeArrayEmpty { nums ->
    val counter = mutableMapOf<Int, Int>()
    for (num in nums) {
        counter[num] = counter.getOrDefault(num, 0) + 1
    }
    var ans = 0
    for ((_, count) in counter) {
        if (count == 1) {
            return@MinOpToMakeArrayEmpty -1
        }
        ans += ceil(count.toDouble() / 3).toInt()
    }
    return@MinOpToMakeArrayEmpty ans
}
