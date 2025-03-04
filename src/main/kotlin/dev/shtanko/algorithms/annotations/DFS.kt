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
 * The Depth-First Search (DFS) approach is a fundamental algorithm used for traversing or searching through
 * tree or graph data structures. It explores as far down a branch of the tree or graph as possible before backtracking.
 *
 * # How It Works
 *
 * 1: Traverse Down to the Deepest Node:
 * Starting at the root or any arbitrary node, DFS explores the deepest possible node along each branch.
 * When it reaches a node with no unvisited adjacent nodes, it backtracks to the previous node to explore the next
 * branch.
 *
 * 2: Use a Stack (or Recursion):
 * DFS can be implemented using a stack to store the nodes to visit next. Alternatively, the algorithm can be
 * implemented recursively, with the function call stack serving as the stack of nodes.
 *
 * Example (Recursive DFS for a Graph):
 * ```kotlin
 * fun dfs(graph: Map<Int, List<Int>>, start: Int, visited: MutableSet<Int> = mutableSetOf()) {
 *     visited.add(start)
 *     println(start)
 *     for (neighbor in graph[start] ?: emptyList()) {
 *         if (neighbor !in visited) {
 *             dfs(graph, neighbor, visited)
 *         }
 *     }
 * }
 * ```
 *
 * 3: Backtrack When Necessary:
 * When a node is fully explored (i.e., all its neighbors are visited), the algorithm backtracks and checks the
 * next unvisited neighbor of its parent node.
 *
 * Example (Iterative DFS for a Graph):
 * ```kotlin
 * fun dfs(graph: Map<Int, List<Int>>, start: Int) {
 *     val stack = mutableListOf(start)
 *     val visited = mutableSetOf<Int>()
 *     while (stack.isNotEmpty()) {
 *         val node = stack.removeAt(stack.size - 1)
 *         if (node !in visited) {
 *             println(node)
 *             visited.add(node)
 *             stack.addAll(graph[node]?.filter { it !in visited } ?: emptyList())
 *         }
 *     }
 * }
 * ```
 *
 * # Advantages:
 * * Simple to Implement: DFS is conceptually simple and can be implemented with basic recursion or using an explicit
 * stack.
 * * Memory Efficiency: DFS requires less memory than some other algorithms, like Breadth-First Search (BFS), as it
 * explores one branch at a time.
 * * Can Be Used for Multiple Problems: DFS is versatile and can be adapted to solve various problems like finding
 * connected components, cycle detection, and topological sorting.
 *
 * # Considerations:
 * * May Get Stuck in Infinite Loops: Without proper checks for revisiting nodes, DFS may enter infinite loops in graphs
 * with cycles.
 * * May Not Find the Shortest Path: DFS does not guarantee finding the shortest path in a graph, unlike BFS.
 * * High Memory Usage for Deep Recursion: Recursive DFS can cause stack overflow errors for large or deep graphs,
 * particularly in languages with a small recursion stack size.
 *
 * DFS is particularly useful when you need to explore a solution space deeply or exhaustively, especially in problems
 * related to trees, graphs, or solving puzzles (e.g., Sudoku, maze-solving).
 *
 * @property info An optional string that provides additional information about the DFS algorithm implementation or
 * usage.
 * @constructor Creates a new DFS annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class DFS(val info: String = "")
