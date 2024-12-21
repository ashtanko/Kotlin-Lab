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

import java.util.LinkedList
import java.util.Queue

/**
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 * @see <a href="https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/">Source</a>
 */
fun interface ShortestPath {
    operator fun invoke(grid: Array<IntArray>, k: Int): Int
}

class ShortestPathBFS : ShortestPath {

    private val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
    override operator fun invoke(grid: Array<IntArray>, k: Int): Int {
        val n: Int = grid.size
        val m: Int = grid[0].size
        if (n <= 1 && m <= 1) return 0
        val dp = Array(n) {
            Array(m) {
                BooleanArray(
                    k + 1,
                )
            }
        }
        dp[0][0][0] = true
        val ls: LinkedList<IntArray> = LinkedList()

        ls.offer(intArrayOf(0, 0, 0))

        return helper(ls, n, m, dp, grid, k)
    }

    private fun helper(
        ls: LinkedList<IntArray>,
        n: Int,
        m: Int,
        dp: Array<Array<BooleanArray>>,
        grid: Array<IntArray>,
        k: Int,
    ): Int {
        var step = 0
        while (ls.isNotEmpty()) {
            var size: Int = ls.size
            step++
            while (size-- > 0) {
                val start: IntArray = ls.poll()
                val x = start[0]
                val y = start[1]
                val curK = start[2]
                loop1@ for (d in dirs) {
                    val xx = x + d[0]
                    val yy = y + d[1]
                    if (xx == n - 1 && yy == m - 1) return step
                    if (xx < 0 || yy < 0 || xx >= n || yy >= m) continue
                    for (i in 0..curK) {
                        if (dp[xx][yy][i]) continue@loop1
                    }
                    if (grid[xx][yy] == 1 && curK < k) {
                        dp[xx][yy][curK + 1] = true
                        ls.offer(intArrayOf(xx, yy, curK + 1))
                    } else if (grid[xx][yy] == 0) {
                        dp[xx][yy][curK] = true
                        ls.offer(intArrayOf(xx, yy, curK))
                    }
                }
            }
        }

        return -1
    }
}

class ShortestPathBFS2 : ShortestPath {

    private val dirs = intArrayOf(0, 1, 0, -1, 0)

    override operator fun invoke(grid: Array<IntArray>, k: Int): Int {
        val m: Int = grid.size
        val n: Int = grid[0].size
        if (k >= m + n - 2) return m + n - 2 // if we can go by manhattan distance -> let's go

        val visited = Array(m) {
            Array(n) {
                BooleanArray(k + 1)
            }
        }
        val q: Queue<IntArray> = LinkedList()
        q.offer(intArrayOf(0, 0, k, 0)) // r, c, k, dist

        visited[0][0][k] = true

        return helper(grid, q, m, n, visited)
    }

    private fun helper(
        grid: Array<IntArray>,
        q: Queue<IntArray>,
        m: Int,
        n: Int,
        visited: Array<Array<BooleanArray>>,
    ): Int {
        while (q.isNotEmpty()) {
            val top: IntArray = q.poll()
            val r = top[0]
            val c = top[1]
            val curK = top[2]
            val dist = top[3]
            if (r == m - 1 && c == n - 1) return dist // Found the result
            for (i in 0..3) {
                val nr = r + dirs[i]
                val nc = c + dirs[i + 1]
                if (nr < 0 || nr == m || nc < 0 || nc == n) {
                    continue
                }
                val newK = curK - grid[nr][nc]
                if (newK >= 0 && !visited[nr][nc][newK]) {
                    visited[nr][nc][newK] = true
                    q.offer(intArrayOf(nr, nc, newK, dist + 1))
                }
            }
        }
        return -1 // Not found
    }
}
