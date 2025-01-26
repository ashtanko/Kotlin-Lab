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

/**
 * 2483. Minimum Penalty for a Shop
 * @see <a href="https://leetcode.com/problems/minimum-penalty-for-a-shop">Source</a>
 */
fun interface MinPenaltyForShop {
    infix operator fun invoke(customers: String): Int

    fun helper(customers: String, penalty: Int, min: Int, earliest: Int): Triple<Int, Int, Int> {
        var curPenalty = penalty
        var minPenalty = min
        var earliestHour = earliest
        for (i in customers.indices) {
            val ch: Char = customers[i]

            // If status in hour i is 'Y', moving it to open hours decrement
            // penalty by 1. Otherwise, moving 'N' to open hours increment
            // penalty by 1.
            if (ch == 'Y') {
                curPenalty--
            } else {
                curPenalty++
            }

            // Update earliestHour if a smaller penalty is encountered.
            if (curPenalty < minPenalty) {
                earliestHour = i + 1
                minPenalty = curPenalty
            }
        }
        return Triple(curPenalty, minPenalty, earliestHour)
    }
}

/**
 * Approach 1: Two Passes
 */
class MinPenaltyForShopTwoPasses : MinPenaltyForShop {
    override infix operator fun invoke(customers: String): Int {
        var curPenalty = 0
        for (i in customers.indices) {
            if (customers[i] == 'Y') {
                curPenalty++
            }
        }

        // Start with closing at hour 0, the penalty equals all 'Y' in closed hours.
        val minPenalty = curPenalty
        val earliestHour = 0

        return helper(customers, curPenalty, minPenalty, earliestHour).third
    }
}

/**
 * Approach 2: One Pass
 */
class MinPenaltyForShopOnePass : MinPenaltyForShop {
    override infix operator fun invoke(customers: String) = helper(customers, 0, 0, 0).third
}
