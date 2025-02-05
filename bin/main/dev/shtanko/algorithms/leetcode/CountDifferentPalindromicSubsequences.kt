/*
 * Copyright 2022 Oleksii Shtanko
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

import dev.shtanko.algorithms.MOD

/**
 * 730. Count Different Palindromic Subsequences
 * @see <a href="https://leetcode.com/problems/count-different-palindromic-subsequences/">Source</a>
 */
fun interface CountDifferentPalindromicSubsequences {
    fun countPalindromicSubsequences(s: String): Int
}

class CountDifferentPalindromicSubsequencesDP : CountDifferentPalindromicSubsequences {
    override fun countPalindromicSubsequences(s: String): Int {
        val len: Int = s.length
        val dp = Array(len) { IntArray(len) }

        val chs = s.toCharArray()
        for (i in 0 until len) {
            dp[i][i] = 1
        }

        for (distance in 1 until len) {
            for (i in 0 until len - distance) {
                val j = i + distance
                if (chs[i] == chs[j]) {
                    var low = i + 1
                    var high = j - 1

                    while (low <= high && chs[low] != chs[j]) {
                        low++
                    }
                    while (low <= high && chs[high] != chs[j]) {
                        high--
                    }
                    if (low > high) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2
                    } else if (low == high) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1]
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1]
                }
                dp[i][j] = if (dp[i][j] < 0) dp[i][j] + MOD else dp[i][j] % MOD
            }
        }

        return dp[0][len - 1]
    }
}
