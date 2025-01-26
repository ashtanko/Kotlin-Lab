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
 * 1992. Find All Groups of Farmland
 * @see <a href="https://leetcode.com/problems/find-all-groups-of-farmland/">Source</a>
 */
fun interface FindAllGroupsOfFarmland {
    operator fun invoke(land: Array<IntArray>): Array<IntArray>
}

// Returns true if the coordinate is within the boundary of the matrix.
private fun isWithinFarm(rowIndex: Int, columnIndex: Int, totalRows: Int, totalColumns: Int): Boolean {
    return rowIndex in 0 until totalRows && columnIndex in 0 until totalColumns
}

class FindAllGroupsOfFarmlandDFS : FindAllGroupsOfFarmland {

    // The four directions in which traversal will be done.
    private val directions = arrayOf(intArrayOf(-1, 0), intArrayOf(0, -1), intArrayOf(0, 1), intArrayOf(1, 0))

    // Global variables with 0 value initially.
    private var maxRow = 0
    private var maxColumn = 0

    override fun invoke(land: Array<IntArray>): Array<IntArray> {
        val visited = Array(land.size) { BooleanArray(land[0].size) }
        val groups = mutableListOf<IntArray>()

        for (startRow in land.indices) {
            for (startColumn in land[0].indices) {
                if (land[startRow][startColumn] == 1 && !visited[startRow][startColumn]) {
                    maxRow = 0
                    maxColumn = 0

                    dfs(land, visited, startRow, startColumn)

                    val group = intArrayOf(startRow, startColumn, maxRow, maxColumn)
                    groups.add(group)
                }
            }
        }

        return groups.toTypedArray()
    }

    private fun dfs(land: Array<IntArray>, visited: Array<BooleanArray>, currentRow: Int, currentColumn: Int) {
        visited[currentRow][currentColumn] = true
        // Maximum currentRow and currentColumn for the bottom right cell.
        maxRow = maxOf(maxRow, currentRow)
        maxColumn = maxOf(maxColumn, currentColumn)

        for (direction in directions) {
            // Neighbor cell coordinates.
            val nextRow = currentRow + direction[0]
            val nextColumn = currentColumn + direction[1]

            // If the neighbor is within the matrix and is a farmland cell and is not visited yet.
            if (isWithinFarm(
                    nextRow,
                    nextColumn,
                    land.size,
                    land[0].size,
                ) && !visited[nextRow][nextColumn] && land[nextRow][nextColumn] == 1
            ) {
                dfs(land, visited, nextRow, nextColumn)
            }
        }
    }
}

class FindAllGroupsOfFarmlandBFS : FindAllGroupsOfFarmland {
    // The four directions in which traversal will be done.
    private val directions = arrayOf(intArrayOf(-1, 0), intArrayOf(0, -1), intArrayOf(0, 1), intArrayOf(1, 0))

    override fun invoke(land: Array<IntArray>): Array<IntArray> {
        val visited = Array(land.size) { BooleanArray(land[0].size) }
        val groups = mutableListOf<IntArray>()

        for (startRow in land.indices) {
            for (startColumn in land[0].indices) {
                if (land[startRow][startColumn] == 1 && !visited[startRow][startColumn]) {
                    val queue: Queue<Pair<Int, Int>> = LinkedList()

                    queue.add(Pair(startRow, startColumn))
                    visited[startRow][startColumn] = true

                    val last = bfs(queue, land, visited)

                    val group = intArrayOf(startRow, startColumn, last.first, last.second)
                    groups.add(group)
                }
            }
        }

        return groups.toTypedArray()
    }

    private fun bfs(queue: Queue<Pair<Int, Int>>, land: Array<IntArray>, visited: Array<BooleanArray>): Pair<Int, Int> {
        var current: Pair<Int, Int> = Pair(0, 0)

        while (queue.isNotEmpty()) {
            current = queue.remove()

            val currentRow = current.first
            val currentColumn = current.second

            for (direction in directions) {
                // Neighbor cell coordinates.
                val nextRow = currentRow + direction[0]
                val nextColumn = currentColumn + direction[1]

                // If the neighbor is within the matrix and is a farmland cell and not visited yet.
                if (isWithinFarm(
                        nextRow,
                        nextColumn,
                        land.size,
                        land[0].size,
                    ) && !visited[nextRow][nextColumn] && land[nextRow][nextColumn] == 1
                ) {
                    visited[nextRow][nextColumn] = true
                    queue.add(Pair(nextRow, nextColumn))
                }
            }
        }

        return current
    }
}
