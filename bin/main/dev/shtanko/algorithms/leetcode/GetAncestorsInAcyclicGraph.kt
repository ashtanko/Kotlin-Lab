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

import dev.shtanko.algorithms.annotations.DFS
import java.util.LinkedList

/**
 * 2192. All Ancestors of a Node in a Directed Acyclic Graph
 * @see <a href="https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph">Source</a>
 */
fun interface GetAncestorsInAcyclicGraph {
    operator fun invoke(n: Int, edges: Array<IntArray>): List<List<Int>>
}

@DFS
class GetAncestorsInAcyclicGraphDFS : GetAncestorsInAcyclicGraph {
    override fun invoke(n: Int, edges: Array<IntArray>): List<List<Int>> {
        // Initialize adjacency list for the graph
        val adjacencyList = Array(n) { mutableListOf<Int>() }
        for (i in 0 until n) {
            adjacencyList[i] = mutableListOf()
        }

        // Populate the adjacency list with reversed edges
        for (edge in edges) {
            val from = edge[0]
            val to = edge[1]
            adjacencyList[to].add(from)
        }

        val ancestorsList = mutableListOf<List<Int>>()

        // For each node, find all its ancestors (children in reversed graph)
        for (i in 0 until n) {
            val ancestors = mutableListOf<Int>()
            val visited = mutableSetOf<Int>()
            findChildren(i, adjacencyList, visited)
            // Add visited nodes to the current nodes' ancestor list
            for (node in 0 until n) {
                if (node == i) continue
                if (visited.contains(node)) ancestors.add(node)
            }
            ancestorsList.add(ancestors)
        }

        return ancestorsList
    }

    // Helper method to perform DFS and find all children of a given node
    private fun findChildren(
        currentNode: Int,
        adjacencyList: Array<MutableList<Int>>,
        visitedNodes: MutableSet<Int>,
    ) {
        // Mark current node as visited
        visitedNodes.add(currentNode)

        // Recursively traverse all neighbors
        for (neighbour in adjacencyList[currentNode]) {
            if (!visitedNodes.contains(neighbour)) {
                findChildren(neighbour, adjacencyList, visitedNodes)
            }
        }
    }
}

class GetAncestorsInAcyclicGraphDFSOpt : GetAncestorsInAcyclicGraph {
    override fun invoke(n: Int, edges: Array<IntArray>): List<List<Int>> {
        // Initialize adjacency list for each node and ancestors list
        val adjacencyList = Array(n) { mutableListOf<Int>() }
        val ancestors = MutableList(n) { mutableListOf<Int>() }

        // Populate the adjacency list with edges
        for (edge in edges) {
            val from = edge[0]
            val to = edge[1]
            adjacencyList[from].add(to)
        }

        // Perform DFS for each node to find all its ancestors
        for (i in 0 until n) {
            findAncestorsDFS(i, adjacencyList, i, ancestors)
        }

        return ancestors
    }

    // Helper method to perform DFS and find ancestors
    private fun findAncestorsDFS(
        ancestor: Int,
        adjacencyList: Array<MutableList<Int>>,
        currentNode: Int,
        ancestors: MutableList<MutableList<Int>>,
    ) {
        for (childNode in adjacencyList[currentNode]) {
            // Check if the ancestor is already added to avoid duplicates
            if (ancestors[childNode].isEmpty() || ancestors[childNode].last() != ancestor) {
                ancestors[childNode].add(ancestor)
                findAncestorsDFS(ancestor, adjacencyList, childNode, ancestors)
            }
        }
    }
}

class GetAncestorsInAcyclicGraphSort : GetAncestorsInAcyclicGraph {
    override fun invoke(n: Int, edges: Array<IntArray>): List<List<Int>> {
        // Create adjacency list
        val adjacencyList = Array(n) { mutableListOf<Int>() }

        // Fill the adjacency list and indegree array based on the edges
        val indegree = IntArray(n)
        for (edge in edges) {
            val from = edge[0]
            val to = edge[1]
            adjacencyList[from].add(to)
            indegree[to]++
        }

        // Queue for nodes with no incoming edges (starting points for topological sort)
        val nodesWithZeroIndegree = LinkedList<Int>()
        for (i in indegree.indices) {
            if (indegree[i] == 0) {
                nodesWithZeroIndegree.add(i)
            }
        }

        // List to store the topological order of nodes
        val topologicalOrder = mutableListOf<Int>()
        while (nodesWithZeroIndegree.isNotEmpty()) {
            val currentNode = nodesWithZeroIndegree.poll()
            topologicalOrder.add(currentNode)

            // Reduce indegree of neighboring nodes and add them to the queue
            // if they have no more incoming edges
            for (neighbor in adjacencyList[currentNode]) {
                indegree[neighbor]--
                if (indegree[neighbor] == 0) {
                    nodesWithZeroIndegree.add(neighbor)
                }
            }
        }

        // Initialize the result list and set list for storing ancestors
        val ancestorsList = MutableList(n) { mutableListOf<Int>() }
        val ancestorsSetList = MutableList(n) { mutableSetOf<Int>() }

        // Fill the set list with ancestors using the topological order
        for (node in topologicalOrder) {
            for (neighbor in adjacencyList[node]) {
                // Add immediate parent, and other ancestors.
                ancestorsSetList[neighbor].add(node)
                ancestorsSetList[neighbor].addAll(ancestorsSetList[node])
            }
        }

        // Convert sets to lists and sort them
        for (i in ancestorsList.indices) {
            ancestorsList[i].addAll(ancestorsSetList[i])
            ancestorsList[i].sort()
        }

        return ancestorsList
    }
}
