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

import kotlin.math.max
import kotlin.math.min

/**
 * 1140. Stone Game II
 * @see <a href="https://leetcode.com/problems/stone-game-ii/">Source</a>
 */
fun interface StoneGame2 {
    /**
     * This function calculates the maximum score a player can get.
     * @param piles The array of piles of stones.
     * @return The maximum score a player can get.
     */
    operator fun invoke(piles: IntArray): Int
}

/**
 * Approach 1: Memoization
 */
class StoneGame2WithMemoization : StoneGame2 {

    /**
     * This function calculates the maximum score a player can get.
     * It uses a memoization approach.
     * @param piles The array of piles of stones.
     * @return The maximum score a player can get.
     */
    override operator fun invoke(piles: IntArray): Int {
        val memo = Array(2) {
            Array(piles.size + 1) { IntArray(piles.size + 1) }
        }
        for (player in 0..1) {
            for (i in 0..piles.size) {
                for (m in 0..piles.size) {
                    memo[player][i][m] = -1
                }
            }
        }
        return calculateMinimumTurns(piles, memo, 0, 0, 1)
    }

    /**
     * This function calculates the maximum score a player can get for a given state of the game.
     * It uses a memoization approach.
     * @param piles The array of piles of stones.
     * @param memo The memoization table.
     * @param player The current player (0 or 1).
     * @param i The current index in the array of piles.
     * @param m The maximum number of piles the player can take.
     * @return The maximum score a player can get for the given state of the game.
     */
    private fun calculateMinimumTurns(piles: IntArray, memo: Array<Array<IntArray>>, player: Int, i: Int, m: Int): Int {
        if (i == piles.size) {
            return 0
        }
        if (memo[player][i][m] != -1) {
            return memo[player][i][m]
        }
        var result = if (player == 1) LIMIT else -1
        var sum = 0
        for (x in 1..min(2 * m, piles.size - i)) {
            sum += piles[i + x - 1]
            result = if (player == 0) {
                max(result, sum + calculateMinimumTurns(piles, memo, 1, i + x, max(m, x)))
            } else {
                min(result, calculateMinimumTurns(piles, memo, 0, i + x, max(m, x)))
            }
        }
        return result.also { memo[player][i][m] = it }
    }

    companion object {
        private const val LIMIT = 1000000
    }
}

/**
 * Approach 2: Dynamic Programming (Tabulation)
 */
class StoneGame2DP : StoneGame2 {
    override fun invoke(piles: IntArray): Int {
        if (piles.isEmpty()) {
            return 0
        }
        val length = piles.size
        val dp = Array(length + 1) { IntArray(length + 1) }

        // Store suffix sum for all possible suffixes
        val suffixSum = IntArray(length + 1)
        for (i in length - 1 downTo 0) {
            suffixSum[i] = suffixSum[i + 1] + piles[i]
        }

        // Initialize the dp array.
        for (i in 0..length) {
            dp[i][length] = suffixSum[i]
        }

        // Start from the last index to store the future state first.
        for (index in length - 1 downTo 0) {
            for (maxTillNow in length - 1 downTo 1) {
                for (x in 1..(2 * maxTillNow).coerceAtMost(length - index)) {
                    dp[index][maxTillNow] = maxOf(
                        dp[index][maxTillNow],
                        suffixSum[index] - dp[index + x][maxTillNow.coerceAtLeast(x)],
                    )
                }
            }
        }
        return dp[0][1]
    }
}
