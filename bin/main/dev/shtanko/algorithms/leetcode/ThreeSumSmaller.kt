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

// 3Sum Smaller
fun interface ThreeSumSmallerStrategy {
    operator fun invoke(nums: IntArray, target: Int): Int
}

class ThreeSumSmallerBinarySearch : ThreeSumSmallerStrategy {
    override operator fun invoke(nums: IntArray, target: Int): Int {
        nums.sort()
        var sum = 0
        for (i in 0 until nums.size - 2) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i])
        }
        return sum
    }

    private fun twoSumSmaller(nums: IntArray, startIndex: Int, target: Int): Int {
        var sum = 0
        for (i in startIndex until nums.size - 1) {
            val j = binarySearch(nums, i, target - nums[i])
            sum += j - i
        }
        return sum
    }

    private fun binarySearch(nums: IntArray, startIndex: Int, target: Int): Int {
        var left = startIndex
        var right = nums.size - 1
        while (left < right) {
            val mid = (left + right + 1) / 2
            if (nums[mid] < target) {
                left = mid
            } else {
                right = mid - 1
            }
        }
        return left
    }
}

class ThreeSumSmallerTwoPointers : ThreeSumSmallerStrategy {
    override operator fun invoke(nums: IntArray, target: Int): Int {
        nums.sort()
        var sum = 0
        for (i in 0 until nums.size - 2) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i])
        }
        return sum
    }

    private fun twoSumSmaller(nums: IntArray, startIndex: Int, target: Int): Int {
        var sum = 0
        var left = startIndex
        var right = nums.size - 1
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left
                left++
            } else {
                right--
            }
        }
        return sum
    }
}
