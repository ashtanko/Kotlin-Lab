/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Easy
import java.util.Stack

/**
 * 1475. Final Prices With a Special Discount in a Shop
 * @see <a href="https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/">Source</a>
 */
@Easy
sealed interface FinalPrices {
    operator fun invoke(prices: IntArray): IntArray

    data object StackStrategy : FinalPrices {
        override fun invoke(prices: IntArray): IntArray {
            val stack = Stack<Int>()
            for (i in prices.indices) {
                while (stack.isNotEmpty() && prices[stack.peek()] >= prices[i]) {
                    prices[stack.pop()] -= prices[i]
                }
                stack.push(i)
            }
            return prices
        }
    }
}
