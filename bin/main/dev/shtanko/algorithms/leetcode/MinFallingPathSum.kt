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

import dev.shtanko.algorithms.MOD
import kotlin.math.min

/**
 * 931. Minimum Falling Path Sum
 * @see <a href="https://leetcode.com/problems/minimum-falling-path-sum">Source</a>
 */
fun interface MinFallingPathSum {
    operator fun invoke(matrix: Array<IntArray>): Int
}

class MinFallingPathSumTopDown : MinFallingPathSum {
    override operator fun invoke(matrix: Array<IntArray>): Int {
        val (rowCount, colCount) = listOf(matrix.size, matrix[0].size)

        fun calculatePathSum(row: Int, col: Int): Int {
            if (col < 0 || col == colCount) {
                return MOD
            }
            if (row == 0) {
                return matrix[row][col]
            }

            val leftSum = calculatePathSum(row - 1, col - 1)
            val straightSum = calculatePathSum(row - 1, col)
            val rightSum = calculatePathSum(row - 1, col + 1)

            return matrix[row][col] + listOf(leftSum, straightSum, rightSum).min()
        }

        var minPathSum = MOD
        for (currentCol in 0 until colCount) {
            minPathSum = min(minPathSum, calculatePathSum(rowCount - 1, currentCol))
        }
        return minPathSum
    }
}

class MinFallingPathSumDPMemo : MinFallingPathSum {
    override operator fun invoke(matrix: Array<IntArray>): Int {
        val memo = mutableMapOf<String, Int>()
        val (rowCount, colCount) = listOf(matrix.size, matrix[0].size)

        fun calculatePathSum(row: Int, col: Int): Int {
            if (col < 0 || col == colCount) {
                return MOD
            }
            if (row == 0) {
                return matrix[row][col]
            }

            val memoKey = "$row,$col"
            if (!memo.contains(memoKey)) {
                val leftSum = calculatePathSum(row - 1, col - 1)
                val straightSum = calculatePathSum(row - 1, col)
                val rightSum = calculatePathSum(row - 1, col + 1)
                memo[memoKey] = matrix[row][col] + listOf(leftSum, straightSum, rightSum).min()
            }
            return memo[memoKey] ?: 0
        }

        var minPathSum = MOD
        for (currentCol in 0 until colCount) {
            minPathSum = min(minPathSum, calculatePathSum(rowCount - 1, currentCol))
        }
        return minPathSum
    }
}

class MinFallingPathSumBottomUp : MinFallingPathSum {
    override operator fun invoke(matrix: Array<IntArray>): Int {
        val (rowCount, colCount) = matrix.size to matrix[0].size

        for (currentRow in 1 until rowCount) {
            updateMatrixRow(matrix, currentRow, colCount)
        }

        return matrix[rowCount - 1].min()
    }

    private fun updateMatrixRow(matrix: Array<IntArray>, row: Int, colCount: Int) {
        for (currentCol in 0 until colCount) {
            val leftValue = getMatrixValue(matrix, row - 1, currentCol - 1, colCount)
            val straightValue = getMatrixValue(matrix, row - 1, currentCol, colCount)
            val rightValue = getMatrixValue(matrix, row - 1, currentCol + 1, colCount)

            matrix[row][currentCol] += listOf(leftValue, straightValue, rightValue).min()
        }
    }

    private fun getMatrixValue(matrix: Array<IntArray>, row: Int, col: Int, colCount: Int): Int {
        return if (col in 0 until colCount) matrix[row][col] else MOD
    }
}
