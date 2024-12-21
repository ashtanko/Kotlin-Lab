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

import dev.shtanko.algorithms.E_9
import kotlin.math.max
import kotlin.math.min

/**
 * 2742. Painting the Walls
 * @see <a href="https://leetcode.com/problems/painting-the-walls">Source</a>
 */
fun interface PaintWalls {
    operator fun invoke(cost: IntArray, time: IntArray): Int
}

/**
 * Approach 1: Top-Down Dynamic Programming
 */
class PaintWallsTopDown : PaintWalls {
    private lateinit var memo: Array<IntArray>
    private var n = 0

    override fun invoke(cost: IntArray, time: IntArray): Int {
        n = cost.size
        memo = Array(n) { IntArray(n + 1) }
        return dp(0, n, cost, time)
    }

    private fun dp(i: Int, remain: Int, cost: IntArray, time: IntArray): Int {
        if (remain <= 0) {
            return 0
        }
        if (i == n) {
            return 1e9.toInt()
        }
        if (memo[i][remain] != 0) {
            return memo[i][remain]
        }
        val paint = cost[i] + dp(i + 1, remain - 1 - time[i], cost, time)
        val dontPaint = dp(i + 1, remain, cost, time)
        memo[i][remain] = min(paint.toDouble(), dontPaint.toDouble()).toInt()
        return memo[i][remain]
    }
}

/**
 * Approach 2: Bottom-Up Dynamic Programming
 */
class PaintWallsBottomUp : PaintWalls {
    override fun invoke(cost: IntArray, time: IntArray): Int {
        val n: Int = cost.size
        val dp = Array(n + 1) { IntArray(n + 1) }

        for (i in 1..n) {
            dp[n][i] = E_9.toInt()
        }

        for (i in n - 1 downTo 0) {
            for (remain in 1..n) {
                val paint = cost[i] + dp[i + 1][max(0, remain - 1 - time[i])]
                val dontPaint = dp[i + 1][remain]
                dp[i][remain] = min(paint.toDouble(), dontPaint.toDouble()).toInt()
            }
        }

        return dp[0][n]
    }
}

/**
 * Approach 3: Space-Optimized Dynamic Programming
 */
class PaintWallsSpaceOptDP : PaintWalls {
    override fun invoke(cost: IntArray, time: IntArray): Int {
        val n: Int = cost.size
        var dp = IntArray(n + 1)
        var prevDp = IntArray(n + 1) { E_9.toInt() }
        prevDp[0] = 0

        for (i in n - 1 downTo 0) {
            dp = IntArray(n + 1)
            for (remain in 1..n) {
                val paint = cost[i] + prevDp[max(0, remain - 1 - time[i])]
                val dontPaint = prevDp[remain]
                dp[remain] = min(paint.toDouble(), dontPaint.toDouble()).toInt()
            }
            prevDp = dp
        }

        return dp[n]
    }
}
