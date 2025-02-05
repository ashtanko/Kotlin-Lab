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
 * 840. Magic Squares In Grid
 * @see <a href="https://leetcode.com/problems/magic-squares-in-grid">Source</a>
 */
fun interface MagicSquaresInGrid {
    operator fun invoke(grid: Array<IntArray>): Int
}

class MagicSquaresInGridManualScan : MagicSquaresInGrid {
    override fun invoke(grid: Array<IntArray>): Int {
        var count = 0
        val rows = grid.size
        val cols = grid[0].size
        for (row in 0 until rows - 2) {
            for (col in 0 until cols - 2) {
                if (isMagicSquare(grid, row, col)) {
                    count++
                }
            }
        }
        return count
    }

    private fun isMagicSquare(grid: Array<IntArray>, startRow: Int, startCol: Int): Boolean {
        val seenNumbers = BooleanArray(10)
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val number = grid[startRow + i][startCol + j]
                if (number < 1 || number > 9) return false
                if (seenNumbers[number]) return false
                seenNumbers[number] = true
            }
        }

        // Check if diagonal sums are the same
        val diagonalSum1 =
            grid[startRow][startCol] + grid[startRow + 1][startCol + 1] + grid[startRow + 2][startCol + 2]
        val diagonalSum2 =
            grid[startRow + 2][startCol] + grid[startRow + 1][startCol + 1] + grid[startRow][startCol + 2]

        if (diagonalSum1 != diagonalSum2) return false

        // Check if all row sums match the diagonal sums
        val rowSum1 = grid[startRow][startCol] + grid[startRow][startCol + 1] + grid[startRow][startCol + 2]
        val rowSum2 = grid[startRow + 1][startCol] + grid[startRow + 1][startCol + 1] + grid[startRow + 1][startCol + 2]
        val rowSum3 = grid[startRow + 2][startCol] + grid[startRow + 2][startCol + 1] + grid[startRow + 2][startCol + 2]

        if (rowSum1 != diagonalSum1 || rowSum2 != diagonalSum1 || rowSum3 != diagonalSum1) return false

        // Check if all column sums match the diagonal sums
        val colSum1 = grid[startRow][startCol] + grid[startRow + 1][startCol] + grid[startRow + 2][startCol]
        val colSum2 = grid[startRow][startCol + 1] + grid[startRow + 1][startCol + 1] + grid[startRow + 2][startCol + 1]
        val colSum3 = grid[startRow][startCol + 2] + grid[startRow + 1][startCol + 2] + grid[startRow + 2][startCol + 2]

        return !(colSum1 != diagonalSum1 || colSum2 != diagonalSum1 || colSum3 != diagonalSum2)
    }
}

class MagicSquaresInGridMagicSquare : MagicSquaresInGrid {
    override fun invoke(grid: Array<IntArray>): Int {
        var ans = 0
        val m = grid.size
        val n = grid[0].size
        for (row in 0 until m - 2) {
            for (col in 0 until n - 2) {
                if (isMagicSquare(grid, row, col)) {
                    ans++
                }
            }
        }
        return ans
    }

    private fun isMagicSquare(grid: Array<IntArray>, row: Int, col: Int): Boolean {
        // The sequences are each repeated twice to account for
        // the different possible starting points of the sequence
        // in the magic square
        val sequence = "2943816729438167"
        val sequenceReversed = "7618349276183492"

        val border = StringBuilder()
        // Flattened indices for bordering elements of 3x3 grid
        val borderIndices = intArrayOf(0, 1, 2, 5, 8, 7, 6, 3)
        for (i in borderIndices) {
            val num = grid[row + i / 3][col + (i % 3)]
            border.append(num)
        }

        val borderConverted = border.toString()

        // Make sure the sequence starts at one of the corners
        return (
            grid[row][col] % 2 == 0 &&
                (sequence.contains(borderConverted) || sequenceReversed.contains(borderConverted)) &&
                grid[row + 1][col + 1] == 5
            )
    }
}
