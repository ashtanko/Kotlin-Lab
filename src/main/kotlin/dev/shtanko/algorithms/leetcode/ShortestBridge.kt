/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.DP
import java.util.LinkedList
import java.util.Queue

/**
 * 934. Shortest Bridge
 * @see <a href="https://leetcode.com/problems/shortest-bridge/">Source</a>
 */
fun interface ShortestBridge {
    operator fun invoke(grid: Array<IntArray>): Int
}

@DP
class ShortestBridgeDP : ShortestBridge {
    override operator fun invoke(grid: Array<IntArray>): Int {
        val queue: Queue<IntArray> = LinkedList()
        var flag = false
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) {
                    dfs(i, j, queue, grid)
                    flag = true
                    break
                }
            }
            if (flag) break
        }

        return bfs(grid, queue)
    }

    private fun bfs(
        grid: Array<IntArray>,
        queue: Queue<IntArray>,
    ): Int {
        var step = 0
        val dirs = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))
        while (queue.isNotEmpty()) {
            val size: Int = queue.size
            for (i in 0 until size) {
                val point: IntArray = queue.poll()
                for (dir in dirs) {
                    val x = dir[0] + point[0]
                    val y = dir[1] + point[1]
                    if (x >= 0 && y >= 0 && x < grid.size && y < grid[0].size && grid[x][y] != -1) {
                        if (grid[x][y] == 1) return step
                        queue.offer(intArrayOf(x, y))
                        grid[x][y] = -1
                    }
                }
            }
            step++
        }
        return -1
    }

    private fun dfs(x: Int, y: Int, queue: Queue<IntArray>, arr: Array<IntArray>) {
        if (x < 0 || y < 0 || x == arr.size || y == arr[0].size || arr[x][y] != 1) {
            return
        }
        queue.offer(intArrayOf(x, y))
        arr[x][y] = -1
        dfs(x + 1, y, queue, arr)
        dfs(x - 1, y, queue, arr)
        dfs(x, y + 1, queue, arr)
        dfs(x, y - 1, queue, arr)
    }
}

@DFS
class ShortestBridgeDFS : ShortestBridge {
    override operator fun invoke(grid: Array<IntArray>): Int {
        return shortestBridge(grid)
    }

    private fun shortestBridge(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val visited = Array(m) { BooleanArray(n) }
        val dirs = arrayOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))
        val q: Queue<IntArray> = LinkedList()
        var found = false
        // 1. dfs to find an island, mark it in `visited`
        for (i in 0 until m) {
            if (found) {
                break
            }
            for (j in 0 until n) {
                if (grid[i][j] == 1) {
                    dfs(grid, visited, q, i, j, dirs)
                    found = true
                    break
                }
            }
        }
        // 2. bfs to expand this island
        return bfs(grid, visited, q, n, m, dirs)
    }

    private fun bfs(
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        q: Queue<IntArray>,
        n: Int,
        m: Int,
        dirs: Array<IntArray>,
    ): Int {
        var step = 0
        while (q.isNotEmpty()) {
            var size = q.size
            while (size-- > 0) {
                val cur = q.poll()
                for (dir in dirs) {
                    val i = cur[0] + dir[0]
                    val j = cur[1] + dir[1]
                    if (i >= 0 && j >= 0 && i < m && j < n && !visited[i][j]) {
                        if (grid[i][j] == 1) {
                            return step
                        }
                        q.offer(intArrayOf(i, j))
                        visited[i][j] = true
                    }
                }
            }
            step++
        }
        return -1
    }

    private fun dfs(
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        q: Queue<IntArray>,
        i: Int,
        j: Int,
        dirs: Array<IntArray>,
    ) {
        val left = i < 0 || j < 0 || i >= grid.size || j >= grid[0].size
        if (left || visited[i][j] || grid[i][j] == 0) {
            return
        }
        visited[i][j] = true
        q.offer(intArrayOf(i, j))
        for (dir in dirs) {
            dfs(grid, visited, q, i + dir[0], j + dir[1], dirs)
        }
    }
}
