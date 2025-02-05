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
 * 1802. Maximum Value at a Given Index in a Bounded Array
 * @see <a href="https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/">Source</a>
 */
fun interface MaxValueBoundedArray {
    fun maxValue(n: Int, index: Int, maxSum: Int): Int
}

/**
 * Approach: Greedy + Binary Search
 */
class MaxValueBoundedArraySolution : MaxValueBoundedArray {
    override fun maxValue(n: Int, index: Int, maxSum: Int): Int {
        var left = 1
        var right = maxSum
        while (left < right) {
            val mid = (left + right + 1) / 2
            if (getSum(index, mid, n) <= maxSum) {
                left = mid
            } else {
                right = mid - 1
            }
        }
        return left
    }

    private fun getSum(index: Int, value: Int, n: Int): Long {
        var count: Long = 0
        // On index's left:
        // If value > index, there are index + 1 numbers in the arithmetic sequence:
        // [value - index, ..., value - 1, value].
        // Otherwise, there are value numbers in the arithmetic sequence:
        // [1, 2, ..., value - 1, value], plus a sequence of length (index - value + 1) of 1s.
        count += if (value > index) {
            (value + value - index).toLong() * (index + 1) / 2
        } else {
            (value + 1).toLong() * value / 2 + index - value + 1
        }
        // On index's right:
        // If value >= n - index, there are n - index numbers in the arithmetic sequence:
        // [value, value - 1, ..., value - n + 1 + index].
        // Otherwise, there are value numbers in the arithmetic sequence:
        // [value, value - 1, ..., 1], plus a sequence of length (n - index - value) of 1s.
        count += if (value >= n - index) {
            (value + value - n + 1 + index).toLong() * (n - index) / 2
        } else {
            (value + 1).toLong() * value / 2 + n - index - value
        }
        return count - value
    }
}
