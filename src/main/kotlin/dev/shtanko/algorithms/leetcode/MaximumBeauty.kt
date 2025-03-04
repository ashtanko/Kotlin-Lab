/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2779. Maximum Beauty of an Array After Applying Operation
 * @see <a href="https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/">Source</a>
 */
@Medium("https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation")
sealed interface MaximumBeauty {
    operator fun invoke(nums: IntArray, k: Int): Int

    @dev.shtanko.algorithms.annotations.BinarySearch
    data object BinarySearch : MaximumBeauty {
        override fun invoke(nums: IntArray, k: Int): Int {
            nums.sort()
            var maxBeauty = 0

            for (i in nums.indices) {
                // Find the farthest index where the value is within the range [nums[i], nums[i] + 2 * k]
                val upperBound = findUpperBound(nums, nums[i] + 2 * k)
                // Update the maximum beauty based on the current range
                maxBeauty = maxOf(maxBeauty, upperBound - i + 1)
            }
            return maxBeauty
        }

        private fun findUpperBound(arr: IntArray, value: Int): Int {
            var low = 0
            var high = arr.size - 1
            var result = 0

            // Perform binary search to find the upper bound
            while (low <= high) {
                val mid = low + (high - low) / 2
                if (arr[mid] <= value) {
                    result = mid // Update the result and move to the right half
                    low = mid + 1
                } else {
                    high = mid - 1 // Move to the left half
                }
            }
            return result
        }
    }

    @dev.shtanko.algorithms.annotations.SlidingWindow
    data object SlidingWindow : MaximumBeauty {
        override fun invoke(nums: IntArray, k: Int): Int {
            nums.sort()
            var right = 0 // Pointer for the end of the valid range
            var maxBeauty = 0

            // Iterate through the array with the left pointer
            for (left in nums.indices) {
                // Expand the right pointer while the range condition is met
                while (right < nums.size && nums[right] - nums[left] <= 2 * k) {
                    right++
                }
                // Update the maximum beauty based on the current range
                maxBeauty = maxOf(maxBeauty, right - left)
            }
            return maxBeauty
        }
    }

    data object LineSweep : MaximumBeauty {
        override fun invoke(nums: IntArray, k: Int): Int {
            if (nums.size == 1) return 1

            var maxBeauty = 0
            var maxValue = 0

            // Find the maximum value in the array
            for (num in nums) {
                maxValue = maxOf(maxValue, num)
            }

            // Create an array to keep track of the count changes
            val count = IntArray(maxValue + 1)

            // Update the count array for each value's range [val - k, val + k]
            for (num in nums) {
                count[maxOf(num - k, 0)]++ // Increment at the start of the range
                if (num + k + 1 <= maxValue) {
                    count[num + k + 1]-- // Decrement after the range
                }
            }

            var currentSum = 0 // Tracks the running sum of counts
            // Calculate the prefix sum and find the maximum value
            for (value in count) {
                currentSum += value
                maxBeauty = maxOf(maxBeauty, currentSum)
            }

            return maxBeauty
        }
    }
}
