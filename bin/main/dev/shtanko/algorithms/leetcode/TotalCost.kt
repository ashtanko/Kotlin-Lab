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

import java.util.PriorityQueue
import kotlin.math.max

/**
 * 2462. Total Cost to Hire K Workers
 * @see <a href="https://leetcode.com/problems/total-cost-to-hire-k-workers/">Source</a>
 */
fun interface TotalCost {
    operator fun invoke(costs: IntArray, k: Int, candidates: Int): Long
}

/**
 * Approach 1: 2 Priority Queues
 */
class TotalCostPriorityQueues : TotalCost {
    override operator fun invoke(costs: IntArray, k: Int, candidates: Int): Long {
        val headWorkers: PriorityQueue<Int> = PriorityQueue()
        val tailWorkers: PriorityQueue<Int> = PriorityQueue()
        // headWorkers stores the first k workers.
        // tailWorkers stores at most last k workers without any workers from the first k workers.
        for (i in 0 until candidates) {
            headWorkers.add(costs[i])
        }
        for (i in max(candidates, costs.size - candidates) until costs.size) {
            tailWorkers.add(costs[i])
        }

        var answer: Long = 0
        var nextHead = candidates
        var nextTail: Int = costs.size - 1 - candidates

        for (i in 0 until k) {
            if (tailWorkers.isEmpty() || headWorkers.isNotEmpty() && headWorkers.peek() <= tailWorkers.peek()) {
                answer += headWorkers.poll()

                // Only refill the queue if there are workers outside the two queues.
                if (nextHead <= nextTail) {
                    headWorkers.add(costs[nextHead])
                    nextHead++
                }
            } else {
                answer += tailWorkers.poll()

                // Only refill the queue if there are workers outside the two queues.
                if (nextHead <= nextTail) {
                    tailWorkers.add(costs[nextTail])
                    nextTail--
                }
            }
        }

        return answer
    }
}

/**
 * Approach 2: 1 Priority Queue
 */
class TotalCostPriorityQueue : TotalCost {
    override operator fun invoke(costs: IntArray, k: Int, candidates: Int): Long {
        // The worker with the lowest cost has the highest priority, if two players has the
        // same cost, break the tie by their indices (0 or 1).
        val pq = PriorityQueue(
            java.util.Comparator { a: IntArray, b: IntArray ->
                if (a[0] == b[0]) {
                    return@Comparator a[1] - b[1]
                }
                a[0] - b[0]
            },
        )
        // Add the first k workers with section id of 0 and
        // the last k workers with section id of 1 (without duplication) to pq.
        for (i in 0 until candidates) {
            pq.offer(intArrayOf(costs[i], 0))
        }
        for (i in max(candidates, costs.size - candidates) until costs.size) {
            pq.offer(intArrayOf(costs[i], 1))
        }

        var answer: Long = 0
        var nextHead = candidates
        var nextTail: Int = costs.size - 1 - candidates

        for (i in 0 until k) {
            val curWorker = pq.poll()
            val curCost = curWorker[0]
            val curSectionId = curWorker[1]
            answer += curCost.toLong()

            // Only refill pq if there are workers outside.
            if (nextHead <= nextTail) {
                if (curSectionId == 0) {
                    pq.offer(intArrayOf(costs[nextHead], 0))
                    nextHead++
                } else {
                    pq.offer(intArrayOf(costs[nextTail], 1))
                    nextTail--
                }
            }
        }

        return answer
    }
}
