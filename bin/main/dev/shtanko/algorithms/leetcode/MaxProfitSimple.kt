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
 * Best Time To Buy And Sell Stock
 *
 * This function calculates the maximum profit that can be achieved by buying and selling stocks.
 * The input is an array where the i-th element is the price of a given stock on day i.
 * You may only complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple
 * times).
 * However, you must sell the stock before you buy again.
 *
 * The function uses a simple algorithm where it iterates over the array and for each pair of elements,
 * it calculates the difference and adds it to the current maximum if it's positive.
 * The maximum of the current maximum and the total maximum so far is then stored as the total maximum.
 *
 * @receiver IntArray the input array of stock prices.
 * @return Int the maximum profit that can be achieved.
 */
fun IntArray.maxProfitSimple(): Int {
    var maxCur = 0
    var maxSoFar = 0
    for (i in 1 until size) {
        maxCur += this[i] - this[i - 1]
        maxCur = 0.coerceAtLeast(maxCur)
        maxSoFar = maxCur.coerceAtLeast(maxSoFar)
    }
    return maxSoFar
}
