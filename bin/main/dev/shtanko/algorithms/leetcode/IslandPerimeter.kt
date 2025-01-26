/*
 * Copyright 2020 Oleksii Shtanko
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

private const val PERIMETER_PER_ISLAND = 4
private const val SHARED_SIDES_PER_NEIGHBOUR = 2

/**
 * Island Perimeter
 *
 * [Problem]
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents
 * water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
 * and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
 * Determine the perimeter of the island.
 *
 * [Example]
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 *
 * [Constraints]
 * - row == grid.length
 * - col == grid[i].length
 * - 1 <= row, col <= 100
 * - grid[i][j] is 0 or 1.
 */
fun interface IslandPerimeter {
    operator fun invoke(grid: Array<IntArray>): Int
}

val islandPerimeterApproach1 = IslandPerimeter { grid ->
    var islands = 0
    var neighbours = 0
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (grid[i][j] == 1) {
                islands++
                // count down neighbours
                if (i < grid.size - 1 && grid[i + 1][j] == 1) {
                    neighbours++
                }
                // count right neighbours
                if (j < grid[i].size - 1 && grid[i][j + 1] == 1) {
                    neighbours++
                }
            }
        }
    }
    return@IslandPerimeter islands * PERIMETER_PER_ISLAND - neighbours * SHARED_SIDES_PER_NEIGHBOUR
}

val calculateIslandPerimeter = IslandPerimeter { grid ->
    if (grid.isEmpty()) return@IslandPerimeter 0
    val totalRows = grid.size
    val totalColumns = grid[0].size
    var perimeter = 0
    for (currentRow in 0 until totalRows) {
        for (currentColumn in 0 until totalColumns) {
            if (grid[currentRow][currentColumn] == 1) {
                perimeter += 4
                if (currentRow > 0 && grid[currentRow - 1][currentColumn] == 1) { // Check up
                    perimeter -= 2
                }
                if (currentColumn > 0 && grid[currentRow][currentColumn - 1] == 1) { // Check left
                    perimeter -= 2
                }
            }
        }
    }
    return@IslandPerimeter perimeter
}
