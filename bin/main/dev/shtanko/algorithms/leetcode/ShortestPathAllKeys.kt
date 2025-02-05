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

import java.util.LinkedList
import java.util.Queue

/**
 * 864. Shortest Path to Get All Keys
 * @see <a href="https://leetcode.com/problems/shortest-path-to-get-all-keys/">Source</a>
 */
fun interface ShortestPathAllKeys {
    operator fun invoke(grid: Array<String>): Int
}

/**
 * Approach: Breadth-First Search
 */
class ShortestPathAllKeysBFS : ShortestPathAllKeys {
    override operator fun invoke(grid: Array<String>): Int {
        val m: Int = grid.size
        val n: Int = grid[0].length
        val queue: Queue<IntArray> = LinkedList()
        val moves = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, -1))

        // seen['key'] is only for BFS with key state equals 'keys'
        val seen: MutableMap<Int, MutableSet<Pair<Int, Int>>> = HashMap()

        val keySet: MutableSet<Char> = HashSet()
        val lockSet: MutableSet<Char> = HashSet()
        var allKeys = 0
        var startR = -1
        var startC = -1
        for (i in 0 until m) {
            for (j in 0 until n) {
                val cell: Char = grid[i][j]
                if (cell in 'a'..'f') {
                    allKeys += 1 shl cell.code - 'a'.code
                    keySet.add(cell)
                }
                if (cell in 'A'..'F') {
                    lockSet.add(cell)
                }
                if (cell == '@') {
                    startR = i
                    startC = j
                }
            }
        }

        // [row, column, key state, distance]
        queue.offer(intArrayOf(startR, startC, 0, 0))
        seen[0] = HashSet()
        seen[0]?.add(Pair(startR, startC))

        while (queue.isNotEmpty()) {
            val cur: IntArray = queue.poll()
            val curR = cur[0]
            val curC = cur[1]
            val keys = cur[2]
            val dist = cur[3]
            for (move in moves) {
                val newR = curR + move[0]
                val newC = curC + move[1]

                // If this cell (newR, newC) is reachable.
                if (newR in 0 until m && newC >= 0 && newC < n && grid[newR][newC] != '#') {
                    val cell: Char = grid[newR][newC]

                    // If it is a key:
                    if (keySet.contains(cell)) {
                        // If we have collected it before, no need to revisit this cell.
                        if (1 shl cell.code - 'a'.code and keys != 0) {
                            continue
                        }

                        // Otherwise, we can walk to this cell and pick it up.
                        val newKeys = keys or (1 shl cell.code - 'a'.code)

                        // If we collect all keys, return dist + 1.
                        // Otherwise, just add this state to seen and queue.
                        if (newKeys == allKeys) {
                            return dist + 1
                        }
                        seen.putIfAbsent(newKeys, HashSet())
                        seen[newKeys]?.add(Pair(newR, newC))
                        queue.offer(intArrayOf(newR, newC, newKeys, dist + 1))
                    }

                    // If it is a lock, and we don't have its key, continue.
                    if (lockSet.contains(cell) && keys and (1 shl cell.code - 'A'.code) == 0) {
                        continue
                    } else if (seen[keys]?.contains(Pair(newR, newC)) == false) {
                        seen[keys]?.add(Pair(newR, newC))
                        queue.offer(intArrayOf(newR, newC, keys, dist + 1))
                    }
                }
            }
        }

        return -1
    }
}
