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

/**
 * 1091. Shortest Path in Binary Matrix
 * @see <a href="https://leetcode.com/problems/shortest-path-in-binary-matrix/">Source</a>
 */
fun interface ShortestPathInBinaryMatrix {
    operator fun invoke(grid: Array<IntArray>): Int
}

class ShortestPathInBinaryMatrixBFS : ShortestPathInBinaryMatrix {

    override operator fun invoke(grid: Array<IntArray>): Int {
        val queue: java.util.ArrayDeque<Pair<Int, Int>> = java.util.ArrayDeque()
        queue.addLast(Pair(0, 0))

        val n: Int = grid.size

        val done: Array<IntArray> = Array(n) { _ -> IntArray(n) { _ -> -1 } }

        if (grid[0][0] == 1) return -1
        done[0][0] = 1

        val x: IntArray = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val y: IntArray = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

        while (queue.isNotEmpty()) {
            val cur: Pair<Int, Int> = queue.pollFirst()
            if (cur.first == n - 1 && cur.second == n - 1) return done[cur.first][cur.second]
            for (i: Int in 0..7) {
                val nx: Int = cur.first + x[i]
                val ny: Int = cur.second + y[i]
                val local = nx < 0 || ny < 0 || nx >= n || ny >= n
                if (local || grid[nx][ny] == 1 || done[nx][ny] != -1) {
                    continue
                }
                done[nx][ny] = done[cur.first][cur.second] + 1
                queue.addLast(Pair(nx, ny))
            }
        }

        return -1
    }
}
