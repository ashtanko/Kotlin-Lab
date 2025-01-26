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

import java.util.LinkedList
import java.util.Queue

/**
 * 1254. Number of Closed Islands
 * @see <a href="https://leetcode.com/problems/number-of-closed-islands/">Source</a>
 */
fun interface NumberOfClosedIslands {
    fun closedIsland(grid: Array<IntArray>): Int
}

/**
 * Approach 1: Breadth First Search
 */
class NumberOfClosedIslandsBFS : NumberOfClosedIslands {
    override fun closedIsland(grid: Array<IntArray>): Int {
        val m: Int = grid.size
        val n: Int = grid[0].size
        val visit = Array(m) { BooleanArray(n) }
        var count = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 0 && !visit[i][j] && bfs(i, j, m, n, grid, visit)) {
                    count++
                }
            }
        }
        return count
    }

    private fun bfs(x: Int, y: Int, m: Int, n: Int, grid: Array<IntArray>, visit: Array<BooleanArray>): Boolean {
        var x1 = x
        var y1 = y
        val q: Queue<IntArray> = LinkedList()
        q.offer(intArrayOf(x1, y1))
        visit[x1][y1] = true
        var isClosed = true
        val dirX = intArrayOf(0, 1, 0, -1)
        val dirY = intArrayOf(-1, 0, 1, 0)
        while (q.isNotEmpty()) {
            val temp: IntArray = q.poll()
            x1 = temp[0]
            y1 = temp[1]
            for (i in 0..3) {
                val r = x1 + dirX[i]
                val c = y1 + dirY[i]
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    // (x, y) is a boundary cell.
                    isClosed = false
                } else if (grid[r][c] == 0 && !visit[r][c]) {
                    q.offer(intArrayOf(r, c))
                    visit[r][c] = true
                }
            }
        }
        return isClosed
    }
}

/**
 * Approach 2: Depth First Search
 */
class NumberOfClosedIslandsDFS : NumberOfClosedIslands {
    override fun closedIsland(grid: Array<IntArray>): Int {
        val m: Int = grid.size
        val n: Int = grid[0].size
        val visit = Array(m) { BooleanArray(n) }
        var count = 0
        for (i in grid.indices) {
            for (j in 0 until grid[0].size) {
                if (grid[i][j] == 0 && !visit[i][j] && dfs(i, j, m, n, grid, visit)) {
                    count++
                }
            }
        }
        return count
    }

    private fun dfs(x: Int, y: Int, m: Int, n: Int, grid: Array<IntArray>, visit: Array<BooleanArray>): Boolean {
        if (x < 0 || x >= grid.size || y < 0 || y >= grid[0].size) {
            return false
        }
        if (grid[x][y] == 1 || visit[x][y]) {
            return true
        }
        visit[x][y] = true
        var isClosed = true
        val dirX = intArrayOf(0, 1, 0, -1)
        val dirY = intArrayOf(-1, 0, 1, 0)
        for (i in 0..3) {
            val r = x + dirX[i]
            val c = y + dirY[i]
            if (!dfs(r, c, m, n, grid, visit)) {
                isClosed = false
            }
        }
        return isClosed
    }
}
