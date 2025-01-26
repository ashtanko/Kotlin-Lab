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
import java.util.PriorityQueue
import java.util.Queue
import kotlin.math.min

/**
 * 787. Cheapest Flights Within K Stops
 * @see <a href="https://leetcode.com/problems/cheapest-flights-within-k-stops/">Source</a>
 */
fun interface FindCheapestPrice {
    operator fun invoke(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int
}

class FindCheapestPriceBFS : FindCheapestPrice {
    override operator fun invoke(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val map: MutableMap<Int, MutableList<IntArray>> = HashMap()
        for (i in flights) {
            map.putIfAbsent(i[0], ArrayList())
            map[i[0]]?.add(intArrayOf(i[1], i[2]))
        }
        var step = 0
        val q: Queue<IntArray> = LinkedList()
        q.offer(intArrayOf(src, 0))
        var ans = Int.MAX_VALUE
        while (q.isNotEmpty()) {
            val size = q.size
            for (i in 0 until size) {
                val curr = q.poll()
                if (curr[0] == dst) ans = min(ans, curr[1])
                if (!map.containsKey(curr[0])) continue
                for (f in map[curr[0]]!!) {
                    if (curr[1] + f[1] > ans) { // Pruning
                        continue
                    }

                    q.offer(intArrayOf(f[0], curr[1] + f[1]))
                }
            }
            if (step++ > k) break
        }
        return if (ans == Int.MAX_VALUE) -1 else ans
    }
}

class FindCheapestPriceBellmanFord : FindCheapestPrice {
    override operator fun invoke(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        var cost = IntArray(n) { Int.MAX_VALUE }
        cost[src] = 0
        for (i in 0..k) {
            val temp: IntArray = cost.copyOf(n)
            for (f in flights) {
                val curr = f[0]
                val next = f[1]
                val price = f[2]
                if (cost[curr] == Int.MAX_VALUE) continue
                temp[next] = min(temp[next], cost[curr] + price)
            }
            cost = temp
        }
        return if (cost[dst] == Int.MAX_VALUE) -1 else cost[dst]
    }
}

class FindCheapestPriceDijkstra : FindCheapestPrice {
    override operator fun invoke(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val map: MutableMap<Int, MutableList<IntArray>> = HashMap()
        for (f in flights) {
            map.putIfAbsent(f[0], ArrayList())
            map[f[0]]?.add(intArrayOf(f[1], f[2]))
        }
        val q: PriorityQueue<IntArray> = PriorityQueue { o1, o2 -> o1[0].compareTo(o2[0]) }
        q.offer(intArrayOf(0, src, k + 1))
        while (q.isNotEmpty()) {
            val c: IntArray = q.poll()
            val cost = c[0]
            val curr = c[1]
            val stop = c[2]
            if (curr == dst) return cost
            if (stop > 0) {
                if (!map.containsKey(curr)) continue
                for (next in map[curr]!!) {
                    q.add(intArrayOf(cost + next[1], next[0], stop - 1))
                }
            }
        }
        return -1
    }
}
