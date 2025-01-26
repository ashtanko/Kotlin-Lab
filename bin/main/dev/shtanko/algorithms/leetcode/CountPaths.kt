/*
 * Copyright 2023 Oleksii Shtanko
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

/**
 * 2328. Number of Increasing Paths in a Grid
 * @see <a href="https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/">Source</a>
 */
fun interface CountPaths {
    operator fun invoke(grid: Array<IntArray>): Int
}

/**
 * Approach 1: Sorting + DP
 */
class CountPathsSortDP : CountPaths {
    override operator fun invoke(grid: Array<IntArray>): Int {
        val directions = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
        val m: Int = grid.size
        val n: Int = grid[0].size

        // Initialize dp, 1 stands for the path made by a cell itself.
        val dp = Array(m) { IntArray(n) { 1 } }

        // Sort all cells by value.
        val cellList = Array(m * n) { IntArray(2) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                val index = i * n + j
                cellList[index][0] = i
                cellList[index][1] = j
            }
        }

        cellList.sortWith { a: IntArray, b: IntArray ->
            grid[a[0]][a[1]] - grid[b[0]][b[1]]
        }

        // Iterate over the sorted cells, for each cell grid[i][j]:
        for (cell in cellList) {
            val i = cell[0]
            val j = cell[1]

            // Check its four neighbor cells, if a neighbor cell grid[currI][currJ] has a
            // larger value, increment dp[currI][currJ] by dp[i][j]
            for (d in directions) {
                val currI = i + d[0]
                val currJ = j + d[1]
                if (currI in 0 until m && 0 <= currJ && currJ < n && grid[currI][currJ] > grid[i][j]) {
                    dp[currI][currJ] += dp[i][j]
                    dp[currI][currJ] %= MOD
                }
            }
        }

        // Sum over dp[i][j]
        var answer = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                answer += dp[i][j]
                answer %= MOD
            }
        }
        return answer
    }
}

/**
 * Approach 2: DFS with Memoization
 */
class CountPathsDFSMemo : CountPaths {

    private lateinit var dp: Array<IntArray>
    private var directions = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))

    override operator fun invoke(grid: Array<IntArray>): Int {
        val m: Int = grid.size
        val n: Int = grid[0].size
        dp = Array(m) { IntArray(n) }
        // Iterate over all cells grid[i][j] and sum over dfs(i, j).
        var answer = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                answer = (answer + dfs(grid, i, j)) % MOD
            }
        }

        return answer
    }

    private fun dfs(grid: Array<IntArray>, i: Int, j: Int): Int {
        // If dp[i][j] is non-zero, it means we have got the value of dfs(i, j),
        // so just return dp[i][j].
        if (dp[i][j] != 0) return dp[i][j]

        // Otherwise, set answer = 1, the path made of grid[i][j] itself.
        var answer = 1

        // Check its four neighbor cells, if a neighbor cell grid[prevI][prevJ] has a
        // smaller value, we move to this cell and solve the sub-problem: dfs(prevI, prevJ).
        for (d in directions) {
            val prevI = i + d[0]
            val prevJ = j + d[1]
            if (0 <= prevI && prevI < grid.size &&
                0 <= prevJ && prevJ < grid[0].size &&
                grid[prevI][prevJ] < grid[i][j]
            ) {
                answer += dfs(grid, prevI, prevJ)
                answer %= MOD
            }
        }

        // Update dp[i][j], so that we don't recalculate its value later.
        dp[i][j] = answer
        return answer
    }
}
