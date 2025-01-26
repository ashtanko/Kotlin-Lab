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

import dev.shtanko.algorithms.annotations.BottomUpDP
import dev.shtanko.algorithms.annotations.TopDownDP

/**
 * 518. Coin Change II
 * @see <a href="https://leetcode.com/problems/coin-change-ii/">Source</a>
 */
fun interface CoinChange2 {
    fun change(amount: Int, coins: IntArray): Int
}

/**
 * Approach 1: Top-Down Dynamic Programming
 */
@TopDownDP
class CoinChange2TopDown : CoinChange2 {

    private lateinit var memo: Array<IntArray>
    private lateinit var coins: IntArray

    override fun change(amount: Int, coins: IntArray): Int {
        this.coins = coins
        memo = Array(coins.size) { IntArray(amount + 1) { -1 } }
        return numberOfWays(0, amount)
    }

    private fun numberOfWays(i: Int, amount: Int): Int {
        if (amount == 0) {
            return 1
        }
        if (i == coins.size) {
            return 0
        }
        if (memo[i][amount] != -1) {
            return memo[i][amount]
        }
        if (coins[i] > amount) {
            return numberOfWays(i + 1, amount).also { memo[i][amount] = it }
        }
        memo[i][amount] = numberOfWays(i, amount - coins[i]) + numberOfWays(i + 1, amount)
        return memo[i][amount]
    }
}

/**
 * Approach 2: Bottom-Up Dynamic Programming
 */
@BottomUpDP
class CoinChange2BottomUp : CoinChange2 {
    override fun change(amount: Int, coins: IntArray): Int {
        val n: Int = coins.size
        val dp = Array(n + 1) { IntArray(amount + 1) }
        for (i in 0 until n) {
            dp[i][0] = 1
        }

        for (i in 1..amount) {
            dp[0][i] = 0
        }

        for (i in n - 1 downTo 0) {
            for (j in 1..amount) {
                if (coins[i] > j) {
                    dp[i][j] = dp[i + 1][j]
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - coins[i]]
                }
            }
        }

        return dp[0][amount]
    }
}

/**
 * Approach 3: Dynamic Programming with Space Optimization
 */
class CoinChange2SpaceOpt : CoinChange2 {
    override fun change(amount: Int, coins: IntArray): Int {
        val n: Int = coins.size
        val dp = IntArray(amount + 1)
        dp[0] = 1

        for (i in n - 1 downTo 0) {
            for (j in coins[i]..amount) {
                dp[j] += dp[j - coins[i]]
            }
        }

        return dp[amount]
    }
}
