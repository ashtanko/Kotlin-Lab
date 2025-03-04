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

import dev.shtanko.algorithms.annotations.BinarySearch
import dev.shtanko.algorithms.annotations.SlidingWindow
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 3097. Shortest Subarray With OR at Least K II
 * @see <a href="https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/">Source</a>
 */
@Medium("https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii")
fun interface MinSubarrayLen {
    operator fun invoke(nums: IntArray, k: Int): Int
}

@BinarySearch
class MinSubarrayLenBinarySearch : MinSubarrayLen {
    override fun invoke(nums: IntArray, k: Int): Int {
        var left = 1
        var right = nums.size
        var minLength = -1

        while (left <= right) {
            val mid = left + (right - left) / 2

            if (hasValidSubarray(nums, k, mid)) {
                minLength = mid
                right = mid - 1 // Try to find smaller length
            } else {
                left = mid + 1 // Try larger length
            }
        }

        return minLength
    }

    private fun hasValidSubarray(nums: IntArray, targetSum: Int, windowSize: Int): Boolean {
        val bitCounts = IntArray(32) // Tracks count of set bits at each position

        // Sliding window approach
        for (right in nums.indices) {
            // Add current number to window
            bitCounts.updateBitCounts(nums[right], 1)

            // Remove leftmost number if window exceeds size
            if (right >= windowSize) {
                bitCounts.updateBitCounts(nums[right - windowSize], -1)
            }

            // Check if current window is valid
            if (right >= windowSize - 1 && bitCounts.convertBitsToNum() >= targetSum) {
                return true
            }
        }

        return false
    }
}

@SlidingWindow
class MinSubarrayLenSlidingWindow : MinSubarrayLen {
    override fun invoke(nums: IntArray, k: Int): Int {
        var minLength = Int.MAX_VALUE
        var windowStart = 0
        var windowEnd = 0
        val bitCounts = IntArray(32) // Tracks count of set bits at each position

        // Expand window until end of array
        while (windowEnd < nums.size) {
            // Add current number to window
            bitCounts.updateBitCounts(nums[windowEnd], 1)

            // Contract window while OR value is valid
            while (windowStart <= windowEnd && bitCounts.convertBitsToNum() >= k) {
                // Update minimum length found so far
                minLength = minOf(minLength, windowEnd - windowStart + 1)

                // Remove leftmost number and shrink window
                bitCounts.updateBitCounts(nums[windowStart], -1)
                windowStart++
            }

            windowEnd++
        }

        return if (minLength == Int.MAX_VALUE) -1 else minLength
    }
}

private fun IntArray.updateBitCounts(number: Int, delta: Int) {
    // Update counts for each set bit in the number
    for (pos in 0 until 32) {
        if (number and (1 shl pos) != 0) {
            this[pos] += delta
        }
    }
}

private fun IntArray.convertBitsToNum(): Int {
    return (0 until 32).sumOf { pos -> if (this[pos] > 0) 1 shl pos else 0 }
}
