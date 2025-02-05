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
 * 2616. Minimize the Maximum Difference of Pairs
 * @see <a href="https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/">Source</a>
 */
fun interface MinimizeMax {
    operator fun invoke(nums: IntArray, p: Int): Int
}

/**
 * Greedy + Binary Search
 */
class MinimizeMaxGreedyBS : MinimizeMax {
    override operator fun invoke(nums: IntArray, p: Int): Int {
        nums.sort()
        val n = nums.size
        var left = 0
        var right = nums[n - 1] - nums[0]
        while (left < right) {
            val mid = left + (right - left) / 2

            // If there are enough pairs, look for a smaller threshold.
            // Otherwise, look for a larger threshold.
            if (countValidPairs(nums, mid) >= p) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }

    // Find the number of valid pairs by greedy approach
    private fun countValidPairs(nums: IntArray, threshold: Int): Int {
        var index = 0
        var count = 0
        while (index < nums.size - 1) {
            // If a valid pair is found, skip both numbers.
            if (nums[index + 1] - nums[index] <= threshold) {
                count++
                index++
            }
            index++
        }
        return count
    }
}
