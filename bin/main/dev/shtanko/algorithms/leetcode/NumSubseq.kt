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

import dev.shtanko.algorithms.MOD
import java.util.Arrays

/**
 * 1498. Number of Subsequences That Satisfy the Given Sum Condition
 * @see <a href="https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition">
 *     Source</a>
 */
fun interface NumSubseq {
    operator fun invoke(nums: IntArray, target: Int): Int
}

class NumSubseqBinarySearch : NumSubseq {
    override operator fun invoke(nums: IntArray, target: Int): Int {
        val n: Int = nums.size
        Arrays.sort(nums)

        val power = IntArray(n)
        power[0] = 1
        for (i in 1 until n) {
            power[i] = power[i - 1] * 2 % MOD
        }

        var answer = 0

        // Iterate over each left pointer.
        for (left in 0 until n) {
            // Find the insertion position for `target - nums[left]`
            // 'right' equals the insertion index minus 1.
            val right = binarySearchRightmost(nums, target - nums[left]) - 1
            if (right >= left) {
                answer += power[right - left]
                answer %= MOD
            }
        }

        return answer
    }

    private fun binarySearchRightmost(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] <= target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }
}

class NumSubseqTwoPointers : NumSubseq {
    override operator fun invoke(nums: IntArray, target: Int): Int {
        val n: Int = nums.size
        nums.sort()

        // Precompute the value of 2 to the power of each value.
        val power = IntArray(n)
        power[0] = 1
        for (i in 1 until n) {
            power[i] = power[i - 1] * 2 % MOD
        }
        var answer = 0
        var left = 0
        var right = n - 1

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                answer += power[right - left]
                answer %= MOD
                left++
            } else {
                right--
            }
        }

        return answer
    }
}
