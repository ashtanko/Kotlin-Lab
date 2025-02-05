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

import java.util.Arrays

/**
 * 403. Frog Jump
 * @see <a href="https://leetcode.com/problems/frog-jump">Source</a>
 */
fun interface FrogJump {
    operator fun invoke(stones: IntArray): Boolean
}

private const val LIMIT = 2000

/**
 * Approach 1: Top-Down Dynamic Programming
 */
class FrogJumpTopDownDp : FrogJump {
    private val mark = HashMap<Int, Int>()
    private val dp = Array(LIMIT + 1) { IntArray(LIMIT + 1) }

    override operator fun invoke(stones: IntArray): Boolean {
        // Mark stones in the map to identify if such stone exists or not.
        for (i in stones.indices) {
            mark[stones[i]] = i
        }

        // Mark all states as -1 to denote they're not calculated.
        for (i in 0 until LIMIT) {
            Arrays.fill(dp[i], -1)
        }
        return solve(stones, stones.size, 0, 0)
    }

    private fun solve(stones: IntArray, n: Int, index: Int, prevJump: Int): Boolean {
        // If reached the last stone, return 1.
        if (index == n - 1) {
            return true
        }

        // If this sub-problem has already been calculated, return it.
        if (dp[index][prevJump] != -1) {
            return dp[index][prevJump] == 1
        }
        var ans = false
        // Iterate over the three possibilities {k - 1, k, k + 1}.
        for (nextJump in prevJump - 1..prevJump + 1) {
            if (nextJump > 0 && mark.containsKey(stones[index] + nextJump)) {
                ans = ans || solve(stones, n, mark[stones[index] + nextJump]!!, nextJump)
            }
        }

        // Store the result to fetch later.
        dp[index][prevJump] = if (ans) 1 else 0
        return ans
    }
}

/**
 * Approach 2: Bottom-up Dynamic Programming
 */
class FrogJumpBottomUpDp : FrogJump {

    private val mark = HashMap<Int, Int>()
    private val dp = Array(LIMIT + 1) { BooleanArray(LIMIT + 1) }

    override operator fun invoke(stones: IntArray): Boolean {
        val n = stones.size
        // Mark stones in the map to identify if such stone exists or not.
        for (i in 0 until n) {
            mark[stones[i]] = i
        }
        dp[0][0] = true
        for (index in 0 until n) {
            for (prevJump in 0..n) {
                // If stone exists, mark the value with position and jump as 1.
                if (dp[index][prevJump]) {
                    if (mark.containsKey(stones[index] + prevJump)) {
                        dp[mark[stones[index] + prevJump]!!][prevJump] = true
                    }
                    if (mark.containsKey(stones[index] + prevJump + 1)) {
                        dp[mark[stones[index] + prevJump + 1]!!][prevJump + 1] = true
                    }
                    if (mark.containsKey(stones[index] + prevJump - 1)) {
                        dp[mark[stones[index] + prevJump - 1]!!][prevJump - 1] = true
                    }
                }
            }
        }

        // If any value with index as n - 1 is true, return true.
        for (index in 0..n) {
            if (dp[n - 1][index]) {
                return true
            }
        }
        return false
    }
}
