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
 * 2134. Minimum Swaps to Group All 1's Together II
 * @see <a href="https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/">Source</a>
 */
fun interface MinSwaps2 {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Approach 1: Using Suffix Sum
 */
class MinSwaps2SuffixSum : MinSwaps2 {
    override fun invoke(nums: IntArray): Int {
        val op1 = minSwapsHelper(nums, 0) // Grouping all 0s together
        val op2 = minSwapsHelper(nums, 1) // Grouping all 1s together
        return minOf(op1, op2)
    }

    private fun minSwapsHelper(data: IntArray, value: Int): Int {
        val length = data.size
        val rightSuffixSum = IntArray(length + 1)

        // Construct the suffix sum array for counting opposite values (val xor 1)
        for (i in length - 1 downTo 0) {
            rightSuffixSum[i] = rightSuffixSum[i + 1]
            if (data[i] == (value xor 1)) rightSuffixSum[i]++
        }
        // Total zeros in the array if `val` is 1 (or vice versa)
        val totalSwapsNeeded = rightSuffixSum[0]
        // Track current number of required swaps in the current segment
        var currentSwapCount = 0
        var minimumSwaps = totalSwapsNeeded - rightSuffixSum[length - totalSwapsNeeded]

        // Iterate to find the minimum swaps by sliding the potential block of grouped `val`
        for (i in 0 until totalSwapsNeeded) {
            if (data[i] == (value xor 1)) currentSwapCount++
            val remaining = totalSwapsNeeded - i - 1
            val requiredSwaps =
                ((i + 1) - currentSwapCount) +
                    (remaining - rightSuffixSum[length - remaining])
            minimumSwaps = minOf(minimumSwaps, requiredSwaps)
        }
        return minimumSwaps
    }
}

/**
 * Approach 2: Using Sliding Window
 */
class MinSwaps2SlidingWindow : MinSwaps2 {
    override fun invoke(nums: IntArray): Int {
        val op1 = minSwapsHelper(nums, 0) // Grouping all 0s together
        val op2 = minSwapsHelper(nums, 1) // Grouping all 1s together
        return minOf(op1, op2)
    }

    private fun minSwapsHelper(data: IntArray, value: Int): Int {
        val length = data.size
        var totalValCount = 0

        // Count the total number of `val` in the array
        for (i in length - 1 downTo 0) {
            if (data[i] == value) totalValCount++
        }

        // If there is no `val` or the array is full of `val`,
        // no swaps are needed
        if (totalValCount == 0 || totalValCount == length) return 0

        var start = 0
        var end = 0
        var maxValInWindow = 0
        var currentValInWindow = 0

        // Initial window setup: count the number of `val` in
        // the first window of size `totalValCount`
        while (end < totalValCount) {
            if (data[end++] == value) currentValInWindow++
        }
        maxValInWindow = maxOf(maxValInWindow, currentValInWindow)

        // Slide the window across the array to find the
        // maximum number of `val` in any window
        while (end < length) {
            if (data[start++] == value) currentValInWindow--
            if (data[end++] == value) currentValInWindow++
            maxValInWindow = maxOf(maxValInWindow, currentValInWindow)
        }

        // Minimum swaps are the total `val` minus
        // the maximum found in any window
        return totalValCount - maxValInWindow
    }
}

/**
 * Approach 3: Cleaner and More Intuitive Sliding Window
 */
class MinSwaps2SlidingWindowBetter : MinSwaps2 {
    override fun invoke(nums: IntArray): Int {
        var minimumSwaps = Int.MAX_VALUE

        // Calculate the total number of 1s in the array
        val totalOnes = nums.sum()

        // Initialize the count of 1s in the current window
        var onesCount = nums[0]
        var end = 0

        // Slide the window across the array
        for (start in nums.indices) {
            // Adjust onesCount by removing the element that is sliding out of
            // the window
            if (start != 0) {
                onesCount -= nums[start - 1]
            }

            // Expand the window to the right until it reaches the desired size
            while (end - start + 1 < totalOnes) {
                end++
                onesCount += nums[end % nums.size]
            }

            // Update the minimum number of swaps needed
            minimumSwaps = minOf(minimumSwaps, totalOnes - onesCount)
        }

        return minimumSwaps
    }
}
