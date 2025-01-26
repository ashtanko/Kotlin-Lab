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

/**
 * 1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree
 * @see <a href="https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree">
 *     Source</a>
 */
fun interface FindCriticalEdges {
    operator fun invoke(n: Int, edges: Array<IntArray>): List<List<Int>>
}

/**
 * Approach 1: Kruskal's Algorithm
 */
class FindCriticalEdgesKruskal : FindCriticalEdges {
    override operator fun invoke(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val critical: MutableList<Int> = ArrayList()
        val pseudoCritical: MutableList<Int> = ArrayList()
        for (i in edges.indices) {
            var edge = edges[i]
            edge = edge.copyOf(edge.size + 1)
            edge[3] = i
            edges[i] = edge
        }
        edges.sortWith { a: IntArray, b: IntArray -> a[2].compareTo(b[2]) }
        val mstwt = findMST(n, edges, -1, -1)
        for (i in edges.indices)
            if (mstwt < findMST(n, edges, i, -1)) {
                critical.add(edges[i][3])
            } else if (mstwt == findMST(n, edges, -1, i)) {
                pseudoCritical.add(edges[i][3])
            }

        val result: MutableList<List<Int>> = ArrayList()
        result.add(critical)
        result.add(pseudoCritical)
        return result
    }

    private fun findParent(p: Int, parent: IntArray): Int =
        if (parent[p] == p) p else findParent(parent[p], parent).also { parent[p] = it }

    fun union(u: Int, v: Int, parent: IntArray) {
        parent[findParent(u, parent)] = findParent(v, parent)
    }

    private fun findMST(n: Int, edges: Array<IntArray>, block: Int, e: Int): Int {
        val parent = IntArray(n)
        for (i in 0 until n) parent[i] = i
        var weight = 0
        if (e != -1) {
            weight += edges[e][2]
            union(edges[e][0], edges[e][1], parent)
        }
        for (i in edges.indices) {
            if (i == block) continue
            if (findParent(edges[i][0], parent) == findParent(edges[i][1], parent)) {
                continue
            }
            union(edges[i][0], edges[i][1], parent)
            weight += edges[i][2]
        }
        for (i in 0 until n) {
            if (findParent(i, parent) != findParent(0, parent)) {
                return Int.MAX_VALUE
            }
        }
        return weight
    }
}
