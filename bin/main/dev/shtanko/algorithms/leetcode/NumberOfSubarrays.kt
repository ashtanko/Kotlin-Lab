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

/**
 * 1248. Count Number of Nice Subarrays
 * @see <a href="https://leetcode.com/problems/count-number-of-nice-subarrays/">Source</a>
 */
fun interface NumberOfSubarrays {
    operator fun invoke(nums: IntArray, target: Int): Int
}

class NumberOfSubarraysOnePass : NumberOfSubarrays {
    override operator fun invoke(nums: IntArray, target: Int): Int {
        var result = 0
        var startIndex = 0
        var currentCount = 0
        val arraySize: Int = nums.size
        var remainingCount = target
        for (endIndex in 0 until arraySize) {
            if (nums[endIndex] % 2 == 1) {
                --remainingCount
                currentCount = 0
            }
            while (remainingCount == 0) {
                remainingCount += nums[startIndex++] and 1
                ++currentCount
            }
            result += currentCount
        }
        return result
    }
}

class NumberOfSubarraysSlidingWindow : NumberOfSubarrays {
    override operator fun invoke(nums: IntArray, target: Int): Int {
        return atMost(nums, target) - atMost(nums, target - 1)
    }

    private fun atMost(nums: IntArray, target: Int): Int {
        var remainingTarget = target
        var result = 0
        var startIndex = 0
        val arraySize = nums.size
        for (endIndex in 0 until arraySize) {
            remainingTarget -= nums[endIndex] % 2
            while (remainingTarget < 0) {
                remainingTarget += nums[startIndex++] % 2
            }
            result += endIndex - startIndex + 1
        }
        return result
    }
}
