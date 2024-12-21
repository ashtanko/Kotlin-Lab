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

package dev.shtanko.algorithms.dfs

/**
 * Depth First Search (DFS) algorithm.
 * The algorithm is used to traverse or search through tree or graph data structures.
 * It explores as far down a branch of the tree or graph as possible before backtracking.
 *
 * @param graph The graph to traverse.
 * @param start The starting node for the traversal.
 * @param visited The set of visited nodes.
 */
private fun dfs(graph: Map<Int, List<Int>>, start: Int, visited: MutableSet<Int> = mutableSetOf()) {
    visited.add(start)
    println(start)
    for (neighbor in graph[start] ?: emptyList()) {
        if (neighbor !in visited) {
            dfs(graph, neighbor, visited)
        }
    }
}

/**
 * Example of Depth First Search (DFS) algorithm.
 * The algorithm is used to traverse or search through tree or graph data structures.
 * It explores as far down a branch of the tree or graph as possible before backtracking.
 */
fun main() {
    /**
     * Graph visualization:
     * 0 -- 1 -- 3
     * |    |
     * 2    4
     */
    val graph = mapOf(
        0 to listOf(1, 2),
        1 to listOf(0, 3, 4),
        2 to listOf(0),
        3 to listOf(1),
        4 to listOf(1),
    )

    dfs(graph, 0)
}
