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

import java.util.PriorityQueue

/**
 * 1928. Minimum Cost to Reach Destination in Time
 * @see <a href="https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time/">Source</a>
 */
fun interface MinCost {
    operator fun invoke(maxTime: Int, edges: Array<IntArray>, passingFees: IntArray): Int
}

class MinCostQueue : MinCost {
    override operator fun invoke(maxTime: Int, edges: Array<IntArray>, passingFees: IntArray): Int {
        val map: MutableMap<Int, MutableList<IntArray>> = HashMap()
        for (e in edges) {
            val from = e[0]
            val to = e[1]
            val time = e[2]
            map.putIfAbsent(from, ArrayList())
            map.putIfAbsent(to, ArrayList())
            map[from]?.add(intArrayOf(to, time))
            map[to]?.add(intArrayOf(from, time))
        }
        val pq: PriorityQueue<IntArray> = PriorityQueue<IntArray> { a, b ->
            if (a[1] == b[1]) a[2] - b[2] else a[1] - b[1]
        }
        pq.add(intArrayOf(0, passingFees[0], 0)) // node cost time
        val n = passingFees.size
        val dist = IntArray(n) { Int.MAX_VALUE }
        val times = IntArray(n) { Int.MAX_VALUE }
        dist[0] = 0
        times[0] = 0
        while (pq.isNotEmpty()) {
            val curr: IntArray = pq.poll()
            val node = curr[0]
            val cost = curr[1]
            val time = curr[2]
            if (time > maxTime) {
                continue
            }
            if (node == n - 1) return cost
            for (nei in map.getOrDefault(node, emptyList())) {
                val neiNode = nei[0]
                val neiCost = passingFees[neiNode]
                if (cost + neiCost < dist[neiNode]) {
                    dist[neiNode] = cost + neiCost
                    pq.add(intArrayOf(neiNode, cost + neiCost, time + nei[1]))
                    times[neiNode] = time + nei[1]
                } else if (time + nei[1] < times[neiNode]) {
                    pq.add(intArrayOf(neiNode, cost + neiCost, time + nei[1]))
                    times[neiNode] = time + nei[1]
                }
            }
        }
        return if (dist[n - 1] == Int.MAX_VALUE || times[n - 1] > maxTime) -1 else dist[n - 1]
    }
}
