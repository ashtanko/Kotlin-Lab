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

/**
 * 861. Score After Flipping Matrix
 * @see <a href="https://leetcode.com/problems/score-after-flipping-matrix">Source</a>
 */
fun interface ScoreAfterFlippingMatrix {
    operator fun invoke(grid: Array<IntArray>): Int
}

class ScoreAfterFlippingMatrixWithModifying : ScoreAfterFlippingMatrix {
    override fun invoke(grid: Array<IntArray>): Int {
        val rowCount = grid.size
        val columnCount = grid.first().size

        setFirstColumn(grid, rowCount, columnCount)
        optimizeColumns(grid, rowCount, columnCount)
        return calculateFinalScore(grid, rowCount, columnCount)
    }

    private fun setFirstColumn(matrix: Array<IntArray>, rowCount: Int, columnCount: Int) {
        for (rowIndex in 0 until rowCount) {
            if (matrix[rowIndex][0] == 0) {
                flipRow(matrix, rowIndex, columnCount)
            }
        }
    }

    private fun flipRow(matrix: Array<IntArray>, rowIndex: Int, columnCount: Int) {
        for (columnIndex in 0 until columnCount) {
            matrix[rowIndex][columnIndex] = 1 - matrix[rowIndex][columnIndex]
        }
    }

    private fun optimizeColumns(matrix: Array<IntArray>, rowCount: Int, columnCount: Int) {
        for (columnIndex in 1 until columnCount) {
            var zeroCount = 0
            for (rowIndex in 0 until rowCount) {
                if (matrix[rowIndex][columnIndex] == 0) {
                    zeroCount++
                }
            }
            if (zeroCount > rowCount - zeroCount) {
                flipColumn(matrix, columnIndex, rowCount)
            }
        }
    }

    private fun flipColumn(matrix: Array<IntArray>, columnIndex: Int, rowCount: Int) {
        for (rowIndex in 0 until rowCount) {
            matrix[rowIndex][columnIndex] = 1 - matrix[rowIndex][columnIndex]
        }
    }

    private fun calculateFinalScore(matrix: Array<IntArray>, rowCount: Int, columnCount: Int): Int {
        var totalScore = 0
        for (rowIndex in 0 until rowCount) {
            for (columnIndex in 0 until columnCount) {
                val columnScore = matrix[rowIndex][columnIndex] shl (columnCount - columnIndex - 1)
                totalScore += columnScore
            }
        }
        return totalScore
    }
}

class ScoreAfterFlippingMatrixWithoutModifying : ScoreAfterFlippingMatrix {
    override fun invoke(grid: Array<IntArray>): Int {
        val rowCount = grid.size
        val columnCount = grid[0].size

        // Set score to summation of first column
        var totalScore = (1 shl (columnCount - 1)) * rowCount

        // Loop over all other columns
        for (columnIndex in 1 until columnCount) {
            var sameBitCount = 0
            for (rowIndex in 0 until rowCount) {
                // Count elements matching first in row
                if (grid[rowIndex][columnIndex] == grid[rowIndex][0]) {
                    sameBitCount++
                }
            }
            // Calculate score based on the number of same bits in a column
            sameBitCount = maxOf(sameBitCount, rowCount - sameBitCount)
            // Calculate the score contribution for the current column
            val columnScore = (1 shl (columnCount - columnIndex - 1)) * sameBitCount
            // Add contribution to score
            totalScore += columnScore
        }

        return totalScore
    }
}
