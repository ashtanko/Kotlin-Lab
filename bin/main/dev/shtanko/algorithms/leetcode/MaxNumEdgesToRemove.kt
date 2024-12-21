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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.UnionFind

/**
 * 1579. Remove Max Number of Edges to Keep Graph Fully Traversable
 * @see <a href="https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable">Source</a>
 */
fun interface MaxNumEdgesToRemove {
    operator fun invoke(n: Int, edges: Array<IntArray>): Int
}

@UnionFind
class MaxNumEdgesToRemoveDSU : MaxNumEdgesToRemove {
    override operator fun invoke(n: Int, edges: Array<IntArray>): Int {
        // Different objects for Alice and Bob.
        val alice = UnionFind(n)
        val bob = UnionFind(n)
        var edgesRequired = 0
        // Perform union for edges of type = 3, for both Alice and Bob.
        for (edge in edges) {
            if (edge[0] == 3) {
                edgesRequired += alice.performUnion(edge[1], edge[2]) or bob.performUnion(edge[1], edge[2])
            }
        }

        // Perform union for Alice if type = 1 and for Bob if type = 2.
        for (edge in edges) {
            if (edge[0] == 1) {
                edgesRequired += alice.performUnion(edge[1], edge[2])
            } else if (edge[0] == 2) {
                edgesRequired += bob.performUnion(edge[1], edge[2])
            }
        }

        // Check if the Graphs for Alice and Bob have n - 1 edges or is a single component.
        return if (alice.isConnected && bob.isConnected) {
            edges.size - edgesRequired
        } else {
            -1
        }
    }

    private class UnionFind(
        // Number of distinct components in the graph.
        var components: Int,
    ) {
        var representative: IntArray = IntArray(components + 1)
        var componentSize: IntArray = IntArray(components + 1)

        // Initialize the list representative and componentSize
        // Each node is representative of itself with size 1.
        init {
            for (i in 0..components) {
                representative[i] = i
                componentSize[i] = 1
            }
        }

        // Get the root of a node.
        fun findRepresentative(x: Int): Int {
            return if (representative[x] == x) {
                x
            } else findRepresentative(representative[x]).also { representative[x] = it }
            // Path compression.
        }

        // Perform the union of two components that belongs to node x and node y.
        fun performUnion(x: Int, y: Int): Int {
            var x1 = x
            var y1 = y
            x1 = findRepresentative(x1)
            y1 = findRepresentative(y1)
            if (x1 == y1) {
                return 0
            }
            if (componentSize[x1] > componentSize[y1]) {
                componentSize[x1] += componentSize[y1]
                representative[y1] = x1
            } else {
                componentSize[y1] += componentSize[x1]
                representative[x1] = y1
            }
            components--
            return 1
        }

        val isConnected: Boolean
            // Returns true if all nodes get merged to one.
            get() = components == 1
    }
}
