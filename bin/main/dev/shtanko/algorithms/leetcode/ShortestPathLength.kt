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

import java.util.Queue

/**
 * 847. Shortest Path Visiting All Nodes
 * @see <a href="https://leetcode.com/problems/shortest-path-visiting-all-nodes">Source</a>
 */
fun interface ShortestPathLength {
    operator fun invoke(graph: Array<IntArray>): Int
}

class ShortestPathLengthBFS : ShortestPathLength {
    override fun invoke(graph: Array<IntArray>): Int {
        val n = graph.size
        val allVisitedMask = (1 shl n) - 1
        val queue: Queue<Pair<Int, Pair<Int, Int>>> = java.util.LinkedList() // Pair<currNode, Pair<dist, mask>>
        val seen = mutableSetOf<Pair<Int, Int>>() // Pair<currNode, mask as path>

        // Initially push all nodes in the queue to start the path with all nodes
        for (i in 0 until n) {
            queue.offer(Pair(i, Pair(0, 1 shl i)))
            seen.add(Pair(i, 1 shl i))
        }

        while (queue.isNotEmpty()) {
            val curr = queue.poll()
            val node = curr.first
            val dist = curr.second.first
            val mask = curr.second.second

            // If all nodes are visited
            if (mask == allVisitedMask) return dist

            // Go through all neighbors
            for (nei in graph[node]) {
                val nextMask = mask or (1 shl nei)
                if (Pair(nei, nextMask) !in seen) {
                    queue.offer(Pair(nei, Pair(dist + 1, nextMask)))
                    seen.add(Pair(nei, nextMask))
                }
            }
        }
        return 0
    }
}
