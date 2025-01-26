/*
 * Copyright 2020 Oleksii Shtanko
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
 * 53. Maximum Subarray
 * @see <a href="https://leetcode.com/problems/maximum-subarray/">Source</a>
 */
fun interface MaximumSubarray {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Approach 1: Optimized Brute Force
 * Time complexity: O(N^2)
 * Space complexity: O(1)
 */
class MaximumSubarrayBruteForce : MaximumSubarray {
    override operator fun invoke(nums: IntArray): Int {
        var maxSubarray = Int.MIN_VALUE
        for (i in nums.indices) {
            var currentSubarray = 0
            for (j in i until nums.size) {
                currentSubarray += nums[j]
                maxSubarray = max(maxSubarray, currentSubarray)
            }
        }
        return maxSubarray
    }
}

/**
 * Approach 2: Dynamic Programming, Kadane's Algorithm
 * Time complexity: O(N)
 * Space complexity: O(1)
 */
class DPKadanesAlgorithm : MaximumSubarray {
    override operator fun invoke(nums: IntArray): Int {
        if (nums.isEmpty()) return Int.MIN_VALUE
        var currentSubarray = nums.first()
        var maxSubarray = nums.first()

        for (i in 1 until nums.size) {
            val num = nums[i]
            currentSubarray = max(num, currentSubarray + num)
            maxSubarray = max(maxSubarray, currentSubarray)
        }

        return maxSubarray
    }
}

/**
 * Approach 3: Divide and Conquer (Advanced)
 * Time complexity: O(N*log N), where N is the length of nums.
 * Space complexity: O(log N)
 */
class MSDivideAndConquer : MaximumSubarray {

    override operator fun invoke(nums: IntArray): Int {
        // Our helper function is designed to solve this problem for
        // any array - so just call it using the entire input!
        return findBestSubarray(nums, 0, nums.size - 1)
    }

    private fun findBestSubarray(numsArray: IntArray, left: Int, right: Int): Int {
        // Base case - empty array.
        if (left > right) {
            return Int.MIN_VALUE
        }
        val mid = Math.floorDiv(left + right, 2)
        var curr = 0
        var bestLeftSum = 0
        var bestRightSum = 0

        // Iterate from the middle to the beginning.
        for (i in mid - 1 downTo left) {
            curr += numsArray[i]
            bestLeftSum = max(bestLeftSum, curr)
        }

        // Reset curr and iterate from the middle to the end.
        curr = 0
        for (i in mid + 1..right) {
            curr += numsArray[i]
            bestRightSum = max(bestRightSum, curr)
        }

        // The bestCombinedSum uses the middle element and the best
        // possible sum from each half.
        val bestCombinedSum: Int = numsArray[mid] + bestLeftSum + bestRightSum

        // Find the best subarray possible from both halves.
        val leftHalf = findBestSubarray(numsArray, left, mid - 1)
        val rightHalf = findBestSubarray(numsArray, mid + 1, right)

        // The largest of the 3 is the answer for any given input array.
        return max(bestCombinedSum, max(leftHalf, rightHalf))
    }
}
