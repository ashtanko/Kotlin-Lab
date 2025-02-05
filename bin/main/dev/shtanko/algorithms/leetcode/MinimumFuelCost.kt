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
 * 2477. Minimum Fuel Cost to Report to the Capital
 * @see <a href="https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/">Source</a>
 */
fun interface MinimumFuelCost {
    operator fun invoke(roads: Array<IntArray>, seats: Int): Long
}

class MinimumFuelCostDFS : MinimumFuelCost {

    private var ans: Long = 0
    private var s: Int = 0

    override operator fun invoke(roads: Array<IntArray>, seats: Int): Long {
        val graph: MutableList<MutableList<Int>> = ArrayList()
        s = seats
        for (i in 0 until roads.size + 1) {
            graph.add(ArrayList())
        }
        for (r in roads) {
            graph[r[0]].add(r[1])
            graph[r[1]].add(r[0])
        }
        dfs(0, 0, graph)
        return ans
    }

    private fun dfs(i: Int, prev: Int, graph: List<List<Int>>): Int {
        var people = 1
        for (x in graph[i]) {
            if (x == prev) continue
            people += dfs(x, i, graph)
        }
        if (i != 0) ans += (people + s - 1) / s
        return people
    }
}
