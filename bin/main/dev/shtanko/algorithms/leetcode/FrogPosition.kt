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
 * 1377. Frog Position After T Seconds
 * @see <a href="https://leetcode.com/problems/frog-position-after-t-seconds/">Source</a>
 */
fun interface FrogPosition {
    operator fun invoke(n: Int, edges: Array<IntArray>, t: Int, target: Int): Double
}

class FrogPositionBFS : FrogPosition {
    override operator fun invoke(n: Int, edges: Array<IntArray>, t: Int, target: Int): Double {
        var t0 = t
        val graph: Map<Int, List<Int>> = initialize(edges)
        val visited: BooleanArray = BooleanArray(n).apply {
            this[0] = true
        }
        val prob: DoubleArray = DoubleArray(n).apply {
            this[0] = 1.0
        }
        val q: Queue<Int> = LinkedList<Int>().apply {
            offer(0)
        }

        while (q.isNotEmpty() && t0-- > 0) {
            for (size in q.size downTo 1) {
                val u = q.poll()
                val nextVerticesCount = adjacentVerticesCount(graph, visited, u)
                val ints = graph[u] ?: emptyList()
                for (v in ints) {
                    if (visited[v]) continue
                    visited[v] = true
                    q.offer(v)
                    prob[v] = prob[u] / nextVerticesCount
                }
                if (nextVerticesCount > 0) prob[u] = 0.0 // frog don't stay vertex u, he keeps going to the next vertex
            }
        }
        return prob[target - 1]
    }

    private fun initialize(edges: Array<IntArray>): Map<Int, MutableList<Int>> {
        val graph: MutableMap<Int, MutableList<Int>> = HashMap()
        for (edge in edges) {
            val p1 = edge[0] - 1
            val p2 = edge[1] - 1
            graph.computeIfAbsent(p1) { LinkedList() }.add(p2)
            graph.computeIfAbsent(p2) { LinkedList() }.add(p1)
        }
        return graph
    }

    private fun adjacentVerticesCount(graph: Map<Int, List<Int>>, visited: BooleanArray, u: Int): Int {
        var nextVerticesCount = 0
        val ints = graph[u] ?: emptyList()
        for (v in ints) {
            if (!visited[v]) nextVerticesCount++
        }
        return nextVerticesCount
    }
}
