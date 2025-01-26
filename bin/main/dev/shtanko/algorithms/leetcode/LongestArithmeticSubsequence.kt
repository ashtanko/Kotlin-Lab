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
 * 1027. Longest Arithmetic Subsequence
 * @see <a href="https://leetcode.com/problems/longest-arithmetic-subsequence/">Source</a>
 */
fun interface LongestArithmeticSubsequence {
    fun longestArithSeqLength(nums: IntArray): Int
}

class LongestArithmeticSubsequenceDP : LongestArithmeticSubsequence {
    override fun longestArithSeqLength(nums: IntArray): Int {
        var res = 2
        val n: Int = nums.size
        val dp: Array<HashMap<Int, Int>> = Array(n) { HashMap() }
        for (j in nums.indices) {
            for (i in 0 until j) {
                val d: Int = nums[j] - nums[i]
                dp[j][d] = dp[i].getOrDefault(d, 1) + 1
                res = max(res, dp[j][d] ?: 0)
            }
        }
        return res
    }
}
