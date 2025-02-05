/*
 * Copyright 2024 Oleksii Shtanko
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
 * 3068. Find the Maximum Sum of Node Values
 */
fun interface MaxValueSum {
    operator fun invoke(nums: IntArray, k: Int, edges: Array<IntArray>): Long
}

class MaxValueSumTopDown : MaxValueSum {
    override fun invoke(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
        val memo = Array(nums.size) { LongArray(2) { -1L } }
        return maxSumOfNodes(0, 1, nums, k, memo)
    }

    private fun maxSumOfNodes(index: Int, isEven: Int, nums: IntArray, k: Int, memo: Array<LongArray>): Long {
        if (index == nums.size) {
            return if (isEven == 1) 0 else Long.MIN_VALUE
        }
        if (memo[index][isEven] != -1L) {
            return memo[index][isEven]
        }
        val noXorDone = nums[index].toLong() + maxSumOfNodes(index + 1, isEven, nums, k, memo)
        val xorDone = (nums[index] xor k).toLong() + maxSumOfNodes(index + 1, isEven xor 1, nums, k, memo)
        memo[index][isEven] = maxOf(xorDone, noXorDone)
        return memo[index][isEven]
    }
}

class MaxValueSumBottomUp : MaxValueSum {
    override fun invoke(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
        val n: Int = nums.size
        val dp = Array(n + 1) { LongArray(2) }
        dp[n][1] = 0
        dp[n][0] = Long.MIN_VALUE

        for (index in n - 1 downTo 0) {
            for (isEven in 0..1) {
                val performOperation = dp[index + 1][isEven xor 1] + (nums[index] xor k)
                val dontPerformOperation = dp[index + 1][isEven] + nums[index]

                dp[index][isEven] = max(performOperation, dontPerformOperation)
            }
        }

        return dp[0][1]
    }
}
