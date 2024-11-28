/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Hard
import java.util.LinkedList
import java.util.PriorityQueue

/**
 * 2290. Minimum Obstacle Removal to Reach Corner
 * @see <a href="https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/">Source</a>
 */
@Hard("https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/")
fun interface MinObstacles {
    operator fun invoke(grid: Array<IntArray>): Int
}

private fun Array<IntArray>.isValid(row: Int, col: Int): Boolean {
    return row in indices && col in first().indices
}

class MinObstaclesDijkstra : MinObstacles {

    private val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(1, 0),
        intArrayOf(-1, 0),
    )

    override fun invoke(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        val minObstacles = Array(m) { IntArray(n) { Int.MAX_VALUE } }

        // Start from the top-left corner, accounting for its obstacle value
        minObstacles[0][0] = grid[0][0]

        val pq = PriorityQueue(compareBy<IntArray> { it[0] })

        // Add the starting cell to the priority queue
        pq.add(intArrayOf(minObstacles[0][0], 0, 0))

        while (pq.isNotEmpty()) {
            // Process the cell with the fewest obstacles removed so far
            val (obstacles, row, col) = pq.poll()

            // If we've reached the bottom-right corner, return the result
            if (row == m - 1 && col == n - 1) {
                return obstacles
            }

            // Explore all four possible directions from the current cell
            for (dir in directions) {
                val newRow = row + dir[0]
                val newCol = col + dir[1]

                if (grid.isValid(newRow, newCol)) {
                    // Calculate the obstacles removed if moving to the new cell
                    val newObstacles = obstacles + grid[newRow][newCol]

                    // Update if we've found a path with fewer obstacles to the new cell
                    if (newObstacles < minObstacles[newRow][newCol]) {
                        minObstacles[newRow][newCol] = newObstacles
                        pq.add(intArrayOf(newObstacles, newRow, newCol))
                    }
                }
            }
        }

        return minObstacles[m - 1][n - 1]
    }
}

class MinObstaclesBFS : MinObstacles {
    private val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(1, 0),
        intArrayOf(-1, 0),
    )

    override fun invoke(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        // Distance matrix to store the minimum obstacles removed to reach each cell
        val minObstacles = Array(m) { IntArray(n) { Int.MAX_VALUE } }

        minObstacles[0][0] = 0

        val deque = LinkedList<IntArray>()
        deque.add(intArrayOf(0, 0, 0)) // {obstacles, row, col}

        while (deque.isNotEmpty()) {
            val (obstacles, row, col) = deque.poll()

            // Explore all four possible directions from the current cell
            for (dir in directions) {
                val newRow = row + dir[0]
                val newCol = col + dir[1]

                if (grid.isValid(newRow, newCol) && minObstacles[newRow][newCol] == Int.MAX_VALUE) {
                    if (grid[newRow][newCol] == 1) {
                        // If it's an obstacle, add 1 to obstacles and push to the back
                        minObstacles[newRow][newCol] = obstacles + 1
                        deque.addLast(intArrayOf(obstacles + 1, newRow, newCol))
                    } else {
                        // If it's an empty cell, keep the obstacle count and push to the front
                        minObstacles[newRow][newCol] = obstacles
                        deque.addFirst(intArrayOf(obstacles, newRow, newCol))
                    }
                }
            }
        }

        return minObstacles[m - 1][n - 1]
    }
}
