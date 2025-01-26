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

import dev.shtanko.algorithms.EPSILON
import kotlin.math.ceil
import kotlin.math.max

/**
 * 808. Soup Servings
 * @see <a href="https://leetcode.com/problems/soup-servings/">Source</a>
 */
fun interface SoupServings {
    operator fun invoke(n: Int): Double
}

private const val DIVISOR = 25.0
private const val HALF = 0.5

/**
 * Approach 1: Bottom-Up Dynamic Programming
 */
class SoupServingsBottomUp : SoupServings {

    override operator fun invoke(n: Int): Double {
        val m = ceil(n / DIVISOR).toInt()
        val dp = mutableMapOf<Int, MutableMap<Int, Double>>()
        dp[0] = mutableMapOf()
        dp[0]?.set(0, HALF)

        for (k in 1..m) {
            dp[k] = mutableMapOf()
            dp[0]?.set(k, 1.0)
            dp[k]?.set(0, 0.0)
            for (j in 1..k) {
                dp[j]?.set(k, calculateDP(j, k, dp))
                dp[k]?.set(j, calculateDP(k, j, dp))
            }
            dp[k]?.let {
                it[k]?.let { k ->
                    if (k > 1 - EPSILON) {
                        return 1.0
                    }
                }
            }
        }

        return dp[m]?.get(m) ?: 0.0
    }

    private fun calculateDP(i: Int, j: Int, dp: Map<Int, Map<Int, Double>>): Double {
        val iMinus4 = max(0, i - 4)
        val iMinus3 = max(0, i - 3)
        val iMinus2 = max(0, i - 2)
        val jMinus1 = j - 1
        val jMinus2 = max(0, j - 2)
        val jMinus3 = max(0, j - 3)

        val term1 = dp.getOrElse(iMinus4) { emptyMap() }.getOrElse(j) { 0.0 }
        val term2 = dp.getOrElse(iMinus3) { emptyMap() }.getOrElse(jMinus1) { 0.0 }
        val term3 = dp.getOrElse(iMinus2) { emptyMap() }.getOrElse(jMinus2) { 0.0 }
        val term4 = dp.getOrElse(i - 1) { emptyMap() }.getOrElse(jMinus3) { 0.0 }

        return (term1 + term2 + term3 + term4) / 4
    }
}

/**
 * Approach 2: Top-Down Dynamic Programming (Memoization)
 */
class SoupServingsTopDown : SoupServings {
    override operator fun invoke(n: Int): Double {
        val m = ceil(n / DIVISOR).toInt()
        val dp: MutableMap<Int, MutableMap<Int, Double>> = HashMap()

        for (k in 1..m) {
            if (calculateDP(k, k, dp) > 1 - EPSILON) {
                return 1.0
            }
        }
        return calculateDP(m, m, dp)
    }

    private fun calculateDP(i: Int, j: Int, dp: MutableMap<Int, MutableMap<Int, Double>>): Double {
        if (i <= 0 && j <= 0) {
            return 0.5
        }
        if (i <= 0) {
            return 1.0
        }
        if (j <= 0) {
            return 0.0
        }
        if (dp.containsKey(i) && dp[i]?.containsKey(j) == true) {
            return dp[i]?.get(j) ?: 0.0
        }
        val result = (
            calculateDP(i - 4, j, dp) +
                calculateDP(i - 3, j - 1, dp) +
                calculateDP(i - 2, j - 2, dp) +
                calculateDP(i - 1, j - 3, dp)
            ) / 4.0
        dp.getOrPut(i) { mutableMapOf() }[j] = result
        return result
    }
}
