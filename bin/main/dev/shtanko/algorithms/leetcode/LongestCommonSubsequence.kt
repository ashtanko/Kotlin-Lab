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
 * 1143. Longest Common Subsequence
 * @see <a href="https://leetcode.com/problems/longest-common-subsequence">Source</a>
 */
fun interface LongestCommonSubsequence {
    operator fun invoke(text1: String, text2: String): Int
}

class LongestCommonSubsequenceDP : LongestCommonSubsequence {
    override operator fun invoke(text1: String, text2: String): Int {
        if (text1.length < text2.length) {
            return calculateLCS(text1, text2)
        }
        return calculateLCS(text2, text1)
    }

    private fun calculateLCS(firstString: String, secondString: String): Int {
        val dpTable = Array(2) { IntArray(firstString.length + 1) }
        // row represents the length of secondString, col represents the length of firstString
        for (i in 1..secondString.length) {
            // base case
            dpTable[i % 2][0] = 0
            for (j in 1..firstString.length) {
                if (firstString[j - 1] == secondString[i - 1]) {
                    dpTable[i % 2][j] = dpTable[(i - 1) % 2][j - 1] + 1
                } else {
                    dpTable[i % 2][j] = max(
                        dpTable[(i - 1) % 2][j - 1],
                        max(dpTable[(i - 1) % 2][j], dpTable[i % 2][j - 1]),
                    )
                }
            }
        }
        return dpTable[secondString.length % 2][firstString.length]
    }
}
