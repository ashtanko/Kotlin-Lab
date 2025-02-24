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

import dev.shtanko.algorithms.annotations.Backtracking
import dev.shtanko.algorithms.annotations.DFS

/**
 * 980. Unique Paths III
 * @see <a href="https://leetcode.com/problems/unique-paths-iii/">Source</a>
 */
fun interface UniquePaths3 {
    operator fun invoke(grid: Array<IntArray>): Int
}

@DFS
@Backtracking
class UniquePaths3Backtracking : UniquePaths3 {
    private var result = 0
    private var emptyCells: Int = 1
    private var startX: Int = 0
    private var startY: Int = 0
    override operator fun invoke(grid: Array<IntArray>): Int {
        if (grid.isEmpty() || grid[0].isEmpty()) {
            return 0
        }
        val gridHeight: Int = grid.size
        val gridWidth: Int = grid[0].size
        for (row in 0 until gridHeight) {
            for (column in 0 until gridWidth) {
                if (grid[row][column] == 0) {
                    emptyCells++
                } else if (grid[row][column] == 1) {
                    startX = row
                    startY = column
                }
            }
        }
        dfs(grid, startX, startY)
        return result
    }

    private fun dfs(grid: Array<IntArray>, x: Int, y: Int) {
        if (x < 0 || x >= grid.size || y < 0 || y >= grid[0].size || grid[x][y] < 0) return
        if (grid[x][y] == 2) {
            if (emptyCells == 0) result++
            return
        }
        grid[x][y] = -2
        emptyCells--
        dfs(grid, x + 1, y)
        dfs(grid, x - 1, y)
        dfs(grid, x, y + 1)
        dfs(grid, x, y - 1)
        grid[x][y] = 0
        emptyCells++
    }
}
