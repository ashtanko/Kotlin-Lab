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
 * 918. Maximum Sum Circular Subarray
 * @see <a href="https://leetcode.com/problems/maximum-sum-circular-subarray/">Source</a>
 */
fun interface MaxSubarraySumCircular {
    operator fun invoke(nums: IntArray): Int
}

class MaxSubarraySumCircularOnePass : MaxSubarraySumCircular {
    override operator fun invoke(nums: IntArray): Int {
        var total = 0
        var maxSum: Int = nums[0]
        var curMax = 0
        var minSum: Int = nums[0]
        var curMin = 0
        for (a in nums) {
            curMax = max(curMax + a, a)
            maxSum = max(maxSum, curMax)
            curMin = min(curMin + a, a)
            minSum = min(minSum, curMin)
            total += a
        }
        return if (maxSum > 0) max(maxSum, total - minSum) else maxSum
    }
}

class MaxSubarraySumCircularKadane : MaxSubarraySumCircular {
    override operator fun invoke(nums: IntArray): Int {
        val nonCircularSum = kadaneMaxSum(nums)
        var totalSum = 0
        for (i in nums.indices) {
            totalSum += nums[i]
            nums[i] = -nums[i]
        }

        val circularSum = totalSum + kadaneMaxSum(nums)
        return if (circularSum == 0) nonCircularSum else max(circularSum, nonCircularSum)
    }

    private fun kadaneMaxSum(nums: IntArray): Int {
        var currentSum = nums[0]
        var maxSum = nums[0]
        for (i in 1 until nums.size) {
            if (currentSum < 0) currentSum = 0
            currentSum += nums[i]
            maxSum = max(maxSum, currentSum)
        }
        return maxSum
    }
}
