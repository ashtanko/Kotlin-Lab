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

package dev.shtanko.algorithms.templates

private var seen: MutableSet<Int> = HashSet()

/**
 * Graph: DFS (recursive)
 *
 * For the graph templates, assume the nodes are numbered from 0 to n - 1 and the graph is given as an adjacency list.
 * Depending on the problem, you may need to convert the input into an equivalent adjacency list before using
 * the templates.
 */
private fun fn(graph: Array<IntArray>): Int {
    val startNode = 0
    seen.add(startNode)
    return dfs(startNode, graph)
}

private fun dfs(node: Int, graph: Array<IntArray>): Int {
    var ans = 0
    // do some logic
    for (neighbor in graph[node]) {
        if (!seen.contains(neighbor)) {
            seen.add(neighbor)
            ans += dfs(neighbor, graph)
        }
    }
    return ans
}
