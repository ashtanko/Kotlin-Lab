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

import java.util.Arrays

/**
 * 1697. Checking Existence of Edge Length Limited Paths
 * @see <a href="https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/">Source</a>
 */
fun interface DistanceLimitedPathsExist {
    operator fun invoke(num: Int, edgeList: Array<IntArray>, queries: Array<IntArray>): BooleanArray
}

class DisjointSetUnion : DistanceLimitedPathsExist {
    override operator fun invoke(num: Int, edgeList: Array<IntArray>, queries: Array<IntArray>): BooleanArray {
        val uf = UnionFind(num)
        val queriesCount = queries.size
        val answer = BooleanArray(queriesCount)

        // Store original indices with all queries.
        val queriesWithIndex = Array(queriesCount) {
            IntArray(4)
        }
        for (i in 0 until queriesCount) {
            queriesWithIndex[i][0] = queries[i][0]
            queriesWithIndex[i][1] = queries[i][1]
            queriesWithIndex[i][2] = queries[i][2]
            queriesWithIndex[i][3] = i
        }

        // Sort all edges in increasing order of their edge weights.
        sort(edgeList)
        // Sort all queries in increasing order of the limit of edge allowed.
        sort(queriesWithIndex)
        var edgesIndex = 0

        // Iterate on each query one by one.
        var queryIndex = 0
        while (queryIndex < queriesCount) {
            val p = queriesWithIndex[queryIndex][0]
            val q = queriesWithIndex[queryIndex][1]
            val limit = queriesWithIndex[queryIndex][2]
            val queryOriginalIndex = queriesWithIndex[queryIndex][3]

            // We can attach all edges which satisfy the limit given by the query.
            while (edgesIndex < edgeList.size && edgeList[edgesIndex][2] < limit) {
                val node1 = edgeList[edgesIndex][0]
                val node2 = edgeList[edgesIndex][1]
                uf.join(node1, node2)
                edgesIndex += 1
            }

            // If both nodes belong to the same component, it means we can reach them.
            answer[queryOriginalIndex] = uf.areConnected(p, q)
            queryIndex += 1
        }
        return answer
    }

    // Sort in increasing order based on the 3rd element of the array.
    private fun sort(array: Array<IntArray>) {
        Arrays.sort(array) { a, b -> a[2] - b[2] }
    }

    class UnionFind(size: Int) {
        private val group: IntArray = IntArray(size)
        private val rank: IntArray = IntArray(size)

        init {
            for (i in 0 until size) {
                group[i] = i
            }
        }

        fun find(node: Int): Int {
            if (group[node] != node) {
                group[node] = find(group[node])
            }
            return group[node]
        }

        fun join(node1: Int, node2: Int) {
            val group1 = find(node1)
            val group2 = find(node2)

            // node1 and node2 already belong to same group.
            if (group1 == group2) {
                return
            }
            if (rank[group1] > rank[group2]) {
                group[group2] = group1
            } else if (rank[group1] < rank[group2]) {
                group[group1] = group2
            } else {
                group[group1] = group2
                rank[group2] += 1
            }
        }

        fun areConnected(node1: Int, node2: Int): Boolean {
            val group1 = find(node1)
            val group2 = find(node2)
            return group1 == group2
        }
    }
}
