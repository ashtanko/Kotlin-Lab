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
import dev.shtanko.algorithms.annotations.BinarySearch
import dev.shtanko.algorithms.annotations.PrefixSum
import kotlin.math.max

/**
 * 1508. Range Sum of Sorted Subarray Sums
 * @see <a href="https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/">Source</a>
 */
fun interface RangeSum {
    operator fun invoke(nums: IntArray, n: Int, left: Int, right: Int): Int
}

@PrefixSum
class RangeSumPrefixSum : RangeSum {
    override operator fun invoke(nums: IntArray, n: Int, left: Int, right: Int): Int {
        // Initialize the result variable to store the final sum
        var result: Long = 0

        // Initialize the currentSum variable to store the sum of the current subarray
        var currentSum: Long = 0

        // List to store the sums of all subarrays
        val subarraySums: MutableList<Long> = ArrayList()

        // List to store the prefix sums
        val prefixSums: MutableList<Long> = ArrayList()

        // Initialize leftIndex with the value of left
        var leftIndex = left

        // Add an initial value of 0 to prefixSums to handle subarrays starting from index 0
        prefixSums.add(0L)

        // Calculate prefix sums and subarray sums
        for (i in 0 until n) {
            // Update the current sum with the current element
            currentSum += nums[i]

            // Add the current sum to the prefix sums list
            prefixSums.add(currentSum)

            // Calculate all subarray sums ending at the current index
            for (j in 0 until prefixSums.size - 1) {
                subarraySums.add(currentSum - prefixSums[j])
            }
        }

        // Sort the list of subarray sums
        subarraySums.sort()

        // Sum the elements from the left-th to the right-th smallest subarray sums
        while (leftIndex <= right) {
            result = (result + subarraySums[leftIndex++ - 1]) % MOD
        }

        // Return the result as an integer
        return result.toInt()
    }
}

@BinarySearch
class RangeSumBinarySearch : RangeSum {
    override operator fun invoke(nums: IntArray, n: Int, left: Int, right: Int): Int {
        // Find the maximum value for the left boundary
        val maxLeft = findMaxValue(nums, left)

        // Find the maximum value for the right boundary
        val maxRight = findMaxValue(nums, right)

        // Calculate the range sum for the left boundary
        val rangeSumLeft = calculateRangeSum(nums, maxLeft)

        // Calculate the range sum for the right boundary
        val rangeSumRight = calculateRangeSum(nums, maxRight)

        // Calculate the result using the range sums and boundaries
        var result = Math.floorMod(rangeSumRight[0] + (right - rangeSumRight[1]) * (maxRight + 1), MOD)
        result = Math.floorMod(result - (rangeSumLeft[0] + (left - rangeSumLeft[1] - 1) * (maxLeft + 1)), MOD)

        // Return the final result
        return result
    }

    private fun findMaxValue(nums: IntArray, count: Int): Int {
        // Initialize low and high pointers
        var low = 0
        var high = 0

        // Calculate the sum of all elements in nums
        for (value in nums) high += value

        // Perform binary search to find the maximum value
        while (low < high) {
            val mid = low + (high - low + 1) / 2
            val sum = calculateRangeSum(nums, mid)
            if (sum[1] >= count) {
                high = mid - 1
            } else {
                low = mid
            }
        }

        // Return the maximum value found
        return low
    }

    private fun calculateRangeSum(nums: IntArray, maxValue: Int): IntArray {
        // Initialize variables to store the total range sum and count
        var totalRangeSum = 0
        var totalRangeCount = 0

        // Initialize low and high pointers
        var low = 0
        var high = 0

        // Get the size of the nums array
        val size = nums.size

        // Initialize variables to store the current sum and cumulative sum
        var currentSum = 0
        var cumulativeSum = 0

        // Iterate through the nums array to calculate range sums
        while (low < size) {
            while (high < size && currentSum + nums[high] <= maxValue) {
                currentSum += nums[high]
                cumulativeSum = (cumulativeSum + currentSum) % MOD
                ++high
            }
            totalRangeSum = (totalRangeSum + cumulativeSum) % MOD
            if (high > low) {
                currentSum -= nums[low]
                cumulativeSum = (cumulativeSum - (high - low) * nums[low] + MOD) % MOD
                totalRangeCount += high - low
            }
            ++low
            high = max(high, low)
        }

        // Return the total range sum and count as an array
        return intArrayOf(totalRangeSum, totalRangeCount)
    }
}
