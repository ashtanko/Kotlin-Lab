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
 * 2257. Count Unguarded Cells in the Grid
 * @see <a href="https://leetcode.com/problems/count-unguarded-cells-in-the-grid/">Count Unguarded Cells in the Grid</a>
 */
@Medium("https://leetcode.com/problems/count-unguarded-cells-in-the-grid/")
fun interface CountUnguarded {
    operator fun invoke(m: Int, n: Int, guards: Array<IntArray>, walls: Array<IntArray>): Int
}

data object CountUnguardedSolution : CountUnguarded {
    private const val UNGUARDED = 0
    private const val GUARDED = 1
    private const val GUARD = 2
    private const val WALL = 3

    override fun invoke(
        m: Int,
        n: Int,
        guards: Array<IntArray>,
        walls: Array<IntArray>,
    ): Int {
        val grid = Array(m) { IntArray(n) { UNGUARDED } }

        // Mark guards' positions
        for ((row, col) in guards) {
            grid[row][col] = GUARD
        }

        // Mark walls' positions
        for ((row, col) in walls) {
            grid[row][col] = WALL
        }

        // Updates the visibility of a cell based on the current guard line state.
        fun updateCellVisibility(row: Int, col: Int, isGuardLineActive: Boolean): Boolean {
            return when (grid[row][col]) {
                GUARD -> true // If current cell is a guard, reactivate the guard line
                WALL -> false // If current cell is a wall, deactivate the guard line
                else -> {
                    // If guard line is active, mark cell as guarded
                    if (isGuardLineActive) grid[row][col] = GUARDED
                    isGuardLineActive
                }
            }
        }

        // Horizontal passes
        for (row in 0 until m) {
            var isGuardLineActive = grid[row][0] == GUARD
            for (col in 1 until n) {
                isGuardLineActive = updateCellVisibility(row, col, isGuardLineActive)
            }
            isGuardLineActive = grid[row][n - 1] == GUARD
            for (col in n - 2 downTo 0) {
                isGuardLineActive = updateCellVisibility(row, col, isGuardLineActive)
            }
        }

        // Vertical passes
        for (col in 0 until n) {
            var isGuardLineActive = grid[0][col] == GUARD
            for (row in 1 until m) {
                isGuardLineActive = updateCellVisibility(row, col, isGuardLineActive)
            }
            isGuardLineActive = grid[m - 1][col] == GUARD
            for (row in m - 2 downTo 0) {
                isGuardLineActive = updateCellVisibility(row, col, isGuardLineActive)
            }
        }

        // Count unguarded cells
        var count = 0
        for (row in 0 until m) {
            for (col in 0 until n) {
                if (grid[row][col] == UNGUARDED) {
                    count++
                }
            }
        }

        return count
    }
}
