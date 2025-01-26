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

import kotlin.math.abs

/**
 * 494. Target Sum
 * @see <a href="https://leetcode.com/problems/target-sum/">Source</a>
 */
fun interface TargetSum {
    fun findTargetSumWays(nums: IntArray, target: Int): Int
}

/**
 * Approach 1: Brute Force
 * Time complexity: O(2^n)
 * Space complexity: O(n)
 */
class TargetSumBruteForce : TargetSum {
    private var count = 0

    override fun findTargetSumWays(nums: IntArray, target: Int): Int {
        calculate(nums, 0, 0, target)
        return count
    }

    fun calculate(nums: IntArray, i: Int, sum: Int, target: Int) {
        if (i == nums.size) {
            if (sum == target) {
                count++
            }
        } else {
            calculate(nums, i + 1, sum + nums[i], target)
            calculate(nums, i + 1, sum - nums[i], target)
        }
    }
}

/**
 * Approach 2: Recursion with Memoization
 * Time complexity: O(t⋅n)
 * Space complexity: O(t⋅n)
 */
class TargetSumMemoization : TargetSum {
    private var total = 0

    override fun findTargetSumWays(nums: IntArray, target: Int): Int {
        total = nums.sum()

        val memo = Array(nums.size) { IntArray(2 * total + 1) { Int.MIN_VALUE } }
        return calculate(nums, 0, 0, target, memo)
    }

    fun calculate(nums: IntArray, i: Int, sum: Int, target: Int, memo: Array<IntArray>): Int {
        return if (i == nums.size) {
            if (sum == target) {
                1
            } else {
                0
            }
        } else {
            if (memo[i][sum + total] != Int.MIN_VALUE) {
                return memo[i][sum + total]
            }
            val add = calculate(nums, i + 1, sum + nums[i], target, memo)
            val subtract = calculate(nums, i + 1, sum - nums[i], target, memo)
            memo[i][sum + total] = add + subtract
            memo[i][sum + total]
        }
    }
}

/**
 * Approach 3: 2D Dynamic Programming
 * Time complexity: O(t⋅n)
 * Space complexity: O(t⋅n)
 */
class TwoDDynamicProgramming : TargetSum {
    override fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val total: Int = nums.sum()
        val dp = Array(nums.size) { IntArray(2 * total + 1) }
        dp[0][nums[0] + total] = 1
        dp[0][-nums[0] + total] += 1

        for (i in 1 until nums.size) {
            for (sum in -total..total) {
                if (dp[i - 1][sum + total] > 0) {
                    dp[i][sum + nums[i] + total] += dp[i - 1][sum + total]
                    dp[i][sum - nums[i] + total] += dp[i - 1][sum + total]
                }
            }
        }
        return if (abs(target) > total) 0 else dp[nums.size - 1][target + total]
    }
}

/**
 * Approach 4: 1D Dynamic Programming
 * Time complexity: O(t⋅n)
 * Space complexity: O(t)
 */
class OneDDynamicProgramming : TargetSum {
    override fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val total: Int = nums.sum()
        var dp = IntArray(2 * total + 1)
        dp[nums[0] + total] = 1
        dp[-nums[0] + total] += 1

        for (i in 1 until nums.size) {
            val next = IntArray(2 * total + 1)
            for (sum in -total..total) {
                if (dp[sum + total] > 0) {
                    next[sum + nums[i] + total] += dp[sum + total]
                    next[sum - nums[i] + total] += dp[sum + total]
                }
            }
            dp = next
        }

        return if (abs(target) > total) 0 else dp[target + total]
    }
}
