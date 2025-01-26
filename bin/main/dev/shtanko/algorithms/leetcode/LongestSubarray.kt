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

/**
 * 1493. Longest Subarray of 1's After Deleting One Element
 * @see <a href="https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/">Source</a>
 */
fun interface LongestSubarray {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Sliding window with at most one 0 inside.
 */
class LongestSubarraySlidingWindow : LongestSubarray {
    override operator fun invoke(nums: IntArray): Int {
        var startIndex = 0
        var remainingZeros = 1
        var result = 0
        var endIndex = 0
        while (endIndex < nums.size) {
            if (nums[endIndex] == 0) {
                remainingZeros--
            }
            while (remainingZeros < 0) {
                if (nums[startIndex] == 0) {
                    remainingZeros++
                }
                startIndex++
            }
            result = max(result, endIndex - startIndex)
            ++endIndex
        }
        return result
    }
}

/**
 * Sliding window that size doesn't shrink.
 */
class LongestSubarraySlidingWindow2 : LongestSubarray {
    override operator fun invoke(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        var startIndex = 0
        var remainingZeros = 1
        var endIndex = 0
        while (endIndex < nums.size) {
            if (nums[endIndex] == 0) remainingZeros--
            if (remainingZeros < 0 && nums[startIndex++] == 0) remainingZeros++
            ++endIndex
        }
        return endIndex - startIndex - 1
    }
}
