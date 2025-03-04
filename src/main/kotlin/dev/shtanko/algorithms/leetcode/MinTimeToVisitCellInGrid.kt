/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.Dijkstra
import dev.shtanko.algorithms.annotations.level.Hard
import java.util.PriorityQueue

/**
 * 2577. Minimum Time to Visit a Cell In a Grid
 * @see <a href="https://leetcode.com/problems/minimum-time-to-visit-all-points/">Source</a>
 */
@Hard("https://leetcode.com/problems/minimum-time-to-visit-all-points/")
fun interface MinTimeToVisitCellInGrid {
    operator fun invoke(grid: Array<IntArray>): Int
}

@Dijkstra
class MinTimeToVisitCellInGridDijkstra : MinTimeToVisitCellInGrid {
    override fun invoke(grid: Array<IntArray>): Int {
        // If both initial moves require more than 1 second, impossible to proceed
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1
        }

        val rows = grid.size
        val cols = grid[0].size
        // Possible movements: down, up, right, left
        val directions = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1),
        )
        val visited = Array(rows) { BooleanArray(cols) }

        // Priority queue stores Triple(time, row, col)
        // Ordered by minimum time to reach each cell
        val pq = PriorityQueue<Triple<Int, Int, Int>> { a, b -> a.first.compareTo(b.first) }
        pq.add(Triple(grid[0][0], 0, 0))

        while (pq.isNotEmpty()) {
            val (time, row, col) = pq.poll()

            // Check if reached target
            if (row == rows - 1 && col == cols - 1) {
                return time
            }

            // Skip if cell already visited
            if (visited[row][col]) {
                continue
            }
            visited[row][col] = true

            // Try all four directions
            for (d in directions) {
                val nextRow = row + d[0]
                val nextCol = col + d[1]
                if (!isValid(visited, nextRow, nextCol)) {
                    continue
                }

                // Calculate the wait time needed to move to next cell
                val waitTime = if ((grid[nextRow][nextCol] - time) % 2 == 0) 1 else 0
                val nextTime = maxOf(grid[nextRow][nextCol] + waitTime, time + 1)
                pq.add(Triple(nextTime, nextRow, nextCol))
            }
        }
        return -1
    }

    private fun isValid(visited: Array<BooleanArray>, row: Int, col: Int): Boolean {
        return row in visited.indices && col in visited[0].indices && !visited[row][col]
    }
}
