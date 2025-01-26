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

/**
 * 1745. Palindrome Partitioning IV
 * @see <a href="https://leetcode.com/problems/palindrome-partitioning-iv/">Source</a>
 */
fun interface PalindromePartitioning4 {
    fun checkPartitioning(s: String): Boolean
}

class PalindromePartitioning4DP : PalindromePartitioning4 {
    override fun checkPartitioning(s: String): Boolean {
        val n = s.length
        val arr = s.toCharArray()
        val dp = Array(n) {
            BooleanArray(
                n,
            )
        }
        for (i in n - 1 downTo 0) {
            for (j in i until n) {
                if (arr[i] == arr[j]) {
                    dp[i][j] = if (i + 1 <= j - 1) {
                        dp[i + 1][j - 1]
                    } else {
                        true
                    }
                } else {
                    dp[i][j] = false
                }
            }
        }

        // iterate every mid and then check: left, mid and right
        for (i in 1 until n - 1) {
            for (j in i until n - 1) {
                if (dp[0][i - 1] && dp[i][j] && dp[j + 1][n - 1]) {
                    return true
                }
            }
        }

        return false
    }
}
