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

import dev.shtanko.algorithms.MOD
import kotlin.math.min

/**
 * 879. Profitable Schemes
 * https://leetcode.com/problems/profitable-schemes/
 */

private const val LIMIT = 101

fun interface ProfitableSchemes {
    operator fun invoke(n: Int, minProfit: Int, group: IntArray, profits: IntArray): Int
}

/**
 * Approach 1: Top-Down Dynamic Programming
 */
class ProfitableSchemesTopDown : ProfitableSchemes {
    private val memo = Array(LIMIT) { Array(LIMIT) { IntArray(LIMIT) { -1 } } }

    override operator fun invoke(n: Int, minProfit: Int, group: IntArray, profits: IntArray): Int {
        return find(0, 0, 0, n, minProfit, group, profits)
    }

    fun find(pos: Int, count: Int, profit: Int, n: Int, minProfit: Int, group: IntArray, profits: IntArray): Int {
        if (pos == group.size) {
            // If profit exceeds the minimum required; it's a profitable scheme.
            return if (profit >= minProfit) 1 else 0
        }
        if (memo[pos][count][profit] != -1) {
            // Repeated sub-problem, return the stored answer.
            return memo[pos][count][profit]
        }

        // Ways to get a profitable scheme without this crime.
        var totalWays = find(pos + 1, count, profit, n, minProfit, group, profits)
        if (count + group[pos] <= n) {
            // Adding ways to get profitable schemes, including this crime.
            totalWays += find(
                pos + 1,
                count + group[pos],
                min(minProfit, profit + profits[pos]),
                n,
                minProfit,
                group,
                profits,
            )
        }
        return totalWays % MOD.also { memo[pos][count][profit] = it }
    }
}

class ProfitableSchemesBottomUp : ProfitableSchemes {

    private val dp = Array(LIMIT) { Array(LIMIT) { IntArray(LIMIT) } }

    override operator fun invoke(n: Int, minProfit: Int, group: IntArray, profits: IntArray): Int {
        // Initializing the base case.
        for (count in 0..n) {
            dp[group.size][count][minProfit] = 1
        }

        for (index in group.size - 1 downTo 0) {
            for (count in 0..n) {
                for (profit in 0..minProfit) {
                    // Ways to get a profitable scheme without this crime.
                    dp[index][count][profit] = dp[index + 1][count][profit]
                    if (count + group[index] <= n) {
                        // Adding ways to get profitable schemes, including this crime.
                        val minIndex = min(minProfit, profit + profits[index])
                        val local = dp[index][count][profit].plus(dp[index + 1][count + group[index]][minIndex])
                        dp[index][count][profit] = local % MOD
                    }
                }
            }
        }
        return dp[0][0][0]
    }
}

class ProfitableSchemesDP : ProfitableSchemes {
    override operator fun invoke(n: Int, minProfit: Int, group: IntArray, profits: IntArray): Int {
        val dp = Array(n + 1) { IntArray(minProfit + 1) }
        dp[0][0] = 1
        for (i in group.indices) {
            val need = group[i]
            val money = profits[i]

            for (a in (need..n).reversed()) {
                var tmp = dp[a][minProfit]
                for (b in maxOf(0, minProfit - money)..minProfit)
                    tmp = (tmp + dp[a - need][b]) % MOD
                dp[a][minProfit] = tmp

                for (b in (money until minProfit).reversed())
                    dp[a][b] = (dp[a][b] + dp[a - need][b - money]) % MOD
            }
        }
        var res = 0
        for (i in 0..n)
            res = (res + dp[i][minProfit]) % MOD
        return res
    }
}
