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

import kotlin.math.min

/**
 * 983. Minimum Cost For Tickets
 * https://leetcode.com/problems/minimum-cost-for-tickets/
 */
fun interface MinCostTickets {
    operator fun invoke(days: IntArray, costs: IntArray): Int
}

/**
 * Approach 1: Dynamic Programming (Day Variant)
 */
class DPDayVariant : MinCostTickets {

    private lateinit var costs: IntArray
    private var memo: Array<Int?> = arrayOfNulls(LEAP_YEAR_DAYS)
    private var dayset: MutableSet<Int> = HashSet()

    override operator fun invoke(days: IntArray, costs: IntArray): Int {
        this.costs = costs
        for (d in days) dayset.add(d)
        return dp(1)
    }

    private fun dp(i: Int): Int {
        if (i > YEAR_DAYS) return 0
        if (memo[i] != null) {
            return memo[i] ?: 0
        }
        var ans: Int
        if (dayset.contains(i)) {
            ans = min(
                dp(i + 1) + costs[0],
                dp(i + 7) + costs[1],
            )
            ans = min(ans, dp(i + THIRTY_DAYS) + costs[2])
        } else {
            ans = dp(i + 1)
        }
        memo[i] = ans
        return ans
    }

    companion object {
        private const val THIRTY_DAYS = 30
        private const val LEAP_YEAR_DAYS = 366
        private const val YEAR_DAYS = 365
    }
}

/**
 * Approach 2: Dynamic Programming (Window Variant)
 */
class DPWindowVariant : MinCostTickets {
    private lateinit var days: IntArray
    private lateinit var costs: IntArray
    private lateinit var memo: Array<Int?>
    private var durations = intArrayOf(ONE_DAY, ONE_WEEK, ONE_MONTH)

    override operator fun invoke(days: IntArray, costs: IntArray): Int {
        this.days = days
        this.costs = costs
        memo = arrayOfNulls(days.size)
        return dp(0)
    }

    private fun dp(i: Int): Int {
        if (i >= days.size) return 0
        if (memo[i] != null) {
            return memo[i] ?: 0
        }
        var ans = Int.MAX_VALUE
        var j = i
        for (k in 0..2) {
            while (j < days.size && days[j] < days[i] + durations[k]) {
                j++
            }
            ans = min(ans, dp(j) + costs[k])
        }
        memo[i] = ans
        return ans
    }

    companion object {
        private const val ONE_DAY = 1
        private const val ONE_WEEK = 7
        private const val ONE_MONTH = 30
    }
}
