/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
 * 2554. Maximum Number of Integers to Choose From a Range I
 * @see <a href="https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i/">Source</a>
 */
@Medium("https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i/")
sealed interface MaxCount {
    operator fun invoke(banned: IntArray, n: Int, maxSum: Int): Int

    @dev.shtanko.algorithms.annotations.BinarySearch
    data object BinarySearch : MaxCount {
        override fun invoke(banned: IntArray, n: Int, maxSum: Int): Int {
            // Sort banned array to enable binary search
            banned.sort()

            var count = 0
            var remainingSum = maxSum

            // Try each number from 1 to n
            for (num in 1..n) {
                // Skip if number is in banned array
                if (customBinarySearch(banned, num)) continue

                remainingSum -= num

                // Break if sum exceeds our limit
                if (remainingSum < 0) break

                count++
            }
            return count
        }

        // Helper method to check if value exists in sorted array
        private fun customBinarySearch(arr: IntArray, target: Int): Boolean {
            var left = 0
            var right = arr.size - 1

            while (left <= right) {
                val mid = left + (right - left) / 2

                when {
                    arr[mid] == target -> return true
                    arr[mid] > target -> right = mid - 1
                    else -> left = mid + 1
                }
            }
            return false
        }
    }

    data object Sweep : MaxCount {
        override fun invoke(banned: IntArray, n: Int, maxSum: Int): Int {
            // Sort the banned array
            banned.sort()

            var bannedIdx = 0
            var count = 0
            var remainingSum = maxSum

            // Check each number from 1 to n while sum is valid
            for (num in 1..n) {
                if (remainingSum < 0) break

                // Skip if current number is in banned array
                if (bannedIdx < banned.size && banned[bannedIdx] == num) {
                    // Handle duplicate banned numbers
                    while (bannedIdx < banned.size && banned[bannedIdx] == num) {
                        bannedIdx++
                    }
                } else {
                    // Include current number if possible
                    remainingSum -= num
                    if (remainingSum >= 0) {
                        count++
                    }
                }
            }
            return count
        }
    }

    data object HashSet : MaxCount {
        override fun invoke(banned: IntArray, n: Int, maxSum: Int): Int {
            // Store banned numbers in a set
            val bannedSet = banned.toSet()

            var count = 0
            var remainingSum = maxSum

            // Try each number from 1 to n
            for (num in 1..n) {
                // Skip banned numbers
                if (num in bannedSet) continue

                // Return if adding current number exceeds maxSum
                if (remainingSum - num < 0) return count

                // Include current number
                remainingSum -= num
                count++
            }
            return count
        }
    }
}
