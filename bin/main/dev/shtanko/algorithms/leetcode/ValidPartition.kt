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
 * 2369. Check if There is a Valid Partition For The Array
 * @see <a href="https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/">Source</a>
 */
fun interface ValidPartition {
    operator fun invoke(nums: IntArray): Boolean
}

/**
 * Approach 1: Top-Down Dynamic Programming
 */
class ValidPartitionTopDown : ValidPartition {
    // Memoization map to store the results of subproblems
    private val memo: MutableMap<Int, Boolean> = HashMap()

    // Overriding the invoke operator to implement the top-down approach
    override operator fun invoke(nums: IntArray): Boolean {
        val n = nums.size
        // Base case: An empty array is considered a valid partition
        memo[-1] = true
        return prefixIsValid(nums, n - 1)
    }

    // Determine if the prefix array nums[0 ~ i] has a valid partition
    private fun prefixIsValid(nums: IntArray, i: Int): Boolean {
        // Check if the result for the current index is already memoized
        if (memo.containsKey(i)) {
            return memo[i]!!
        }

        var ans = false

        // Check the three possibilities for a valid partition
        if (i > 0 && nums[i] == nums[i - 1]) {
            // Case 1: Two consecutive equal elements
            ans = ans or prefixIsValid(nums, i - 2)
        }
        if (i > 1 && nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2]) {
            // Case 2: Three consecutive equal elements
            ans = ans or prefixIsValid(nums, i - 3)
        }
        if (i > 1 && nums[i] == nums[i - 1] + 1 && nums[i - 1] == nums[i - 2] + 1) {
            // Case 3: Three consecutive elements in increasing order
            ans = ans or prefixIsValid(nums, i - 3)
        }

        // Memoize the result to avoid redundant computations
        memo[i] = ans
        return ans
    }
}

/**
 * Approach 2: Bottom-Up Dynamic Programming
 */
class ValidPartitionBottomUp : ValidPartition {
    override operator fun invoke(nums: IntArray): Boolean {
        val n: Int = nums.size
        val dp = BooleanArray(n + 1)
        dp[0] = true

        // Determine if the prefix array nums[0 ~ i] has a valid partition
        for (i in 0 until n) {
            val dpIndex = i + 1

            // Check 3 possibilities
            if (i > 0 && nums[i] == nums[i - 1]) {
                dp[dpIndex] = dp[dpIndex] or dp[dpIndex - 2]
            }
            if (i > 1 && nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) {
                dp[dpIndex] = dp[dpIndex] or dp[dpIndex - 3]
            }
            if (i > 1 && nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2) {
                dp[dpIndex] = dp[dpIndex] or dp[dpIndex - 3]
            }
        }

        return dp[n]
    }
}

/**
 * Approach 3: Space Optimized Bottom-Up Dynamic Programming
 */
class ValidPartitionBottomUpSpaceOpt : ValidPartition {
    override operator fun invoke(nums: IntArray): Boolean {
        val n: Int = nums.size
        val dp = BooleanArray(3)
        dp[0] = true

        // Determine if prefix array nums[0 ~ i] has a valid partition
        for (i in 0 until n) {
            val dpIndex = i + 1
            var ans = false

            // Check 3 possibilities
            if (i > 0 && nums[i] == nums[i - 1]) {
                ans = ans or dp[(dpIndex - 2) % 3]
            }
            if (i > 1 && nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) {
                ans = ans or dp[(dpIndex - 3) % 3]
            }
            if (i > 1 && nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2) {
                ans = ans or dp[(dpIndex - 3) % 3]
            }
            dp[dpIndex % 3] = ans
        }

        return dp[n % 3]
    }
}
