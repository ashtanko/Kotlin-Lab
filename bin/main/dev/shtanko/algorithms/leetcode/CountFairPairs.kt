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

import dev.shtanko.algorithms.annotations.BinarySearch
import dev.shtanko.algorithms.annotations.TwoPointers
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2563. Count the Number of Fair Pairs
 * @see <a href="https://leetcode.com/problems/count-the-number-of-fair-pairs/">Count the Number of Fair Pairs</a>
 */
@Medium(link = "https://leetcode.com/problems/count-the-number-of-fair-pairs/")
fun interface CountFairPairs {
    operator fun invoke(nums: IntArray, lower: Int, upper: Int): Long
}

@BinarySearch
data object CountFairPairsBinarySearch : CountFairPairs {
    override fun invoke(nums: IntArray, lower: Int, upper: Int): Long {
        nums.sort()
        var ans = 0L
        for (i in nums.indices) {
            // Assume we have picked nums[i] as the first pair element.

            // `low` indicates the number of possible pairs with sum < lower.
            val low = lowerBound(nums, i + 1, nums.size - 1, lower - nums[i])

            // `high` indicates the number of possible pairs with sum <= upper.
            val high = lowerBound(nums, i + 1, nums.size - 1, upper - nums[i] + 1)

            // Their difference gives the number of elements with sum in the
            // given range.
            ans += high - low
        }
        return ans
    }

    private fun lowerBound(nums: IntArray, low: Int, high: Int, element: Int): Int {
        var low = low
        var high = high
        while (low <= high) {
            val mid = low + (high - low) / 2
            if (nums[mid] >= element) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return low
    }
}

@TwoPointers
data object CountFairPairsTwoPointers : CountFairPairs {
    override fun invoke(nums: IntArray, lower: Int, upper: Int): Long {
        nums.sort()
        return lowerBound(nums, upper + 1) - lowerBound(nums, lower)
    }

    private fun lowerBound(nums: IntArray, value: Int): Long {
        var left = 0
        var right = nums.size - 1
        var result = 0L
        while (left < right) {
            val sum = nums[left] + nums[right]
            // If sum is less than value, add the size of the window to result and move to the next index.
            if (sum < value) {
                result += right - left
                left++
            } else {
                // Otherwise, shift the right pointer backwards until we get a valid window.
                right--
            }
        }
        return result
    }
}
