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
 * The Bellman-Ford algorithm is a fundamental graph algorithm used to find the shortest paths from a single source
 * vertex to all other vertices in a weighted graph. Unlike Dijkstra's algorithm, it can handle graphs with negative
 * weight edges, making it versatile for a variety of applications.
 *
 * # How It Works
 *
 * 1: Initialization:
 * Initialize the distance to the source vertex as 0 and the distances to all other vertices as infinity. Create an
 * array to store the shortest distance to each vertex.
 * Example:
 * ```kotlin
 * val distance = IntArray(vertices) { Int.MAX_VALUE }
 * distance[source] = 0
 * ```
 *
 * 2: Relaxation:
 * For each edge in the graph, update the distance to the destination vertex if the sum of the distance to the source
 * vertex and the edge weight is less than the current distance to the destination vertex. Repeat this process for
 * (vertices - 1) iterations.
 * Example:
 * ```kotlin
 * for (i in 1 until vertices) {
 *     for (edge in edges) {
 *         val (u, v, weight) = edge
 *         if (distance[u] != Int.MAX_VALUE && distance[u] + weight < distance[v]) {
 *             distance[v] = distance[u] + weight
 *         }
 *     }
 * }
 * ```
 *
 * 3: Negative Cycle Detection:
 * After (vertices - 1) iterations, check for negative weight cycles. If a distance can still be updated, a negative
 * weight cycle exists.
 * Example:
 * ```kotlin
 * for (edge in edges) {
 *     val (u, v, weight) = edge
 *     if (distance[u] != Int.MAX_VALUE && distance[u] + weight < distance[v]) {
 *         throw IllegalArgumentException("Graph contains a negative weight cycle")
 *     }
 * }
 * ```
 *
 * # Advantages:
 * * Handles Negative Weights: The Bellman-Ford algorithm works correctly with graphs containing negative weight edges.
 * * Simplicity: The algorithm is straightforward to implement and easy to understand.
 *
 * # Considerations:
 * * Performance: The algorithm has a time complexity of O(V * E), where V is the number of vertices and E is the
 *   number of edges, making it less efficient for dense graphs compared to other shortest path algorithms.
 * * Negative Cycles: It can detect negative weight cycles, which are cycles where the total weight of the edges is
 *   negative, making it unique among shortest path algorithms.
 *
 * The Bellman-Ford algorithm is particularly useful in scenarios where graphs contain negative weight edges or cycles,
 * such as network routing, currency arbitrage detection, and scheduling problems.
 *
 * @property info An optional string that provides additional information about the Bellman-Ford algorithm
 * implementation or usage.
 * @constructor Creates a new BellmanFord annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class BellmanFord(val info: String = "")
