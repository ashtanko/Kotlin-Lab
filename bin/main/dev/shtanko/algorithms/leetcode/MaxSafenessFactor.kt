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

import java.util.LinkedList
import java.util.Queue

/**
 * 2812. Find the Safest Path in a Grid
 * @see <a href="https://leetcode.com/problems/find-the-safest-path-in-a-grid/">Source</a>
 */
fun interface MaxSafenessFactor {
    operator fun invoke(grid: List<List<Int>>): Int
}

class MaxSafenessFactorBS : MaxSafenessFactor {
    // Directions for moving to neighboring cells: right, left, down, up
    private val directions = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))

    override fun invoke(grid: List<List<Int>>): Int {
        val size = grid.size
        val safetyGrid = Array(size) { IntArray(size) }
        val thiefPositions: Queue<IntArray> = LinkedList()

        // Convert the grid to a 2D array and mark thief positions
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (grid[i][j] == 1) {
                    thiefPositions.add(intArrayOf(i, j))
                    safetyGrid[i][j] = 0 // Mark thief cell with 0
                } else {
                    safetyGrid[i][j] = -1 // Mark empty cell with -1
                }
            }
        }

        // Calculate safety factor for each cell using BFS
        while (thiefPositions.isNotEmpty()) {
            val currSize = thiefPositions.size
            repeat(currSize) {
                val current = thiefPositions.poll()
                // Explore neighboring cells
                for (dir in directions) {
                    val nextI = current[0] + dir[0]
                    val nextJ = current[1] + dir[1]
                    val currentSafety = safetyGrid[current[0]][current[1]]
                    // Check if the neighboring cell is valid and unvisited
                    if (isValidCell(nextI, nextJ, size) && safetyGrid[nextI][nextJ] == -1) {
                        // Update safety factor and add to the queue
                        safetyGrid[nextI][nextJ] = currentSafety + 1
                        thiefPositions.add(intArrayOf(nextI, nextJ))
                    }
                }
            }
        }

        // Binary search for maximum safety factor
        var start = 0
        var end = 0
        var maxSafety = -1
        for (i in 0 until size) {
            for (j in 0 until size) {
                // Set end as the maximum safety factor possible
                end = end.coerceAtLeast(safetyGrid[i][j])
            }
        }

        while (start <= end) {
            val mid = start + (end - start) / 2
            if (isValidSafetyPath(safetyGrid, mid, size)) {
                // Store valid safety and search for larger ones
                maxSafety = mid
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        return maxSafety
    }

    // Check if a path exists with given minimum safety value
    private fun isValidSafetyPath(grid: Array<IntArray>, minSafety: Int, size: Int): Boolean {
        // Check if the source and destination cells satisfy minimum safety
        if (grid[0][0] < minSafety || grid[size - 1][size - 1] < minSafety) {
            return false
        }

        val traversalQueue: Queue<IntArray> = LinkedList()
        traversalQueue.add(intArrayOf(0, 0))
        val visited = Array(size) { BooleanArray(size) }
        visited[0][0] = true

        // Breadth-first search to find a valid path
        while (traversalQueue.isNotEmpty()) {
            val curr = traversalQueue.poll()
            if (curr[0] == size - 1 && curr[1] == size - 1) {
                return true // Valid path found
            }
            // Explore neighboring cells
            for (dir in directions) {
                val nextI = curr[0] + dir[0]
                val nextJ = curr[1] + dir[1]
                // Check if the neighboring cell is valid, unvisited and satisfies minimum safety
                if (isValidCell(nextI, nextJ, size) && !visited[nextI][nextJ] && grid[nextI][nextJ] >= minSafety) {
                    visited[nextI][nextJ] = true
                    traversalQueue.add(intArrayOf(nextI, nextJ))
                }
            }
        }
        return false // No valid path found
    }

    // Check if a given cell lies within the grid
    private fun isValidCell(i: Int, j: Int, size: Int): Boolean {
        return i in 0 until size && j in 0 until size
    }
}
