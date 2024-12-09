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
 * 3152. Special Array II
 * @see <a href="https://leetcode.com/problems/special-array-ii/">Source</a>
 */
@Medium("https://leetcode.com/problems/special-array-ii/")
sealed interface SpecialArray2 {
    operator fun invoke(nums: IntArray, queries: Array<IntArray>): BooleanArray

    @dev.shtanko.algorithms.annotations.BinarySearch
    data object BinarySearch : SpecialArray2 {
        override fun invoke(nums: IntArray, queries: Array<IntArray>): BooleanArray {
            val ans = BooleanArray(queries.size)
            val violatingIndices = mutableListOf<Int>()

            for (i in 1 until nums.size) {
                // Same parity, found violating index
                if (nums[i] % 2 == nums[i - 1] % 2) {
                    violatingIndices.add(i)
                }
            }

            for (i in queries.indices) {
                val (start, end) = queries[i]
                val foundViolatingIndex = binarySearchHelper(start + 1, end, violatingIndices)

                ans[i] = !foundViolatingIndex
            }

            return ans
        }

        private fun binarySearchHelper(start: Int, end: Int, violatingIndices: List<Int>): Boolean {
            var left = 0
            var right = violatingIndices.size - 1
            while (left <= right) {
                val mid = left + (right - left) / 2
                val violatingIndex = violatingIndices[mid]

                when {
                    violatingIndex < start -> left = mid + 1 // Check right half
                    violatingIndex > end -> right = mid - 1 // Check left half
                    else -> return true // Violating index falls within range
                }
            }

            return false
        }
    }

    @dev.shtanko.algorithms.annotations.PrefixSum
    data object PrefixSum : SpecialArray2 {
        override fun invoke(nums: IntArray, queries: Array<IntArray>): BooleanArray {
            val ans = BooleanArray(queries.size)
            val prefix = IntArray(nums.size)
            prefix[0] = 0

            for (i in 1 until nums.size) {
                if (nums[i] % 2 == nums[i - 1] % 2) {
                    // New violative index found
                    prefix[i] = prefix[i - 1] + 1
                } else {
                    prefix[i] = prefix[i - 1]
                }
            }

            for (i in queries.indices) {
                val query = queries[i]
                val start = query[0]
                val end = query[1]

                ans[i] = prefix[end] - prefix[start] == 0
            }

            return ans
        }
    }

    @dev.shtanko.algorithms.annotations.SlidingWindow
    data object SlidingWindow : SpecialArray2 {
        override fun invoke(nums: IntArray, queries: Array<IntArray>): BooleanArray {
            val n = nums.size
            val maxReach = IntArray(n)
            var end = 0

            // Step 1: Compute the maximum reachable index for each starting index
            for (start in 0 until n) {
                // Ensure 'end' always starts from the current index or beyond
                end = maxOf(end, start)

                // Expand 'end' as long as adjacent elements have different parity
                while (end < n - 1 && nums[end] % 2 != nums[end + 1] % 2) {
                    end++
                }

                // Store the farthest index reachable from 'start'
                maxReach[start] = end
            }

            val ans = BooleanArray(queries.size)

            // Step 2: Answer each query based on precomputed 'maxReach'
            for (i in queries.indices) {
                val start = queries[i][0]
                val endQuery = queries[i][1]

                // Check if the query range [start, end] lies within the max reachable range
                ans[i] = endQuery <= maxReach[start]
            }

            return ans
        }
    }
}
