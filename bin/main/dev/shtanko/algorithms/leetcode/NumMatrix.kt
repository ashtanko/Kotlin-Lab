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

/**
 * 304. Range Sum Query 2D - Immutable
 * @see <a href="https://leetcode.com/problems/range-sum-query-2d-immutable/">Source</a>
 */
fun interface NumMatrix {
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int
}

class CachingRows(matrix: Array<IntArray>) : NumMatrix {
    private lateinit var dp: Array<IntArray>

    init {
        if (matrix.isNotEmpty() || matrix[0].isNotEmpty()) {
            dp = Array(matrix.size) { IntArray(matrix[0].size + 1) }
            for (r in matrix.indices) {
                for (c in 0 until matrix[0].size) {
                    dp[r][c + 1] = dp[r][c] + matrix[r][c]
                }
            }
        }
    }

    override fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        var sum = 0
        for (row in row1..row2) {
            sum += dp[row][col2 + 1] - dp[row][col1]
        }
        return sum
    }
}

class CachingSmarter(matrix: Array<IntArray>) : NumMatrix {
    private lateinit var dp: Array<IntArray>

    init {
        if (matrix.isNotEmpty() || matrix[0].isNotEmpty()) {
            dp = Array(matrix.size + 1) { IntArray(matrix[0].size + 1) }
            for (r in matrix.indices) {
                for (c in 0 until matrix[0].size) {
                    dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c]
                }
            }
        }
    }

    override fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1]
    }
}
