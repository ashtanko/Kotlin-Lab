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

import java.util.LinkedList
import java.util.Queue

/**
 * 1926. Nearest Exit from Entrance in Maze
 * @see <a href="https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/">Source</a>
 */
fun interface NearestExit {
    operator fun invoke(maze: Array<CharArray>, entrance: IntArray): Int
}

/**
 * Approach 1: Breadth First Search (BFS)
 */
class NearestExitBFS : NearestExit {
    override operator fun invoke(maze: Array<CharArray>, entrance: IntArray): Int {
        val rows: Int = maze.size
        val cols: Int = maze[0].size
        val dirs = arrayOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))

        // Mark the entrance as visited since it's not an exit.
        val startRow = entrance[0]
        val startCol = entrance[1]
        maze[startRow][startCol] = '+'

        // Start BFS from the entrance, and use a queue `queue` to store all
        // the cells to be visited.
        val queue: Queue<IntArray> = LinkedList()
        queue.offer(intArrayOf(startRow, startCol, 0))

        while (queue.isNotEmpty()) {
            val currState: IntArray = queue.poll()
            val currRow = currState[0]
            val currCol = currState[1]
            val currDistance = currState[2]

            // For the current cell, check its four neighbor cells.
            for (dir in dirs) {
                val nextRow = currRow + dir[0]
                val nextCol = currCol + dir[1]

                // If there exists an unvisited empty neighbor:
                if (nextRow in 0 until rows && 0 <= nextCol && nextCol < cols && maze[nextRow][nextCol] == '.') {
                    // If this empty cell is an exit, return distance + 1.
                    if (nextRow == 0 || nextRow == rows - 1 || nextCol == 0 || nextCol == cols - 1) {
                        return currDistance + 1
                    }

                    // Otherwise, add this cell to 'queue' and mark it as visited.
                    maze[nextRow][nextCol] = '+'
                    queue.offer(intArrayOf(nextRow, nextCol, currDistance + 1))
                }
            }
        }

        // If we finish iterating without finding an exit, return -1.
        return -1
    }
}
