/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Hard
import kotlin.math.max

/**
 * 1617. Count Subtrees With Max Distance Between Cities
 * @see <a href="https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/">Source</a>
 */
@Hard("https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities")
fun interface CountSubgraphsForEachDiameter {
    operator fun invoke(num: Int, edges: Array<IntArray>): IntArray
}

class CountSubgraphsForEachDiameterDFS : CountSubgraphsForEachDiameter {

    private var max = 0

    override operator fun invoke(num: Int, edges: Array<IntArray>): IntArray {
        val graph: MutableMap<Int, MutableSet<Int>> = HashMap()
        for (edge in edges) {
            graph.computeIfAbsent(
                edge[0],
            ) { HashSet() }.add(edge[1])
            graph.computeIfAbsent(
                edge[1],
            ) { HashSet() }.add(edge[0])
        }
        val ans = IntArray(num - 1)
        for (bit in 0 until (1 shl num)) {
            val subtree: MutableSet<Int?> = HashSet()
            for (i in 0 until num) if (bit and (1 shl i) != 0) subtree.add(i + 1)
            var edgeCount = 0
            max = 0
            for (edge in edges) {
                if (subtree.contains(edge[0]) && subtree.contains(edge[1])) edgeCount++
            }
            if (edgeCount == 0 || edgeCount != subtree.size - 1) continue
            dfs(subtree, graph, HashSet(), subtree.iterator().next())
            ans[max - 1]++
        }
        return ans
    }

    private fun dfs(subtree: Set<Int?>, graph: Map<Int, Set<Int>>, visited: MutableSet<Int>, beg: Int?): Int {
        var dist1 = 0
        var dist2 = 0
        beg?.let { visited.add(it) }
        for (neighbor in graph[beg] ?: emptySet()) {
            if (!visited.contains(neighbor) && subtree.contains(neighbor)) {
                val d = dfs(subtree, graph, visited, neighbor)
                if (d > dist1) {
                    dist2 = dist1
                    dist1 = d
                } else if (d > dist2) dist2 = d
            }
        }
        max = max(max, dist1 + dist2)
        return 1 + dist1
    }
}
