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
 * 1706. Where Will the Ball Fall
 * @see <a href="https://leetcode.com/problems/where-will-the-ball-fall/">Source</a>
 */
fun interface FindBall {
    operator fun invoke(grid: Array<IntArray>): IntArray
}

/**
 * Approach 1: Depth First Search (DFS)
 */
class FindBallDFS : FindBall {
    override operator fun invoke(grid: Array<IntArray>): IntArray {
        if (grid.isEmpty()) return intArrayOf()
        if (grid.first().isEmpty()) return intArrayOf()
        val result = IntArray(grid.first().size)
        for (i in 0 until grid.first().size) {
            result[i] = findBallDropColumn(0, i, grid)
        }
        return result
    }

    private fun findBallDropColumn(row: Int, col: Int, grid: Array<IntArray>): Int {
        // base case; ball reached the last row
        if (row == grid.size) return col
        val nextColumn = col + grid[row][col]
        return if (nextColumn < 0 || nextColumn > grid[0].size - 1 || grid[row][col] != grid[row][nextColumn]
        ) {
            -1
        } else {
            findBallDropColumn(row + 1, nextColumn, grid)
        }
    }
}

/**
 * Approach 2: Dynamic Programming Approach
 */
class FindBallDP : FindBall {
    override operator fun invoke(grid: Array<IntArray>): IntArray {
        if (grid.isEmpty()) return intArrayOf()
        if (grid.first().isEmpty()) return intArrayOf()
        val result = IntArray(grid[0].size)
        val memo = Array<Array<Int>>(grid.size + 1) {
            Array(grid[0].size) { 0 }
        }

        for (row in grid.size downTo 0) {
            for (col in 0 until grid[0].size) {
                if (row == grid.size) {
                    memo[row][col] = col
                    continue
                }
                val nextColumn = col + grid[row][col]
                if (nextColumn < 0 || nextColumn > grid[0].size - 1 || grid[row][col] != grid[row][nextColumn]) {
                    memo[row][col] = -1
                } else {
                    memo[row][col] = memo[row + 1][nextColumn]
                }
                if (row == 0) {
                    result[col] = memo[row][col]
                }
            }
        }
        return result
    }
}

/**
 * Approach 3: Iterative Approach, Simulation
 */
class FindBallIterative : FindBall {
    override operator fun invoke(grid: Array<IntArray>): IntArray {
        if (grid.isEmpty()) return intArrayOf()
        if (grid.first().isEmpty()) return intArrayOf()
        val result = IntArray(grid[0].size)

        for (col in 0 until grid[0].size) {
            var currentCol = col
            for (row in grid.indices) {
                val nextColumn = currentCol + grid[row][currentCol]
                if (nextColumn < 0 || nextColumn > grid[0].size - 1 || grid[row][currentCol] != grid[row][nextColumn]) {
                    result[col] = -1
                    break
                }
                result[col] = nextColumn
                currentCol = nextColumn
            }
        }
        return result
    }
}
