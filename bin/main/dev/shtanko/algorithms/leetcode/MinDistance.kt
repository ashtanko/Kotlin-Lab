/*
 * Copyright 2021 Oleksii Shtanko
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
import kotlin.math.min

/**
 * 583. Delete Operation for Two Strings
 * @see <a href="https://leetcode.com/problems/delete-operation-for-two-strings/">Source</a>
 */
fun interface MinDistance {
    operator fun invoke(word1: String, word2: String): Int
}

/**
 * Approach #1 Using Longest Common Subsequence
 * Time complexity : O(2^{max(m,n)}).
 * Space complexity : O(max (m,n))).
 */
class MinDistanceLCS : MinDistance {
    override operator fun invoke(word1: String, word2: String): Int {
        return word1.length + word2.length - 2 * lcs(word1, word2, word1.length, word2.length)
    }

    private fun lcs(s1: String, s2: String, m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0
        return if (s1[m - 1] == s2[n - 1]) {
            1 + lcs(s1, s2, m - 1, n - 1)
        } else {
            max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n))
        }
    }
}

/**
 * Approach #2 Longest Common Subsequence with Memoization
 * Time complexity : O(m*n).
 * Space complexity : O(m*n).
 */
class MinDistanceLCSMemo : MinDistance {
    override operator fun invoke(word1: String, word2: String): Int {
        val memo = Array(word1.length + 1) {
            IntArray(word2.length + 1)
        }
        return word1.length + word2.length - 2 * lcs(word1, word2, word1.length, word2.length, memo)
    }

    private fun lcs(s1: String, s2: String, m: Int, n: Int, memo: Array<IntArray>): Int {
        if (m == 0 || n == 0) {
            return 0
        }
        if (memo[m][n] > 0) {
            return memo[m][n]
        }
        if (s1[m - 1] == s2[n - 1]) {
            memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1, memo)
        } else {
            memo[m][n] = max(lcs(s1, s2, m, n - 1, memo), lcs(s1, s2, m - 1, n, memo))
        }
        return memo[m][n]
    }
}

/**
 * Approach #3 Using The Longest Common Subsequence- Dynamic Programming
 * Time complexity : O(m*n).
 * Space complexity : O(m*n).
 */
class MinDistanceLCSDP : MinDistance {
    override operator fun invoke(word1: String, word2: String): Int {
        val dp = Array(word1.length + 1) { IntArray(word2.length + 1) }
        for (i in 0..word1.length) {
            for (j in 0..word2.length) {
                if (i == 0 || j == 0) {
                    continue
                }
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return word1.length + word2.length - 2 * dp[word1.length][word2.length]
    }
}

/**
 * Approach #4 Without using LCS Dynamic Programming
 * Time complexity : O(m*n).
 * Space complexity : O(m*n).
 */
class MinDistanceDP : MinDistance {
    override operator fun invoke(word1: String, word2: String): Int {
        val dp = Array(word1.length + 1) {
            IntArray(
                word2.length + 1,
            )
        }
        for (i in 0..word1.length) {
            for (j in 0..word2.length) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j
                } else if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] =
                        dp[i - 1][j - 1]
                } else {
                    dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[word1.length][word2.length]
    }
}

/**
 * Approach #5 1-D Dynamic Programming
 * Time complexity : O(m*n).
 * Space complexity : O(n).
 */
class MinDistance1DDP : MinDistance {
    override operator fun invoke(word1: String, word2: String): Int {
        var dp = IntArray(word2.length + 1)
        for (i in 0..word1.length) {
            val temp = IntArray(word2.length + 1)
            for (j in 0..word2.length) {
                if (i == 0 || j == 0) {
                    temp[j] = i + j
                } else if (word1[i - 1] == word2[j - 1]) {
                    temp[j] = dp[j - 1]
                } else {
                    temp[j] = 1 + min(dp[j], temp[j - 1])
                }
            }
            dp = temp
        }
        return dp[word2.length]
    }
}
