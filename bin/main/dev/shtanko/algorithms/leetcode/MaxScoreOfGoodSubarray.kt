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

import java.util.Stack
import kotlin.math.max
import kotlin.math.min

/**
 * 1793. Maximum Score of a Good Subarray
 * @see <a href="https://leetcode.com/problems/maximum-score-of-a-good-subarray">Source</a>
 */
fun interface MaxScoreOfGoodSubarray {
    operator fun invoke(nums: IntArray, k: Int): Int
}

/**
 * Approach 1: Binary Search
 */
class MaxScoreBS : MaxScoreOfGoodSubarray {
    override fun invoke(nums: IntArray, k: Int): Int {
        val ans = solve(nums, k)
        for (i in 0 until nums.size / 2) {
            val temp = nums[i]
            nums[i] = nums[nums.size - i - 1]
            nums[nums.size - i - 1] = temp
        }

        return max(ans, solve(nums, nums.size - k - 1))
    }

    private fun solve(nums: IntArray, k: Int): Int {
        val n = nums.size
        val left = IntArray(k)
        var currMin = Int.MAX_VALUE
        for (i in k - 1 downTo 0) {
            currMin = min(currMin, nums[i])
            left[i] = currMin
        }
        val right: MutableList<Int> = ArrayList()
        currMin = Int.MAX_VALUE
        for (i in k until n) {
            currMin = min(currMin, nums[i])
            right.add(currMin)
        }
        var ans = 0
        for (j in right.indices) {
            currMin = right[j]
            val i = binarySearch(left, currMin)
            val size = k + j - i + 1
            ans = max(ans, currMin * size)
        }
        return ans
    }

    private fun binarySearch(nums: IntArray, num: Int): Int {
        var left = 0
        var right = nums.size
        while (left < right) {
            val mid = (left + right) / 2
            if (nums[mid] < num) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}

/**
 * Approach 2: Monotonic Stack
 */
class MaxScoreStack : MaxScoreOfGoodSubarray {
    override fun invoke(nums: IntArray, k: Int): Int {
        val n: Int = nums.size
        val left = IntArray(n) { -1 }
        var stack: Stack<Int> = Stack()

        for (i in n - 1 downTo 0) {
            while (stack.isNotEmpty() && nums[stack.peek()] > nums[i]) {
                left[stack.pop()] = i
            }
            stack.push(i)
        }

        val right = IntArray(n) { n }
        stack = Stack()

        for (i in 0 until n) {
            while (stack.isNotEmpty() && nums[stack.peek()] > nums[i]) {
                right[stack.pop()] = i
            }
            stack.push(i)
        }

        var ans = 0
        for (i in 0 until n) {
            if (left[i] < k && right[i] > k) {
                ans = max(ans, nums[i] * (right[i] - left[i] - 1))
            }
        }

        return ans
    }
}

/**
 * Approach 3: Greedy
 */
class MaxScoreGreedy : MaxScoreOfGoodSubarray {
    override fun invoke(nums: IntArray, k: Int): Int {
        val n: Int = nums.size
        var left = k
        var right = k
        var ans = nums[k]
        var currMin = nums[k]

        while (left > 0 || right < n - 1) {
            currMin = if ((if (left > 0) nums[left - 1] else 0) < (if (right < n - 1) nums[right + 1] else 0)) {
                right++
                min(currMin, nums[right])
            } else {
                left--
                min(currMin, nums[left])
            }
            ans = max(ans, currMin * (right - left + 1))
        }

        return ans
    }
}
