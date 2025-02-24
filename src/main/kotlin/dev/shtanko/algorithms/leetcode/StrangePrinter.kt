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

import kotlin.math.min

/**
 * 664. Strange Printer
 * @see <a href="https://leetcode.com/problems/strange-printer/">Source</a>
 */
fun interface StrangePrinter {
    /**
     * This function calculates the minimum number of turns the printer will need in order to print it.
     * @param str The string to print.
     * @return The minimum number of turns the printer will need.
     */
    operator fun invoke(str: String): Int
}

/**
 * Approach 1: Bottom-Up Dynamic Programming
 */
class StrangePrinterBottomUp : StrangePrinter {
    /**
     * This function calculates the minimum number of turns the printer will need in order to print the string.
     * It uses a bottom-up dynamic programming approach.
     * @param str The string to print.
     * @return The minimum number of turns the printer will need.
     */
    override operator fun invoke(str: String): Int {
        if (str.isEmpty()) return 0
        val length: Int = str.length
        val dp = Array(length) { IntArray(length) }
        for (subLength in 1..length) {
            for (start in 0..length - subLength) {
                val end = start + subLength - 1
                var splitIndex = -1
                dp[start][end] = length
                for (i in start until end) {
                    if (str[i] != str[end] && splitIndex == -1) {
                        splitIndex = i
                    }
                    if (splitIndex != -1) {
                        dp[start][end] = min(
                            dp[start][end].toDouble(),
                            (1 + dp[splitIndex][i] + dp[i + 1][end]).toDouble(),
                        ).toInt()
                    }
                }
                if (splitIndex == -1) {
                    dp[start][end] = 0
                }
            }
        }

        return dp[0][length - 1] + 1
    }
}

/**
 * Approach 2: Top-Down Dynamic Programming (Memoization)
 */
class StrangePrinterTopDown : StrangePrinter {
    private lateinit var memo: Array<IntArray>

    /**
     * This function calculates the minimum number of turns the printer will need in order to print the string.
     * It uses a top-down dynamic programming approach (memoization).
     * @param str The string to print.
     * @return The minimum number of turns the printer will need.
     */
    override operator fun invoke(str: String): Int {
        if (str.isEmpty()) return 0
        val length: Int = str.length
        memo = Array(length) { IntArray(length) }
        for (left in 0 until length) {
            for (right in 0 until length) {
                memo[left][right] = -1
            }
        }

        return calculateMinTurns(str, length, 0, length - 1) + 1
    }

    /**
     * This function is a helper function for the top-down dynamic programming approach.
     * It recursively solves the problem for a given substring of the string.
     * @param s The string to print.
     * @param n The length of the string.
     * @param left The starting index of the substring.
     * @param right The ending index of the substring.
     * @return The minimum number of turns the printer will need to print the substring.
     */
    private fun calculateMinTurns(s: String, length: Int, start: Int, end: Int): Int {
        if (memo[start][end] != -1) {
            return memo[start][end]
        }
        memo[start][end] = length
        var splitIndex = -1
        for (i in start until end) {
            if (s[i] != s[end] && splitIndex == -1) {
                splitIndex = i
            }
            if (splitIndex != -1) {
                memo[start][end] = min(
                    memo[start][end].toDouble(),
                    1.plus(calculateMinTurns(s, length, splitIndex, i)).plus(
                        calculateMinTurns(
                            s,
                            length,
                            i + 1,
                            end,
                        ),
                    ).toDouble(),
                ).toInt()
            }
        }
        if (splitIndex == -1) {
            memo[start][end] = 0
        }
        return memo[start][end]
    }
}
