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

import java.util.Stack

/**
 * This class represents a Stock Spanner.
 * It calculates the span of stock's price for all previous days.
 */
class StockSpanner {

    /**
     * This stack stores the price and span of each day.
     */
    var stack: Stack<IntArray> = Stack()

    /**
     * This function calculates the span of the stock's price for the current day.
     * @param price The stock's price for the current day.
     * @return The span of the stock's price for the current day.
     */
    fun next(price: Int): Int {
        var span = 1
        while (stack.isNotEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1]
        }
        stack.push(intArrayOf(price, span))
        return span
    }
}
