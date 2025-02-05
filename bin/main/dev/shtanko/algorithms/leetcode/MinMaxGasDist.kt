/*
 * Copyright 2021 Oleksii Shtanko
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

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.PriorityQueue
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 * 774. Minimize Max Distance to Gas Station
 */
fun interface MinMaxGasDist {
    operator fun invoke(stations: IntArray, k: Int): Double

    companion object {
        const val LIMIT = 999999999.0
    }
}

/**
 * Approach #1: Dynamic Programming [Memory Limit Exceeded]
 */
val minMaxGasDistDP = MinMaxGasDist { stations, station ->
    val n: Int = stations.size
    val deltas = DoubleArray(n - 1)
    for (i in 0 until n - 1) {
        deltas[i] = (stations[i + 1] - stations[i]).toDouble()
    }

    val dp = Array(n - 1) {
        DoubleArray(
            station + 1,
        )
    }
    for (i in 0..station) {
        dp[0][i] = deltas[0] / (i + 1)
    }

    for (p in 1 until n - 1) for (k in 0..station) {
        var bns = MinMaxGasDist.LIMIT
        for (x in 0..k) bns = min(bns, max(deltas[p] / (x + 1), dp[p - 1][k - x]))
        dp[p][k] = bns
    }

    dp[n - 2][station]
}

/**
 * Approach #2: Brute Force [Time Limit Exceeded]
 */
val minMaxGasDistBruteForce = MinMaxGasDist { stations, station ->
    val n: Int = stations.size
    val deltas = DoubleArray(n - 1)
    for (i in 0 until n - 1) {
        deltas[i] = (stations[i + 1] - stations[i]).toDouble()
    }

    val count = IntArray(n - 1) { 1 }

    for (k in 0 until station) {
        var best = 0
        for (i in 0 until n - 1) if (deltas[i] / count[i] > deltas[best] / count[best]) best = i
        count[best]++
    }

    var ans = 0.0
    for (i in 0 until n - 1) ans = max(ans, deltas[i] / count[i])
    ans
}

/**
 * Approach #3: Heap [Time Limit Exceeded]
 */
val minMaxGasDistHeap = MinMaxGasDist { stations, station ->
    val n: Int = stations.size
    val pq: PriorityQueue<IntArray> = PriorityQueue<IntArray> { a, b ->
        if (b[0].toDouble() / b[1] < a[0].toDouble() / a[1]
        ) {
            -1
        } else {
            1
        }
    }
    for (i in 0 until n - 1) pq.add(intArrayOf(stations[i + 1] - stations[i], 1))
    for (k in 0 until station) {
        val node: IntArray = pq.poll()
        node[1]++
        pq.add(node)
    }
    val node: IntArray = pq.poll()
    node[0].toDouble() / node[1]
}

/**
 * Approach #4: Binary Search
 */
class MinMaxGasDistBS : MinMaxGasDist {
    override operator fun invoke(stations: IntArray, k: Int): Double {
        if (stations.isEmpty() || k <= 0) return 0.0

        val n: Int = stations.size
        var start = 0.0
        var end = (stations[n - 1] - stations[0]).toDouble()

        while (start <= end) {
            val mid = start + (end - start) / 2
            var count = 0
            for (i in 0 until n - 1) count += (ceil((stations[i + 1] - stations[i]) / mid) - 1).toInt()
            if (count > k) {
                start = mid + DELTA
            } else {
                end = mid - DELTA
            }
        }
        val decimal = BigDecimal(start).setScale(5, RoundingMode.HALF_EVEN)
        return decimal.toDouble()
    }

    companion object {
        private const val DELTA = 1e-6
    }
}
