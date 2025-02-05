/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.datastructures

/**
 * Implementation of the disjoint-set data structure using union by rank and path compression.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">Disjoint-set data structure</a>
 *
 * @property size The number of elements in the set.
 */
class DisjointSet(val size: Int) {
    private val parent = IntArray(size)
    private val rank = ByteArray(size)

    /**
     * The number of disjoint sets in the current structure.
     */
    var count = size
        private set

    init {
        for (i in parent.indices) {
            parent[i] = i
        }
    }

    /**
     * Checks if two elements belong to the same set.
     *
     * @param v The index of the first element.
     * @param w The index of the second element.
     * @return `true` if the elements belong to the same set, `false` otherwise.
     */
    fun connected(v: Int, w: Int): Boolean {
        return find(v) == find(w)
    }

    /**
     * Finds the parent (root) of the specified element and applies path compression to optimize future queries.
     *
     * @param v The index of the element.
     * @return The index of the parent (root) of the element.
     */
    private fun find(v: Int): Int {
        var value = v
        while (parent[value] != value) {
            parent[value] = parent[parent[value]]
            value = parent[value]
        }
        return value
    }

    /**
     * Unites the sets containing the specified elements using union by rank to optimize future queries.
     *
     * @param v The index of the first element.
     * @param w The index of the second element.
     */
    fun union(v: Int, w: Int) {
        val rootV = find(v)
        val rootW = find(w)
        if (rootV == rootW) return
        when {
            rank[rootV] > rank[rootW] -> {
                parent[rootW] = rootV
            }

            rank[rootW] > rank[rootV] -> {
                parent[rootV] = rootW
            }

            else -> {
                parent[rootV] = rootW
                rank[rootW]++
            }
        }
        count--
    }
}
