/*
 * Copyright 2020 Oleksii Shtanko
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
 * 392. Is Subsequence
 * @see <a href="https://leetcode.com/problems/unique-paths-ii/">Source</a>
 */
fun interface IsSubsequence {
    operator fun invoke(source: String, target: String): Boolean
}

class IsSubsequenceDP : IsSubsequence {
    override operator fun invoke(source: String, target: String): Boolean {
        val rows: Int = source.length
        val cols: Int = target.length
        // the source string is empty
        if (rows == 0) return true

        val matrix = Array(rows + 1) { IntArray(cols + 1) }
        // DP calculation, we fill the matrix column by column, bottom up
        for (col in 1..cols) {
            for (row in 1..rows) {
                // find another match
                if (source[row - 1] == target[col - 1]) {
                    matrix[row][col] =
                        matrix[row - 1][col - 1] + 1
                } else {
                    // retrieve the maximal result from previous prefixes
                    matrix[row][col] = max(matrix[row][col - 1], matrix[row - 1][col])
                }
            }
            // check if we can consume the entire source string,
            // with the current prefix of the target string.
            if (matrix[rows][col] == rows) return true
        }
        // matching failure
        return false
    }
}

class IsSubsequenceTwoPointers : IsSubsequence {
    override fun invoke(source: String, target: String): Boolean {
        var j = 0
        val n = source.length
        val m = target.length

        for (i in 0 until m) {
            if (j < n && source[j] == target[i]) {
                j++
            }
        }

        return j == n
    }
}

class IsSubsequenceRecursion : IsSubsequence {
    override fun invoke(source: String, target: String): Boolean {
        val m = source.length
        val n = target.length
        if (m > n) {
            return false
        }
        return isSubSequence(source, target, m, n) == m
    }

    private fun isSubSequence(s1: String, s2: String, i: Int, j: Int): Int {
        if (i == 0 || j == 0) {
            return 0
        }
        return if (s1[i - 1] == s2[j - 1]) {
            1 + isSubSequence(s1, s2, i - 1, j - 1)
        } else {
            isSubSequence(s1, s2, i, j - 1)
        }
    }
}
