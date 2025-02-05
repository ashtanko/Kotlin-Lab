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

import kotlin.math.max

/**
 * 486. Predict the Winner
 */
fun interface PredictTheWinner {
    operator fun invoke(nums: IntArray): Boolean
}

/**
 * Approach #1 Using Recursion
 */
class PredictTheWinnerRecursion : PredictTheWinner {
    override operator fun invoke(nums: IntArray): Boolean {
        return winner(nums, 0, nums.size - 1, 1) >= 0
    }

    private fun winner(nums: IntArray, s: Int, e: Int, turn: Int): Int {
        if (s == e) return turn * nums[s]
        val a = turn * nums[s] + winner(nums, s + 1, e, -turn)
        val b = turn * nums[e] + winner(nums, s, e - 1, -turn)
        return turn * max(turn * a, turn * b)
    }
}

/**
 * Approach #2 Similar Approach
 */
class PredictTheWinnerMemo : PredictTheWinner {
    override operator fun invoke(nums: IntArray): Boolean {
        val memo = Array(nums.size) {
            arrayOfNulls<Int>(nums.size)
        }
        return winner(nums, 0, nums.size - 1, memo) >= 0
    }

    private fun winner(nums: IntArray, s: Int, e: Int, memo: Array<Array<Int?>>): Int {
        if (s == e) return nums[s]
        if (memo[s][e] != null) return memo[s][e] ?: -1
        val a = nums[s] - winner(nums, s + 1, e, memo)
        val b = nums[e] - winner(nums, s, e - 1, memo)
        memo[s][e] = max(a, b)
        return memo[s][e] ?: -1
    }
}

/**
 * Approach #3 Dynamic Programming
 */
class PredictTheWinnerDP : PredictTheWinner {
    override operator fun invoke(nums: IntArray): Boolean {
        val dp = Array(nums.size) { IntArray(nums.size) }
        for (s in nums.size downTo 0) {
            for (e in s until nums.size) {
                if (s == e) {
                    dp[s][e] = nums[s]
                } else {
                    val a = nums[s] - dp[s + 1][e]
                    val b = nums[e] - dp[s][e - 1]
                    dp[s][e] = max(a, b)
                }
            }
        }
        return dp[0][nums.size - 1] >= 0
    }
}

/**
 * Approach #4 1-D Dynamic Programming
 */
class PredictTheWinnerDP2 : PredictTheWinner {
    override operator fun invoke(nums: IntArray): Boolean {
        val dp = IntArray(nums.size)
        for (s in nums.size downTo 0) {
            for (e in s until nums.size) {
                if (s == e) {
                    dp[s] = nums[s]
                } else {
                    val a = nums[s] - dp[e]
                    val b = nums[e] - dp[e - 1]
                    dp[e] = max(a, b)
                }
            }
        }
        return dp[nums.size - 1] >= 0
    }
}
