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
import java.util.Arrays

/**
 * 2466. Count Ways To Build Good Strings
 * @see <a href="https://leetcode.com/problems/count-ways-to-build-good-strings/">Source</a>
 */
fun interface CountGoodStrings {
    operator fun invoke(low: Int, high: Int, zero: Int, one: Int): Int
}

/**
 * Approach 1: Dynamic Programming (Iterative)
 */
class CountGoodStringsDPRecursive : CountGoodStrings {

    override operator fun invoke(low: Int, high: Int, zero: Int, one: Int): Int {
        // Use dp[i] to record to number of good strings of length i.
        val dp = IntArray(high + 1)
        dp[0] = 1

        // Iterate over each length `end`.
        for (end in 1..high) {
            // check if the current string can be made by append zero `0`s or one `1`s.
            if (end >= zero) {
                dp[end] += dp[end - zero]
            }
            if (end >= one) {
                dp[end] += dp[end - one]
            }
            dp[end] %= MOD
        }

        // Add up the number of strings with each valid length [low ~ high].
        var answer = 0
        for (i in low..high) {
            answer += dp[i]
            answer %= MOD
        }
        return answer
    }
}

/**
 * Approach 2: Dynamic Programming (Recursive)
 */
class CountGoodStringsDPIterative : CountGoodStrings {

    // Use dp[i] to record to number of good strings of length i.
    private lateinit var dp: IntArray

    override operator fun invoke(low: Int, high: Int, zero: Int, one: Int): Int {
        dp = IntArray(high + 1)
        Arrays.fill(dp, -1)
        dp[0] = 1

        // Add up the number of strings with each valid length [low ~ high].
        var answer = 0
        for (end in low..high) {
            answer += dfs(end, zero, one)
            answer %= MOD
        }
        return answer
    }

    // Find the number of good strings of length `end`.
    private fun dfs(end: Int, zero: Int, one: Int): Int {
        if (dp[end] != -1) return dp[end]
        var count = 0
        if (end >= one) {
            count += dfs(end - one, zero, one)
        }
        if (end >= zero) {
            count += dfs(end - zero, zero, one)
        }
        dp[end] = count % MOD
        return dp[end]
    }
}
