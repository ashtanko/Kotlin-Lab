/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 2097. Valid Arrangement of Pairs
 * @see <a href="https://leetcode.com/problems/valid-arrangement-of-pairs/">Valid Arrangement of Pairs</a>
 */
@Hard("https://leetcode.com/problems/valid-arrangement-of-pairs/")
fun interface ValidArrangementOfPairs {
    operator fun invoke(pairs: Array<IntArray>): Array<IntArray>
}

class HierholzersAlgorithm : ValidArrangementOfPairs {
    override fun invoke(pairs: Array<IntArray>): Array<IntArray> {
        val adjacencyMatrix = mutableMapOf<Int, ArrayDeque<Int>>()
        val inDegree = mutableMapOf<Int, Int>()
        val outDegree = mutableMapOf<Int, Int>()

        // Build the adjacency list and track in-degrees and out-degrees
        for ((start, end) in pairs) {
            adjacencyMatrix.computeIfAbsent(start) { ArrayDeque() }.add(end)
            outDegree[start] = outDegree.getOrDefault(start, 0) + 1
            inDegree[end] = inDegree.getOrDefault(end, 0) + 1
        }

        val result = mutableListOf<Int>()

        // Find the start node (outDegree == inDegree + 1)
        var startNode = -1
        for ((node, out) in outDegree) {
            if (out == inDegree.getOrDefault(node, 0) + 1) {
                startNode = node
                break
            }
        }

        // If no such node exists, start from the first pair's first element
        if (startNode == -1) {
            startNode = pairs[0][0]
        }

        val nodeStack = ArrayDeque<Int>()
        nodeStack.add(startNode)

        // Iterative DFS using stack
        while (nodeStack.isNotEmpty()) {
            val node = nodeStack.last()
            if (adjacencyMatrix[node]?.isNotEmpty() == true) {
                // Visit the next node
                val nextNode = adjacencyMatrix[node]?.removeFirst() ?: continue
                nodeStack.add(nextNode)
            } else {
                // No more neighbors to visit, add node to result
                result.add(node)
                nodeStack.removeLast()
            }
        }

        // Reverse the result since we collected nodes in reverse order
        result.reverse()

        // Construct the result pairs
        return Array(result.size - 1) { i ->
            intArrayOf(result[i], result[i + 1])
        }
    }
}
