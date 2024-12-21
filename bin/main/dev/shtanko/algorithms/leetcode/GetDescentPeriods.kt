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
 * 2110. Number of Smooth Descent Periods of a Stock
 * @see <a href="https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/">Source</a>
 */
fun interface GetDescentPeriods {
    operator fun invoke(prices: IntArray): Long
}

/**
 * O(N) Space
 */
class GetDescentPeriodsOnePass : GetDescentPeriods {
    override operator fun invoke(prices: IntArray): Long {
        val dp = LongArray(prices.size)
        dp[0] = 1
        var ans: Long = 1
        for (i in 1 until prices.size) {
            if (prices[i] == prices[i - 1] - 1) {
                dp[i] = dp[i - 1] + 1
            } else {
                dp[i] = 1
            }
            ans += dp[i]
        }
        return ans
    }
}

/**
 * O(1) Space
 */
class GetDescentPeriodsSimple : GetDescentPeriods {
    override operator fun invoke(prices: IntArray): Long {
        var dp: Long = 1
        var ans: Long = 1
        for (i in 1 until prices.size) {
            if (prices[i] == prices[i - 1] - 1) {
                dp++
            } else {
                dp = 1
            }
            ans += dp
        }
        return ans
    }
}
