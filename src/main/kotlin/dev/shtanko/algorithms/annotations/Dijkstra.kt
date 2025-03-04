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
 * Dijkstra's algorithm is a popular algorithm used for finding the shortest paths between nodes in a graph.
 * It can handle graphs with non-negative edge weights. Here’s an overview of how it works:
 *
 * ### Steps of Dijkstra's Algorithm:
 *
 * #### Initialization:
 * - Set the distance to the source node to 0 and the distance to all other nodes to infinity.
 * - Create a priority queue (or a min-heap) to keep track of the node with the smallest known distance.
 *
 * #### Processing the Nodes:
 * - While there are nodes in the priority queue:
 *   - Extract the node `u` with the smallest distance from the priority queue.
 *   - For each neighbor `v` of `u`, calculate the tentative distance from the source node to `v` through `u`.
 *   - If this new distance is smaller than the currently known distance to `v`, update the distance to `v` and add `v`
 *      to the priority queue.
 *
 * #### Termination:
 * - The algorithm terminates when the priority queue is empty, and all nodes have their shortest distance from the
 * source node.
 *
 * ### Detailed Explanation:
 *
 * #### Initialization:
 * - **Distance Table**: Maintain a table of distances from the source node to every other node. Initialize the source
 * node's distance to 0 and all others to infinity.
 * - **Priority Queue**: Use a priority queue to fetch the node with the smallest distance quickly.
 *
 * #### Relaxation:
 * - **Extract-Min**: Extract the node `u` with the smallest distance from the priority queue.
 * - **Update Distances**: For each neighbor `v` of `u`, compute the distance `d = dist[u] + weight(u, v)`.
 *   - If `d` is less than the known distance `dist[v]`, update `dist[v]` to `d`.
 *   - Add or update `v` in the priority queue with the new distance.
 *
 * #### Termination:
 * - The algorithm stops when all nodes have been processed (i.e., the priority queue is empty).
 *
 * ### Example:
 * Consider a graph with nodes A, B, C, D, and E with the following edges and weights:
 * - A -> B (1)
 * - A -> C (4)
 * - B -> C (2)
 * - B -> D (5)
 * - C -> D (1)
 * - D -> E (3)
 *
 * To find the shortest path from node A to all other nodes using Dijkstra’s algorithm:
 *
 * #### Initialize distances:
 * ```css
 * dist[A] = 0
 * dist[B] = ∞
 * dist[C] = ∞
 * dist[D] = ∞
 * dist[E] = ∞
 * ```
 * Priority queue: `[(A, 0)]`
 *
 * #### Process node A:
 * Update distances:
 * ```css
 * dist[B] = 1
 * dist[C] = 4
 * ```
 * Priority queue: `[(B, 1), (C, 4)]`
 *
 * #### Process node B:
 * Update distances:
 * ```scss
 * dist[C] = 3  (since 1 + 2 < 4)
 * dist[D] = 6
 * ```
 * Priority queue: `[(C, 3), (C, 4), (D, 6)]`
 *
 * #### Process node C:
 * Update distances:
 * ```scss
 * dist[D] = 4  (since 3 + 1 < 6)
 * ```
 * Priority queue: `[(C, 4), (D, 4), (D, 6)]`
 *
 * #### Process node D:
 * Update distances:
 * ```scss
 * dist[E] = 7  (since 4 + 3)
 * ```
 * Priority queue: `[(C, 4), (D, 6), (E, 7)]`
 *
 * Continue until the queue is empty.
 *
 * ### Key Points:
 * - **Graph Representation**: Typically, the graph is represented using adjacency lists.
 * - **Priority Queue**: The priority queue is essential for efficiently extracting the node with the smallest distance.
 * - **Non-negative Weights**: Dijkstra’s algorithm assumes all edge weights are non-negative. For graphs with negative
 * weights, algorithms like Bellman-Ford are used.
 *
 * Dijkstra's algorithm is widely used in routing and as a subroutine in other graph algorithms.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Dijkstra(val info: String = "")
