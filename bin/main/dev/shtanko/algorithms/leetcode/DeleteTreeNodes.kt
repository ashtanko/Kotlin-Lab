/*
 * Copyright 2021 Oleksii Shtanko
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
 * 1273. Delete Tree Nodes
 * @see <a href="https://leetcode.com/problems/delete-tree-nodes/">Source</a>
 */
fun interface DeleteTreeNodes {
    operator fun invoke(nodes: Int, parent: IntArray, value: IntArray): Int
}

/**
 * One Pass
 * Time O(N).
 * Space O(N).
 */
class DeleteTreeNodesBruteForce : DeleteTreeNodes {
    override operator fun invoke(nodes: Int, parent: IntArray, value: IntArray): Int {
        val res = IntArray(nodes)
        for (i in nodes - 1 downTo 1) {
            value[parent[i]] += value[i]
            res[parent[i]] += if (value[i] != 0) res[i] + 1 else 0
        }
        return if (value.first() != 0) res.first() + 1 else 0
    }
}

/**
 * DFS
 * Time complexity: O(N), N is the number of nodes in the tree, N <= 10^4
 */
class DeleteTreeNodesDFS : DeleteTreeNodes {
    override operator fun invoke(nodes: Int, parent: IntArray, value: IntArray): Int {
        val graph: MutableList<MutableList<Int>> = ArrayList(nodes) // Create graph for the tree
        for (i in 0 until nodes) graph.add(ArrayList())
        for (i in 0 until nodes) {
            if (parent[i] != -1) {
                graph[parent[i]].add(i)
            }
        }
        return dfs(graph, value, 0)[1]
    }

    private fun dfs(graph: List<List<Int>>, value: IntArray, root: Int): IntArray {
        var sum = value[root]
        var cntRemainNodes = 1
        for (child in graph[root]) {
            val temp = dfs(graph, value, child)
            sum += temp[0]
            cntRemainNodes += temp[1]
        }
        if (sum == 0) cntRemainNodes = 0 // Don't count nodes of this subtree
        return intArrayOf(sum, cntRemainNodes)
    }
}
