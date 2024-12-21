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

import kotlin.math.max

/**
 * 1615. Maximal Network Rank
 * @see <a href="https://leetcode.com/problems/maximal-network-rank/">Source</a>
 */
fun interface MaximalNetworkRank {
    operator fun invoke(n: Int, roads: Array<IntArray>): Int
}

class MaximalNetworkRankInDegree : MaximalNetworkRank {
    override operator fun invoke(n: Int, roads: Array<IntArray>): Int {
        var maxRank = 0
        val adj: MutableMap<Int, MutableSet<Int>> = HashMap()
        // Construct adjacency list 'adj', where adj[node] stores all nodes connected to 'node'.
        for (road in roads) {
            adj.computeIfAbsent(road[0]) { HashSet() }.add(road[1])
            adj.computeIfAbsent(road[1]) { HashSet() }.add(road[0])
        }

        // Iterate on each possible pair of nodes.
        for (node1 in 0 until n) {
            for (node2 in node1 + 1 until n) {
                var currentRank = adj.getOrDefault(node1, emptySet()).size +
                    adj.getOrDefault(node2, emptySet()).size

                // Find the current pair's respective network rank and store if it's maximum till now.
                if (adj.getOrDefault(node1, emptySet()).contains(node2)) {
                    --currentRank
                }
                maxRank = max(maxRank.toDouble(), currentRank.toDouble()).toInt()
            }
        }
        // Return the maximum network rank.
        return maxRank
    }
}
