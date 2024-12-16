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
 * Kruskal's Algorithm is a greedy algorithm used to find the minimum spanning tree (MST) of a connected, undirected
 * graph.
 * The algorithm works by selecting the edges with the smallest weight and adding them to the MST, provided they do not
 * form a cycle.
 * This process continues until the MST contains exactly (V-1) edges, where V is the number of vertices in the graph.
 *
 * # How It Works
 *
 * 1: Sort All Edges:
 * The first step is to sort all the edges of the graph in non-decreasing order of their weights.
 *
 * 2: Select the Smallest Edge:
 * Start by selecting the smallest edge and add it to the MST if it does not form a cycle with the already chosen edges.
 * This check is typically done using a disjoint-set (union-find) data structure.
 *
 * 3: Repeat:
 * Continue selecting the smallest edges and adding them to the MST, until the MST contains (V - 1) edges.
 *
 * # Example:
 * In a graph with vertices A, B, C, and D, and edges with weights:
 * - (A, B) = 1, (A, C) = 3, (B, C) = 2, (C, D) = 4
 * The algorithm will first select (A, B), then (B, C), and finally (C, D), resulting in the MST with
 * edges (A, B), (B, C), and (C, D).
 *
 * # Advantages:
 * * Greedy Approach: Kruskal’s algorithm is a greedy algorithm that ensures the MST is constructed in a way that
 * guarantees optimality.
 * * Efficient: Sorting the edges takes O(E log E) time, and the union-find operations take nearly constant time,
 * resulting in an overall time complexity of O(E log E).
 *
 * # Common Applications:
 * - Network design (e.g., designing a minimal-cost network)
 * - Cluster analysis (e.g., connecting data points with minimum cost)
 *
 * @property info An optional string that provides additional information about the Kruskal's Algorithm implementation
 * or usage.
 * @constructor Creates a new KruskalsAlgorithm annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class KruskalsAlgorithm(val info: String = "")
