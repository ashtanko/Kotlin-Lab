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

/**
 * 1791. Find Center of Star Graph
 * @see <a href="https://leetcode.com/problems/find-center-of-star-graph/">Find Center of Star Graph</a>
 */
fun interface CenterOfStarGraph {
    operator fun invoke(edges: Array<IntArray>): Int
}

class DegreeCount : CenterOfStarGraph {
    override fun invoke(edges: Array<IntArray>): Int {
        val degree: MutableMap<Int, Int> = HashMap()

        for (edge in edges) {
            degree[edge[0]] = degree.getOrDefault(edge[0], 0) + 1
            degree[edge[1]] = degree.getOrDefault(edge[1], 0) + 1
        }

        for (node in degree.keys) {
            if (degree[node] == edges.size) {
                return node
            }
        }

        return -1
    }
}

class CenterOfStarGraphGreedy : CenterOfStarGraph {
    override fun invoke(edges: Array<IntArray>): Int {
        val firstEdge = edges[0]
        val secondEdge = edges[1]

        return if ((firstEdge[0] == secondEdge[0] || firstEdge[0] == secondEdge[1])) {
            firstEdge[0]
        } else {
            firstEdge[1]
        }
    }
}
