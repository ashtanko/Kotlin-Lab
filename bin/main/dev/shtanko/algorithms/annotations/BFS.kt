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

package dev.shtanko.algorithms.annotations

/**
 * The Breadth-First Search (BFS) algorithm is a fundamental graph traversal technique used to explore the vertices of a
 * graph level by level. It is particularly useful for finding the shortest path in unweighted graphs and solving
 * connectivity-related problems.
 *
 * # How It Works
 *
 * 1: Initialization:
 * Begin the traversal from a given source vertex. Use a queue to manage the vertices to be explored. Mark the source
 * vertex as visited.
 * Example:
 * ```kotlin
 * val visited = BooleanArray(vertices) { false }
 * val queue: Queue<Int> = LinkedList()
 * queue.add(source)
 * visited[source] = true
 * ```
 *
 * 2: Traversal:
 * While the queue is not empty, dequeue a vertex, process it, and enqueue all its unvisited adjacent vertices, marking
 * them as visited to avoid revisiting.
 * Example:
 * ```kotlin
 * while (queue.isNotEmpty()) {
 *     val current = queue.poll()
 *     for (neighbor in graph[current]) {
 *         if (!visited[neighbor]) {
 *             queue.add(neighbor)
 *             visited[neighbor] = true
 *         }
 *     }
 * }
 * ```
 *
 * 3: Shortest Path in Unweighted Graphs:
 * BFS can be used to find the shortest path in an unweighted graph by tracking the distance from the source vertex to
 * each visited vertex.
 * Example:
 * ```kotlin
 * val distance = IntArray(vertices) { -1 }
 * distance[source] = 0
 * while (queue.isNotEmpty()) {
 *     val current = queue.poll()
 *     for (neighbor in graph[current]) {
 *         if (distance[neighbor] == -1) {
 *             distance[neighbor] = distance[current] + 1
 *             queue.add(neighbor)
 *         }
 *     }
 * }
 * ```
 *
 * # Advantages:
 * * Level-Order Exploration: BFS explores all vertices at the same level before moving to the next, making it ideal for
 *   finding the shortest path in unweighted graphs.
 * * Simplicity: BFS is straightforward to implement and understand.
 *
 * # Considerations:
 * * Space Complexity: BFS requires additional space to store the queue and visited vertices, with a complexity of O(V)
 *   in the worst case.
 * * Applicability: BFS is not suitable for graphs with weighted edges when shortest path calculation is required. For
 *   weighted graphs, algorithms like Dijkstra's or Bellman-Ford are more appropriate.
 *
 * BFS is widely used in solving problems such as finding connected components, detecting cycles, and solving puzzles
 * like the shortest path in a maze. Its level-order traversal property makes it a versatile and powerful algorithm.
 *
 * @property info An optional string that provides additional information about the BFS algorithm implementation
 * or usage.
 * @constructor Creates a new BFS annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class BFS(val info: String = "")
