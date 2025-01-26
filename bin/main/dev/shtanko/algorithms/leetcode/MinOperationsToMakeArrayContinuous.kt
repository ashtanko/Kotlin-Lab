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

import kotlin.math.min

/**
 * 2009. Minimum Number of Operations to Make Array Continuous
 * @see <a href="https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous">Source</a>
 */
fun interface MinOperationsToMakeArrayContinuous {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Approach 1: Binary Search
 */
class MinOperationsToMakeArrayContinuousBS : MinOperationsToMakeArrayContinuous {
    override fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        var ans = n
        val newNums = nums.newUniqueArray()

        for (i in newNums.indices) {
            val left = newNums[i]
            val right = left + n - 1
            val j = binarySearch(newNums, right)
            val count = j - i
            ans = min(ans.toDouble(), (n - count).toDouble()).toInt()
        }

        return ans
    }

    private fun binarySearch(newNums: IntArray, target: Int): Int {
        var left = 0
        var right = newNums.size
        while (left < right) {
            val mid = (left + right) / 2
            if (target < newNums[mid]) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}

/**
 * Approach 2: Sliding Window
 */
class MinOperationsToMakeArrayContinuousSW : MinOperationsToMakeArrayContinuous {
    override fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        var ans = n
        val newNums = nums.newUniqueArray()

        var j = 0
        for (i in newNums.indices) {
            while (j < newNums.size && newNums[j] < newNums[i] + n) {
                j++
            }
            val count = j - i
            ans = min(ans.toDouble(), (n - count).toDouble()).toInt()
        }

        return ans
    }
}

private fun IntArray.newUniqueArray(): IntArray {
    val unique = HashSet<Int>()
    for (num in this) {
        unique.add(num)
    }

    val newNums = IntArray(unique.size)
    var index = 0

    for (num in unique) {
        newNums[index++] = num
    }

    newNums.sort()
    return newNums
}
