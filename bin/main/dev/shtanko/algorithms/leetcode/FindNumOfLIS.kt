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
 * 673. Number of Longest Increasing Subsequence
 * @see <a href="https://leetcode.com/problems/number-of-longest-increasing-subsequence/">Source</a>
 */
fun interface FindNumOfLIS {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Approach 1: Bottom-up Dynamic Programming
 */
class FindNumOfLISBottomUp : FindNumOfLIS {
    override operator fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        val length = IntArray(n) { 1 }
        val count = IntArray(n) { 1 }

        for (i in 0 until n) {
            for (j in 0 until i) {
                if (nums[j] < nums[i]) {
                    if (length[j] + 1 > length[i]) {
                        length[i] = length[j] + 1
                        count[i] = 0
                    }
                    if (length[j] + 1 == length[i]) {
                        count[i] += count[j]
                    }
                }
            }
        }

        var maxLength = 0
        var result = 0

        for (len in length) {
            maxLength = max(maxLength, len)
        }

        for (i in 0 until n) {
            if (length[i] == maxLength) {
                result += count[i]
            }
        }

        return result
    }
}

/**
 * Approach 2: Top-down Dynamic Programming (Memoization)
 */
class FindNumOfLISTopDown : FindNumOfLIS {
    override operator fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        val length = IntArray(n)
        val count = IntArray(n)
        var maxLength = 0
        var result = 0
        for (i in 0 until n) {
            calculateDP(i, nums, length, count)
            maxLength = max(maxLength, length[i])
        }

        for (i in 0 until n) {
            if (length[i] == maxLength) {
                result += count[i]
            }
        }

        return result
    }

    private fun calculateDP(i: Int, nums: IntArray, length: IntArray, count: IntArray) {
        if (length[i] != 0) {
            return
        }
        length[i] = 1
        count[i] = 1
        for (j in 0 until i) {
            if (nums[j] < nums[i]) {
                calculateDP(j, nums, length, count)
                if (length[j] + 1 > length[i]) {
                    length[i] = length[j] + 1
                    count[i] = 0
                }
                if (length[j] + 1 == length[i]) {
                    count[i] += count[j]
                }
            }
        }
    }
}
