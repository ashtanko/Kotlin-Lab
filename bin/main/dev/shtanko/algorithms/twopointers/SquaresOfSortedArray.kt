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

package dev.shtanko.algorithms.twopointers

/**
 * Squares of a Sorted Array:
 * Given an array of integers sorted in non-decreasing order, return an array of the squares of each number,
 * also sorted in non-decreasing order.
 */
fun sortedSquares(nums: IntArray): IntArray {
    val n = nums.size
    val result = IntArray(n)
    var left = 0
    var right = n - 1
    var index = n - 1

    while (left <= right) {
        val leftSquare = nums[left] * nums[left]
        val rightSquare = nums[right] * nums[right]

        if (leftSquare > rightSquare) {
            result[index] = leftSquare
            left++
        } else {
            result[index] = rightSquare
            right--
        }
        index--
    }

    return result
}
