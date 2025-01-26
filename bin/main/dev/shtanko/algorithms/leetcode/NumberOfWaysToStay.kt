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
 * 1269. Number of Ways to Stay in the Same Place After Some Steps
 * @see <a href="https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps">Source</a>
 */
fun interface NumberOfWaysToStay {
    operator fun invoke(steps: Int, arrLen: Int): Int
}

/**
 * Approach 1: Top-Down Dynamic Programming
 */
class NumberOfWaysToStayTopDown : NumberOfWaysToStay {

    lateinit var memo: Array<IntArray>
    private var arrLen = 0

    override fun invoke(steps: Int, arrLen: Int): Int {
        this.arrLen = min(arrLen, steps)
        memo = Array(arrLen) { IntArray(steps + 1) { -1 } }
        return dp(0, steps)
    }

    private fun dp(curr: Int, remain: Int): Int {
        if (remain == 0) {
            return if (curr == 0) {
                1
            } else {
                0
            }
        }
        if (memo[curr][remain] != -1) {
            return memo[curr][remain]
        }
        var ans = dp(curr, remain - 1)
        if (curr > 0) {
            ans = (ans + dp(curr - 1, remain - 1)) % MOD
        }
        if (curr < arrLen - 1) {
            ans = (ans + dp(curr + 1, remain - 1)) % MOD
        }
        memo[curr][remain] = ans
        return ans
    }
}

/**
 * Approach 2: Bottom-Up Dynamic Programming
 */
class NumberOfWaysToStayBottomUp : NumberOfWaysToStay {
    override fun invoke(steps: Int, arrLen: Int): Int {
        val len = min(arrLen, steps)
        val dp = Array(len) { IntArray(steps + 1) }
        dp[0][0] = 1

        for (remain in 1..steps) {
            for (curr in len - 1 downTo 0) {
                var ans = dp[curr][remain - 1]
                if (curr > 0) {
                    ans = (ans + dp[curr - 1][remain - 1]) % MOD
                }
                if (curr < len - 1) {
                    ans = (ans + dp[curr + 1][remain - 1]) % MOD
                }
                dp[curr][remain] = ans
            }
        }

        return dp[0][steps]
    }
}
