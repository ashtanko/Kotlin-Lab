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

import java.util.LinkedList
import java.util.Queue

/**
 * 261. Graph Valid Tree
 * @see <a href="https://leetcode.com/problems/graph-valid-tree/">Source</a>
 */
fun interface GraphValidTree {
    fun validTree(n: Int, edges: Array<IntArray>): Boolean
}

private class GraphValidTreeUnionFind(n: Int) {
    private val parent: IntArray = IntArray(n)
    private val size: IntArray = IntArray(n)

    init {
        for (node in 0 until n) {
            parent[node] = node
            size[node] = 1
        }
    }

    fun Int.find(): Int {
        // Step 1: Find the root.
        var a = this
        var root = a
        while (parent[root] != root) {
            root = parent[root]
        }
        // Step 2: Do a second traversal, this time setting each node to point
        // directly at A as we go.
        while (a != root) {
            val oldRoot = parent[a]
            parent[a] = root
            a = oldRoot
        }
        return root
    }

    fun union(a: Int, b: Int): Boolean {
        // Find the roots for A and B.
        val rootA = a.find()
        val rootB = b.find()
        // Check if A and B are already in the same set.
        if (rootA == rootB) {
            return false
        }
        // We want to ensure the larger set remains the root.
        if (size[rootA] < size[rootB]) {
            // Make rootB the overall root.
            parent[rootA] = rootB
            // The size of the set rooted at B is the sum of the 2.
            size[rootB] += size[rootA]
        } else {
            // Make rootA the overall root.
            parent[rootB] = rootA
            // The size of the set rooted at A is the sum of the 2.
            size[rootA] += size[rootB]
        }
        return true
    }
}

/**
 * Approach 1: Graph Theory + Iterative Depth-First Search
 */
class GVTSimpleIterativeDFS : GraphValidTree {
    override fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        val adjacencyList: MutableList<MutableList<Int>> = ArrayList()
        for (i in 0 until n) {
            adjacencyList.add(ArrayList())
        }
        for (edge in edges) {
            adjacencyList[edge[0]].add(edge[1])
            adjacencyList[edge[1]].add(edge[0])
        }

        val parent: MutableMap<Int, Int> = HashMap()
        parent[0] = -1
        val queue: Queue<Int> = LinkedList()
        queue.offer(0)

        while (queue.isNotEmpty()) {
            val node: Int = queue.poll()
            for (neighbour in adjacencyList[node]) {
                if (parent[node] == neighbour) {
                    continue
                }
                if (parent.containsKey(neighbour)) {
                    return false
                }
                queue.offer(neighbour)
                parent[neighbour] = node
            }
        }

        return parent.size == n
    }
}

/**
 * Approach 2: Advanced Graph Theory + Iterative Depth-First Search
 */
class GVTAdvancedIterativeDFS : GraphValidTree {
    override fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        if (edges.size != n - 1) return false

        // Make the adjacency list.

        // Make the adjacency list.
        val adjacencyList: MutableList<MutableList<Int>> = ArrayList()
        for (i in 0 until n) {
            adjacencyList.add(ArrayList())
        }
        for (edge in edges) {
            adjacencyList[edge[0]].add(edge[1])
            adjacencyList[edge[1]].add(edge[0])
        }

        val queue: Queue<Int> = LinkedList()
        val seen: MutableSet<Int> = HashSet()
        queue.offer(0)
        seen.add(0)

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            for (neighbour in adjacencyList[node]) {
                if (seen.contains(neighbour)) continue
                seen.add(neighbour)
                queue.offer(neighbour)
            }
        }

        return seen.size == n
    }
}

/**
 * Approach 3: Advanced Graph Theory + Union Find
 */
class GVTAdvancedUnionFind : GraphValidTree {
    override fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        // Condition 1: The graph must contain n - 1 edges.
        if (edges.size != n - 1) return false
        // Condition 2: The graph must contain a single connected component.
        // Create a new UnionFind object with n nodes.
        val unionFind = GraphValidTreeUnionFind(n)
        // Add each edge. Check if a merge happened, because if it
        // didn't, there must be a cycle.
        for (edge in edges) {
            val a = edge[0]
            val b = edge[1]
            if (!unionFind.union(a, b)) {
                return false
            }
        }
        // If we got this far, there's no cycles!
        return true
    }
}
