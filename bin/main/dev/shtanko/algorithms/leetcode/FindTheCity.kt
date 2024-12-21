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

import dev.shtanko.algorithms.annotations.BellmanFord
import dev.shtanko.algorithms.annotations.Dijkstra
import dev.shtanko.algorithms.annotations.FloydWarshall
import java.util.Deque
import java.util.PriorityQueue

/**
 * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 * @see <a href="https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold
 * -distance">Source</a>
 */
fun interface FindTheCity {
    operator fun invoke(num: Int, edges: Array<IntArray>, distanceThreshold: Int): Int
}

// Determine the city with the fewest number of reachable cities within the distance threshold
private fun getCityWithFewestReachable(
    n: Int,
    shortestPathMatrix: Array<IntArray>,
    distanceThreshold: Int,
): Int {
    var cityWithFewestReachable = -1
    var fewestReachableCount = n

    // Count number of cities reachable within the distance threshold for each city
    for (i in 0 until n) {
        var reachableCount = 0
        for (j in 0 until n) {
            if (i != j && shortestPathMatrix[i][j] <= distanceThreshold) {
                reachableCount++
            }
        }
        // Update the city with the fewest reachable cities
        if (reachableCount <= fewestReachableCount) {
            fewestReachableCount = reachableCount
            cityWithFewestReachable = i
        }
    }
    return cityWithFewestReachable
}

/**
 * Approach 1: Dijkstra Algorithm
 */
@Dijkstra
class FindTheCityDijkstra : FindTheCity {

    override fun invoke(
        num: Int,
        edges: Array<IntArray>,
        distanceThreshold: Int,
    ): Int {
        // Adjacency list to store the graph
        val adjacencyList = Array(num) { mutableListOf<Pair<Int, Int>>() }
        // Matrix to store shortest path distances from each city
        val shortestPathMatrix = Array(num) { IntArray(num) }

        // Initialize adjacency list and shortest path matrix
        for (i in 0 until num) {
            shortestPathMatrix[i].fill(Int.MAX_VALUE) // Set all distances to infinity
            shortestPathMatrix[i][i] = 0 // Distance to itself is zero
        }

        // Populate the adjacency list with edges
        for (edge in edges) {
            val (start, end, weight) = edge
            adjacencyList[start].add(Pair(end, weight))
            adjacencyList[end].add(Pair(start, weight)) // For undirected graph
        }

        // Compute the shortest paths from each city using Dijkstra's algorithm
        for (i in 0 until num) {
            dijkstra(adjacencyList, shortestPathMatrix[i], i)
        }

        // Find the city with the fewest number of reachable cities within the distance threshold
        return getCityWithFewestReachable(num, shortestPathMatrix, distanceThreshold)
    }

    // Dijkstra's algorithm to find the shortest paths from a source city
    private fun dijkstra(
        adjacencyList: Array<MutableList<Pair<Int, Int>>>,
        shortestPathDistances: IntArray,
        source: Int,
    ) {
        // Priority queue to process nodes with the smallest distance first
        val priorityQueue = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
        priorityQueue.add(Pair(source, 0))
        shortestPathDistances.fill(Int.MAX_VALUE) // Set all distances to infinity
        shortestPathDistances[source] = 0 // Distance to source itself is zero

        // Process nodes in priority order
        while (priorityQueue.isNotEmpty()) {
            val (currentCity, currentDistance) = priorityQueue.poll()
            if (currentDistance > shortestPathDistances[currentCity]) {
                continue
            }

            // Update distances to neighboring cities
            for ((neighborCity, edgeWeight) in adjacencyList[currentCity]) {
                if (shortestPathDistances[neighborCity] > currentDistance + edgeWeight) {
                    shortestPathDistances[neighborCity] = currentDistance + edgeWeight
                    priorityQueue.add(Pair(neighborCity, shortestPathDistances[neighborCity]))
                }
            }
        }
    }
}

@BellmanFord
class FindTheCityBellmanFord : FindTheCity {
    override fun invoke(
        num: Int,
        edges: Array<IntArray>,
        distanceThreshold: Int,
    ): Int {
        // Large value to represent infinity
        val inf = 1e9.toInt() + 7
        // Matrix to store shortest path distances from each city
        val shortestPathMatrix = Array(num) { IntArray(num) }

        // Initialize the shortest path matrix
        for (i in 0 until num) {
            shortestPathMatrix[i].fill(inf) // Set all distances to infinity
            shortestPathMatrix[i][i] = 0 // Distance to itself is zero
        }

        // Populate the matrix with initial edge weights
        for (edge in edges) {
            val (start, end, weight) = edge
            shortestPathMatrix[start][end] = weight
            shortestPathMatrix[end][start] = weight // For undirected graph
        }

        // Compute the shortest paths from each city using Bellman-Ford algorithm
        for (i in 0 until num) {
            bellmanFord(num, edges, shortestPathMatrix[i], i)
        }

        // Find the city with the fewest number of reachable cities within the distance threshold
        return getCityWithFewestReachable(num, shortestPathMatrix, distanceThreshold)
    }

    // Bellman-Ford algorithm to find the shortest paths from a source city
    private fun bellmanFord(
        n: Int,
        edges: Array<IntArray>,
        shortestPathDistances: IntArray,
        source: Int,
    ) {
        // Initialize distances from the source
        shortestPathDistances.fill(Int.MAX_VALUE)
        shortestPathDistances[source] = 0 // Distance to source itself is zero

        // Relax edges up to n-1 times
        for (i in 1 until n) {
            for (edge in edges) {
                val (start, end, weight) = edge
                // Update the shortest path distances if a shorter path is found
                if (shortestPathDistances[start] != Int.MAX_VALUE &&
                    shortestPathDistances[start] + weight < shortestPathDistances[end]
                ) {
                    shortestPathDistances[end] = shortestPathDistances[start] + weight
                }
                if (shortestPathDistances[end] != Int.MAX_VALUE &&
                    shortestPathDistances[end] + weight < shortestPathDistances[start]
                ) {
                    shortestPathDistances[start] = shortestPathDistances[end] + weight
                }
            }
        }
    }
}

/**
 * Approach 3: Shortest Path First Algorithm (SPFA)
 */
class FindTheCitySPFA : FindTheCity {
    override fun invoke(
        num: Int,
        edges: Array<IntArray>,
        distanceThreshold: Int,
    ): Int {
        // Adjacency list to store the graph
        val adjacencyList = Array(num) { mutableListOf<IntArray>() }
        // Matrix to store shortest path distances from each city
        val shortestPathMatrix = Array(num) { IntArray(num) }

        // Initialize adjacency list and shortest path matrix
        for (i in 0 until num) {
            shortestPathMatrix[i].fill(Int.MAX_VALUE) // Set all distances to infinity
            shortestPathMatrix[i][i] = 0 // Distance to itself is zero
        }

        // Populate the adjacency list with edges
        for (edge in edges) {
            val (start, end, weight) = edge
            adjacencyList[start].add(intArrayOf(end, weight))
            adjacencyList[end].add(intArrayOf(start, weight)) // For undirected graph
        }

        // Compute the shortest paths from each city using SPFA algorithm
        for (i in 0 until num) {
            spfa(num, adjacencyList, shortestPathMatrix[i], i)
        }

        // Find the city with the fewest number of reachable cities within the distance threshold
        return getCityWithFewestReachable(num, shortestPathMatrix, distanceThreshold)
    }

    // SPFA algorithm to find the shortest paths from a source city
    private fun spfa(
        n: Int,
        adjacencyList: Array<MutableList<IntArray>>,
        shortestPathDistances: IntArray,
        source: Int,
    ) {
        // Queue to process nodes with updated the shortest path distances
        val queue: Deque<Int> = java.util.ArrayDeque<Int>()
        // Array to track the number of updates for each node
        val updateCount = IntArray(n)
        queue.add(source)
        shortestPathDistances.fill(Int.MAX_VALUE) // Set all distances to infinity
        shortestPathDistances[source] = 0 // Distance to source itself is zero

        // Process nodes in queue
        while (queue.isNotEmpty()) {
            val currentCity = queue.removeFirst()
            for (neighbor in adjacencyList[currentCity]) {
                val (neighborCity, edgeWeight) = neighbor

                // Update the shortest path distance if a shorter path is found
                if (shortestPathDistances[neighborCity] > shortestPathDistances[currentCity] + edgeWeight) {
                    shortestPathDistances[neighborCity] = shortestPathDistances[currentCity] + edgeWeight
                    updateCount[neighborCity]++
                    queue.add(neighborCity)

                    // Detect negative weight cycles (not expected in this problem)
                    if (updateCount[neighborCity] > n) {
                        println("Negative weight cycle detected")
                    }
                }
            }
        }
    }
}

/**
 * Approach 4: Floyd-Warshall Algorithm
 */
@FloydWarshall
class FindTheCityFloydWarshall : FindTheCity {
    override fun invoke(
        num: Int,
        edges: Array<IntArray>,
        distanceThreshold: Int,
    ): Int {
        // Large value to represent infinity
        val inf = (1e9 + 7).toInt()
        // Distance matrix to store shortest paths between all pairs of cities
        val distanceMatrix = Array(num) { IntArray(num) }

        // Initialize distance matrix
        for (i in 0 until num) {
            distanceMatrix[i].fill(inf) // Set all distances to infinity
            distanceMatrix[i][i] = 0 // Distance to itself is zero
        }

        // Populate the distance matrix with initial edge weights
        for (edge in edges) {
            val (start, end, weight) = edge
            distanceMatrix[start][end] = weight
            distanceMatrix[end][start] = weight // For undirected graph
        }

        // Compute the shortest paths using Floyd-Warshall algorithm
        floyd(num, distanceMatrix)

        // Find the city with the fewest number of reachable cities within the distance threshold
        return getCityWithFewestReachable(num, distanceMatrix, distanceThreshold)
    }

    // Floyd-Warshall algorithm to compute the shortest paths between all pairs of cities
    private fun floyd(n: Int, distanceMatrix: Array<IntArray>) {
        // Update distances for each intermediate city
        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    // Update the shortest path from i to j through k
                    if (distanceMatrix[i][k] < Int.MAX_VALUE && distanceMatrix[k][j] < Int.MAX_VALUE) {
                        distanceMatrix[i][j] = minOf(
                            distanceMatrix[i][j],
                            distanceMatrix[i][k] + distanceMatrix[k][j],
                        )
                    }
                }
            }
        }
    }
}
