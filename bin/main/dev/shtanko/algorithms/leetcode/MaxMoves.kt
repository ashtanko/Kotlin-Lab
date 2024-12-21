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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2684. Maximum Number of Moves in a Grid
 * @see <a href="https://leetcode.com/problems/maximum-number-of-moves-in-a-grid/">Source</a>
 */
@Medium("https://leetcode.com/problems/maximum-number-of-moves-in-a-grid")
fun interface MaxMoves {
    operator fun invoke(grid: Array<IntArray>): Int
}

class MaxMovesTopDown : MaxMoves {
    override fun invoke(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size

        val dp = Array(rows) { IntArray(cols) { -1 } }

        var maxMoves = 0
        for (row in 0 until rows) {
            maxMoves = maxOf(maxMoves, dfs(row, 0, grid, dp))
        }

        return maxMoves
    }

    private val directions = intArrayOf(-1, 0, 1)

    private fun dfs(row: Int, col: Int, grid: Array<IntArray>, dp: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size

        if (dp[row][col] != -1) {
            return dp[row][col]
        }

        var maxMoves = 0
        for (dir in directions) {
            val newRow = row + dir
            val newCol = col + 1

            if (newRow in 0 until rows && newCol in 0 until cols && grid[row][col] < grid[newRow][newCol]) {
                maxMoves = maxOf(maxMoves, 1 + dfs(newRow, newCol, grid, dp))
            }
        }

        dp[row][col] = maxMoves
        return maxMoves
    }
}

class MaxMovesBottomUpDp : MaxMoves {
    override fun invoke(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size

        // Create a 2D array to store the maximum moves to reach a cell
        val dp = Array(rows) { IntArray(2) }

        // Initialize the first column
        for (row in 0 until rows) {
            dp[row][0] = 1
        }

        var maxMoves = 0

        for (col in 1 until cols) {
            for (row in 0 until rows) {
                // Check if we can move from the previous column
                if (grid[row][col] > grid[row][col - 1] && dp[row][0] > 0) {
                    dp[row][1] = maxOf(dp[row][1], dp[row][0] + 1)
                }

                // Check if we can move from the upper diagonal
                if (row - 1 >= 0 && grid[row][col] > grid[row - 1][col - 1] && dp[row - 1][0] > 0) {
                    dp[row][1] = maxOf(dp[row][1], dp[row - 1][0] + 1)
                }

                // Check if we can move from the lower diagonal
                if (row + 1 < rows && grid[row][col] > grid[row + 1][col - 1] && dp[row + 1][0] > 0) {
                    dp[row][1] = maxOf(dp[row][1], dp[row + 1][0] + 1)
                }

                maxMoves = maxOf(maxMoves, dp[row][1] - 1)
            }

            // Shift dp values for the next iteration
            for (row in 0 until rows) {
                dp[row][0] = dp[row][1]
                dp[row][1] = 0
            }
        }

        return maxMoves
    }
}
