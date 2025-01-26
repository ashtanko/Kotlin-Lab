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

import dev.shtanko.algorithms.annotations.Greedy
import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.OnePass
import dev.shtanko.algorithms.annotations.StraightForward
import kotlin.math.max
import kotlin.math.min

/**
 * 2706. Buy Two Chocolates
 * @see <a href="https://leetcode.com/problems/buy-two-chocolates">Source</a>
 */
sealed interface BuyTwoChocolates {
    operator fun invoke(prices: IntArray, money: Int): Int

    @Iterative
    data object CheckEveryPairOfChocolate : BuyTwoChocolates {
        override fun invoke(prices: IntArray, money: Int): Int {
            // Assume Minimum Cost to be Infinity
            var minCost = Int.MAX_VALUE

            // Number of Chocolates
            val n = prices.size

            // Check Every Pair of Chocolates
            for (firstChoco in 0 until n) {
                for (secondChoco in firstChoco + 1 until n) {
                    // Sum of Prices of the Two Chocolates
                    val cost = prices[firstChoco] + prices[secondChoco]

                    // If the Sum of Prices is Less than the Minimum Cost
                    if (cost < minCost) {
                        // Update the Minimum Cost
                        minCost = cost
                    }
                }
            }

            // We can buy chocolates only if we have enough money
            return if (minCost <= money) {
                // Return the Amount of Money Left
                money - minCost
            } else {
                // We cannot buy chocolates. Return the initial amount of money
                money
            }
        }
    }

    @dev.shtanko.algorithms.annotations.Greedy
    data object Greedy : BuyTwoChocolates {
        override fun invoke(prices: IntArray, money: Int): Int {
            require(prices.size > 1) {
                return money
            }
            prices.sort()
            val minCost = prices[0] + prices[1]
            if (minCost <= money) {
                return money - minCost
            }
            return money
        }
    }

    @Iterative
    data object CountingSort : BuyTwoChocolates {

        private const val ARR_SIZE = 101

        override fun invoke(prices: IntArray, money: Int): Int {
            val freq = calculateFrequency(prices)
            val (minimum, secondMinimum) = findMinimumAndSecondMinimum(freq)

            val minCost = calculateMinCost(minimum, secondMinimum)

            return if (minCost <= money) {
                money - minCost
            } else {
                money
            }
        }

        private fun calculateFrequency(prices: IntArray): IntArray {
            val freq = IntArray(ARR_SIZE)
            for (p in prices) {
                freq[p]++
            }
            return freq
        }

        private fun findMinimumAndSecondMinimum(freq: IntArray): Pair<Int, Int> {
            var minimum = 0
            var secondMinimum = 0
            for (price in 1 until ARR_SIZE) {
                if (freq[price] > 1) {
                    minimum = price
                    secondMinimum = price
                    break
                } else if (freq[price] == 1) {
                    minimum = price
                    break
                }
            }

            if (secondMinimum == 0) {
                secondMinimum = findSecondMinimum(freq, minimum)
            }

            return Pair(minimum, secondMinimum)
        }

        private fun findSecondMinimum(freq: IntArray, minimum: Int): Int {
            for (price in minimum + 1 until ARR_SIZE) {
                if (freq[price] > 0) {
                    return price
                }
            }
            return 0
        }

        private fun calculateMinCost(minimum: Int, secondMinimum: Int): Int {
            return minimum + secondMinimum
        }
    }

    data object TwoPasses : BuyTwoChocolates {
        override fun invoke(prices: IntArray, money: Int): Int {
            require(prices.size > 1) {
                return money
            }
            // Find the index of the minimum price
            val minIndex = indexMinimum(prices)

            // Remove the minimum price from the array.
            // Save the minimum price in a variable minCost
            var minCost = prices[minIndex]
            prices[minIndex] = Int.MAX_VALUE

            // Find the index of the second minimum price
            // which is the minimum of the remaining array
            val secondMinIndex = indexMinimum(prices)

            // Add the second minimum price to minCost
            minCost += prices[secondMinIndex]

            // We can buy chocolates only if we have enough money
            if (minCost <= money) {
                // Return the Amount of Money Left
                return money - minCost
            }

            // We cannot buy chocolates. Return the initial amount of money
            return money
        }

        private fun indexMinimum(arr: IntArray): Int {
            // Assume the First Element to be the Minimum
            var minIndex = 0

            // Compare the Assumed Minimum with the Remaining Elements
            // and update assumed minimum if necessary
            for (i in 1 until arr.size) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i
                }
            }

            // Return the Index of the Minimum Element
            return minIndex
        }
    }

    @dev.shtanko.algorithms.annotations.OnePass
    data object OnePass : BuyTwoChocolates {
        override fun invoke(prices: IntArray, money: Int): Int {
            require(prices.size > 1) {
                return money
            }
            // Assume minimum and second minimum
            var minimum = min(prices[0].toDouble(), prices[1].toDouble()).toInt()
            var secondMinimum = max(prices[0].toDouble(), prices[1].toDouble()).toInt()

            // Iterate over the remaining elements
            for (i in 2 until prices.size) {
                if (prices[i] < minimum) {
                    secondMinimum = minimum
                    minimum = prices[i]
                } else if (prices[i] < secondMinimum) {
                    secondMinimum = prices[i]
                }
            }

            // Minimum Cost
            val minCost = minimum + secondMinimum

            // We can buy chocolates only if we have enough money
            if (minCost <= money) {
                // Return the Amount of Money Left
                return money - minCost
            }

            // We cannot buy chocolates. Return the initial amount of money
            return money
        }
    }

    @StraightForward
    data object Simple : BuyTwoChocolates {
        private const val ARR_SIZE = 101
        override fun invoke(prices: IntArray, money: Int): Int {
            var min1 = ARR_SIZE
            var min2 = ARR_SIZE
            for (price in prices) {
                if (price < min1) {
                    min2 = min1
                    min1 = price
                } else if (price < min2) {
                    min2 = price
                }
            }
            return if ((min1 + min2 <= money)) {
                money - (min1 + min2)
            } else {
                money
            }
        }
    }
}
