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
 * 1760. Minimum Limit of Balls in a Bag
 * @see <a href="https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/">Source</a>
 */
@Medium("https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/")
sealed interface MinLimitOfBallsInBag {
    operator fun invoke(nums: IntArray, maxOperations: Int): Int

    @dev.shtanko.algorithms.annotations.BinarySearch
    data object BinarySearch : MinLimitOfBallsInBag {
        override fun invoke(nums: IntArray, maxOperations: Int): Int {
            // Binary search bounds
            var left = 1
            var right = nums.maxOrNull() ?: 0

            // Perform binary search to find the optimal maxBallsInBag
            while (left < right) {
                val middle = (left + right) / 2

                // Check if a valid distribution is possible with the current middle value
                if (isPossible(middle, nums, maxOperations)) {
                    right = middle // If possible, try a smaller value (shift right to middle)
                } else {
                    left = middle + 1 // If not possible, try a larger value (shift left to middle + 1)
                }
            }

            // Return the smallest possible value for maxBallsInBag
            return left
        }

        // Helper function to check if a distribution is possible for a given maxBallsInBag
        private fun isPossible(maxBallsInBag: Int, nums: IntArray, maxOperations: Int): Boolean {
            var totalOperations = 0

            // Iterate through each bag in the array
            for (num in nums) {
                // Calculate the number of operations needed to split this bag
                val operations = (num + maxBallsInBag - 1) / maxBallsInBag - 1
                totalOperations += operations

                // If total operations exceed maxOperations, return false
                if (totalOperations > maxOperations) {
                    return false
                }
            }

            // We can split the balls within the allowed operations, return true
            return true
        }
    }
}
