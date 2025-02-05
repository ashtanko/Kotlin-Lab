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
 * 785. Is Graph Bipartite?
 * @see <a href="https://leetcode.com/problems/is-graph-bipartite/">Source</a>
 */
fun interface IsGraphBipartite {
    fun isBipartite(graph: Array<IntArray>): Boolean
}

class IsGraphBipartiteDFS : IsGraphBipartite {
    override fun isBipartite(graph: Array<IntArray>): Boolean {
        val n: Int = graph.size
        val colors = IntArray(n)

        // This graph might be a disconnected graph. So check each unvisited node.
        for (i in 0 until n) {
            if (colors[i] == 0 && !validColor(graph, colors, 1, i)) {
                return false
            }
        }
        return true
    }

    private fun validColor(graph: Array<IntArray>, colors: IntArray, color: Int, node: Int): Boolean {
        if (colors[node] != 0) {
            return colors[node] == color
        }
        colors[node] = color
        for (next in graph[node]) {
            if (!validColor(graph, colors, -color, next)) {
                return false
            }
        }
        return true
    }
}

class IsGraphBipartiteBFS : IsGraphBipartite {
    override fun isBipartite(graph: Array<IntArray>): Boolean {
        val len: Int = graph.size
        val colors = IntArray(len)

        for (i in 0 until len) {
            if (colors[i] != 0) continue
            val queue: Queue<Int> = LinkedList()
            queue.offer(i)
            colors[i] = 1 // Blue: 1; Red: -1.
            while (queue.isNotEmpty()) {
                val cur: Int = queue.poll()
                for (next in graph[cur]) {
                    if (colors[next] == 0) { // If this node hasn't been colored;
                        colors[next] = -colors[cur] // Color it with a different color;
                        queue.offer(next)
                    } else if (colors[next] != -colors[cur]) {
                        // If it is colored and its color is different, return false
                        return false
                    }
                }
            }
        }
        return true
    }
}
