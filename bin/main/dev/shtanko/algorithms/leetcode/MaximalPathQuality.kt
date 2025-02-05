/*
 * Copyright 2022 Oleksii Shtanko
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

import java.util.LinkedList
import kotlin.math.max

/**
 * 2065. Maximum Path Quality of a Graph
 * @see <a href="https://leetcode.com/problems/maximum-path-quality-of-a-graph/">Source</a>
 */
fun interface MaximalPathQuality {
    operator fun invoke(values: IntArray, edges: Array<IntArray>, maxTime: Int): Int
}

class MaximalPathQualityDFS : MaximalPathQuality {
    override operator fun invoke(values: IntArray, edges: Array<IntArray>, maxTime: Int): Int {
        if (values.isEmpty()) return 0
        if (edges.isEmpty()) return 0
        if (edges.first().isEmpty()) return 0
        val n: Int = values.size
        val adj: Array<MutableList<IntArray>> = Array(n) { mutableListOf() }
        for (i in 0 until n) {
            adj[i] = LinkedList()
        }
        for (e in edges) {
            val i = e[0]
            val j = e[1]
            val t = e[2]
            adj[i].add(intArrayOf(j, t))
            adj[j].add(intArrayOf(i, t))
        }
        val res = IntArray(1)
        val seen = IntArray(n)
        seen[0]++
        dfs(adj, 0, values, maxTime, seen, res, values[0])
        return res[0]
    }

    private fun dfs(
        adj: Array<MutableList<IntArray>>,
        src: Int,
        values: IntArray,
        maxTime: Int,
        seen: IntArray,
        res: IntArray,
        sum: Int,
    ) {
        if (0 == src) {
            res[0] = max(res[0], sum)
        }
        if (0 > maxTime) return
        for (data in adj[src]) {
            val dst = data[0]
            val t = data[1]
            if (0 > maxTime - t) continue
            seen[dst]++
            dfs(adj, dst, values, maxTime - t, seen, res, sum + if (1 == seen[dst]) values[dst] else 0)
            seen[dst]--
        }
    }
}
