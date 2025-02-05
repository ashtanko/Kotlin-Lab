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

import kotlin.math.min

/**
 * 1278. Palindrome Partitioning III
 * @see <a href="https://leetcode.com/problems/palindrome-partitioning-iii/">Source</a>
 */
fun interface PalindromePartitioning3 {
    fun palindromePartition(s: String, k: Int): Int
}

class PalindromePartitioning3DP : PalindromePartitioning3 {
    override fun palindromePartition(s: String, k: Int): Int {
        val toPal = Array(s.length) { IntArray(s.length) }
        val dp = Array(k + 1) { IntArray(s.length) }
        for (i in s.indices) {
            toPal[i][i] = 0
        }
        for (i in s.length - 1 downTo 0) {
            for (j in i + 1 until s.length) {
                toPal[i][j] = getChanges(s, i, j)
            }
        }
        for (i in s.indices) {
            dp[1][i] = toPal[0][i]
        }
        for (i in 2..k) {
            for (end in i - 1 until s.length) {
                var min: Int = s.length
                for (start in end - 1 downTo i - 2) {
                    min = min(min, dp[i - 1][start] + toPal[start + 1][end])
                }
                dp[i][end] = min
            }
        }
        return dp[k][s.length - 1]
    }

    private fun getChanges(s: String, start: Int, end: Int): Int {
        var start0 = start
        var end0 = end
        var changes = 0
        while (start0 < end0) {
            if (s[start0++] != s[end0--]) {
                changes++
            }
        }
        return changes
    }
}
