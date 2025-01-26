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

import kotlin.math.abs

/**
 * 977. Squares of a Sorted Array
 * @see <a href="https://leetcode.com/problems/squares-of-a-sorted-array">Source</a>
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number,
 * also in sorted non-decreasing order.
 */
fun interface SortedSquares {
    operator fun invoke(nums: IntArray): IntArray
}

class SortedSquaresTwoPointers : SortedSquares {
    override operator fun invoke(nums: IntArray): IntArray {
        val n = nums.size
        val result = IntArray(n)
        var i = 0
        var j = n - 1

        for (p in n - 1 downTo 0) {
            if (abs(nums[i]) > abs(nums[j])) {
                result[p] = nums[i] * nums[i]
                i++
            } else {
                result[p] = nums[j] * nums[j]
                j--
            }
        }

        return result
    }
}
