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

import dev.shtanko.algorithms.INF
import java.util.PriorityQueue

/**
 * 2699. Modify Graph Edge Weights
 * @see <a href="https://leetcode.com/problems/modify-graph-edge-weights/">Source</a>
 */
fun interface ModifyGraphEdgeWeights {
    operator fun invoke(n: Int, edges: Array<IntArray>, source: Int, destination: Int, target: Int): Array<IntArray>
}

class ModifyGraphEdgeWeightsMinHeap : ModifyGraphEdgeWeights {
    override fun invoke(
        n: Int,
        edges: Array<IntArray>,
        source: Int,
        destination: Int,
        target: Int,
    ): Array<IntArray> {
        val maxWeight = INF.toInt()
        val adjacencyList = Array(n) { mutableListOf<Pair<Int, Int>>() }

        // Build the graph with known weights
        for ((startNode, endNode, weight) in edges) {
            if (weight != -1) {
                adjacencyList[startNode].add(Pair(endNode, weight))
                adjacencyList[endNode].add(Pair(startNode, weight))
            }
        }

        // Compute the initial shortest distance
        val initialShortestDistance = dijkstra(adjacencyList, source, destination)
        if (initialShortestDistance < target) return emptyArray()

        if (initialShortestDistance == target) {
            // Update edges with -1 weight to an impossible value
            for (edge in edges) {
                if (edge[2] == -1) {
                    edge[2] = maxWeight
                }
            }
            return edges
        }

        // Adjust edges with unknown weights
        for (i in edges.indices) {
            val (startNode, endNode, weight) = edges[i]
            if (weight != -1) continue

            // Set edge weight to 1 initially
            edges[i][2] = 1
            adjacencyList[startNode].add(Pair(endNode, 1))
            adjacencyList[endNode].add(Pair(startNode, 1))

            // Recompute the shortest distance with updated edge weight
            val updatedDistance = dijkstra(adjacencyList, source, destination)

            if (updatedDistance <= target) {
                edges[i][2] += target - updatedDistance

                // Update remaining edges with -1 weight to an impossible value
                for (j in (i + 1) until edges.size) {
                    if (edges[j][2] == -1) {
                        edges[j][2] = maxWeight
                    }
                }
                return edges
            }
        }
        return emptyArray()
    }

    private fun dijkstra(
        adjacencyList: Array<MutableList<Pair<Int, Int>>>,
        source: Int,
        destination: Int,
    ): Int {
        val minDistances = IntArray(adjacencyList.size) { Int.MAX_VALUE }
        minDistances[source] = 0
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

        priorityQueue.add(Pair(0, source)) // (distance, node)

        while (priorityQueue.isNotEmpty()) {
            val (currentDistance, currentNode) = priorityQueue.poll()
            if (currentDistance > minDistances[currentNode]) continue
            for ((neighborNode, edgeWeight) in adjacencyList[currentNode]) {
                if (currentDistance + edgeWeight < minDistances[neighborNode]) {
                    minDistances[neighborNode] = currentDistance + edgeWeight
                    priorityQueue.add(Pair(minDistances[neighborNode], neighborNode))
                }
            }
        }
        return minDistances[destination]
    }
}

class ModifyGraphEdgeWeightsDijkstra : ModifyGraphEdgeWeights {
    override fun invoke(
        n: Int,
        edges: Array<IntArray>,
        source: Int,
        destination: Int,
        target: Int,
    ): Array<IntArray> {
        // Step 1: Compute the initial shortest distance from source to destination
        var currentShortestDistance = runDijkstra(edges, n, source, destination)

        // If the current shortest distance is less than the target, return an empty result
        if (currentShortestDistance < target) return emptyArray()
        var matchesTarget = currentShortestDistance == target

        // Step 2: Iterate through each edge to adjust its weight if necessary
        for (edge in edges) {
            // Skip edges that already have a positive weight
            if (edge[2] > 0) continue

            // Set edge weight to a large value if current distance matches target, else set to 1
            edge[2] = if (matchesTarget) INF.toInt() else 1

            // Step 3: If current shortest distance does not match target
            if (!matchesTarget) {
                // Compute the new shortest distance with the updated edge weight
                val newDistance = runDijkstra(edges, n, source, destination)
                // If the new distance is within the target range, update edge weight to match target
                if (newDistance <= target) {
                    matchesTarget = true
                    edge[2] += target - newDistance
                }
            }
        }

        // Return modified edges if the target distance is achieved, otherwise return an empty result
        return if (matchesTarget) edges else emptyArray()
    }

    private fun runDijkstra(edges: Array<IntArray>, num: Int, source: Int, destination: Int): Int {
        // Step 1: Initialize adjacency matrix and distance arrays
        val adjMatrix = Array(num) { IntArray(num) { INF.toInt() } }
        val minDistance = IntArray(num) { INF.toInt() }
        val visited = BooleanArray(num) { false }

        // Set the distance to the source node as 0
        minDistance[source] = 0

        // Step 2: Fill the adjacency matrix with edge weights
        for ((nodeA, nodeB, weight) in edges) {
            if (weight != -1) {
                adjMatrix[nodeA][nodeB] = weight
                adjMatrix[nodeB][nodeA] = weight
            }
        }

        // Step 3: Perform Dijkstra's algorithm
        for (i in 0 until num) {
            // Find the nearest unvisited node
            var nearestUnvisitedNode = -1
            for (j in 0 until num) {
                if (!visited[j] && (nearestUnvisitedNode == -1 || minDistance[j] < minDistance[nearestUnvisitedNode])) {
                    nearestUnvisitedNode = j
                }
            }

            // Mark the nearest node as visited
            visited[nearestUnvisitedNode] = true

            // Update the minimum distance for each adjacent node
            for (v in 0 until num) {
                minDistance[v] = minOf(
                    minDistance[v],
                    minDistance[nearestUnvisitedNode] + adjMatrix[nearestUnvisitedNode][v],
                )
            }
        }
        // Return the shortest distance to the destination node
        return minDistance[destination]
    }
}
