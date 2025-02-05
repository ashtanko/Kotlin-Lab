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

/**
 * 1406. Stone Game III
 * @see <a href="https://leetcode.com/problems/stone-game-iii/">Source</a>
 */
fun interface StoneGame3 {
    operator fun invoke(stoneValue: IntArray): String
}

private const val ALICE = "Alice"
private const val BOB = "Bob"
private const val TIE = "Tie"
private const val NOT_COMPUTED = 1000000000

/**
 * Approach 1: Bottom-Up Dynamic Programming
 */
class StoneGame3BottomUp : StoneGame3 {
    override operator fun invoke(stoneValue: IntArray): String {
        val n: Int = stoneValue.size
        val dp = IntArray(n + 1)
        for (i in n - 1 downTo 0) {
            dp[i] = stoneValue[i] - dp[i + 1]
            if (i + 2 <= n) {
                dp[i] = max(dp[i], stoneValue[i] + stoneValue[i + 1] - dp[i + 2])
            }
            if (i + 3 <= n) {
                dp[i] = max(dp[i], stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3])
            }
        }
        if (dp[0] > 0) {
            return ALICE
        }
        return if (dp[0] < 0) {
            BOB
        } else {
            TIE
        }
    }
}

/**
 * Approach 2: Top-Down Dynamic Programming (Memoization)
 */
class StoneGame3TopDown : StoneGame3 {
    override operator fun invoke(stoneValue: IntArray): String {
        val dif = f(stoneValue, stoneValue.size, 0)
        if (dif > 0) {
            return ALICE
        }
        return if (dif < 0) {
            BOB
        } else {
            TIE
        }
    }

    private fun f(stoneValue: IntArray, n: Int, i: Int): Int {
        if (i == n) {
            return 0
        }
        var result = stoneValue[i] - f(stoneValue, n, i + 1)
        if (i + 2 <= n) {
            result = max(
                result,
                stoneValue[i] + stoneValue[i + 1] - f(stoneValue, n, i + 2),
            )
        }
        if (i + 3 <= n) {
            result = max(
                result,
                stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - f(stoneValue, n, i + 3),
            )
        }
        return result
    }
}

class StoneGame3Optimized : StoneGame3 {

    private lateinit var dp: IntArray

    override operator fun invoke(stoneValue: IntArray): String {
        val n: Int = stoneValue.size
        dp = IntArray(n + 1)
        for (i in 0 until n) {
            dp[i] = NOT_COMPUTED
        }
        val dif = f(stoneValue, stoneValue.size, 0)
        if (dif > 0) {
            return ALICE
        }
        return if (dif < 0) {
            BOB
        } else {
            TIE
        }
    }

    private fun f(stoneValue: IntArray, n: Int, i: Int): Int {
        if (i == n) {
            return 0
        }
        if (dp[i] != NOT_COMPUTED) {
            return dp[i]
        }
        dp[i] = stoneValue[i] - f(stoneValue, n, i + 1)
        if (i + 2 <= n) {
            dp[i] = max(
                dp[i],
                stoneValue[i] + stoneValue[i + 1] - f(stoneValue, n, i + 2),
            )
        }
        if (i + 3 <= n) {
            dp[i] = max(
                dp[i],
                stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - f(stoneValue, n, i + 3),
            )
        }
        return dp[i]
    }
}
