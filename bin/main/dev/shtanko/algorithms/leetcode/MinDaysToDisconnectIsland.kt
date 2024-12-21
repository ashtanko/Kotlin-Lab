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

import dev.shtanko.algorithms.annotations.BruteForce
import dev.shtanko.algorithms.annotations.TarjansAlgorithm

/**
 * 1568. Minimum Number of Days to Disconnect Island
 * @see <a href="https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island">Source</a>
 */
fun interface MinDaysToDisconnectIsland {
    operator fun invoke(grid: Array<IntArray>): Int
}

/**
 * Brute force solution
 */
@BruteForce
class MinDaysToDisconnectIslandBF : MinDaysToDisconnectIsland {

    private val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(1, 0),
        intArrayOf(-1, 0),
    )

    override fun invoke(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size

        // Count initial islands
        val initialIslandCount = countIslands(grid)

        // Already disconnected or no land
        if (initialIslandCount != 1) {
            return 0
        }

        // Try removing each land cell
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (grid[row][col] == 0) continue // Skip water

                // Temporarily change to water
                grid[row][col] = 0
                val newIslandCount = countIslands(grid)

                // Check if disconnected
                if (newIslandCount != 1) return 1

                // Revert change
                grid[row][col] = 1
            }
        }

        return 2
    }

    private fun countIslands(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        val visited = Array(rows) { BooleanArray(cols) }
        var islandCount = 0

        // Iterate through all cells
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                // Found new island
                if (!visited[row][col] && grid[row][col] == 1) {
                    exploreIsland(grid, row, col, visited)
                    islandCount++
                }
            }
        }
        return islandCount
    }

    // Helper method to explore all cells of an island
    private fun exploreIsland(
        grid: Array<IntArray>,
        row: Int,
        col: Int,
        visited: Array<BooleanArray>,
    ) {
        visited[row][col] = true

        // Check all adjacent cells
        for (direction in directions) {
            val newRow = row + direction[0]
            val newCol = col + direction[1]
            // Explore if valid land cell
            if (isValidLandCell(grid, newRow, newCol, visited)) {
                exploreIsland(grid, newRow, newCol, visited)
            }
        }
    }

    private fun isValidLandCell(
        grid: Array<IntArray>,
        row: Int,
        col: Int,
        visited: Array<BooleanArray>,
    ): Boolean {
        val rows = grid.size
        val cols = grid[0].size
        // Check bounds, land, and not visited
        return (
            row in 0 until rows &&
                col in 0 until cols &&
                grid[row][col] == 1 &&
                !visited[row][col]
            )
    }
}

/**
 * Approach 2: Tarjan's Algorithm
 */
@TarjansAlgorithm
class MinDaysToDisconnectIslandTarjansAlgorithm : MinDaysToDisconnectIsland {

    private val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(-1, 0),
    )

    override fun invoke(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        val apInfo = ArticulationPointInfo(false, 0)
        var landCells = 0
        var islandCount = 0

        // Arrays to store information for each cell
        val discoveryTime = Array(rows) { IntArray(cols) { -1 } } // Time when a cell is first discovered
        val lowestReachable = Array(rows) { IntArray(cols) { -1 } }
        val parentCell = Array(rows) { IntArray(cols) { -1 } } // Parent of each cell in DFS tree

        // Traverse the grid to find islands and articulation points
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (grid[i][j] == 1) {
                    landCells++
                    if (discoveryTime[i][j] == -1) { // If not yet visited
                        // Start DFS for a new island
                        findArticulationPoints(
                            grid,
                            i,
                            j,
                            discoveryTime,
                            lowestReachable,
                            parentCell,
                            apInfo,
                        )
                        islandCount++
                    }
                }
            }
        }

        // Determine the minimum number of days to disconnect the grid
        return when {
            islandCount == 0 || islandCount >= 2 -> 0 // Already disconnected or no land
            landCells == 1 -> 1 // Only one land cell
            apInfo.hasArticulationPoint -> 1 // An articulation point exists
            else -> 2 // Need to remove any two land cells
        }
    }

    private fun findArticulationPoints(
        grid: Array<IntArray>,
        row: Int,
        col: Int,
        discoveryTime: Array<IntArray>,
        lowestReachable: Array<IntArray>,
        parentCell: Array<IntArray>,
        apInfo: ArticulationPointInfo,
    ) {
        val cols = grid[0].size
        discoveryTime[row][col] = apInfo.time
        apInfo.time++
        lowestReachable[row][col] = discoveryTime[row][col]
        var children = 0

        // Explore all adjacent cells
        for (direction in directions) {
            val newRow = row + direction[0]
            val newCol = col + direction[1]
            if (isValidLandCell(grid, newRow, newCol)) {
                if (discoveryTime[newRow][newCol] == -1) {
                    children++
                    parentCell[newRow][newCol] = row * cols + col // Set parent
                    findArticulationPoints(
                        grid,
                        newRow,
                        newCol,
                        discoveryTime,
                        lowestReachable,
                        parentCell,
                        apInfo,
                    )

                    // Update lowest reachable time
                    lowestReachable[row][col] = minOf(
                        lowestReachable[row][col],
                        lowestReachable[newRow][newCol],
                    )

                    // Check for articulation point condition
                    if (
                        lowestReachable[newRow][newCol] >= discoveryTime[row][col] &&
                        parentCell[row][col] != -1
                    ) {
                        apInfo.hasArticulationPoint = true
                    }
                } else if (newRow * cols + newCol != parentCell[row][col]) {
                    // Update lowest reachable time for back edge
                    lowestReachable[row][col] = minOf(
                        lowestReachable[row][col],
                        discoveryTime[newRow][newCol],
                    )
                }
            }
        }

        // Root of DFS tree is an articulation point if it has more than one child
        if (parentCell[row][col] == -1 && children > 1) {
            apInfo.hasArticulationPoint = true
        }
    }

    // Check if the given cell is a valid land cell
    private fun isValidLandCell(grid: Array<IntArray>, row: Int, col: Int): Boolean {
        val rows = grid.size
        val cols = grid[0].size
        return (
            row >= 0 &&
                col >= 0 &&
                row < rows &&
                col < cols &&
                grid[row][col] == 1
            )
    }

    private data class ArticulationPointInfo(
        var hasArticulationPoint: Boolean,
        var time: Int,
    )
}
