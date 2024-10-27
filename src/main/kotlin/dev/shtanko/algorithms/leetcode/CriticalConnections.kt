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

import dev.shtanko.algorithms.annotations.level.Hard
import java.util.LinkedList
import kotlin.math.max
import kotlin.math.min

/**
 * 1192. Critical Connections in a Network
 * @see <a href="https://leetcode.com/problems/critical-connections-in-a-network">Source</a>
 */
@Hard("https://leetcode.com/problems/critical-connections-in-a-network")
fun interface CriticalConnections {
    operator fun invoke(n: Int, connections: List<List<Int>>): List<List<Int>>
}

/**
 * Approach: Depth First Search for Cycle Detection
 */
class CycleDetection : CriticalConnections {

    private val adjacencyList: MutableMap<Int, MutableList<Int>> = HashMap()
    private val nodeRank: MutableMap<Int, Int?> = HashMap()
    private val connectionMap: MutableMap<Pair<Int, Int>, Boolean> = HashMap()

    override operator fun invoke(n: Int, connections: List<List<Int>>): List<List<Int>> {
        buildGraph(n, connections)
        this.depthFirstSearch(0, 0)

        val criticalConnections: MutableList<List<Int>> = ArrayList()
        for (connection in connectionMap.keys) {
            criticalConnections.add(listOf(connection.first, connection.second))
        }

        return criticalConnections
    }

    private fun depthFirstSearch(node: Int, discoveryRank: Int): Int {
        if (nodeRank[node] != null) {
            return nodeRank.getOrDefault(node, 0) ?: 0
        }

        nodeRank[node] = discoveryRank

        var minRank = discoveryRank + 1
        for (neighbor in adjacencyList.getOrDefault(node, ArrayList())) {
            val neighborRank = nodeRank[neighbor]
            if (neighborRank != null && neighborRank == discoveryRank - 1) {
                continue
            }

            val recursiveRank = this.depthFirstSearch(neighbor, discoveryRank + 1)

            if (recursiveRank <= discoveryRank) {
                val sortedU = min(node, neighbor)
                val sortedV = max(node, neighbor)
                connectionMap.remove(Pair(sortedU, sortedV))
            }

            minRank = min(minRank, recursiveRank)
        }
        return minRank
    }

    private fun buildGraph(n: Int, connections: List<List<Int>>) {
        for (i in 0 until n) {
            adjacencyList[i] = ArrayList()
            nodeRank[i] = null
        }
        for (edge in connections) {
            val u = edge[0]
            val v = edge[1]
            adjacencyList[u]?.add(v)
            adjacencyList[v]?.add(u)
            val sortedU = min(u, v)
            val sortedV = max(u, v)
            connectionMap[Pair(sortedU, sortedV)] = true
        }
    }
}

class CriticalConnectionsGraph : CriticalConnections {
    private var currentTime = 0
    private val criticalBridges = ArrayList<List<Int>>()

    override operator fun invoke(n: Int, connections: List<List<Int>>): List<List<Int>> {
        val graph = buildGraph(n, connections)
        graph.vertices.forEach {
            if (!it.visited) depthFirstSearch(it, graph)
        }
        return criticalBridges
    }

    private fun depthFirstSearch(vertex: Vertex, graph: Graph) {
        currentTime++
        vertex.discoveryTime = currentTime
        vertex.lowTime = currentTime
        vertex.visited = true
        var childrenCount = 0
        for (neighbor in graph.adjList[vertex.id]) {
            if (!neighbor.visited) {
                childrenCount++
                neighbor.parent = vertex.id
                depthFirstSearch(neighbor, graph)
                vertex.lowTime = minOf(vertex.lowTime, neighbor.lowTime)
                if (vertex.parent == -1 && childrenCount > 1) {
                    vertex.isArticulationPoint = true
                } else if (vertex.parent != -1 && neighbor.lowTime >= vertex.discoveryTime) {
                    vertex.isArticulationPoint = true
                }
                if (neighbor.lowTime > vertex.discoveryTime) {
                    criticalBridges.add(listOf(vertex.id, neighbor.id))
                }
            } else if (vertex.parent != neighbor.id) {
                vertex.lowTime = minOf(vertex.lowTime, neighbor.discoveryTime)
            }
        }
    }

    private fun buildGraph(n: Int, connections: List<List<Int>>): Graph {
        val graph = Graph(n)
        connections.forEach { path ->
            val (from, to) = path[0] to path[1]
            graph.addEdge(from, to)
        }
        return graph
    }

    private data class Vertex(
        val id: Int,
        var discoveryTime: Int = 0,
        var lowTime: Int = Int.MAX_VALUE,
        var parent: Int = -1,
        var visited: Boolean = false,
        var isArticulationPoint: Boolean = false,
    )

    private class Graph(n: Int) {
        val vertices = Array(n) { Vertex(it) }
        val adjList = Array(n) { LinkedList<Vertex>() }

        fun addEdge(from: Int, to: Int) {
            val (fromVertex, toVertex) = vertices[from] to vertices[to]
            adjList[from].add(toVertex)
            adjList[to].add(fromVertex)
        }
    }
}
