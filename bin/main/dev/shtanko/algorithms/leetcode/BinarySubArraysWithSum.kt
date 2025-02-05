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

import dev.shtanko.algorithms.annotations.OnePass
import dev.shtanko.algorithms.annotations.PrefixSum
import dev.shtanko.algorithms.annotations.SlidingWindow
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 930. Binary Subarrays With Sum
 * @see <a href="https://leetcode.com/problems/binary-subarrays-with-sum">Source</a>
 */
@Medium("https://leetcode.com/problems/binary-subarrays-with-sum")
fun interface BinarySubArraysWithSum {
    operator fun invoke(nums: IntArray, goal: Int): Int
}

@PrefixSum
class BinarySubArraysWithSumPrefixSum : BinarySubArraysWithSum {
    override fun invoke(nums: IntArray, goal: Int): Int {
        var totalCount = 0
        var currentSum = 0

        // {prefix: number of occurrence}
        val freq: MutableMap<Int, Int> = HashMap() // To store the frequency of prefix sums

        for (num in nums) {
            currentSum += num
            if (currentSum == goal) {
                totalCount++
            }

            // Check if there is any prefix sum that can be subtracted from the current sum to get the desired goal
            if (freq.containsKey(currentSum - goal)) {
                totalCount += freq.getOrDefault(currentSum - goal, 0)
            }

            freq[currentSum] = freq.getOrDefault(currentSum, 0) + 1
        }

        return totalCount
    }
}

@SlidingWindow
class BinarySubArraysWithSumSlidingWindow : BinarySubArraysWithSum {
    override fun invoke(nums: IntArray, goal: Int): Int {
        return slidingWindowAtMost(nums, goal) - slidingWindowAtMost(nums, goal - 1)
    }

    // Helper function to count the number of subarrays with sum at most the given goal
    private fun slidingWindowAtMost(nums: IntArray, goal: Int): Int {
        var start = 0
        var currentSum = 0
        var totalCount = 0

        // Iterate through the array using a sliding window approach
        for (end in nums.indices) {
            currentSum += nums[end]

            // Adjust the window by moving the start pointer to the right
            // until the sum becomes less than or equal to the goal
            while (start <= end && currentSum > goal) {
                currentSum -= nums[start++]
            }

            // Update the total count by adding the length of the current subarray
            totalCount += end - start + 1
        }
        return totalCount
    }
}

@OnePass
class BinarySubArraysWithSumOnePass : BinarySubArraysWithSum {
    override fun invoke(nums: IntArray, goal: Int): Int {
        var start = 0
        var prefixZeros = 0
        var currentSum = 0
        var totalCount = 0

        // Loop through the array using end pointer
        for (end in nums.indices) {
            // Add current element to the sum
            currentSum += nums[end]

            // Slide the window while condition is met
            while (start < end && (nums[start] == 0 || currentSum > goal)) {
                if (nums[start] == 1) {
                    prefixZeros = 0
                } else {
                    prefixZeros++
                }

                currentSum -= nums[start]
                start++
            }

            // Count subarrays when window sum matches the goal
            if (currentSum == goal) {
                totalCount += 1 + prefixZeros
            }
        }

        return totalCount
    }
}
