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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2316. Count Unreachable Pairs of Nodes in an Undirected Graph
 * @see <a href="https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph">
 *     Source</a>
 */
@Medium("https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph")
fun interface CountUnreachablePairs {
    operator fun invoke(num: Int, edges: Array<IntArray>): Long
}

class CountUnreachablePairsDFS : CountUnreachablePairs {

    private val adjacencyList: MutableList<MutableList<Int>> = ArrayList()

    override fun invoke(num: Int, edges: Array<IntArray>): Long {
        for (i in 0 until num) {
            adjacencyList.add(ArrayList())
        }
        for (edge in edges) {
            adjacencyList[edge[0]].add(edge[1]) // make graph
            adjacencyList[edge[1]].add(edge[0])
        }

        var res: Long = 0
        var sum = num.toLong()
        val visited = BooleanArray(num)
        for (i in 0 until num) if (!visited[i]) {
            val curr = dfs(i, visited, IntArray(1)) // find size of connected component
            sum -= curr
            res += curr * sum
        }
        return res
    }

    private fun dfs(node: Int, visited: BooleanArray, count: IntArray): Int {
        if (visited[node]) return count[0]
        visited[node] = true
        count[0]++
        for (curr in adjacencyList[node]) dfs(curr, visited, count)
        return count[0]
    }
}
