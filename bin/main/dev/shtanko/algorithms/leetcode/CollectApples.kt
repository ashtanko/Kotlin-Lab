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

import dev.shtanko.algorithms.annotations.DFS

/**
 * 1443. Minimum Time to Collect All Apples in a Tree
 * @see <a href="https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/">Source</a>
 */
fun interface CollectApples {
    fun minTime(n: Int, edges: Array<IntArray>, hasApple: List<Boolean>): Int
}

@DFS
class CollectApplesDFS : CollectApples {
    override fun minTime(n: Int, edges: Array<IntArray>, hasApple: List<Boolean>): Int {
        val graph: Array<MutableList<Int>> = Array(n) { ArrayList() }
        for (edge in edges) {
            graph[edge[0]].add(edge[1])
            graph[edge[1]].add(edge[0])
        }
        val visited = BooleanArray(n)
        visited[0] = true
        return dfs(graph, 0, hasApple, visited)
    }

    private fun dfs(graph: Array<MutableList<Int>>, curr: Int, hasApple: List<Boolean>, visited: BooleanArray): Int {
        var res = 0
        for (next in graph[curr]) {
            if (visited[next]) continue
            visited[next] = true
            res += dfs(graph, next, hasApple, visited)
        }
        if ((res > 0 || hasApple[curr]) && curr != 0) {
            res += 2
        }
        return res
    }
}
