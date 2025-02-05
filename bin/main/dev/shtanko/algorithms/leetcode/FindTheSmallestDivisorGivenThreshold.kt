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

// Find the Smallest Divisor Given a Threshold
fun interface SmallestDivisorStrategy {
    operator fun invoke(nums: IntArray, threshold: Int): Int

    fun computeSum(nums: IntArray, divisor: Int): Long {
        var sum: Long = 0
        for (number in nums) {
            sum += (number / divisor + if (number % divisor == 0) 0 else 1).toLong()
        }
        return sum
    }
}

class SmallestDivisorBinarySearch : SmallestDivisorStrategy {
    override operator fun invoke(nums: IntArray, threshold: Int): Int {
        // search boundaries for the divisor
        var left = 1
        var right = 2
        while (computeSum(nums, right) > threshold) {
            left = right
            right = right shl 1
        }

        // binary search
        while (left <= right) {
            val pivot = left + (right - left shr 1)
            val num: Long = computeSum(nums, pivot)
            if (num > threshold) {
                left = pivot + 1
            } else {
                right = pivot - 1
            }
        }

        // at the end of loop, left > right,
        // computeSum(right) > threshold
        // computeSum(left) <= threshold
        // --> return left
        return left
    }
}

class SmallestDivisorMath : SmallestDivisorStrategy {
    override operator fun invoke(nums: IntArray, threshold: Int): Int {
        var left = 1

        var right = nums[nums.size - 1]
        while (left <= right) {
            val pivot = left + (right - left shr 1)
            val num = computeSum(nums, pivot).toInt()
            if (num > threshold) {
                left = pivot + 1
            } else {
                right = pivot - 1
            }
        }
        return left
    }
}
