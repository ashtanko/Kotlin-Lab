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

/**
 * 2392. Build a Matrix With Conditions
 * @see <a href="https://leetcode.com/problems/build-a-matrix-with-conditions">Source</a>
 */
fun interface BuildMatrixWithConditions {
    operator fun invoke(k: Int, rowConditions: Array<IntArray>, colConditions: Array<IntArray>): Array<IntArray>
}

class BuildMatrixWithConditionsDFS : BuildMatrixWithConditions {
    override fun invoke(
        k: Int,
        rowConditions: Array<IntArray>,
        colConditions: Array<IntArray>,
    ): Array<IntArray> {
        // Store the topologically sorted sequences.
        val orderRows = topoSort(rowConditions, k)
        val orderColumns = topoSort(colConditions, k)

        // If no topological sort exists, return empty array.
        if (orderRows.isEmpty() || orderColumns.isEmpty()) return Array(0) { IntArray(0) }

        val matrix = Array(k) { IntArray(k) }
        for (i in 0 until k) {
            for (j in 0 until k) {
                if (orderRows[i] == orderColumns[j]) {
                    matrix[i][j] = orderRows[i]
                }
            }
        }
        return matrix
    }

    private fun topoSort(edges: Array<IntArray>, n: Int): List<Int> {
        // Build adjacency list
        val adj = MutableList(n + 1) { mutableListOf<Int>() }
        for (edge in edges) {
            adj[edge[0]].add(edge[1])
        }

        val order = mutableListOf<Int>()
        // 0: not visited, 1: visiting, 2: visited
        val visited = IntArray(n + 1)
        val hasCycle = BooleanArray(1) { false }

        // Perform DFS for each node
        for (i in 1..n) {
            if (visited[i] == 0) {
                dfs(i, adj, visited, order, hasCycle)
                // Return empty if cycle detected
                if (hasCycle[0]) return emptyList()
            }
        }

        // Reverse to get the correct order
        return order.reversed()
    }

    private fun dfs(
        node: Int,
        adj: List<List<Int>>,
        visited: IntArray,
        order: MutableList<Int>,
        hasCycle: BooleanArray,
    ) {
        visited[node] = 1 // Mark node as visiting
        for (neighbor in adj[node]) {
            when (visited[neighbor]) {
                0 -> {
                    dfs(neighbor, adj, visited, order, hasCycle)
                    // Early exit if a cycle is detected
                    if (hasCycle[0]) return
                }

                1 -> {
                    // Cycle detected
                    hasCycle[0] = true
                    return
                }
            }
        }
        // Mark node as visited
        visited[node] = 2
        // Add node to the order
        order.add(node)
    }
}

class BuildMatrixWithConditionsKahn : BuildMatrixWithConditions {
    override fun invoke(
        k: Int,
        rowConditions: Array<IntArray>,
        colConditions: Array<IntArray>,
    ): Array<IntArray> {
        val orderRows = topoSort(rowConditions, k)
        val orderColumns = topoSort(colConditions, k)

        if (orderRows.isEmpty() || orderColumns.isEmpty()) return Array(0) { IntArray(0) }

        val matrix = Array(k) { IntArray(k) }
        for (i in orderRows.indices) {
            for (j in orderColumns.indices) {
                if (orderRows[i] == orderColumns[j]) {
                    matrix[i][j] = orderRows[i]
                }
            }
        }
        return matrix
    }

    private fun topoSort(edges: Array<IntArray>, n: Int): IntArray {
        val adj = Array(n + 1) { mutableListOf<Int>() }
        val deg = IntArray(n + 1)
        val order = IntArray(n)
        var idx = 0

        for (edge in edges) {
            adj[edge[0]].add(edge[1])
            deg[edge[1]]++
        }

        val q = ArrayDeque<Int>()
        for (i in 1..n) {
            if (deg[i] == 0) q.add(i)
        }

        while (q.isNotEmpty()) {
            val f = q.removeFirst()
            order[idx++] = f
            for (v in adj[f]) {
                if (--deg[v] == 0) q.add(v)
            }
        }

        return if (idx == n) order else intArrayOf()
    }
}
