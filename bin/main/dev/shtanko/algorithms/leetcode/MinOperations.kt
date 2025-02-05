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
 * 1658. Minimum Operations to Reduce X to Zero
 * @see <a href="https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero">Source</a>
 */
fun interface MinOperations {
    operator fun invoke(nums: IntArray, target: Int): Int
}

/**
 * Approach 1: Hash Map
 */
class MinOperationsHashMap : MinOperations {
    override fun invoke(nums: IntArray, target: Int): Int {
        val left = mutableMapOf<Int, Int>()
        var res = Int.MAX_VALUE
        var sum = 0

        for (l in nums.indices) {
            left[sum] = l
            if (sum < target) {
                sum += nums[l]
            }
        }

        sum = 0
        var r = nums.size - 1

        while (r >= 0) {
            val diff = target - sum
            if (left.containsKey(diff) && r + 1 >= left[diff]!!) {
                res = minOf(res, nums.size - r - 1 + left[diff]!!)
            }
            sum += nums[r]
            r--
        }

        return if (res == Int.MAX_VALUE) -1 else res
    }
}

/**
 * Approach 2: Two Sum
 */
class MinOperationsTwoSum : MinOperations {
    override fun invoke(nums: IntArray, target: Int): Int {
        var totalSum = nums.sum()
        var leftIndex = 0
        var rightIndex = 0
        var minimumOperations = Int.MAX_VALUE
        val arraySize = nums.size

        while (leftIndex <= rightIndex) {
            if (totalSum >= target) {
                if (totalSum == target) {
                    minimumOperations = minOf(minimumOperations, leftIndex + arraySize - rightIndex)
                }
                if (rightIndex < arraySize) {
                    totalSum -= nums[rightIndex++]
                } else {
                    break
                }
            } else {
                totalSum += nums[leftIndex++]
            }
        }

        return if (minimumOperations == Int.MAX_VALUE) -1 else minimumOperations
    }
}
