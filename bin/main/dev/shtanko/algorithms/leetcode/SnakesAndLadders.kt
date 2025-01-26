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
 * 909. Snakes and Ladders
 * @see <a href="https://leetcode.com/problems/snakes-and-ladders/">Source</a>
 */
fun interface SnakesAndLadders {
    operator fun invoke(board: Array<IntArray>): Int
}

class SnakesAndLaddersBFS : SnakesAndLadders {
    override operator fun invoke(board: Array<IntArray>): Int {
        if (board.isEmpty() || board[0].isEmpty()) {
            return -1
        }
        val rows: Int = board.size
        val cols: Int = board[0].size
        val dest = rows * cols
        val queue: Queue<Int> = LinkedList()
        queue.offer(1)
        val set: MutableSet<Int> = HashSet()
        set.add(1)
        var steps = 0
        while (queue.isNotEmpty()) {
            val size: Int = queue.size
            for (i in 0 until size) {
                val curr: Int = queue.poll()
                if (curr == dest) {
                    return steps
                }
                var diff = 1
                while (diff <= 6 && curr + diff <= dest) {
                    val pos = getCoordinate(curr + diff, rows, cols)
                    val next = if (board[pos[0]][pos[1]] == -1) curr + diff else board[pos[0]][pos[1]]
                    if (!set.contains(next)) {
                        queue.offer(next)
                        set.add(next)
                    }
                    diff++
                }
            }
            steps++
        }
        return -1
    }

    private fun getCoordinate(n: Int, rows: Int, cols: Int): IntArray {
        val r = rows - 1 - (n - 1) / cols
        val c = (n - 1) % cols
        return if (r % 2 == rows % 2) {
            intArrayOf(r, cols - 1 - c)
        } else {
            intArrayOf(r, c)
        }
    }
}
