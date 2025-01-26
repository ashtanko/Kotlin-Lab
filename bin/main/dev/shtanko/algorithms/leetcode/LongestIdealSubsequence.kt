/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import kotlin.math.abs
import kotlin.math.max

/**
 * 2370. Longest Ideal Subsequence
 * @see <a href="https://leetcode.com/problems/longest-ideal-subsequence">Source</a>
 */
fun interface LongestIdealSubsequence {
    /**
     * This function calculates the length of the longest ideal subsequence in the input string.
     * An ideal subsequence is defined as a subsequence where the absolute difference between the ASCII values of any
     * two characters does not exceed a given maximum distance.
     * The function uses a dynamic programming approach to solve the problem.
     *
     * @param str The string in which to find the longest ideal subsequence.
     * @param k The maximum allowed absolute difference between the ASCII values of any two characters in an ideal
     * subsequence.
     * @return The length of the longest ideal subsequence in the input string.
     */
    operator fun invoke(str: String, k: Int): Int
}

class LongestIdealSubsequenceTopDownDP : LongestIdealSubsequence {
    override fun invoke(str: String, k: Int): Int {
        if (str.isEmpty()) {
            return 0
        }
        val stringLength: Int = str.length
        val memo = Array(stringLength) { IntArray(ALPHABET_LETTERS_COUNT) { -1 } }

        var result = 0
        for (charCode in 0 until ALPHABET_LETTERS_COUNT) {
            result = max(result, depthFirstSearch(stringLength - 1, charCode, memo, str, k))
        }
        return result
    }

    private fun depthFirstSearch(index: Int, charCode: Int, memo: Array<IntArray>, str: String, maxDistance: Int): Int {
        if (memo[index][charCode] != -1) {
            return memo[index][charCode]
        }

        memo[index][charCode] = 0
        val isMatch = charCode == (str[index].code - 'a'.code)
        if (isMatch) {
            memo[index][charCode] = 1
        }

        if (index > 0) {
            memo[index][charCode] = depthFirstSearch(index - 1, charCode, memo, str, maxDistance)
            if (isMatch) {
                for (prevCharCode in 0 until ALPHABET_LETTERS_COUNT) {
                    if (abs((charCode - prevCharCode).toDouble()) <= maxDistance) {
                        memo[index][charCode] =
                            max(
                                memo[index][charCode],
                                (depthFirstSearch(index - 1, prevCharCode, memo, str, maxDistance) + 1),
                            )
                    }
                }
            }
        }
        return memo[index][charCode]
    }
}

class LongestIdealSubsequenceBottomUpDP : LongestIdealSubsequence {
    override fun invoke(str: String, k: Int): Int {
        val stringLength: Int = str.length
        val dp = IntArray(ALPHABET_LETTERS_COUNT)

        var maximumSubsequenceLength = 0

        for (index in 0 until stringLength) {
            val currCharCode: Int = str[index] - 'a'
            var bestSubsequenceLength = 0

            // For each character, iterate over all possible previous character codes.
            for (prevCharCode in 0 until ALPHABET_LETTERS_COUNT) {
                // If the absolute difference between the current character code and the previous character code is
                // within the maximum distance, update the best subsequence length.
                if (abs((prevCharCode - currCharCode)) <= k) {
                    bestSubsequenceLength = max(bestSubsequenceLength, dp[prevCharCode])
                }
            }
            // Update the dynamic programming array and the maximum subsequence length.
            dp[currCharCode] = max(dp[currCharCode], bestSubsequenceLength.plus(1))
            maximumSubsequenceLength = max(maximumSubsequenceLength, dp[currCharCode])
        }
        return maximumSubsequenceLength
    }
}
