/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.extensions.lessThanZero
import java.util.LinkedList
import java.util.Queue

private val DIRECTIONS = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, -1))

fun interface PacificAtlanticWaterFlow {
    operator fun invoke(matrix: Array<IntArray>): List<List<Int>>
}

@DFS
class PacificAtlanticDFS : PacificAtlanticWaterFlow {
    override operator fun invoke(matrix: Array<IntArray>): List<List<Int>> {
        val res: MutableList<MutableList<Int>> = LinkedList()
        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return res
        }
        val n: Int = matrix.size
        val m: Int = matrix[0].size
        val pacific = Array(n) { BooleanArray(m) }
        val atlantic = Array(n) { BooleanArray(m) }
        for (i in 0 until n) {
            dfs(matrix, pacific, Int.MIN_VALUE, i, 0)
            dfs(matrix, atlantic, Int.MIN_VALUE, i, m - 1)
        }
        for (i in 0 until m) {
            dfs(matrix, pacific, Int.MIN_VALUE, 0, i)
            dfs(matrix, atlantic, Int.MIN_VALUE, n - 1, i)
        }
        for (i in 0 until n) for (j in 0 until m) if (pacific[i][j] && atlantic[i][j]) res.add(mutableListOf(i, j))
        return res
    }

    private fun dfs(matrix: Array<IntArray>, visited: Array<BooleanArray>, height: Int, x: Int, y: Int) {
        val n = matrix.size
        val m: Int = matrix[0].size
        val left = x.lessThanZero().or(x >= n).or(y.lessThanZero()).or(y >= m)
        if (left || visited[x][y] || matrix[x][y] < height) return
        visited[x][y] = true
        for (d in DIRECTIONS) {
            dfs(matrix, visited, matrix[x][y], x + d[0], y + d[1])
        }
    }
}

@BFS
class PacificAtlanticBFS : PacificAtlanticWaterFlow {

    override operator fun invoke(matrix: Array<IntArray>): List<List<Int>> {
        val res: MutableList<MutableList<Int>> = LinkedList()
        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return res
        }
        val n = matrix.size
        val m: Int = matrix[0].size
        val pacific = Array(n) { BooleanArray(m) }
        val atlantic = Array(n) { BooleanArray(m) }
        val pQueue: Queue<IntArray> = LinkedList()
        val aQueue: Queue<IntArray> = LinkedList()
        for (i in 0 until n) {
            pQueue.offer(intArrayOf(i, 0))
            aQueue.offer(intArrayOf(i, m - 1))
            pacific[i][0] = true
            atlantic[i][m - 1] = true
        }
        for (i in 0 until m) {
            pQueue.offer(intArrayOf(0, i))
            aQueue.offer(intArrayOf(n - 1, i))
            pacific[0][i] = true
            atlantic[n - 1][i] = true
        }
        bfs(matrix, pQueue, pacific)
        bfs(matrix, aQueue, atlantic)
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (pacific[i][j] && atlantic[i][j]) res.add(mutableListOf(i, j))
            }
        }
        return res
    }

    private fun bfs(matrix: Array<IntArray>, queue: Queue<IntArray>, visited: Array<BooleanArray>) {
        val n = matrix.size
        val m: Int = matrix[0].size
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (d in DIRECTIONS) {
                val x = cur[0] + d[0]
                val y = cur[1] + d[1]
                val start = x.lessThanZero().or(x >= n).or(y.lessThanZero()).or(y >= m)
                if (start || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]) {
                    continue
                }
                visited[x][y] = true
                queue.offer(intArrayOf(x, y))
            }
        }
    }
}
