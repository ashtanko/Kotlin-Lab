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
 * 2256. Minimum Average Difference
 * @see <a href="https://leetcode.com/problems/minimum-average-difference/">Source</a>
 */
fun interface MinimumAverageDifference {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Approach 1: Brute Force
 */
class MinimumAverageDifferenceBruteForce : MinimumAverageDifference {
    override operator fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        var ans = -1
        var minAvgDiff = Int.MAX_VALUE

        for (index in 0 until n) {
            // Calculate average of left part of array, index 0 to i.
            var leftPartAverage: Long = 0
            for (i in 0..index) {
                leftPartAverage += nums[i]
            }
            leftPartAverage /= (index + 1).toLong()

            // Calculate average of right part of array, index i+1 to n-1.
            var rightPartAverage: Long = 0
            for (j in index + 1 until n) {
                rightPartAverage += nums[j]
            }

            // If right part have 0 elements, then we don't divide by 0.
            if (index != n - 1) {
                rightPartAverage /= (n - index - 1).toLong()
            }
            val currDifference = abs(leftPartAverage - rightPartAverage).toInt()
            // If current difference of averages is smaller,
            // then current index can be our answer.
            if (currDifference < minAvgDiff) {
                minAvgDiff = currDifference
                ans = index
            }
        }

        return ans
    }
}

/**
 * Approach 2: Prefix Sum
 */
class MinimumAverageDifferencePrefixSum : MinimumAverageDifference {
    override operator fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        var ans = -1
        var minAvgDiff = Int.MAX_VALUE

        // Generate prefix and suffix sum arrays.

        // Generate prefix and suffix sum arrays.
        val prefixSum = LongArray(n + 1)
        val suffixSum = LongArray(n + 1)

        for (index in 0 until n) {
            prefixSum[index + 1] = prefixSum[index] + nums[index]
        }

        for (index in n - 1 downTo 0) {
            suffixSum[index] = suffixSum[index + 1] + nums[index]
        }

        for (i in 0 until n) {
            // Calculate average of left part of array, index 0 to i.
            var leftPartAverage = prefixSum[i + 1]
            leftPartAverage /= (i + 1).toLong()

            // Calculate average of right part of array, index i+1 to n-1.
            var rightPartAverage = suffixSum[i + 1]
            // If right part have 0 elements, then we don't divide by 0.
            if (i != n - 1) {
                rightPartAverage /= (n - i - 1).toLong()
            }
            val currDifference = abs(leftPartAverage - rightPartAverage).toInt()
            // If current difference of averages is smaller,
            // then current index can be our answer.
            if (currDifference < minAvgDiff) {
                minAvgDiff = currDifference
                ans = i
            }
        }

        return ans
    }
}

/**
 * Approach 3: Prefix Sum Optimized
 */
class MinimumAverageDifferencePrefixSumOpt : MinimumAverageDifference {
    override operator fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        var ans = -1
        var minAvgDiff = Int.MAX_VALUE
        var currPrefixSum: Long = 0

        // Get total sum of array.

        // Get total sum of array.
        var totalSum: Long = 0
        for (index in 0 until n) {
            totalSum += nums[index]
        }

        for (index in 0 until n) {
            currPrefixSum += nums[index]

            // Calculate average of left part of array, index 0 to i.
            var leftPartAverage = currPrefixSum
            leftPartAverage /= (index + 1).toLong()

            // Calculate average of right part of array, index i+1 to n-1.
            var rightPartAverage = totalSum - currPrefixSum
            // If right part have 0 elements, then we don't divide by 0.
            if (index != n - 1) {
                rightPartAverage /= (n - index - 1).toLong()
            }
            val currDifference = abs(leftPartAverage - rightPartAverage).toInt()
            // If current difference of averages is smaller,
            // then current index can be our answer.
            if (currDifference < minAvgDiff) {
                minAvgDiff = currDifference
                ans = index
            }
        }

        return ans
    }
}

class MinimumAverageDifferenceKt : MinimumAverageDifference {
    override operator fun invoke(nums: IntArray): Int {
        var sum = 0L
        nums.forEach { sum += it.toLong() }
        var leftSum = 0L
        var min = Long.MAX_VALUE
        var minInd = 0
        for (i in 0..nums.lastIndex) {
            val leftCount = (i + 1).toLong()
            leftSum += nums[i].toLong()
            val front = leftSum / leftCount
            val rightCount = nums.size.toLong() - leftCount
            val rightSum = sum - leftSum
            val back = if (rightCount == 0L) 0L else rightSum / rightCount
            val diff = abs(front - back)
            if (diff < min) {
                min = diff
                minInd = i
            }
        }
        return minInd
    }
}
