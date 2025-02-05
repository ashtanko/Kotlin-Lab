/*
 * Copyright 2021 Oleksii Shtanko
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
 * 300. Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
sealed interface LongestIncreasingSubsequence {
    operator fun invoke(nums: IntArray): Int

    /**
     * Approach 1: Dynamic Programming
     */
    data object DynamicProgramming : LongestIncreasingSubsequence {
        override operator fun invoke(nums: IntArray): Int {
            val dp = IntArray(nums.size) { 1 }

            for (i in 1 until nums.size) {
                for (j in 0 until i) {
                    if (nums[i] > nums[j]) {
                        dp[i] = max(dp[i], dp[j] + 1)
                    }
                }
            }

            var longest = 0
            for (c in dp) {
                longest = max(longest, c)
            }

            return longest
        }
    }

    /**
     * Approach 2: Intelligently Build a Subsequence
     */
    data object BuildSubsequence : LongestIncreasingSubsequence {
        override operator fun invoke(nums: IntArray): Int {
            val sub = ArrayList<Int>()
            if (nums.isNotEmpty()) {
                sub.add(nums[0])
            } else {
                return 0
            }

            for (i in 1 until nums.size) {
                val num = nums[i]
                if (num > sub[sub.size - 1]) {
                    sub.add(num)
                } else {
                    // Find the first element in sub that is greater than or equal to num
                    var j = 0
                    while (num > sub[j]) {
                        j += 1
                    }
                    sub[j] = num
                }
            }

            return sub.size
        }
    }

    /**
     * Approach 3: Improve With Binary Search
     */
    data object BinarySearch : LongestIncreasingSubsequence {
        override operator fun invoke(nums: IntArray): Int {
            val sub = ArrayList<Int>()
            if (nums.isNotEmpty()) {
                sub.add(nums[0])
            } else {
                return 0
            }

            for (i in 1 until nums.size) {
                val num = nums[i]
                if (num > sub[sub.size - 1]) {
                    sub.add(num)
                } else {
                    val j = bs(sub, num)
                    sub[j] = num
                }
            }

            return sub.size
        }

        private fun bs(sub: ArrayList<Int>, num: Int): Int {
            var left = 0
            var right = sub.size - 1
            var mid: Int
            while (left < right) {
                mid = (left + right) / 2
                if (sub[mid] == num) {
                    return mid
                }
                if (sub[mid] < num) {
                    left = mid + 1
                } else {
                    right = mid
                }
            }
            return left
        }
    }
}
