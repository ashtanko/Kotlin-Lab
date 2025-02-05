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

import dev.shtanko.algorithms.annotations.BottomUpDP
import dev.shtanko.algorithms.annotations.TopDownDP

fun interface CherryPickup2Strategy {
    operator fun invoke(grid: Array<IntArray>): Int
}

@TopDownDP
class CherryPickup2DPTopDown : CherryPickup2Strategy {
    override operator fun invoke(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        val rows: Int = grid.size
        val cols: Int = grid[0].size
        val dpCache = Array(rows) { Array(cols) { IntArray(cols) } }
        // Initialize all elements to -1 to mark unseen
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                for (k in 0 until cols) {
                    dpCache[i][j][k] = -1
                }
            }
        }
        return dp(0, 0, cols - 1, grid, dpCache)
    }

    private fun dp(row: Int, col1: Int, col2: Int, grid: Array<IntArray>, dpCache: Array<Array<IntArray>>): Int {
        val cols = grid[0].size
        val col1OutOfBounds = isOutOfBounds(col1, cols)
        val col2OutOfBounds = isOutOfBounds(col2, cols)
        if (col1OutOfBounds || col2OutOfBounds) {
            return 0
        }
        // Check cache
        if (dpCache[row][col1][col2] != -1) {
            return dpCache[row][col1][col2]
        }
        // Current cell
        var result = 0
        result += grid[row][col1]
        if (col1 != col2) {
            result += grid[row][col2]
        }
        // Transition
        if (row != grid.size - 1) {
            var max = 0
            for (newCol1 in col1 - 1..col1 + 1) {
                for (newCol2 in col2 - 1..col2 + 1) {
                    max = maxOf(max, dp(row + 1, newCol1, newCol2, grid, dpCache))
                }
            }
            result += max
        }
        dpCache[row][col1][col2] = result
        return result
    }

    private fun isOutOfBounds(col: Int, size: Int) = col < 0 || col >= size
}

@BottomUpDP
class CherryPickup2DPBottomUp : CherryPickup2Strategy {
    override operator fun invoke(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        val rows: Int = grid.size
        val cols: Int = grid[0].size
        val dp = Array(rows) { Array(cols) { IntArray(cols) } }

        for (row in rows - 1 downTo 0) {
            for (col1 in 0 until cols) {
                for (col2 in 0 until cols) {
                    var result = 0
                    // Current cell
                    result += grid[row][col1]
                    if (col1 != col2) {
                        result += grid[row][col2]
                    }
                    // Transition
                    if (row != rows - 1) {
                        var maxValue = 0
                        for (newCol1 in col1 - 1..col1 + 1) {
                            for (newCol2 in col2 - 1..col2 + 1) {
                                if (newCol1 in 0 until cols && newCol2 >= 0 && newCol2 < cols) {
                                    maxValue = maxOf(maxValue, dp[row + 1][newCol1][newCol2])
                                }
                            }
                        }
                        result += maxValue
                    }
                    dp[row][col1][col2] = result
                }
            }
        }
        return dp[0][0][cols - 1]
    }
}
