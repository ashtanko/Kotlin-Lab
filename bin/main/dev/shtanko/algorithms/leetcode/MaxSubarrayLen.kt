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

import kotlin.math.max

/**
 * 2958. Length of Longest Subarray With at Most K Frequency
 * @see <a href="https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency">Source</a>
 */
fun interface MaxSubarrayLen {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class MaxSubarrayLenSlidingWindow : MaxSubarrayLen {
    override fun invoke(nums: IntArray, k: Int): Int {
        var ans = 0
        var start = -1
        val frequency: MutableMap<Int, Int> = HashMap()

        for (end in nums.indices) {
            frequency[nums[end]] = frequency.getOrDefault(nums[end], 0) + 1
            while (frequency.getOrDefault(nums[end], 0) > k) {
                start++
                frequency[nums[start]] = frequency.getOrDefault(nums[start], 0) - 1
            }
            ans = max(ans.toDouble(), (end - start).toDouble()).toInt()
        }

        return ans
    }
}

class MaxSubarrayLenSlidingWindowOpt : MaxSubarrayLen {
    override fun invoke(nums: IntArray, k: Int): Int {
        val n: Int = nums.size
        val frequency: MutableMap<Int, Int> = HashMap()
        var start = 0
        var charsWithFreqOverK = 0

        for (end in 0 until n) {
            frequency[nums[end]] = frequency.getOrDefault(nums[end], 0) + 1
            if (frequency[nums[end]] == k + 1) {
                charsWithFreqOverK++
            }
            if (charsWithFreqOverK > 0) {
                frequency[nums[start]] = frequency.getOrDefault(nums[start], 0) - 1
                if (frequency[nums[start]] == k) {
                    charsWithFreqOverK--
                }
                start++
            }
        }
        return n - start
    }
}
