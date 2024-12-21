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

import kotlin.math.min

/**
 * 1553. Minimum Number of Days to Eat N Oranges
 * @see <a href="https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/">Source</a>
 */
sealed interface MinDays {
    operator fun invoke(n: Int): Int
}

class MinDaysSimple : MinDays {
    private var dp: MutableMap<Int, Int> = HashMap()

    override operator fun invoke(n: Int): Int {
        if (n <= 1) {
            return n
        }
        if (!dp.containsKey(n)) {
            dp[n] = 1 + min(n % 2 + invoke(n / 2), n % 3 + invoke(n / 3))
        }
        return dp.getOrDefault(n, n)
    }
}

class MinDaysDP : MinDays {
    private var dp = mutableMapOf(0 to 0, 1 to 1, 2 to 2)

    override operator fun invoke(n: Int): Int {
        return calcMinDays(n)
    }

    private fun calcMinDays(days: Int): Int {
        if (dp.containsKey(days)) {
            return dp.getOrDefault(days, -1)
        }

        var minDays = Int.MAX_VALUE
        minDays = when {
            days % 2 == 0 && days % 3 == 0 -> calculateMinDaysForMultipleOfTwoAndThree(days, minDays)
            days % 3 == 0 -> calculateMinDaysForMultipleOfThree(days, minDays)
            days % 2 == 0 -> calculateMinDaysForMultipleOfTwo(days, minDays)
            else -> calculateMinDaysForOtherCases(days, minDays)
        }

        dp[days] = minDays
        return dp.getOrDefault(days, days)
    }

    private fun calculateMinDaysForMultipleOfTwoAndThree(days: Int, minDays: Int): Int {
        return min(minDays, 1 + min(calcMinDays(days / 2), calcMinDays(days / 3)))
    }

    private fun calculateMinDaysForMultipleOfThree(days: Int, minDays: Int): Int {
        return min(minDays, 1 + min(calcMinDays(days - 1), calcMinDays(days / 3)))
    }

    private fun calculateMinDaysForMultipleOfTwo(days: Int, minDays: Int): Int {
        return if ((days - 1) % 3 == 0) {
            min(minDays, 1 + min(calcMinDays(days / 2), calcMinDays(days - 1)))
        } else {
            min(minDays, min(1 + calcMinDays(days / 2), 2 + calcMinDays(days - 2)))
        }
    }

    private fun calculateMinDaysForOtherCases(days: Int, minDays: Int): Int {
        var result = min(minDays, 1 + calcMinDays(days - 1))
        if ((days - 2) % 3 == 0) {
            result = min(result, 2 + calcMinDays(days - 2))
        }
        return result
    }
}
