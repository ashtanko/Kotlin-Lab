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

import kotlin.math.max

/**
 * 1312. Minimum Insertion Steps to Make a String Palindrome
 * @see <a href="https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/">Source</a>
 */
fun interface MinInsertionsPalindrome {
    fun minInsertions(s: String): Int
}

/**
 * Approach 1: Recursive Dynamic Programming
 */
class MinInsertionsPalindromeRecursive : MinInsertionsPalindrome {
    override fun minInsertions(s: String): Int {
        val n: Int = s.length
        val sReverse = StringBuilder(s).reverse().toString()
        val memo = Array(n + 1) { IntArray(n + 1) }

        for (i in 0..n) {
            for (j in 0..n) {
                memo[i][j] = -1
            }
        }

        return n - lcs(s, sReverse, n, n, memo)
    }

    private fun lcs(s1: String, s2: String, m: Int, n: Int, memo: Array<IntArray>): Int {
        if (m == 0 || n == 0) {
            return 0
        }
        if (memo[m][n] != -1) {
            return memo[m][n]
        }
        return if (s1[m - 1] == s2[n - 1]) {
            1 + lcs(s1, s2, m - 1, n - 1, memo).also { memo[m][n] = it }
        } else {
            max(lcs(s1, s2, m - 1, n, memo), lcs(s1, s2, m, n - 1, memo)).also { memo[m][n] = it }
        }
    }
}

/**
 * Approach 2: Iterative Dynamic Programming
 */
class MinInsertionsPalindromeIterative : MinInsertionsPalindrome {
    override fun minInsertions(s: String): Int {
        val n: Int = s.length
        val sReverse = java.lang.StringBuilder(s).reverse().toString()

        return n - lcs(s, sReverse, n, n)
    }

    private fun lcs(s1: String, s2: String, m: Int, n: Int): Int {
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) {
            for (j in 0..n) {
                if (i == 0 || j == 0) {
                    // One of the two strings is empty.
                    dp[i][j] = 0
                } else if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[m][n]
    }
}

/**
 * Longest Common Sequence
 */
class MinInsertionsPalindromeLCS : MinInsertionsPalindrome {
    override fun minInsertions(s: String): Int {
        val n: Int = s.length
        val dp = Array(n + 1) { IntArray(n + 1) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                dp[i + 1][j + 1] = if (s[i] == s[n - 1 - j]) {
                    dp[i][j] + 1
                } else {
                    max(dp[i][j + 1], dp[i + 1][j])
                }
            }
        }
        return n - dp[n][n]
    }
}
