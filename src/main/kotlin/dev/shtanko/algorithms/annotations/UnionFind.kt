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
 * The UnionFind annotation is used to indicate that a function or class implements or utilizes the Union-Find
 * data structure, also known as Disjoint Set Union (DSU). This data structure is used to efficiently manage a
 * collection of disjoint sets
 * and supports two primary operations: **union** and **find**.
 *
 * # How It Works
 *
 * The Union-Find data structure is designed to efficiently handle the merging (union) of sets and the finding of
 * which set an element belongs to (find). The main operations in Union-Find are:
 * 1. **Find**: Determines the set to which a particular element belongs.
 * 2. **Union**: Merges two sets into one set.
 *
 * To make these operations more efficient, **path compression** and **union by rank/size** are often used:
 * - **Path Compression**: Ensures that trees representing sets remain shallow by pointing all nodes directly to
 * the root during the find operation.
 * - **Union by Rank/Size**: Merges the smaller tree (rank or size based) under the larger tree to keep the resulting
 * tree as flat as possible.
 *
 * Example of Union-Find with path compression and union by rank:
 *
 * ```kotlin
 * class UnionFind(val size: Int) {
 *     private val parent = IntArray(size) { it }
 *     private val rank = IntArray(size) { 0 }
 *
 *     // Find with path compression
 *     fun find(x: Int): Int {
 *         if (parent[x] != x) {
 *             parent[x] = find(parent[x]) // Path compression
 *         }
 *         return parent[x]
 *     }
 *
 *     // Union by rank
 *     fun union(x: Int, y: Int) {
 *         val rootX = find(x)
 *         val rootY = find(y)
 *         if (rootX != rootY) {
 *             when {
 *                 rank[rootX] > rank[rootY] -> parent[rootY] = rootX
 *                 rank[rootX] < rank[rootY] -> parent[rootX] = rootY
 *                 else -> {
 *                     parent[rootY] = rootX
 *                     rank[rootX]++
 *                 }
 *             }
 *         }
 *     }
 *
 *     // Check if two elements are in the same set
 *     fun connected(x: Int, y: Int): Boolean {
 *         return find(x) == find(y)
 *     }
 * }
 * ```
 *
 * # Advantages:
 * * **Efficient Operations**: With path compression and union by rank, the time complexity of both find and union
 * operations is nearly constant, amortized O(α(n)), where α is the inverse Ackermann function.
 * * **Space Efficiency**: The Union-Find structure typically uses linear space, O(n), which is very space-efficient
 * for managing disjoint sets.
 * * **Scalability**: Union-Find can handle a large number of elements efficiently, making it suitable for applications
 * involving network connectivity, clustering, or dynamic connectivity problems.
 *
 * # Disadvantages:
 * * **Complexity**: The implementation of Union-Find can be tricky due to the optimization techniques like path
 * compression and union by rank, especially when handling large data structures.
 * * **Limited Use Cases**: Union-Find is mainly useful for problems where merging sets and finding set membership is
 * required (e.g., connected components, network connectivity, Kruskal's algorithm for minimum spanning trees).
 *
 * The Union-Find data structure is commonly used in graph-related algorithms, such as finding connected components,
 * and in problems related to dynamic connectivity.
 *
 * @property info An optional string that provides additional information about the Union-Find data structure
 * implementation or its usage.
 * @constructor Creates a new UnionFind annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class UnionFind(val info: String = "")
