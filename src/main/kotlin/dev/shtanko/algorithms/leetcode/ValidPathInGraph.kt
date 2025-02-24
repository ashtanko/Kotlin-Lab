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

import dev.shtanko.algorithms.annotations.DFS
import java.util.LinkedList
import java.util.Queue

/**
 * 1971. Find if Path Exists in Graph
 * @see <a href="https://leetcode.com/problems/find-if-path-exists-in-graph">Source</a>
 */
fun interface ValidPathInGraph {
    operator fun invoke(num: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean
}

/**
 * 1. Simple union-find without any rank consideration
 */
class ValidPathUnionFind : ValidPathInGraph {
    override operator fun invoke(num: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val set = DisjointSetUnion(num)
        for (edge in edges) {
            set.union(edge[0], edge[1])
        }

        return set.areConnected(source, destination)
    }

    private class DisjointSetUnion(private val n: Int, val parent: IntArray = IntArray(n)) {

        init {
            for (i in 0 until n) {
                parent[i] = i
            }
        }

        fun areConnected(u: Int, v: Int): Boolean {
            return find(u) == find(v)
        }

        fun union(u: Int, v: Int) {
            if (u != v) {
                val a = find(u)
                val b = find(v)
                parent[a] = b
            }
        }

        private fun find(u: Int): Int {
            var x = u
            while (x != parent[x]) {
                x = parent[x]
            }
            parent[u] = x
            return x
        }
    }
}

/**
 * 2. Disjoint Set Union by Rank
 */
class ValidPathUnionByRank : ValidPathInGraph {
    override operator fun invoke(num: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val set = DisjointSetUnion(num)
        for (edge in edges) {
            set.union(edge[0], edge[1])
        }

        return set.areConnected(source, destination)
    }

    private class DisjointSetUnion(
        private val n: Int,
        val parent: IntArray = IntArray(n),
        val rank: IntArray = IntArray(n),
    ) {

        init {
            for (i in 0 until n) {
                parent[i] = i
                rank[i] = 1
            }
        }

        fun areConnected(u: Int, v: Int): Boolean {
            return find(u) == find(v)
        }

        fun union(u: Int, v: Int) {
            if (u != v) {
                val a = find(u)
                val b = find(v)
                if (a != b) {
                    if (rank[a] > rank[b]) {
                        parent[b] = a
                        rank[a] += rank[b]
                    } else {
                        parent[a] = b
                        rank[b] += rank[a]
                    }
                }
            }
        }

        private fun find(u: Int): Int {
            var x = u
            while (x != parent[x]) {
                x = parent[x]
            }
            parent[u] = x
            return x
        }
    }
}

/**
 * 3. DFS - Depth First Search
 */
@DFS
class ValidPathDFS : ValidPathInGraph {

    private var seen = false

    override operator fun invoke(num: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val visited = BooleanArray(num)
        val graph: Array<HashSet<Int>> = Array(num) { HashSet() }
        for (edge in edges) {
            graph[edge[0]].add(edge[1])
            graph[edge[1]].add(edge[0])
        }

        // direct connection exists
        if (graph[source].contains(destination)) {
            return true
        }

        seen = false
        dfs(graph, visited, source, destination)
        return seen
    }

    private fun dfs(graph: Array<HashSet<Int>>, visited: BooleanArray, start: Int, end: Int) {
        if (!visited[start] && !seen) {
            if (start == end) {
                seen = true
                return
            }
            visited[start] = true
            for (neighbor in graph[start]) {
                dfs(graph, visited, neighbor, end)
            }
        }
    }
}

/**
 * 3. BFS - Breadth First Search
 */
class ValidPathBFS : ValidPathInGraph {
    override operator fun invoke(num: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val visited = BooleanArray(num)
        val graph: Array<HashSet<Int>> = Array(num) { java.util.HashSet() }
        for (edge in edges) {
            graph[edge[0]].add(edge[1])
            graph[edge[1]].add(edge[0])
        }

        // direct connection exists
        if (graph[source].contains(destination)) {
            return true
        }

        val queue: Queue<Int> = LinkedList()
        var current: Int
        queue.offer(source)
        visited[source] = true

        while (queue.isNotEmpty()) {
            current = queue.poll()
            if (current == destination) {
                return true
            }
            for (neighbor in graph[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true
                    queue.offer(neighbor)
                }
            }
        }

        return false
    }
}
