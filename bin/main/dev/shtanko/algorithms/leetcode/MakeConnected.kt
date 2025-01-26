/*
 * Copyright 2023 Oleksii Shtanko
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
 * 1319. Number of Operations to Make Network Connected
 * @see <a href="https://leetcode.com/problems/number-of-operations-to-make-network-connected/">Source</a>
 */
fun interface MakeConnected {
    operator fun invoke(n: Int, connections: Array<IntArray>): Int

    fun findParent(parent: IntArray, i: Int): Int {
        var i0 = i
        while (i0 != parent[i0]) i0 = parent[i0]
        return i0 // without path compression
    }
}

/**
 * ✔️ Solution 1a: Naive Union-Find ~ 23ms
 */
class MakeConnectedNaiveUnionFind : MakeConnected {
    override operator fun invoke(n: Int, connections: Array<IntArray>): Int {
        if (connections.size < n - 1) return -1 // To connect all nodes need at least n-1 edges
        val parent = IntArray(n)
        for (i in 0 until n) parent[i] = i
        var components = n
        for (c in connections) {
            val p1 = findParent(parent, c[0])
            val p2 = findParent(parent, c[1])
            if (p1 != p2) {
                parent[p1] = p2 // Union 2 component
                components--
            }
        }
        return components - 1 // Need (components-1) cables to connect components together
    }
}

/**
 * ✔️ Solution 1c: Union-Find with Path Compression and Union by Size ~ 3ms
 */
class UnionBySizeMakeConnected : MakeConnected {
    override operator fun invoke(n: Int, connections: Array<IntArray>): Int {
        if (connections.size < n - 1) return -1 // to connect all nodes need at least n-1 edges

        val parent = IntArray(n)
        val size = IntArray(n)
        for (i in 0 until n) {
            parent[i] = i
            size[i] = 1
        }
        var components = n
        for (c in connections) {
            val p1: Int = findParent(parent, c[0])
            val p2: Int = findParent(parent, c[1])
            if (p1 != p2) {
                if (size[p1] < size[p2]) { // merge small size to large size
                    size[p2] += size[p1]
                    parent[p1] = p2
                } else {
                    size[p1] += size[p2]
                    parent[p2] = p1
                }
                components--
            }
        }
        return components - 1 // need (components-1) cables to connect components together
    }
}

/**
 * ✔️ Solution 2: DFS ~ 11ms
 */
class MakeConnectedDFS : MakeConnected {
    override operator fun invoke(n: Int, connections: Array<IntArray>): Int {
        if (connections.size < n - 1) return -1 // To connect all nodes need at least n-1 edges

        val graph: Array<MutableList<Int>> = Array(n) { mutableListOf() }
        for (i in 0 until n) graph[i] = ArrayList()
        for (c in connections) {
            graph[c[0]].add(c[1])
            graph[c[1]].add(c[0])
        }
        var components = 0
        val visited = BooleanArray(n)
        for (v in 0 until n) {
            components += dfs(v, graph, visited)
        }
        return components - 1 // Need (components-1) cables to connect components together
    }

    private fun dfs(u: Int, graph: Array<MutableList<Int>>, visited: BooleanArray): Int {
        if (visited[u]) return 0
        visited[u] = true
        for (v in graph[u]) dfs(v, graph, visited)
        return 1
    }
}

/**
 * ✔️ Solution 3: BFS ~ 12ms
 */
class MakeConnectedBFS : MakeConnected {
    override operator fun invoke(n: Int, connections: Array<IntArray>): Int {
        if (connections.size < n - 1) {
            return -1 // To connect all nodes need at least n-1 edges
        }
        val graph: Array<MutableList<Int>> = Array(n) { mutableListOf() }
        for (c in connections) {
            graph[c[0]].add(c[1])
            graph[c[1]].add(c[0])
        }
        var components = 0
        val visited = BooleanArray(n)
        for (v in 0 until n) components += bfs(v, graph.map { it.toList() }.toTypedArray(), visited)
        return components - 1 // Need (components-1) cables to connect components together
    }

    private fun bfs(src: Int, graph: Array<List<Int>>, visited: BooleanArray): Int {
        if (visited[src]) return 0
        visited[src] = true
        val q: Queue<Int> = LinkedList()
        q.offer(src)
        while (q.isNotEmpty()) {
            val u: Int = q.poll()
            for (v in graph[u]) {
                if (!visited[v]) {
                    visited[v] = true
                    q.offer(v)
                }
            }
        }
        return 1
    }
}
