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
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee">Source</a>
 */
fun interface MaxProfitWithFee {
    fun maxProfit(prices: IntArray, fee: Int): Int
}

/**
 * Approach 1: Dynamic Programming
 */
class MaxProfitWithFeeDP : MaxProfitWithFee {
    override fun maxProfit(prices: IntArray, fee: Int): Int {
        val n: Int = prices.size
        val free = IntArray(n)
        val hold = IntArray(n)

        // In order to hold a stock on day 0, we have no other choice but to buy it for prices[0].
        hold[0] = -prices[0]

        for (i in 1 until n) {
            hold[i] = max(hold[i - 1], free[i - 1] - prices[i])
            free[i] = max(free[i - 1], hold[i - 1] + prices[i] - fee)
        }

        return free[n - 1]
    }
}

/**
 * Approach 2: Space-Optimized Dynamic Programming
 */
class MaxProfitWithFeeSpaceOptimizedDP : MaxProfitWithFee {
    override fun maxProfit(prices: IntArray, fee: Int): Int {
        val n: Int = prices.size
        var free = 0
        var hold = -prices[0]

        for (i in 1 until n) {
            val tmp = hold
            hold = max(hold, free - prices[i])
            free = max(free, tmp + prices[i] - fee)
        }

        return free
    }
}
