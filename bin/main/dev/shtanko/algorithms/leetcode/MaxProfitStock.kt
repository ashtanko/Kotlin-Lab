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

import kotlin.math.max

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown">
 *     Source</a>
 */
fun interface MaxProfitStock {
    operator fun invoke(prices: IntArray): Int
}

class MaxProfitStockImpl : MaxProfitStock {
    override operator fun invoke(prices: IntArray): Int {
        var coolDown = 0
        var sell = 0
        var hold = Int.MIN_VALUE

        for (stockPrice in prices) {
            val prevCoolDown = coolDown
            val prevHold = hold

            coolDown = max(prevCoolDown, sell)
            sell = prevHold + stockPrice
            hold = max(hold, prevCoolDown - stockPrice)
        }
        return max(coolDown, sell)
    }
}
