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

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1905. Count Sub Islands
 * @see <a href="https://leetcode.com/problems/count-sub-islands/">Source</a>
 */
@Medium("https://leetcode.com/problems/count-sub-islands")
fun interface CountSubIslands {
    operator fun invoke(grid1: Array<IntArray>, grid2: Array<IntArray>): Int
}

/**
 * Approach 1: Breadth-First Search (BFS)
 */
@BFS
class CountSubIslandsBFS : CountSubIslands {
    // Directions in which we can traverse inside the grids.
    private val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(-1, 0),
    )

    override fun invoke(grid1: Array<IntArray>, grid2: Array<IntArray>): Int {
        val totalRows = grid2.size
        val totalCols = grid2[0].size

        val visited = Array(totalRows) { BooleanArray(totalCols) }
        var subIslandCount = 0

        // Iterate on each cell in 'grid2'.
        for (x in 0 until totalRows) {
            for (y in 0 until totalCols) {
                // If the cell at the position (x, y) in 'grid2' is not visited,
                // is a land cell in 'grid2', and the island starting from this
                // cell is a sub-island in 'grid1', then we increment the count
                // of sub-islands.
                if (
                    !visited[x][y] &&
                    isCellLand(x, y, grid2) &&
                    isSubIsland(x, y, grid1, grid2, visited)
                ) {
                    subIslandCount++
                }
            }
        }

        // Return the total count of sub-islands.
        return subIslandCount
    }

    // Helper method to check if the cell at the position (x, y) in the 'grid'
    // is a land cell.
    private fun isCellLand(x: Int, y: Int, grid: Array<IntArray>): Boolean {
        return grid[x][y] == 1
    }

    // Traverse all cells of the island starting at position (x, y) in 'grid2',
    // and check if this island is a sub-island in 'grid1'.
    private fun isSubIsland(
        x: Int,
        y: Int,
        grid1: Array<IntArray>,
        grid2: Array<IntArray>,
        visited: Array<BooleanArray>,
    ): Boolean {
        val totalRows = grid2.size
        val totalCols = grid2[0].size

        var isSubIsland = true
        val pendingCells = ArrayDeque<Pair<Int, Int>>()

        // Push the starting cell in the queue and mark it as visited.
        pendingCells.add(Pair(x, y))
        visited[x][y] = true

        // Traverse all cells using the breadth-first search method.
        while (pendingCells.isNotEmpty()) {
            val (currX, currY) = pendingCells.removeFirst()

            // If the current position cell is not a land cell in 'grid1',
            // then the current island can't be a sub-island.
            if (!isCellLand(currX, currY, grid1)) {
                isSubIsland = false
            }

            for (direction in directions) {
                val nextX = currX + direction[0]
                val nextY = currY + direction[1]

                // If the next cell is inside 'grid2', is never visited and
                // is a land cell, then we traverse to the next cell.
                if (
                    nextX in 0 until totalRows &&
                    nextY in 0 until totalCols &&
                    !visited[nextX][nextY] &&
                    isCellLand(nextX, nextY, grid2)
                ) {
                    // Push the next cell in the queue and mark it as visited.
                    pendingCells.add(Pair(nextX, nextY))
                    visited[nextX][nextY] = true
                }
            }
        }
        return isSubIsland
    }
}

/**
 * Approach 2: Depth-First Search
 */
@DFS
class CountSubIslandsDFS : CountSubIslands {
    override fun invoke(grid1: Array<IntArray>, grid2: Array<IntArray>): Int {
        val totalRows = grid2.size
        val totalCols = grid2[0].size

        val visited = Array(totalRows) { BooleanArray(totalCols) }
        var subIslandCount = 0

        // Iterate over each cell in 'grid2'.
        for (x in 0 until totalRows) {
            for (y in 0 until totalCols) {
                // If the cell at position (x, y) in 'grid2' is not visited,
                // is a land cell in 'grid2', and the island starting from this cell is a sub-island in 'grid1',
                // then increment the count of sub-islands.
                if (!visited[x][y] && isCellLand(x, y, grid2)) {
                    visited[x][y] = true
                    if (isSubIsland(x, y, grid1, grid2, visited)) {
                        subIslandCount++
                    }
                }
            }
        }

        // Return the total count of sub-islands.
        return subIslandCount
    }

    // Directions in which we can traverse inside the grids.
    private val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(-1, 0),
    )

    // Helper method to check if the cell at the position (x, y) in the 'grid'
    // is a land cell.
    private fun isCellLand(x: Int, y: Int, grid: Array<IntArray>): Boolean {
        return grid[x][y] == 1
    }

    // Traverse all cells of the island starting at position (x, y) in 'grid2',
    // and check if this island is a sub-island in 'grid1'.
    private fun isSubIsland(
        x: Int,
        y: Int,
        grid1: Array<IntArray>,
        grid2: Array<IntArray>,
        visited: Array<BooleanArray>,
    ): Boolean {
        val totalRows = grid2.size
        val totalCols = grid2[0].size

        // Traverse all cells using the depth-first search method.
        var isSubIsland = true

        // If the current cell is not a land cell in 'grid1', then the current island can't be a sub-island.
        if (!isCellLand(x, y, grid1)) {
            isSubIsland = false
        }

        // Traverse all adjacent cells.
        for (direction in directions) {
            val nextX = x + direction[0]
            val nextY = y + direction[1]

            // If the next cell is inside 'grid2', is not visited, and is a land cell,
            // then we traverse to the next cell.
            if (
                nextX in 0 until totalRows &&
                nextY in 0 until totalCols &&
                !visited[nextX][nextY] &&
                isCellLand(nextX, nextY, grid2)
            ) {
                // Mark the next cell as visited.
                visited[nextX][nextY] = true

                val nextCellIsPartOfSubIsland = isSubIsland(nextX, nextY, grid1, grid2, visited)
                isSubIsland = isSubIsland && nextCellIsPartOfSubIsland
            }
        }
        return isSubIsland
    }
}

class CountSubIslandsUnionFind : CountSubIslands {
    override fun invoke(grid1: Array<IntArray>, grid2: Array<IntArray>): Int {
        val rowCount = grid2.size
        val colCount = grid2[0].size
        val unionFind = UnionFind(rowCount * colCount)

        // Traverse each land cell in 'grid2'.
        for (row in 0 until rowCount) {
            for (col in 0 until colCount) {
                if (isLandCell(row, col, grid2)) {
                    // Union adjacent land cells with the current land cell.
                    for (direction in directions) {
                        val nextRow = row + direction[0]
                        val nextCol = col + direction[1]
                        if (
                            nextRow in 0 until rowCount &&
                            nextCol in 0 until colCount &&
                            isLandCell(nextRow, nextCol, grid2)
                        ) {
                            unionFind.union(
                                toIndex(row, col, colCount),
                                toIndex(nextRow, nextCol, colCount),
                            )
                        }
                    }
                }
            }
        }

        // Mark roots as not sub-islands if the corresponding land cell is not present in 'grid1'.
        val isSubIsland = BooleanArray(rowCount * colCount) { true }
        for (row in 0 until rowCount) {
            for (col in 0 until colCount) {
                if (isLandCell(row, col, grid2) && !isLandCell(row, col, grid1)) {
                    val root = unionFind.find(toIndex(row, col, colCount))
                    isSubIsland[root] = false
                }
            }
        }

        // Count all the sub-islands.
        var subIslandCount = 0
        for (row in 0 until rowCount) {
            for (col in 0 until colCount) {
                if (isLandCell(row, col, grid2)) {
                    val root = unionFind.find(toIndex(row, col, colCount))
                    if (isSubIsland[root]) {
                        subIslandCount++
                        // Avoid counting the same island multiple times.
                        isSubIsland[root] = false
                    }
                }
            }
        }

        return subIslandCount
    }

    // Directions in which we can traverse inside the grids.
    private val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(-1, 0),
    )

    // Helper method to check if the cell at the position (x, y) in the 'grid' is a land cell.
    private fun isLandCell(row: Int, col: Int, grid: Array<IntArray>): Boolean {
        return grid[row][col] == 1
    }

    // Union-Find class.
    private class UnionFind(n: Int) {
        val parent = IntArray(n) { it }
        val rank = IntArray(n) { 0 }

        // Find the root of element 'u' using the path-compression technique.
        fun find(u: Int): Int {
            if (parent[u] != u) {
                parent[u] = find(parent[u])
            }
            return parent[u]
        }

        // Union two components of elements 'u' and 'v' respectively based on their ranks.
        fun union(u: Int, v: Int) {
            val rootU = find(u)
            val rootV = find(v)
            if (rootU != rootV) {
                when {
                    rank[rootU] > rank[rootV] -> parent[rootV] = rootU
                    rank[rootU] < rank[rootV] -> parent[rootU] = rootV
                    else -> {
                        parent[rootV] = rootU
                        rank[rootU]++
                    }
                }
            }
        }
    }

    // Helper method to convert (row, col) position to a 1-dimensional index.
    private fun toIndex(row: Int, col: Int, colCount: Int): Int {
        return row * colCount + col
    }
}
