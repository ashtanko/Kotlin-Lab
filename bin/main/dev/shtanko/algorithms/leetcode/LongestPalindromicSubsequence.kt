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
 * 516. Longest Palindromic Subsequence
 * @see <a href="https://leetcode.com/problems/longest-palindromic-subsequence/">Source</a>
 */
fun interface LongestPalindromicSubsequence {
    fun longestPalindromeSubseq(s: String): Int
}

/**
 * Approach 1: Recursive Dynamic Programming
 */
class LPSRecursiveDP : LongestPalindromicSubsequence {
    override fun longestPalindromeSubseq(s: String): Int {
        val n: Int = s.length
        val memo = Array(n) { IntArray(n) }
        return lps(s, 0, n - 1, memo)
    }

    private fun lps(s: String, i: Int, j: Int, memo: Array<IntArray>): Int {
        if (memo[i][j] != 0) {
            return memo[i][j]
        }
        if (i > j) {
            return 0
        }
        if (i == j) {
            return 1
        }
        if (s[i] == s[j]) {
            memo[i][j] = lps(s, i + 1, j - 1, memo) + 2
        } else {
            memo[i][j] = max(lps(s, i + 1, j, memo), lps(s, i, j - 1, memo))
        }
        return memo[i][j]
    }
}

/**
 * Approach 2: Iterative Dynamic Programming
 */
class LPSIterativeDP : LongestPalindromicSubsequence {
    override fun longestPalindromeSubseq(s: String): Int {
        val dp = Array(s.length) { IntArray(s.length) }

        for (i in s.length - 1 downTo 0) {
            dp[i][i] = 1
            for (j in i + 1 until s.length) {
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2
                } else {
                    dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
                }
            }
        }

        return dp[0][s.length - 1]
    }
}

/**
 * Approach 3: Dynamic Programming with Space Optimization
 */
class LPSOptimizedDP : LongestPalindromicSubsequence {
    override fun longestPalindromeSubseq(s: String): Int {
        val n: Int = s.length
        val dp = IntArray(n)
        var dpPrev = IntArray(n)

        for (i in n - 1 downTo 0) {
            dp[i] = 1
            for (j in i + 1 until n) {
                if (s[i] == s[j]) {
                    dp[j] = dpPrev[j - 1] + 2
                } else {
                    dp[j] = max(dpPrev[j], dp[j - 1])
                }
            }
            dpPrev = dp.clone()
        }

        return dp[n - 1]
    }
}
