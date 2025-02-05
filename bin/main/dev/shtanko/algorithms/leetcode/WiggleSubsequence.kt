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

fun interface WiggleSubsequence {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Brute Force
 * Time complexity : O(n!).
 * Space complexity : O(n).
 */
class WSBruteForce : WiggleSubsequence {
    override operator fun invoke(nums: IntArray): Int {
        val n = nums.size
        return if (n < 2) n else 1 + max(calculate(nums, 0, true), calculate(nums, 0, false))
    }

    private fun calculate(nums: IntArray, index: Int, isUp: Boolean): Int {
        var maxCount = 0
        for (i in index + 1 until nums.size) {
            if (isUp.and(nums[i] > nums[index]).or(isUp.not().and(nums[i] < nums[index]))) {
                maxCount = max(maxCount, 1 + calculate(nums, i, isUp.not()))
            }
        }
        return maxCount
    }
}

/**
 * Dynamic Programming
 * Time complexity : O(n^2).
 * Space complexity : O(n).
 */
class WSDP : WiggleSubsequence {
    override operator fun invoke(nums: IntArray): Int {
        val n = nums.size
        if (n < 2) return n
        val up = IntArray(n)
        val down = IntArray(n)
        for (i in 1 until n) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    up[i] = max(up[i], down[j] + 1)
                } else if (nums[i] < nums[j]) {
                    down[i] = max(down[i], up[j] + 1)
                }
            }
        }
        return 1 + max(down[n - 1], up[n - 1])
    }
}

/**
 * Linear Dynamic Programming
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
class WSLinearDP : WiggleSubsequence {
    override operator fun invoke(nums: IntArray): Int {
        val n = nums.size
        if (n < 2) return n
        val up = IntArray(n)
        val down = IntArray(n)
        up[0] = 1.also { down[0] = it }
        for (i in 1 until n) {
            when {
                nums[i] > nums[i - 1] -> {
                    up[i] = down[i - 1] + 1
                    down[i] = down[i - 1]
                }

                nums[i] < nums[i - 1] -> {
                    down[i] = up[i - 1] + 1
                    up[i] = up[i - 1]
                }

                else -> {
                    down[i] = down[i - 1]
                    up[i] = up[i - 1]
                }
            }
        }
        return max(down[n - 1], up[n - 1])
    }
}

/**
 * Space-Optimized Dynamic Programming
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class WSSpaceOptimizedDP : WiggleSubsequence {
    override operator fun invoke(nums: IntArray): Int {
        val n = nums.size
        if (n < 2) return n
        var up = 1
        var down = 1
        for (i in 1 until n) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1
            }
        }
        return max(down, up)
    }
}

/**
 * Greedy Approach
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class WSGreedy : WiggleSubsequence {
    override operator fun invoke(nums: IntArray): Int {
        val n = nums.size
        if (n < 2) return n
        var diffPrev = nums[1] - nums[0]
        var count = if (diffPrev != 0) 2 else 1
        for (i in 2 until n) {
            val diff = nums[i] - nums[i - 1]
            if ((diff > 0).and(diffPrev <= 0).or((diff < 0).and(diffPrev >= 0))) {
                count++
                diffPrev = diff
            }
        }

        return count
    }
}
