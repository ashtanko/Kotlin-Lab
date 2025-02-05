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

import kotlin.math.max

/**
 * 1218. Longest Arithmetic Subsequence of Given Difference
 * @see <a href="https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/">Source</a>
 */
fun interface LongestSubsequence {
    operator fun invoke(arr: IntArray, difference: Int): Int
}

class LongestSubsequenceDP : LongestSubsequence {
    override operator fun invoke(arr: IntArray, difference: Int): Int {
        val map: MutableMap<Int, Int> = HashMap()
        var res = 1
        for (num in arr) {
            val prev = map[num - difference] ?: 0
            map[num] = prev + 1
            res = max(map[num] ?: 0, res)
        }
        return res
    }
}
