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
 * 1219. Path with Maximum Gold
 * @see <a href="https://leetcode.com/problems/path-with-maximum-gold/">Source</a>
 */
fun interface GetMaximumGold {
    operator fun invoke(grid: Array<IntArray>): Int
}

class GetMaximumGoldBacktracking : GetMaximumGold {

    private val dir = intArrayOf(0, 1, 0, -1, 0)

    override operator fun invoke(grid: Array<IntArray>): Int {
        if (grid.isEmpty() || grid.first().isEmpty()) return 0
        val m: Int = grid.size
        val n: Int = grid.first().size
        var maxGold = 0
        for (r in 0 until m) for (c in 0 until n) {
            maxGold = max(maxGold, findMaxGold(grid, m, n, r, c))
        }
        return maxGold
    }

    private fun findMaxGold(grid: Array<IntArray>, m: Int, n: Int, r: Int, c: Int): Int {
        val left = r < 0 || r == m || c < 0
        if (left || c == n || grid[r][c] == 0) return 0
        val origin = grid[r][c]
        grid[r][c] = 0 // mark as visited
        var maxGold = 0
        for (i in 0..3) {
            maxGold = max(maxGold, findMaxGold(grid, m, n, dir[i] + r, dir[i + 1] + c))
        }
        grid[r][c] = origin // backtrack
        return maxGold + origin
    }
}

class GetMaximumGoldDFS : GetMaximumGold {
    override operator fun invoke(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        val m: Int = grid.size
        val n: Int = grid[0].size
        val max = IntArray(1)
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] != 0) {
                    dfs(grid, i, j, 0, max)
                }
            }
        }
        return max[0]
    }

    private fun dfs(grid: Array<IntArray>, i: Int, j: Int, currSum: Int, max: IntArray) {
        val left = i < 0 || j < 0 || i >= grid.size
        if (left || j >= grid[0].size || grid[i][j] == 0) {
            max[0] = max(max[0], currSum)
            return
        }
        val temp = grid[i][j]
        grid[i][j] = 0
        dfs(grid, i - 1, j, currSum + temp, max)
        dfs(grid, i + 1, j, currSum + temp, max)
        dfs(grid, i, j - 1, currSum + temp, max)
        dfs(grid, i, j + 1, currSum + temp, max)
        grid[i][j] = temp
    }
}
