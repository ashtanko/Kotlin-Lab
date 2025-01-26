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

import kotlin.math.max

fun interface LongestIncreasingPath {
    operator fun invoke(grid: Array<IntArray>): Int
}

class LongestIncreasingPathDFS : LongestIncreasingPath {

    private val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
    private var m = 0
    private var n = 0

    override operator fun invoke(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        m = grid.size
        n = grid[0].size
        val cache = Array(m) { IntArray(n) }
        var ans = 0
        for (i in 0 until m) for (j in 0 until n) ans = Math.max(ans, dfs(grid, i, j, cache))
        return ans
    }

    private fun dfs(matrix: Array<IntArray>, i: Int, j: Int, cache: Array<IntArray>): Int {
        if (cache[i][j] != 0) return cache[i][j]
        for (d in dirs) {
            val x = i + d[0]
            val y = j + d[1]
            if (x in 0 until m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
                cache[i][j] = max(cache[i][j], dfs(matrix, x, y, cache))
            }
        }
        return ++cache[i][j]
    }
}

class LongestIncreasingPathPeelingOnion : LongestIncreasingPath {
    private val dir = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))

    override operator fun invoke(grid: Array<IntArray>): Int {
        var m: Int = grid.size
        if (m == 0) return 0
        var n: Int = grid[0].size
        val matrix = Array(m + 2) { IntArray(n + 2) }
        for (i in 0 until m) System.arraycopy(grid[i], 0, matrix[i + 1], 1, n)
        val outdegree = Array(m + 2) { IntArray(n + 2) }
        for (i in 1..m) {
            for (j in 1..n) {
                for (d in dir) {
                    if (matrix[i][j] < matrix[i + d[0]][j + d[1]]) {
                        outdegree[i][j]++
                    }
                }
            }
        }
        n += 2
        m += 2
        var leaves: MutableList<IntArray> = ArrayList()
        for (i in 1 until m - 1) {
            for (j in 1 until n - 1) {
                if (outdegree[i][j] == 0) {
                    leaves.add(intArrayOf(i, j))
                }
            }
        }
        var height = 0
        while (leaves.isNotEmpty()) {
            height++
            val newLeaves: MutableList<IntArray> = ArrayList()
            for (node in leaves) {
                for (d in dir) {
                    val x = node[0] + d[0]
                    val y = node[1] + d[1]
                    if (matrix[node[0]][node[1]] > matrix[x][y] && --outdegree[x][y] == 0) {
                        newLeaves.add(intArrayOf(x, y))
                    }
                }
            }
            leaves = newLeaves
        }
        return height
    }
}
