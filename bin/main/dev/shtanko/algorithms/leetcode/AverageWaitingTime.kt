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

import dev.shtanko.algorithms.annotations.level.Medium
import kotlin.math.max

/**
 * 1701. Average Waiting Time
 * @see <a href="https://leetcode.com/problems/average-waiting-time">Source</a>
 */
@Medium("https://leetcode.com/problems/average-waiting-time")
fun interface AverageWaitingTime {
    operator fun invoke(customers: Array<IntArray>): Double
}

class AverageWaitingTimeIterative : AverageWaitingTime {
    override fun invoke(customers: Array<IntArray>): Double {
        var nextIdleTime = 0
        var netWaitTime: Long = 0

        for (i in customers.indices) {
            // The next idle time for the chef is given by the time of delivery
            // of current customer's order.
            nextIdleTime = max(customers[i][0], nextIdleTime) +
                customers[i][1]

            // The wait time for the current customer is the difference between
            // his delivery time and arrival time.
            netWaitTime += nextIdleTime - customers[i][0]
        }
        // Divide by total customers to get average.
        val averageWaitTime: Double = netWaitTime.toDouble() / customers.size
        return averageWaitTime
    }
}
