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

import java.util.LinkedList
import java.util.Queue
import kotlin.math.min

/**
 * 2492. Minimum Score of a Path Between Two Cities
 * @see <a href="https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/">Source</a>
 */
fun interface MinScore {
    operator fun invoke(n: Int, roads: Array<IntArray>): Int
}

class MinScoreBFS : MinScore {
    override operator fun invoke(n: Int, roads: Array<IntArray>): Int {
        var ans = Int.MAX_VALUE
        val gr: MutableList<MutableList<Pair<Int, Int>>> = ArrayList()
        for (i in 0 until n + 1) {
            gr.add(ArrayList())
        }

        for (edge in roads) {
            gr[edge[0]].add(Pair(edge[1], edge[2])) // u-> {v, dis}
            gr[edge[1]].add(Pair(edge[0], edge[2])) // v-> {u, dis}
        }

        val vis = IntArray(n + 1) { 0 }
        val q: Queue<Int> = LinkedList()
        q.add(1)
        vis[1] = 1
        while (q.isNotEmpty()) {
            val node: Int = q.poll()
            for (pair in gr[node]) {
                val v: Int = pair.first
                val dis: Int = pair.second
                ans = min(ans, dis)
                if (vis[v] == 0) {
                    vis[v] = 1
                    q.add(v)
                }
            }
        }

        return ans
    }
}

class MinScoreDFS : MinScore {
    private var ans = Int.MAX_VALUE

    override operator fun invoke(n: Int, roads: Array<IntArray>): Int {
        val adj: MutableList<MutableList<IntArray>> = ArrayList()
        for (i in 0..n) adj.add(ArrayList())
        for (k in roads) {
            adj[k[0]].add(intArrayOf(k[1], k[2]))
            adj[k[1]].add(intArrayOf(k[0], k[2]))
        }
        val vis = BooleanArray(n + 1)
        dfs(adj, 1, vis)
        return ans
    }

    private fun dfs(g: List<List<IntArray>>, node: Int, vis: BooleanArray) {
        vis[node] = true
        for (k in g[node]) {
            ans = min(ans, k[1])
            if (!vis[k[0]]) {
                dfs(g, k[0], vis)
            }
        }
    }
}
