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

import dev.shtanko.algorithms.DECIMAL
import kotlin.math.min

/**
 * 1531. String Compression II
 * @see <a href="https://leetcode.com/problems/string-compression-ii/">Source</a>
 */
fun interface StringCompression2 {
    operator fun invoke(s: String, k: Int): Int
}

class StringCompression2DP : StringCompression2 {

    /**
     * This function compresses the given string and returns the length of the compressed string.
     * It uses dynamic programming to find the optimal solution.
     * @param s The string to compress.
     * @param k The maximum number of changes that can be made to the string.
     * @return The length of the compressed string.
     */
    override fun invoke(s: String, k: Int): Int {
        val n: Int = s.length
        val dp = initializeDP(n, k)

        for (i in 1..n) {
            for (m in 0..k) {
                updateDP(dp, s, i, m)
            }
        }

        return dp[n][k]
    }

    /**
     * This function initializes the dynamic programming table.
     * @param n The length of the string.
     * @param k The maximum number of changes that can be made to the string.
     * @return The initialized dynamic programming table.
     */
    private fun initializeDP(n: Int, k: Int): Array<IntArray> {
        val dp = Array(n + 1) { IntArray(k + 1) { n } }
        dp[0][0] = 0
        return dp
    }

    /**
     * This function updates the dynamic programming table for a given string and index.
     * @param dp The dynamic programming table.
     * @param s The string to compress.
     * @param i The current index in the string.
     * @param m The current number of changes made to the string.
     */
    private fun updateDP(dp: Array<IntArray>, s: String, i: Int, m: Int) {
        if (m > 0) {
            dp[i][m] = min(dp[i][m], dp[i - 1][m - 1])
        }

        // keep s[i], concat the same, remove the different
        var same = 0
        var diff = 0

        for (j in i downTo 1) {
            if (s[j - 1] == s[i - 1]) {
                same++
            } else {
                diff++
            }

            if (diff > m) break

            dp[i][m] = min(dp[i][m], dp[j - 1][m - diff] + getLen(same))
        }
    }

    /**
     * This function calculates the length of the compressed string for a given count of characters.
     * @param count The count of characters.
     * @return The length of the compressed string.
     */
    private fun getLen(count: Int): Int {
        return when {
            count == 1 -> 1
            count < DECIMAL -> 2
            count < LIMIT -> 3
            else -> 4
        }
    }

    companion object {
        private const val LIMIT = 100
    }
}
