/*
 * Copyright 2020 Oleksii Shtanko
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
 * Time complexity : O(NlogN).
 * Space complexity : O(N).
 */
class MinimumCostToConnectSticks {
    fun connectSticks(sticks: IntArray): Int {
        var totalCost = 0
        val pq: PriorityQueue<Int> = PriorityQueue()
        // add all sticks to the min heap.
        for (stick in sticks) {
            pq.add(stick)
        }

        // combine two of the smallest sticks until we are left with just one.
        while (pq.size > 1) {
            val stick1: Int = pq.remove()
            val stick2: Int = pq.remove()
            val cost = stick1 + stick2
            totalCost += cost
            pq.add(stick1 + stick2)
        }

        return totalCost
    }
}
