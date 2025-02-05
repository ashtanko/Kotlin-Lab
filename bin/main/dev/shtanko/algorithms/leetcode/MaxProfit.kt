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

/**
 * Best Time To Buy And Sell Stock 2
 *
 * This function calculates the maximum profit that can be achieved by buying and selling stocks.
 * The input is an array where the i-th element is the price of a given stock on day i.
 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * @receiver IntArray the input array of stock prices.
 * @return Int the maximum profit that can be achieved.
 */
fun IntArray.maxProfit(): Int {
    var profit = 0
    if (this.isEmpty()) {
        return 0
    }
    for (i in 1 until size) {
        profit += 0.coerceAtLeast(this[i] - this[i - 1])
    }
    return profit
}
