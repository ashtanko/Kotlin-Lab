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
import dev.shtanko.algorithms.annotations.Dijkstra
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

/**
 * 2045. Second Minimum Time to Reach Destination
 * @see <a href="https://leetcode.com/problems/second-minimum-time-to-reach-destination">Source</a>
 */
fun interface SecondMinimum {
    operator fun invoke(numNodes: Int, edges: Array<IntArray>, travelTime: Int, signalChange: Int): Int
}

/**
 * Approach 1: Dijkstra's Algorithm
 */
@Dijkstra
class SecondMinimumDijkstra : SecondMinimum {
    override fun invoke(
        numNodes: Int,
        edges: Array<IntArray>,
        travelTime: Int,
        signalChange: Int,
    ): Int {
        val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
        for (edge in edges) {
            val nodeA = edge[0]
            val nodeB = edge[1]
            adjacencyList.computeIfAbsent(nodeA) { mutableListOf() }.add(nodeB)
            adjacencyList.computeIfAbsent(nodeB) { mutableListOf() }.add(nodeA)
        }

        val shortestDist = IntArray(numNodes + 1) { Int.MAX_VALUE }
        val secondShortestDist = IntArray(numNodes + 1) { Int.MAX_VALUE }
        val visitFrequency = IntArray(numNodes + 1)

        val priorityQueue = PriorityQueue(compareBy<IntArray> { it[1] })
        priorityQueue.offer(intArrayOf(1, 0))
        shortestDist[1] = 0

        while (priorityQueue.isNotEmpty()) {
            val current = priorityQueue.poll()
            val currentNode = current[0]
            var currentTime = current[1]

            visitFrequency[currentNode]++

            if (visitFrequency[currentNode] == 2 && currentNode == numNodes) return currentTime

            currentTime = if ((currentTime / signalChange) % 2 == 1) {
                signalChange * (currentTime / signalChange + 1) + travelTime
            } else {
                currentTime + travelTime
            }

            adjacencyList[currentNode]?.forEach { neighbor ->
                if (visitFrequency[neighbor] == 2) return@forEach

                if (shortestDist[neighbor] > currentTime) {
                    secondShortestDist[neighbor] = shortestDist[neighbor]
                    shortestDist[neighbor] = currentTime
                    priorityQueue.offer(intArrayOf(neighbor, currentTime))
                } else if (secondShortestDist[neighbor] > currentTime && shortestDist[neighbor] != currentTime) {
                    secondShortestDist[neighbor] = currentTime
                    priorityQueue.offer(intArrayOf(neighbor, currentTime))
                }
            }
        }

        return 0
    }
}

/**
 * Approach 2: Breadth First Search
 */
@BFS
class SecondMinimumBFS : SecondMinimum {
    override fun invoke(
        numNodes: Int,
        edges: Array<IntArray>,
        travelTime: Int,
        signalChange: Int,
    ): Int {
        val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
        for (edge in edges) {
            val nodeA = edge[0]
            val nodeB = edge[1]
            adjacencyList.computeIfAbsent(nodeA) { mutableListOf() }.add(nodeB)
            adjacencyList.computeIfAbsent(nodeB) { mutableListOf() }.add(nodeA)
        }

        val shortestDist = IntArray(numNodes + 1) { -1 }
        val secondShortestDist = IntArray(numNodes + 1) { -1 }

        val queue: Queue<IntArray> = LinkedList()
        queue.offer(intArrayOf(1, 1))
        shortestDist[1] = 0

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val currentNode = current[0]
            val visitCount = current[1]

            var currentTime = if (visitCount == 1) shortestDist[currentNode] else secondShortestDist[currentNode]

            currentTime = if ((currentTime / signalChange) % 2 == 1) {
                signalChange * (currentTime / signalChange + 1) + travelTime
            } else {
                currentTime + travelTime
            }

            adjacencyList[currentNode]?.forEach { neighbor ->
                if (shortestDist[neighbor] == -1) {
                    shortestDist[neighbor] = currentTime
                    queue.offer(intArrayOf(neighbor, 1))
                } else if (secondShortestDist[neighbor] == -1 && shortestDist[neighbor] != currentTime) {
                    if (neighbor == numNodes) return currentTime
                    secondShortestDist[neighbor] = currentTime
                    queue.offer(intArrayOf(neighbor, 2))
                }
            }
        }

        return 0
    }
}
