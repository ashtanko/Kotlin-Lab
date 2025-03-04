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

import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.UnionFind

/**
 * 947. Most Stones Removed with Same Row or Column
 * @see <a href="https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/">Source</a>
 */
fun interface RemoveStones {
    operator fun invoke(stones: Array<IntArray>): Int
}

@UnionFind
class RemoveStonesMap : RemoveStones {
    private val parentMap: MutableMap<Int, Int> = HashMap()
    private var islandCount = 0

    override operator fun invoke(stones: Array<IntArray>): Int {
        for (i in stones.indices) union(stones[i][0], stones[i][1].inv())
        return stones.size - islandCount
    }

    fun find(node: Int): Int {
        if (parentMap.putIfAbsent(node, node) == null) {
            islandCount++
        }
        if (node != parentMap[node]) {
            parentMap[node]?.let {
                parentMap[node] = find(it)
            }
        }
        return parentMap.getOrDefault(node, 0)
    }

    private fun union(node1: Int, node2: Int) {
        var root1 = find(node1)
        var root2 = find(node2)
        if (root1 != root2) {
            parentMap[root1] = root2
            islandCount--
        }
    }
}

@DFS
class RemoveStonesDFS : RemoveStones {
    override operator fun invoke(stones: Array<IntArray>): Int {
        val graph: MutableMap<Int, MutableList<Int>> = HashMap()
        for (stone in stones) {
            graph.computeIfAbsent(stone[0]) { ArrayList() }.add(stone[1].inv())
            graph.computeIfAbsent(stone[1].inv()) { ArrayList() }.add(stone[0])
        }
        var componentCount = 0
        val visitedNodes: MutableSet<Int> = HashSet()
        for (stone in stones) {
            for (i in 0..1) {
                val node = if (i == 0) stone[0] else stone[1].inv()
                if (!visitedNodes.contains(node)) {
                    componentCount++
                    depthFirstSearch(node, graph, visitedNodes)
                }
            }
        }
        return stones.size - componentCount
    }

    private fun depthFirstSearch(node: Int, graph: Map<Int, List<Int>>, visitedNodes: MutableSet<Int>) {
        if (visitedNodes.add(node)) {
            for (neighbor in graph.getOrDefault(node, emptyList())) {
                depthFirstSearch(neighbor, graph, visitedNodes)
            }
        }
    }
}
