/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 3011. Find if Array Can Be Sorted
 * @see <a href="https://leetcode.com/problems/find-if-array-can-be-sorted/">Source</a>
 */
@Medium("https://leetcode.com/problems/find-if-array-can-be-sorted")
fun interface CanSortArray {
    operator fun invoke(nums: IntArray): Boolean
}

class CanSortArrayBackwardPass : CanSortArray {
    override fun invoke(nums: IntArray): Boolean {
        val n = nums.size

        // Copy the original array to values
        val values = nums.copyOf()

        // First Pass: Iterate from left to right
        // Goal: Move the maximum value of each segment as far right as possible
        for (i in 0 until n - 1) {
            if (values[i] <= values[i + 1]) {
                continue
            } else {
                // Count the number of set bits using Integer.bitCount
                if (values[i].countOneBits() == values[i + 1].countOneBits()) {
                    // Swap them if they have the same number of set bits
                    val temp = values[i]
                    values[i] = values[i + 1]
                    values[i + 1] = temp
                } else {
                    return false // Return false if they cannot be swapped
                }
            }
        }

        // Second Pass: Iterate from right to left
        // Goal: Move the minimum value of each segment as far left as possible
        for (i in n - 1 downTo 1) {
            if (values[i] >= values[i - 1]) {
                continue
            } else {
                // Count the number of set bits using Integer.bitCount
                if (values[i].countOneBits() == values[i - 1].countOneBits()) {
                    // Swap them if they have the same number of set bits
                    val temp = values[i]
                    values[i] = values[i - 1]
                    values[i - 1] = temp
                } else {
                    return false // Return false if they cannot be swapped
                }
            }
        }

        // If both passes complete without returning false, the array can be sorted
        return true
    }
}
