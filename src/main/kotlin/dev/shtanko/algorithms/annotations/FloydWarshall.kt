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
 * The Floyd-Warshall algorithm is a classic algorithm used for finding the shortest paths between all pairs of vertices
 * in a weighted graph, where the graph can have positive or negative edge weights but no negative weight cycles.
 * It is an example of a dynamic programming algorithm that works by iteratively improving the solution to all pairs of
 * nodes.
 *
 * # How It Works
 *
 * 1: Initialization:
 * The algorithm begins by initializing a distance matrix `dist` where each element `dist[i][j]` represents the shortest
 * known path from vertex `i` to vertex `j`. Initially, the distance between two vertices is set to the edge weight
 * between them, or infinity if no edge exists. The distance from a vertex to itself is set to 0.
 *
 * 2: Iterative Update:
 * The algorithm then iterates over all possible intermediate vertices (represented by `k`). For each pair of vertices
 * `i` and `j`, it checks whether a path from `i` to `j` through `k` is shorter than the previously known shortest path.
 * If so, the distance matrix is updated to reflect this shorter path.
 *
 * The update formula is:
 * ```kotlin
 * dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
 * ```
 *
 * 3: Repeating for All Pairs:
 * This process is repeated for all pairs of vertices (i, j), with each vertex serving as an intermediate vertex.
 * After the algorithm has gone through all possible intermediate vertices, the final distance matrix contains the
 * shortest distances between all pairs of vertices.
 *
 * Example (Floyd-Warshall Algorithm):
 * ```kotlin
 * fun floydWarshall(graph: Array<IntArray>): Array<IntArray> {
 *     val n = graph.size
 *     val dist = graph.map { it.clone() }.toTypedArray()
 *
 *     for (k in 0 until n) {
 *         for (i in 0 until n) {
 *             for (j in 0 until n) {
 *                 if (dist[i][k] != Int.MAX_VALUE && dist[k][j] != Int.MAX_VALUE) {
 *                     dist[i][j] = minOf(dist[i][j], dist[i][k] + dist[k][j])
 *                 }
 *             }
 *         }
 *     }
 *     return dist
 * }
 * ```
 *
 * # Advantages:
 * * All-Pairs Shortest Paths: Unlike algorithms like Dijkstra's algorithm, which only finds the shortest path from a
 * single source, the Floyd-Warshall algorithm finds the shortest path between all pairs of nodes in the graph.
 * * Simplicity: The algorithm is simple to implement and provides an elegant solution to the problem of finding
 * all-pairs shortest paths.
 *
 * # Considerations:
 * * Time Complexity: Floyd-Warshall has a time complexity of `O(n^3)`, where `n` is the number of vertices in the
 * graph.
 * This can be inefficient for large graphs.
 * * Space Complexity: The algorithm requires `O(n^2)` space to store the distance matrix.
 * * Negative Weight Cycles: While the algorithm works with negative weights, it cannot handle graphs that contain
 * negative weight cycles (i.e., cycles where the sum of the edge weights is negative), as it will incorrectly produce
 * infinite loop behavior.
 *
 * The Floyd-Warshall algorithm is typically used in smaller graphs or when it's necessary to find all-pairs shortest
 * paths in situations where performance isn't a critical concern.
 *
 * @property info An optional string that provides additional information about the Floyd-Warshall algorithm
 * implementation or usage.
 * @constructor Creates a new FloydWarshall annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class FloydWarshall(val info: String = "")
