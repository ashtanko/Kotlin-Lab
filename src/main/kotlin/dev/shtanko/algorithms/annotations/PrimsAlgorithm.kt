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
 * Prim's Algorithm is a greedy algorithm used to find the minimum spanning tree (MST) of a weighted, undirected graph.
 * The algorithm works by starting with a single vertex and expanding the MST one edge at a time, choosing the edge with
 * the smallest weight that connects a vertex in the MST to a vertex outside of it.
 *
 * # How It Works
 *
 * 1. Start with an arbitrary vertex and add it to the MST.
 * 2. Select the edge with the smallest weight that connects a vertex inside the MST to a vertex outside the MST.
 * 3. Add the chosen edge and the corresponding vertex to the MST.
 * 4. Repeat steps 2 and 3 until all vertices are included in the MST.
 *
 * Prim's algorithm guarantees the MST, as it always chooses the edge with the smallest weight at each step, ensuring
 * the minimal total weight of the spanning tree.
 *
 * The algorithm can be implemented using a priority queue or binary heap to efficiently choose the next smallest edge.
 *
 * Given a graph with vertices and weighted edges, Prim's algorithm follows these steps:
 * - Initialize a set `MST` to keep track of the vertices included in the minimum spanning tree.
 * - Start with an arbitrary vertex and mark it as part of the MST.
 * - While the MST doesn't contain all vertices, pick the minimum weight edge that connects a vertex in the MST to a
 * vertex outside of it, add the edge and the vertex to the MST.
 *
 * # Time Complexity
 * - With an adjacency matrix and a simple approach, the time complexity is O(V^2), where V is the number of vertices.
 * - With a priority queue and adjacency list representation, the time complexity improves to O(E log V), where E is
 * the number of edges and V is the number of vertices.
 *
 * # Example:
 * Consider the following graph:
 * ```
 *  A --(1)-- B --(4)-- D
 *   |       /   \
 *  (2)   (3)   (5)
 *   |  /       |
 *  C --------- E
 * ```
 * Starting from vertex `A`, Prim's algorithm will select edges `(A, B)`, `(B, C)`, `(C, E)`, and `(B, D)` to form the
 * MST.
 *
 * The resulting MST will have the following edges:
 * ```
 * A --(1)-- B --(3)-- C --(2)-- E --(5)-- D
 * ```
 *
 * # Advantages:
 * * Simple and greedy: Prim's algorithm is easy to implement and ensures the minimum spanning tree.
 * * Efficient with adjacency lists: When combined with a priority queue, the algorithm runs efficiently for sparse
 * graphs.
 *
 * # Disadvantages:
 * * Can be slow for dense graphs: Without efficient data structures, Prim's algorithm can be slower for graphs with
 * many edges.
 * * Requires the entire graph to be known upfront: The algorithm needs the full graph to compute the MST.
 *
 * # Applications:
 * - Network design: For connecting a set of nodes with the least cost.
 * - Cluster analysis: For connecting objects based on some minimal distance metric.
 * - Minimum spanning tree problems in computer networks, circuit design, etc.
 *
 * @property info An optional string that provides additional information about the Prim's algorithm implementation
 * or usage.
 * @constructor Creates a new PrimsAlgorithm annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class PrimsAlgorithm(val info: String = "")
