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

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.Sort
import java.util.LinkedList
import java.util.Queue

/**
 * 2872. Maximum Number of K-Divisible Components
 * @see <a href="https://leetcode.com/problems/maximum-number-of-k-divisible-components/">Source</a>
 */
sealed interface MaxKDivisibleComponents {
    operator fun invoke(n: Int, edges: Array<IntArray>, values: IntArray, k: Int): Int

    @DFS
    data object DepthFirstSearch : MaxKDivisibleComponents {
        override fun invoke(
            n: Int,
            edges: Array<IntArray>,
            values: IntArray,
            k: Int,
        ): Int {
            // Step 1: Create adjacency list from edges
            val adjList = Array(n) { mutableListOf<Int>() }
            for (edge in edges) {
                val (node1, node2) = edge
                adjList[node1].add(node2)
                adjList[node2].add(node1)
            }

            // Step 2: Initialize component count
            val componentCount = intArrayOf(0) // Use array to pass by reference

            // Step 3: Start DFS traversal from node 0
            dfs(0, -1, adjList, values, k, componentCount)

            // Step 4: Return the total number of components
            return componentCount[0]
        }

        private fun dfs(
            currentNode: Int,
            parentNode: Int,
            adjList: Array<MutableList<Int>>,
            nodeValues: IntArray,
            k: Int,
            componentCount: IntArray,
        ): Int {
            // Step 1: Initialize sum for the current subtree
            var sum = 0

            // Step 2: Traverse all neighbors
            for (neighborNode in adjList[currentNode]) {
                if (neighborNode != parentNode) {
                    // Recursive call to process the subtree rooted at the neighbor
                    sum += dfs(neighborNode, currentNode, adjList, nodeValues, k, componentCount)
                    sum %= k // Ensure the sum stays within bounds
                }
            }

            // Step 3: Add the value of the current node to the sum
            sum += nodeValues[currentNode]
            sum %= k

            // Step 4: Check if the sum is divisible by k
            if (sum == 0) {
                componentCount[0]++
            }

            // Step 5: Return the computed sum for the current subtree
            return sum
        }
    }

    @BFS
    data object BreadthFirstSearch : MaxKDivisibleComponents {
        override fun invoke(
            n: Int,
            edges: Array<IntArray>,
            values: IntArray,
            k: Int,
        ): Int {
            // Base case: if there are less than 2 nodes, return 1
            if (n < 2) return 1

            var componentCount = 0
            val graph = mutableMapOf<Int, MutableSet<Int>>()

            // Step 1: Build the graph
            for (edge in edges) {
                val (node1, node2) = edge
                graph.computeIfAbsent(node1) { mutableSetOf() }.add(node2)
                graph.computeIfAbsent(node2) { mutableSetOf() }.add(node1)
            }

            // Convert values to Long to prevent overflow
            val longValues = values.map { it.toLong() }.toMutableList()

            // Step 2: Initialize the BFS queue with leaf nodes (nodes with only one connection)
            val queue: Queue<Int> = LinkedList()
            for ((node, neighbors) in graph) {
                if (neighbors.size == 1) {
                    queue.add(node)
                }
            }

            // Step 3: Process nodes in BFS order
            while (queue.isNotEmpty()) {
                val currentNode = queue.poll()

                // Find the neighbor node
                val neighborNode = graph[currentNode]?.firstOrNull()

                if (neighborNode != null) {
                    // Remove the edge between current and neighbor
                    graph[neighborNode]?.remove(currentNode)
                    graph[currentNode]?.remove(neighborNode)
                }

                // Check divisibility of the current node's value
                if (longValues[currentNode] % k == 0L) {
                    componentCount++
                } else if (neighborNode != null) {
                    // Add current node's value to the neighbor
                    longValues[neighborNode] += longValues[currentNode]
                }

                // If the neighbor becomes a leaf node, add it to the queue
                if (neighborNode != null && graph[neighborNode]?.size == 1) {
                    queue.add(neighborNode)
                }
            }

            return componentCount
        }
    }

    @Sort
    data object TopologicalSort : MaxKDivisibleComponents {
        override fun invoke(
            n: Int,
            edges: Array<IntArray>,
            values: IntArray,
            k: Int,
        ): Int {
            if (n < 2) return 1 // Handle the base case where the graph has fewer than 2 nodes.

            var componentCount = 0
            val graph = List(n) { mutableListOf<Int>() }
            val inDegree = IntArray(n)

            // Build the graph and calculate in-degrees
            for (edge in edges) {
                val (node1, node2) = edge
                graph[node1].add(node2)
                graph[node2].add(node1)
                inDegree[node1]++
                inDegree[node2]++
            }

            // Convert values to Long to prevent overflow
            val longValues = values.map { it.toLong() }.toMutableList()

            // Initialize the queue with nodes having in-degree of 1 (leaf nodes)
            val queue: Queue<Int> = LinkedList()
            for (node in 0 until n) {
                if (inDegree[node] == 1) {
                    queue.offer(node)
                }
            }

            while (queue.isNotEmpty()) {
                val currentNode = queue.poll()
                inDegree[currentNode]--

                var addValue = 0L

                // Check if the current node's value is divisible by k
                if (longValues[currentNode] % k == 0L) {
                    componentCount++
                } else {
                    addValue = longValues[currentNode]
                }

                // Propagate the value to the neighbor nodes
                for (neighborNode in graph[currentNode]) {
                    if (inDegree[neighborNode] == 0) continue

                    inDegree[neighborNode]--
                    longValues[neighborNode] += addValue

                    // If the neighbor node's in-degree becomes 1, add it to the queue
                    if (inDegree[neighborNode] == 1) {
                        queue.offer(neighborNode)
                    }
                }
            }

            return componentCount
        }
    }
}
