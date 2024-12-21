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

/**
 * 992. Subarrays with K Different Integers
 * @see <a href="https://leetcode.com/problems/subarrays-with-k-different-integers">Source</a>
 */
fun interface SubarraysWithKDistinct {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class SubarraysWithKDistinctSlidingWindow : SubarraysWithKDistinct {
    override fun invoke(nums: IntArray, k: Int): Int {
        return slidingWindowAtMost(nums, k) - slidingWindowAtMost(nums, k - 1)
    }

    // Helper function to count the number of subarrays with at most k distinct elements.
    private fun slidingWindowAtMost(nums: IntArray, distinctK: Int): Int {
        // To store the occurrences of each element.
        val freqMap: MutableMap<Int, Int> = HashMap()
        var left = 0
        var totalCount = 0

        // Right pointer of the sliding window iterates through the array.
        for (right in nums.indices) {
            freqMap[nums[right]] = freqMap.getOrDefault(nums[right], 0) + 1

            // If the number of distinct elements in the window exceeds k,
            // we shrink the window from the left until we have at most k distinct elements.
            while (freqMap.size > distinctK) {
                freqMap[nums[left]] = freqMap.getOrDefault(nums[left], 0) - 1
                if (freqMap[nums[left]] == 0) {
                    freqMap.remove(nums[left])
                }
                left++
            }

            // Update the total count by adding the length of the current subarray.
            totalCount += (right - left + 1)
        }
        return totalCount
    }
}

class SubarraysWithKDistinctOnePass : SubarraysWithKDistinct {
    override fun invoke(nums: IntArray, k: Int): Int {
        // Array to store the count of distinct values encountered
        val distinctCount = IntArray(nums.size + 1)

        var totalCount = 0
        var left = 0
        var right = 0
        var currCount = 0
        var k0 = k

        while (right < nums.size) {
            // Increment the count of the current element in the window
            if (distinctCount[nums[right++]]++ == 0) {
                // If encountering a new distinct element, decrement K
                k0--
            }

            // If K becomes negative, adjust the window from the left
            if (k0 < 0) {
                // Move the left pointer until the count of distinct elements becomes valid again
                --distinctCount[nums[left++]]
                k0++
                currCount = 0
            }

            // If K becomes zero, calculate subarrays
            if (k0 == 0) {
                // While the count of left remains greater than 1, keep shrinking the window from the left
                while (distinctCount[nums[left]] > 1) {
                    --distinctCount[nums[left++]]
                    currCount++
                }
                // Add the count of subarrays with K distinct elements to the total count
                totalCount += (currCount + 1)
            }
        }
        return totalCount
    }
}
