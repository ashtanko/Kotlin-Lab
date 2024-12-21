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

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * 2448. Minimum Cost to Make Array Equal
 * @see <a href="https://leetcode.com/problems/minimum-cost-to-make-array-equal/">Source</a>
 */
fun interface MinCostArrayEqual {
    fun minCost(nums: IntArray, cost: IntArray): Long
}

/**
 * Approach 1: Prefix Sum
 */
class MinCostArrayEqualPrefixSum : MinCostArrayEqual {
    override fun minCost(nums: IntArray, cost: IntArray): Long {
        // Sort integers by values.
        val n: Int = nums.size
        val numsAndCost = Array(n) { IntArray(2) }
        for (i in 0 until n) {
            numsAndCost[i][0] = nums[i]
            numsAndCost[i][1] = cost[i]
        }
        numsAndCost.sortWith { a: IntArray, b: IntArray -> a[0] - b[0] }

        // Get the prefix sum array of the costs.
        val prefixCost = LongArray(n)
        prefixCost[0] = numsAndCost[0][1].toLong()
        for (i in 1 until n) prefixCost[i] = numsAndCost[i][1] + prefixCost[i - 1]

        // Then we try every integer nums[i] and make every element equals nums[i],
        // Start with nums[0]
        var totalCost = 0L
        for (i in 1 until n) totalCost += 1L * numsAndCost[i][1] * (numsAndCost[i][0] - numsAndCost[0][0])
        var answer = totalCost

        // Then we try nums[1], nums[2] and so on. The cost difference is made by the change of
        // two parts: 1. prefix sum of costs. 2. suffix sum of costs.
        // During the iteration, record the minimum cost we have met.
        for (i in 1 until n) {
            val gap = numsAndCost[i][0] - numsAndCost[i - 1][0]
            totalCost += 1L * prefixCost[i - 1] * gap
            totalCost -= 1L * (prefixCost[n - 1] - prefixCost[i - 1]) * gap
            answer = min(answer, totalCost)
        }

        return answer
    }
}

/**
 * Approach 2: Binary Search
 */
class MinCostArrayEqualBinarySearch : MinCostArrayEqual {

    companion object {
        private const val MAX = 1000001
    }

    override fun minCost(nums: IntArray, cost: IntArray): Long {
        // Initialize the left and the right boundary of the binary search.
        var left = MAX
        var right = 0
        for (num in nums) {
            left = min(left, num)
            right = max(right, num)
        }
        var answer = getCost(nums, cost, nums[0])

        // As shown in the previous picture, if F(mid) > F(mid + 1), then the minimum
        // is to the right of mid, otherwise, the minimum is to the left of mid.
        while (left < right) {
            val mid = (right + left) / 2
            val cost1 = getCost(nums, cost, mid)
            val cost2 = getCost(nums, cost, mid + 1)
            answer = min(cost1, cost2)
            if (cost1 > cost2) left = mid + 1 else right = mid
        }
        return answer
    }

    // Get the cost of making every element equals base.
    private fun getCost(nums: IntArray, cost: IntArray, base: Int): Long {
        var result = 0L
        for (i in nums.indices) result += 1L * abs(nums[i] - base) * cost[i]
        return result
    }
}
