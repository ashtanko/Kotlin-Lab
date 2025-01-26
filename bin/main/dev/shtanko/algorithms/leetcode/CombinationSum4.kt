/*
 * Copyright 2021 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.BottomUpDP
import dev.shtanko.algorithms.annotations.TopDownDP

/**
 * Combination Sum IV
 * @see <a href="https://leetcode.com/problems/combination-sum-iv/">Source</a>
 */
fun interface CombinationSum4 {
    operator fun invoke(nums: IntArray, target: Int): Int
}

/**
 * Approach 1: Top-Down Dynamic Programming
 * Let T be the target value, and N be the number of elements in the input array.
 * Time Complexity: O(T*N)
 * Space Complexity: O(T)
 */
@TopDownDP
class CombinationSum4TopDown : CombinationSum4 {

    // Memoization map to store previously calculated results for specific remain values
    private val memo: MutableMap<Int, Int> = HashMap()

    // Overridden invoke function to conform to the CombinationSum4 interface
    override operator fun invoke(nums: IntArray, target: Int): Int {
        // Delegate the computation to the combs function
        return combs(nums, target)
    }

    // Recursive function to calculate the number of combinations for a given remain value
    private fun combs(nums: IntArray, remain: Int): Int {
        // Base case: if remain is 0, there is one valid combination
        if (remain == 0) return 1

        // Check if the result for the current remain value is already memoized
        if (memo.containsKey(remain)) return memo.getOrDefault(remain, -1)

        // Initialize the result
        var result = 0

        // Iterate through each number in the given array
        for (num in nums) {
            // Check if subtracting the current number is a valid option
            if (remain - num >= 0) {
                // Recursively calculate combinations for the new remain value
                result += combs(nums, remain - num)
            }
        }

        // Memoize the result for the current remain value
        memo[remain] = result

        // Return the calculated result
        return result
    }
}

/**
 * Approach 2: Bottom-Up Dynamic Programming
 * Let T be the target value, and N be the number of elements in the input array.
 * Time Complexity: O(T*N)
 * Space Complexity: O(T)
 */
@BottomUpDP
class CombinationSum4BottomUp : CombinationSum4 {
    override operator fun invoke(nums: IntArray, target: Int): Int {
        // minor optimization
        val dp = IntArray(target + 1)
        dp[0] = 1

        for (combSum in 1 until target + 1) {
            for (num in nums) {
                if (combSum - num >= 0) dp[combSum] += dp[combSum - num]
            }
        }
        return dp[target]
    }
}
