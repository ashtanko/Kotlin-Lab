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
 * 1671. Minimum Number of Removals to Make Mountain Array
 * @see <a href="https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/">Source</a>
 */
@Medium("https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array")
fun interface MinMountainRemovals {
    operator fun invoke(nums: IntArray): Int
}

class MinMountainRemovalsDP : MinMountainRemovals {
    override fun invoke(nums: IntArray): Int {
        val n = nums.size

        val lisLengths = IntArray(n) { 1 }
        val ldsLengths = IntArray(n) { 1 }

        // Calculate lengths of longest increasing subsequences ending at each index
        for (i in nums.indices) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    lisLengths[i] = maxOf(lisLengths[i], lisLengths[j] + 1)
                }
            }
        }

        // Calculate lengths of longest decreasing subsequences starting at each index
        for (i in nums.lastIndex downTo 0) {
            for (j in i + 1 until n) {
                if (nums[i] > nums[j]) {
                    ldsLengths[i] = maxOf(ldsLengths[i], ldsLengths[j] + 1)
                }
            }
        }

        var minRemovals = Int.MAX_VALUE
        for (i in nums.indices) {
            if (lisLengths[i] > 1 && ldsLengths[i] > 1) {
                minRemovals = minOf(minRemovals, n - lisLengths[i] - ldsLengths[i] + 1)
            }
        }

        return minRemovals
    }
}

class MinMountainRemovalsBS : MinMountainRemovals {
    override fun invoke(nums: IntArray): Int {
        val sequence = nums.toList()
        val lisLengths = getLongestIncreasingSubsequenceLengths(sequence)

        val reversedSequence = sequence.reversed()
        val ldsLengths = getLongestIncreasingSubsequenceLengths(reversedSequence).reversed()

        var minRemovals = Int.MAX_VALUE
        for (i in nums.indices) {
            if (lisLengths[i] > 1 && ldsLengths[i] > 1) {
                minRemovals = minOf(minRemovals, nums.size - lisLengths[i] - ldsLengths[i] + 1)
            }
        }

        return minRemovals
    }

    private fun getLongestIncreasingSubsequenceLengths(sequence: List<Int>): List<Int> {
        val lisLengths = MutableList(sequence.size) { 1 }
        val lis = mutableListOf(sequence[0])

        for (i in 1 until sequence.size) {
            val index = lis.binarySearchInsertionIndex(sequence[i])

            if (index == lis.size) {
                lis.add(sequence[i])
            } else {
                lis[index] = sequence[i]
            }

            lisLengths[i] = lis.size
        }

        return lisLengths
    }

    // Returns the index where the target should be inserted to keep the list sorted.
    private fun List<Int>.binarySearchInsertionIndex(target: Int): Int {
        var left = 0
        var right = this.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (this[mid] >= target) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }
}
