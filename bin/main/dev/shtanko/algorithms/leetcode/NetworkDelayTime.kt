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
import java.util.PriorityQueue
import java.util.Queue
import kotlin.math.max

/**
 * 743. Network Delay Time
 * @see <a href="https://leetcode.com/problems/network-delay-time/">Source</a>
 */
fun interface NetworkDelayTime {
    operator fun invoke(times: Array<IntArray>, n: Int, k: Int): Int
}

class NetworkDelayTimeDFS : NetworkDelayTime {
    override operator fun invoke(times: Array<IntArray>, n: Int, k: Int): Int {
        // Build the adjacency list
        for (time in times) {
            val source = time[0]
            val dest = time[1]
            val travelTime = time[2]
            adj.putIfAbsent(source, ArrayList())
            adj[source]?.add(Pair(travelTime, dest))
        }

        // Sort the edges connecting to every node
        for (node in adj.keys) {
            adj[node]?.let { it.sortWith { a, b -> a.first - b.second } }
        }
        val signalReceivedAt = IntArray(n + 1) { Int.MAX_VALUE }
        dfs(signalReceivedAt, k, 0)
        var answer = Int.MIN_VALUE
        for (node in 1..n) {
            answer = max(answer, signalReceivedAt[node])
        }

        // Integer.MAX_VALUE signifies at least one node is unreachable
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    // Adjacency list
    private val adj: MutableMap<Int, MutableList<Pair<Int, Int>>> = HashMap()

    private fun dfs(signalReceivedAt: IntArray, currNode: Int, currTime: Int) {
        // If the current time is greater than or equal to the fastest signal received
        // Then no need to iterate over adjacent nodes
        if (currTime >= signalReceivedAt[currNode]) {
            return
        }

        // Fastest signal time for currNode so far
        signalReceivedAt[currNode] = currTime
        if (!adj.containsKey(currNode)) {
            return
        }

        // Broadcast the signal to adjacent nodes
        val pairs = adj[currNode] ?: return
        for (edge in pairs) {
            val (travelTime, neighborNode) = edge
            // currTime + time : time when signal reaches neighborNode
            dfs(signalReceivedAt, neighborNode, currTime + travelTime)
        }
    }
}

class NetworkDelayTimeBFS : NetworkDelayTime {
    override operator fun invoke(times: Array<IntArray>, n: Int, k: Int): Int {
        // Build the adjacency list
        for (time in times) {
            val source = time[0]
            val dest = time[1]
            val travelTime = time[2]
            adj.putIfAbsent(source, ArrayList())
            adj[source]?.add(Pair(travelTime, dest))
        }
        val signalReceivedAt = IntArray(n + 1) { Int.MAX_VALUE }
        bfs(signalReceivedAt, k)
        var answer = Int.MIN_VALUE
        for (i in 1..n) {
            answer = max(answer, signalReceivedAt[i])
        }

        // INT_MAX signifies at least one node is unreachable
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    // Adjacency list
    var adj: MutableMap<Int, MutableList<Pair<Int, Int>>> = HashMap()

    private fun bfs(signalReceivedAt: IntArray, sourceNode: Int) {
        val q: Queue<Int> = LinkedList()
        q.add(sourceNode)

        // Time for starting node is 0
        signalReceivedAt[sourceNode] = 0
        while (q.isNotEmpty()) {
            val currNode: Int = q.remove()
            if (!adj.containsKey(currNode)) {
                continue
            }

            // Broadcast the signal to adjacent nodes
            val pairs = adj[currNode] ?: return
            for (edge in pairs) {
                val (travelTime, neighborNode) = edge

                // Fastest signal time for neighborNode so far
                // signalReceivedAt[currNode] + time :
                // time when signal reaches neighborNode
                val arrivalTime = signalReceivedAt[currNode] + travelTime
                if (signalReceivedAt[neighborNode] > arrivalTime) {
                    signalReceivedAt[neighborNode] = arrivalTime
                    q.add(neighborNode)
                }
            }
        }
    }
}

class NetworkDelayTimeDijkstra : NetworkDelayTime {
    data class GraphNode(val id: Int, val neighbors: MutableList<GraphNode> = mutableListOf())

    override operator fun invoke(times: Array<IntArray>, n: Int, k: Int): Int {
        val graph = (1..n).associateWith { GraphNode(it) }
        val weight = mutableMapOf<Pair<Int, Int>, Int>()
        val distance = mutableMapOf<Int, Int>()
        times.forEach { time ->
            graph[time[1]]?.let { graph[time[0]]?.neighbors?.add(it) }
            weight[Pair(time[0], time[1])] = time[2]
        }
        dijkstra(graph, weight, distance, k)

        return distance.map { it.value }.maxOf { it }.takeIf { it != Int.MAX_VALUE } ?: -1
    }

    private fun dijkstra(
        graph: Map<Int, GraphNode>,
        weight: Map<Pair<Int, Int>, Int>,
        distance: MutableMap<Int, Int>,
        sourceId: Int,
    ) {
        initializeSingleSource(graph, distance, sourceId)
        val minHeap = PriorityQueue<GraphNode> { o1, o2 -> (distance[o1.id] ?: 0) - (distance[o2.id] ?: 0) }
        minHeap.offer(graph[sourceId]!!)
        while (minHeap.isNotEmpty()) {
            val node = minHeap.poll()
            node.neighbors.forEach { graphNode ->
                val newDist = (distance[node.id] ?: 0) + (weight[Pair(node.id, graphNode.id)] ?: 0)
                val safeDist = distance[graphNode.id] ?: 0
                if (safeDist > newDist) {
                    distance[graphNode.id] = newDist
                    graph[graphNode.id]?.let { minHeap.offer(it) }
                }
            }
        }
    }

    private fun initializeSingleSource(graph: Map<Int, GraphNode>, distance: MutableMap<Int, Int>, sourceId: Int) {
        graph.map { it.key }.forEach { distance[it] = Int.MAX_VALUE }
        distance[sourceId] = 0
    }
}
