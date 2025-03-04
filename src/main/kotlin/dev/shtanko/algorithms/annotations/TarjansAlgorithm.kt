/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.annotations

/**
 * The Tarjan's Algorithm annotation is used to indicate that a function or class implements Tarjan's Algorithm for
 * finding strongly connected components (SCCs) in a directed graph. This algorithm is widely used in graph theory and
 * is efficient for determining
 * the SCCs of a graph in linear time, O(V + E), where V is the number of vertices and E is the number of edges.
 *
 * # How It Works
 *
 * Tarjan's Algorithm uses a depth-first search (DFS) approach to identify strongly connected components in a
 * directed graph.
 * An SCC is a maximal subgraph where every vertex is reachable from every other vertex in that subgraph.
 *
 * The key idea of Tarjan's Algorithm is to perform DFS while keeping track of the discovery times of nodes and the
 * lowest point reachable from each node. By maintaining these values, the algorithm can determine when a strongly
 * connected component has been fully discovered.
 *
 * The algorithm works as follows:
 * 1. Each node is assigned a discovery time and a low-link value.
 * 2. Nodes are visited via DFS, and each node is placed on a stack.
 * 3. The algorithm backtracks and checks the low-link values to determine when a strongly connected component has been
 * completely visited.
 * 4. Once a SCC is found, it is popped off the stack, and the process continues until all nodes have been processed.
 *
 * # Example:
 * Here's a simplified implementation of Tarjan's Algorithm for finding SCCs in a directed graph:
 *
 * ```kotlin
 * fun tarjanSCC(graph: List<List<Int>>): List<List<Int>> {
 *     val index = IntArray(graph.size) { -1 }
 *     val lowLink = IntArray(graph.size)
 *     val onStack = BooleanArray(graph.size)
 *     val stack = mutableListOf<Int>()
 *     val sccs = mutableListOf<List<Int>>()
 *     var currentIndex = 0
 *
 *     fun dfs(v: Int) {
 *         index[v] = currentIndex++
 *         lowLink[v] = index[v]
 *         stack.add(v)
 *         onStack[v] = true
 *
 *         for (w in graph[v]) {
 *             if (index[w] == -1) {
 *                 dfs(w)
 *                 lowLink[v] = minOf(lowLink[v], lowLink[w])
 *             } else if (onStack[w]) {
 *                 lowLink[v] = minOf(lowLink[v], index[w])
 *             }
 *         }
 *
 *         if (lowLink[v] == index[v]) {
 *             val scc = mutableListOf<Int>()
 *             var w: Int
 *             do {
 *                 w = stack.removeAt(stack.size - 1)
 *                 onStack[w] = false
 *                 scc.add(w)
 *             } while (w != v)
 *             sccs.add(scc)
 *         }
 *     }
 *
 *     for (v in graph.indices) {
 *         if (index[v] == -1) {
 *             dfs(v)
 *         }
 *     }
 *
 *     return sccs
 * }
 * ```
 *
 * # Advantages:
 * * **Efficient Algorithm**: Tarjan's Algorithm runs in linear time, O(V + E), which is optimal for finding SCCs.
 * * **Space Efficient**: It only requires a few arrays to store state, making it space efficient.
 * * **Widely Used**: This algorithm is well-known and widely used for solving problems related to graph connectivity.
 *
 * # Disadvantages:
 * * **Complexity**: The algorithm is more complex than simpler graph traversal algorithms like DFS or BFS, requiring
 * careful management of indices and low-link values.
 * * **Requires Recursive DFS**: The depth-first search used in Tarjan's Algorithm can lead to issues with stack
 * overflow for very large graphs if not optimized.
 *
 * Tarjan's Algorithm is an essential tool for graph-based problems, particularly when dealing with strongly connected
 * components in directed graphs.
 *
 * @property info An optional string that provides additional information about the Tarjan's Algorithm implementation
 * or its usage.
 * @constructor Creates a new TarjansAlgorithm annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class TarjansAlgorithm(val info: String = "")
