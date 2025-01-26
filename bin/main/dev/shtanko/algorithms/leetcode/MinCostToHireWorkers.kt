/*
 * Copyright 2024 Oleksii Shtanko
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
 * 857. Minimum Cost to Hire K Workers
 * @see <a href="https://leetcode.com/problems/minimum-cost-to-hire-k-workers">Source</a>
 */
fun interface MinCostToHireWorkers {
    operator fun invoke(quality: IntArray, wage: IntArray, k: Int): Double
}

class MinCostToHireWorkersPriorityQueue : MinCostToHireWorkers {
    override fun invoke(quality: IntArray, wage: IntArray, k: Int): Double {
        val n = quality.size
        val workers = Array(n) { Pair(quality[it].toDouble(), wage[it].toDouble()) }
        workers.sortBy { it.second / it.first }

        var result = Double.MAX_VALUE
        var sum = 0.0
        val pq = java.util.PriorityQueue<Double>()

        for (worker in workers) {
            sum += worker.first
            pq.offer(-worker.first)
            if (pq.size > k) {
                sum += pq.poll()
            }
            if (pq.size == k) {
                result = minOf(result, sum * worker.second / worker.first)
            }
        }

        return result
    }
}
