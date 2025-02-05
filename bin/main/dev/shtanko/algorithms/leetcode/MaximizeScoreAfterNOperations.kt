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

import dev.shtanko.algorithms.math.gcd
import kotlin.math.max

/**
 * 1799. Maximize Score After N Operations
 * @see <a href="https://leetcode.com/problems/maximize-score-after-n-operations/">Source</a>
 */
fun interface MaximizeScoreAfterNOperations {
    fun maxScore(nums: IntArray): Int
}

/**
 * Approach 1: DP with Bitmasking (Recursive)
 */
class MaxScoreDpRecursive : MaximizeScoreAfterNOperations {
    override fun maxScore(nums: IntArray): Int {
        val memoSize = 1 shl nums.size // 2^(nums array size)
        val memo = IntArray(memoSize) { -1 }
        return backtrack(nums, 0, 0, memo)
    }

    private fun backtrack(nums: IntArray, mask: Int, pairsPicked: Int, memo: IntArray): Int {
        // If we have picked all the numbers from 'nums' array, we can't get more score.
        if (2 * pairsPicked == nums.size) {
            return 0
        }

        // If we already solved this sub-problem then return the stored result.
        if (memo[mask] != -1) {
            return memo[mask]
        }
        var maxScore = 0

        // Iterate on 'nums' array to pick the first and second number of the pair.
        for (firstIndex in nums.indices) {
            for (secondIndex in firstIndex + 1 until nums.size) {
                // If the numbers are same, or already picked, then we move to next number.
                if (mask shr firstIndex and 1 == 1 || mask shr secondIndex and 1 == 1) {
                    continue
                }

                // Both numbers are marked as picked in this new mask.
                val newMask = mask or (1 shl firstIndex) or (1 shl secondIndex)

                // Calculate score of current pair of numbers, and the remaining array.
                val currScore: Int = (pairsPicked + 1) * gcd(nums[firstIndex], nums[secondIndex])
                val remainingScore = backtrack(nums, newMask, pairsPicked + 1, memo)

                // Store the maximum score.
                maxScore = max(maxScore, currScore + remainingScore)
                // We will use old mask in loop's next iteration,
                // means we discarded the picked number and backtracked.
            }
        }

        // Store the result of the current sub-problem.
        memo[mask] = maxScore
        return maxScore
    }
}

/**
 * Approach 2: DP with Bitmasking (Iterative)
 */
class MaxScoreDpIterative : MaximizeScoreAfterNOperations {
    override fun maxScore(nums: IntArray): Int {
        val maxStates = 1 shl nums.size // 2^(nums array size)

        val finalMask = maxStates - 1

        // 'dp[i]' stores max score we can get after picking remaining numbers represented by 'i'.
        val dp = IntArray(maxStates)

        // Iterate on all possible states one-by-one.
        for (state in finalMask downTo 0) {
            // If we have picked all numbers, we know we can't get more score as no number is remaining.
            if (state == finalMask) {
                dp[state] = 0
                continue
            }
            val numbersTaken = Integer.bitCount(state)
            val pairsFormed = numbersTaken / 2
            // States representing even numbers are taken are only valid.
            if (numbersTaken % 2 != 0) {
                continue
            }

            // We have picked 'pairsFormed' pairs, we try all combinations of one more pair now.
            // We iterate on two numbers using two nested for loops.
            for (firstIndex in nums.indices) {
                for (secondIndex in firstIndex + 1 until nums.size) {
                    // We only choose those numbers which were not already picked.
                    if (state shr firstIndex and 1 == 1 || state shr secondIndex and 1 == 1) {
                        continue
                    }
                    val currentScore = (pairsFormed + 1) * gcd(nums[firstIndex], nums[secondIndex])
                    val stateAfterPickingCurrPair = state or (1 shl firstIndex) or (1 shl secondIndex)
                    val remainingScore = dp[stateAfterPickingCurrPair]
                    dp[state] = Math.max(dp[state], currentScore + remainingScore)
                }
            }
        }

        // Returning score we get from 'n' remaining numbers of array.
        return dp[0]
    }
}
