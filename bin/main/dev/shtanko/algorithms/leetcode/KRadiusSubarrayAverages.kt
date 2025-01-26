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
 * 2090. K Radius Subarray Averages
 * @see <a href="https://leetcode.com/problems/k-radius-subarray-averages/">Source</a>
 */
fun interface KRadiusSubarrayAverages {
    fun getAverages(nums: IntArray, k: Int): IntArray
}

class GetAveragesPrefixSum : KRadiusSubarrayAverages {
    override fun getAverages(nums: IntArray, k: Int): IntArray {
        // When a single element is considered then its average will be the number itself only.
        if (k == 0) {
            return nums
        }

        val n: Int = nums.size
        val averages = IntArray(n) { -1 }
        // Any index will not have 'k' elements in it's left and right.
        if (2 * k + 1 > n) {
            return averages
        }
        // Generate 'prefix' array for 'nums'.
        // 'prefix[i + 1]' will be sum of all elements of 'nums' from index '0' to 'i'.
        val prefix = LongArray(n + 1)
        for (i in 0 until n) {
            prefix[i + 1] = prefix[i] + nums[i]
        }
        // We iterate only on those indices which have at least 'k' elements in their left and right.
        // i.e. indices from 'k' to 'n - k'
        for (i in k until n - k) {
            val leftBound = i - k
            val rightBound = i + k
            val subArraySum = prefix[rightBound + 1] - prefix[leftBound]
            val average = (subArraySum / (2 * k + 1)).toInt()
            averages[i] = average
        }

        return averages
    }
}

class GetAveragesSlidingWindow : KRadiusSubarrayAverages {
    override fun getAverages(nums: IntArray, k: Int): IntArray {
        // When a single element is considered then its average will be the number itself only.
        if (k == 0) {
            return nums
        }

        val n: Int = nums.size
        val averages = IntArray(n) { -1 }
        // Any index will not have 'k' elements in its left and right.
        if (2 * k + 1 > n) {
            return averages
        }

        // First get the sum of first window of the 'nums' array.
        var windowSum: Long = 0
        for (i in 0 until 2 * k + 1) {
            windowSum += nums[i]
        }
        averages[k] = (windowSum / (2 * k + 1)).toInt()
        // Iterate on rest indices which have at least 'k' elements
        // on its left and right sides.
        for (i in 2 * k + 1 until n) {
            // We remove the discarded element and add the new element to get current window sum.
            // 'i' is the index of new inserted element, and
            // 'i - (window size)' is the index of the last removed element.
            windowSum = windowSum - nums[i - (2 * k + 1)] + nums[i]
            averages[i - k] = (windowSum / (2 * k + 1)).toInt()
        }

        return averages
    }
}
