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

import dev.shtanko.algorithms.NANO
import kotlin.math.ln

/**
 * 713. Subarray Product Less Than K
 * @see <a href="https://leetcode.com/problems/subarray-product-less-than-k">Source</a>
 */
fun interface SubarrayProductLessThanK {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class SubarrayProductLessThanKUsingSlidingWindow : SubarrayProductLessThanK {
    override fun invoke(nums: IntArray, k: Int): Int {
        // Handle edge cases where k is 0 or 1 (no subarrays possible)
        if (k <= 1) return 0

        var totalCount = 0
        var product = 1

        var left = 0
        var right = 0
        while (right < nums.size) {
            // Expand the window by including the element at the right pointer
            product *= nums[right]

            // Shrink the window from the left while the product is greater than or equal to k
            while (product >= k) {
                // Remove the element at the left pointer from the product
                product /= nums[left++]
            }

            // Update the total count by adding the number of valid subarrays with the current window size
            totalCount += right - left + 1 // right - left + 1 represents the current window size
            right++
        }

        return totalCount
    }
}

class SubarrayProductLessThanKBinarySearch : SubarrayProductLessThanK {
    override fun invoke(nums: IntArray, k: Int): Int {
        if (k == 0) return 0
        val logK = ln(k.toDouble())
        val m: Int = nums.size + 1
        val logsPrefixSum = DoubleArray(m)

        // Calculate prefix sum of logarithms of elements
        for (i in nums.indices) {
            logsPrefixSum[i + 1] = logsPrefixSum[i] + ln(nums[i].toDouble())
        }

        var totalCount = 0

        // Calculate subarray count with product less than k
        for (currIdx in 0 until m) {
            var low = currIdx + 1
            var high = m
            while (low < high) {
                val mid = low + (high - low) / 2
                if (logsPrefixSum[mid] < logsPrefixSum[currIdx] + logK - NANO) {
                    low = mid + 1
                } else {
                    high = mid
                }
            }
            totalCount += low - currIdx - 1
        }
        return totalCount
    }
}
