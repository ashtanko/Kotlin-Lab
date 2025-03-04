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

import dev.shtanko.algorithms.annotations.Bitwise
import dev.shtanko.algorithms.annotations.DP
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2044. Count Number of Maximum Bitwise-OR Subsets
 * @see <a href="https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/">Source</a>
 */
@Medium("https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets")
fun interface CountMaxOrSubsets {
    operator fun invoke(nums: IntArray): Int
}

@Bitwise
@DP
class CountMaxOrSubsetsDPBitwise : CountMaxOrSubsets {
    override fun invoke(nums: IntArray): Int {
        var max = 0
        val dp = IntArray(1 shl MAX_BITS)

        // Initialize the empty subset
        dp[0] = 1

        // Iterate through each number in the input array
        for (num in nums) {
            for (i in max downTo 0) {
                // For each existing subset, create a new subset by including the current number
                dp[i or num] += dp[i]
            }
            // Update the maximum OR value
            max = max or num
        }

        return dp[max]
    }

    companion object {
        private const val MAX_BITS = 17
    }
}
