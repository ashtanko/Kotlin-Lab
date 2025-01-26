/*
 * Copyright 2021 Oleksii Shtanko
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
 * 913. Cat and Mouse
 * @see <a href="https://leetcode.com/problems/cat-and-mouse/">Source</a>
 */
fun interface CatAndMouse {
    fun catMouseGame(graph: Array<IntArray>): Int
}

class CatAndMouseMinimax : CatAndMouse {

    override fun catMouseGame(graph: Array<IntArray>): Int {
        val n: Int = graph.size
        val status = Array(SIZE) { Array(SIZE) { IntArray(CHUNK_SIZE) } }
        val childrenNum = Array(SIZE) { Array(SIZE) { IntArray(CHUNK_SIZE) } }

        // mouse location
        for (m in 0 until n) {
            // cat location
            for (c in 0 until n) {
                childrenNum[m][c][MOUSE_TURN] = graph[m].size
                childrenNum[m][c][CAT_TURN] = graph[c].size
                for (x in graph[c]) {
                    // The cat can not stay at the hole (position 0).
                    if (x == 0) {
                        childrenNum[m][c][CAT_TURN]--
                        break
                    }
                }
            }
        }

        // enqueued : all nodes that we know who wins in the end. Nodes with DRAW status is not in this queue.
        val queue = enqueuedAllNodes(n, status)

        // percolate nodes that we know who wins in the end
        while (queue.isNotEmpty()) {
            val node: IntArray = queue.remove()
            val m = node[0]
            val c = node[1]
            val t = node[2]
            val s = node[3] // mouse_location, cat_location, turn, status
            // for every parent of this node (m, c, t) :
            for (parent in parents(graph, m, c, t)) {
                val m2 = parent[0]
                val c2 = parent[1]
                val t2 = parent[2] // mouse_location2, cat_location2, turn2
                // if we do not know who wins in this parent node
                if (status[m2][c2][t2] == DRAW) {
                    if (t2 == MOUSE_TURN && s == MOUSE_WIN || t2 == CAT_TURN && s == CAT_WIN) {
                        // if the parent can make a winning move (mouse to MOUSE_WIN, or cat to CAT_WIN)
                        status[m2][c2][t2] = s
                        queue.add(intArrayOf(m2, c2, t2, s))
                    } else {
                        // else, this parent has neutral children_num[parent]--.
                        // Enqueue if all children of this parent are colored as losing moves.
                        childrenNum[m2][c2][t2]--
                        if (childrenNum[m2][c2][t2] == 0) {
                            status[m2][c2][t2] = if (t2 == MOUSE_TURN) CAT_WIN else MOUSE_WIN
                            queue.add(intArrayOf(m2, c2, t2, status[m2][c2][t2]))
                        }
                    }
                }
            }
        }

        return status[1][2][MOUSE_TURN] // The mouse is at location 1. The cat is at location 2. The mouse moves first.
    }

    private fun enqueuedAllNodes(n: Int, status: Array<Array<IntArray>>): Queue<IntArray> {
        // enqueued : all nodes that we know who wins in the end. Nodes with DRAW status is not in this queue.
        val queue: Queue<IntArray> = LinkedList()
        for (i in 0 until n) {
            // turn
            for (t in 1..2) {
                status[0][i][t] = MOUSE_WIN // The mouse wins if it is at the hole (position 0).
                queue.add(intArrayOf(0, i, t, MOUSE_WIN))
                if (i > 0) {
                    status[i][i][t] = CAT_WIN // The cat wins if mouse and cat are at the same location.
                    queue.add(intArrayOf(i, i, t, CAT_WIN))
                }
            }
        }
        return queue
    }

    // What nodes could play their turn to arrive at node (m, c, t) ?
    private fun parents(graph: Array<IntArray>, m: Int, c: Int, t: Int): List<IntArray> {
        val ans: MutableList<IntArray> = ArrayList()
        if (t == CAT_TURN) {
            for (m2 in graph[m]) {
                ans.add(intArrayOf(m2, c, MOUSE_TURN))
            }
        } else {
            for (c2 in graph[c]) {
                // The cat can not stay at the hole (position 0).
                if (c2 > 0) {
                    ans.add(intArrayOf(m, c2, CAT_TURN))
                }
            }
        }
        return ans
    }

    companion object {
        private const val SIZE = 50
        private const val CHUNK_SIZE = 3
        private const val DRAW = 0
        private const val MOUSE_WIN = 1
        private const val CAT_WIN = 2
        private const val MOUSE_TURN = 1
        private const val CAT_TURN = 2
    }
}
