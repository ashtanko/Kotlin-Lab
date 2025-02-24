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
import dev.shtanko.algorithms.annotations.BottomUpDP
import dev.shtanko.algorithms.annotations.Recursive
import dev.shtanko.algorithms.annotations.TopDownDP
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.LinkedList
import java.util.Queue

/**
 * 3243. Shortest Distance After Road Addition Queries I
 * @see <a href="https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i/">Source</a>
 */
@Medium("https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i/")
fun interface ShortestDistanceAfterQueries {
    operator fun invoke(n: Int, queries: Array<IntArray>): IntArray
}

@BFS
class ShortestDistanceAfterQueriesBFS : ShortestDistanceAfterQueries {
    override fun invoke(n: Int, queries: Array<IntArray>): IntArray {
        val answer = mutableListOf<Int>()
        val adjList = List(n) { mutableListOf<Int>() }

        // Initialize the graph with edges between consecutive nodes
        for (i in 0 until n - 1) {
            adjList[i].add(i + 1)
        }

        // Process each query to add new roads
        for (road in queries) {
            val (u, v) = road
            adjList[u].add(v) // Add road from u to v
            // Perform BFS to find the shortest path after adding the new road
            answer.add(bfs(n, adjList))
        }

        // Convert List<Int> to IntArray
        return answer.toIntArray()
    }

    private fun bfs(n: Int, adjList: List<MutableList<Int>>): Int {
        val visited = BooleanArray(n)
        val nodeQueue: Queue<Int> = LinkedList()

        // Start BFS from node 0
        nodeQueue.add(0)
        visited[0] = true

        // Track the number of nodes in the current layer and the next layer
        var currentLayerNodeCount = 1
        var nextLayerNodeCount = 0
        // Initialize layers explored count
        var layersExplored = 0

        // Perform BFS until the queue is empty
        while (nodeQueue.isNotEmpty()) {
            // Process nodes in the current layer
            repeat(currentLayerNodeCount) {
                val currentNode = nodeQueue.poll()

                // Check if we reached the destination node
                if (currentNode == n - 1) {
                    return layersExplored // Return the number of edges in the shortest path
                }

                // Explore all adjacent nodes
                for (neighbor in adjList[currentNode]) {
                    if (visited[neighbor]) continue
                    nodeQueue.add(neighbor) // Add neighbor to the queue for exploration
                    nextLayerNodeCount++ // Increment the count of nodes in the next layer
                    visited[neighbor] = true
                }
            }

            // Move to the next layer
            currentLayerNodeCount = nextLayerNodeCount
            nextLayerNodeCount = 0 // Reset next layer count
            layersExplored++ // Increment the layer count after processing the current layer
        }

        return -1 // Algorithm will never reach this point
    }
}

@Recursive
@TopDownDP
class ShortestDistanceAfterQueriesTopDown : ShortestDistanceAfterQueries {
    override fun invoke(n: Int, queries: Array<IntArray>): IntArray {
        val dp = IntArray(n) { -1 } // DP array to store minimum distances from each node
        val adjList = List(n) { mutableListOf<Int>() }

        // Initialize the graph with edges between consecutive nodes
        for (i in 0 until n - 1) {
            adjList[i].add(i + 1)
        }

        val answer = mutableListOf<Int>()

        // Process each query to add new edges
        for (road in queries) {
            val (u, v) = road

            // Add the directed edge from u to v
            adjList[u].add(v)

            // Find the minimum distance from the starting node (0) to the destination (n-1)
            answer.add(findMinDistance(adjList, n, 0, dp))

            // Clear and reset the dp array
            dp.fill(-1)
        }

        // Convert the result list to an IntArray
        return answer.toIntArray()
    }

    private fun findMinDistance(
        adjList: List<MutableList<Int>>,
        n: Int,
        currentNode: Int,
        dp: IntArray,
    ): Int {
        // We've reached the destination node
        if (currentNode == n - 1) return 0

        // If this node has already been computed, return the stored value
        if (dp[currentNode] != -1) return dp[currentNode]

        var minDistance = n

        for (neighbor in adjList[currentNode]) {
            // Recursively find the minimum distance from the neighbor to the destination
            minDistance = minOf(minDistance, findMinDistance(adjList, n, neighbor, dp) + 1)
        }

        // Store the computed minimum distance in the dp array and return it
        dp[currentNode] = minDistance
        return minDistance
    }
}

@BottomUpDP
class ShortestDistanceAfterQueriesBottomUp : ShortestDistanceAfterQueries {
    override fun invoke(n: Int, queries: Array<IntArray>): IntArray {
        val answer = mutableListOf<Int>()
        val adjList = List(n) { mutableListOf<Int>() }

        // Initialize edges between consecutive nodes
        for (i in 0 until n - 1) {
            adjList[i].add(i + 1)
        }

        // Process each query to add new edges
        for (road in queries) {
            val (u, v) = road
            adjList[u].add(v) // Add the directed edge from u to v

            // Calculate the minimum distance after adding the new edge
            answer.add(findMinDistance(adjList, n))
        }

        // Convert List<Int> to IntArray
        return answer.toIntArray()
    }

    private fun findMinDistance(adjList: List<MutableList<Int>>, n: Int): Int {
        val dp = IntArray(n) { n } // Initialize distances with a high value
        dp[n - 1] = 0 // Base case: distance to destination (n-1) is 0

        // Iterate from the second last node down to the first node
        for (currentNode in n - 2 downTo 0) {
            var minDistance = n
            // Explore neighbors to find the minimum distance
            for (neighbor in adjList[currentNode]) {
                minDistance = minOf(minDistance, dp[neighbor] + 1)
            }
            dp[currentNode] = minDistance // Store the calculated distance for the current node
        }
        return dp[0]
    }
}
