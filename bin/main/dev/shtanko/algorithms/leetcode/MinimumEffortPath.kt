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

import java.util.PriorityQueue
import kotlin.math.abs

/**
 * 1631. Path With Minimum Effort
 * @see <a href="https://leetcode.com/problems/path-with-minimum-effort">Source</a>
 */
fun interface MinimumEffortPath {
    operator fun invoke(heights: Array<IntArray>): Int
}

/**
 * Solution 1: Dijikstra
 */
class MinimumEffortPathDijikstra : MinimumEffortPath {

    private val dir = intArrayOf(0, 1, 0, -1, 0)

    override fun invoke(heights: Array<IntArray>): Int {
        val m = heights.size
        val n = heights[0].size
        val dist = Array(m) { IntArray(n) { Int.MAX_VALUE } }

        val minHeap = PriorityQueue(compareBy<IntArray> { it[0] })
        minHeap.offer(intArrayOf(0, 0, 0)) // distance, row, col
        dist[0][0] = 0

        while (minHeap.isNotEmpty()) {
            val top = minHeap.poll()
            val d = top[0]
            val r = top[1]
            val c = top[2]
            if (d > dist[r][c]) continue // this is an outdated version -> skip it
            if (r == m - 1 && c == n - 1) return d // Reach to bottom right
            for (i in 0 until 4) {
                val nr = r + dir[i]
                val nc = c + dir[i + 1]
                if (nr in 0 until m && nc >= 0 && nc < n) {
                    val newDist = maxOf(d, abs(heights[nr][nc] - heights[r][c]))
                    if (dist[nr][nc] > newDist) {
                        dist[nr][nc] = newDist
                        minHeap.offer(intArrayOf(dist[nr][nc], nr, nc))
                    }
                }
            }
        }
        return 0 // Unreachable code, Kotlin does not require an explicit return here.
    }
}

/**
 * Solution 2: Binary Search + DFS
 */
class MinimumEffortPathDFS : MinimumEffortPath {
    private val dir = intArrayOf(0, 1, 0, -1, 0)

    companion object {
        private const val UPPER_BOUND = 1000000
    }

    override fun invoke(heights: Array<IntArray>): Int {
        val m = heights.size
        val n = heights[0].size

        fun dfs(r: Int, c: Int, visited: Array<BooleanArray>, threshold: Int): Boolean {
            if (r == m - 1 && c == n - 1) return true // Reach destination
            visited[r][c] = true
            for (i in 0 until 4) {
                val nr = r + dir[i]
                val nc = c + dir[i + 1]
                if (nr < 0 || nr == m || nc < 0 || nc == n || visited[nr][nc]) continue
                if (abs(heights[nr][nc] - heights[r][c]) <= threshold && dfs(nr, nc, visited, threshold)) {
                    return true
                }
            }
            return false
        }

        fun canReachDestination(threshold: Int): Boolean {
            val visited = Array(m) { BooleanArray(n) }
            return dfs(0, 0, visited, threshold)
        }

        var left = 0
        var ans = 0
        var right = UPPER_BOUND // Set an upper bound
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (canReachDestination(mid)) {
                right = mid - 1 // Try to find a better result on the left side
                ans = mid
            } else {
                left = mid + 1
            }
        }
        return ans
    }
}
