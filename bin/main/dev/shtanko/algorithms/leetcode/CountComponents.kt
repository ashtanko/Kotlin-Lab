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

/**
 * Number of Connected Components in an Undirected Graph
 * @see <a href="https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph">Source</a>
 */
fun interface CountComponents {
    operator fun invoke(n: Int, edges: Array<IntArray>): Int
}

class CountComponentsDFS : CountComponents {
    override operator fun invoke(n: Int, edges: Array<IntArray>): Int {
        val representative = IntArray(n)
        val size = IntArray(n)

        for (i in 0 until n) {
            representative[i] = i
            size[i] = 1
        }

        var components = n
        for (i in edges.indices) {
            components -= combine(representative, size, edges[i][0], edges[i][1])
        }

        return components
    }

    private fun find(representative: IntArray, vertex: Int): Int {
        return if (vertex == representative[vertex]) {
            vertex
        } else {
            find(representative, representative[vertex]).also { representative[vertex] = it }
        }
    }

    private fun combine(representative: IntArray, size: IntArray, vertex1: Int, vertex2: Int): Int {
        var v1 = vertex1
        var v2 = vertex2
        v1 = find(representative, v1)
        v2 = find(representative, v2)
        return if (v1 == v2) {
            0
        } else {
            if (size[v1] > size[v2]) {
                size[v1] += size[v2]
                representative[v2] = v1
            } else {
                size[v2] += size[v1]
                representative[v1] = v2
            }
            1
        }
    }
}
