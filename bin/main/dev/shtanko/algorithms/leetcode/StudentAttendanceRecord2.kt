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

import dev.shtanko.algorithms.MOD

/**
 * 552. Student Attendance Record II
 * @see <a href="https://leetcode.com/problems/student-attendance-record-ii">Source</a>
 */
fun interface StudentAttendanceRecord2 {
    operator fun invoke(num: Int): Int
}

/**
 * # Approach
 *
 * - The problem can be solved using dynamic programming.
 * - Let's define a state `dp[i][j][k]` as the number of possible attendance records of length `i` with `j` absent days
 * and ending with `k` consecutive late days.
 * - The answer will be the sum of all `dp[n][j][k]` where `0 <= j <= 1` and `0 <= k <= 2`.
 * - The state transition can be defined as follows:
 * - `dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1] + dp[i - 1][j][2]`
 * - `dp[i][j][1] = dp[i - 1][j][0]`
 * - `dp[i][j][2] = dp[i - 1][j][1]`
 * - `dp[i][j + 1][0] = dp[i - 1][j][0] + dp[i - 1][j][1] + dp[i - 1][j][2]`
 * - `dp[i][j + 1][1] = dp[i - 1][j][0]`
 * - `dp[i][j + 1][2] = dp[i - 1][j][1]`
 * - The base case is `dp[0][0][0] = 1`.
 * - The final answer will be the sum of all `dp[n][j][k]` where `0 <= j <= 1` and `0 <= k <= 2`.
 *
 * # Complexity
 *
 * - The time complexity is `O(n)` where `n` is the given number of days.
 *
 * - Space complexity:
 * - The space complexity is `O(n)` where `n` is the given number of days.
 */
class StudentAttendanceRecord2TopDownMemoization : StudentAttendanceRecord2 {
    override fun invoke(num: Int): Int {
        memo = Array(num + 1) { Array(2) { IntArray(3) { -1 } } }
        return eligibleCombinations(num, 0, 0)
    }

    private lateinit var memo: Array<Array<IntArray>>

    private fun eligibleCombinations(n: Int, totalAbsences: Int, consecutiveLates: Int): Int {
        if (totalAbsences >= 2 || consecutiveLates >= 3) {
            return 0
        }
        if (n == 0) {
            return 1
        }
        if (memo[n][totalAbsences][consecutiveLates] != -1) {
            return memo[n][totalAbsences][consecutiveLates]
        }

        var count = 0
        count = (count + eligibleCombinations(n - 1, totalAbsences, 0)) % MOD
        count = (count + eligibleCombinations(n - 1, totalAbsences + 1, 0)) % MOD
        count = (count + eligibleCombinations(n - 1, totalAbsences, consecutiveLates + 1)) % MOD

        memo[n][totalAbsences][consecutiveLates] = count
        return count
    }
}

/**
 * # Intuition
 * <!-- Describe your first thoughts on how to solve this problem. -->
 * - The problem can be solved using dynamic programming.
 *
 * # Approach
 * <!-- Describe your approach to solving the problem. -->
 * - Let's define a state `dp[i][j][k]` as the number of possible attendance records of length `i`
 * with `j` absent days and ending with `k` consecutive late days.
 * - The answer will be the sum of all `dp[n][j][k]` where `0 <= j <= 1` and `0 <= k <= 2`.
 * - The state transition can be defined as follows:
 * - `dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1] + dp[i - 1][j][2]`
 * - `dp[i][j][1] = dp[i - 1][j][0]`
 * - `dp[i][j][2] = dp[i - 1][j][1]`
 * - `dp[i][j + 1][0] = dp[i - 1][j][0] + dp[i - 1][j][1] + dp[i - 1][j][2]`
 * - `dp[i][j + 1][1] = dp[i - 1][j][0]`
 * - `dp[i][j + 1][2] = dp[i - 1][j][1]`
 * - The base case is `dp[0][0][0] = 1`.
 * - The final answer will be the sum of all `dp[n][j][k]` where `0 <= j <= 1` and `0 <= k <= 2`.
 *
 * # Complexity
 *
 * - Time complexity:
 * - The time complexity is `O(n)` where `n` is the given number of days.
 *
 * - Space complexity:
 * - The space complexity is `O(n)` where `n` is the given number of days.
 */
class StudentAttendanceRecord2BottomUpDP : StudentAttendanceRecord2 {
    override fun invoke(num: Int): Int {
        val dp = Array(num + 1) { Array(2) { IntArray(3) } }
        dp[0][0][0] = 1

        for (len in 0 until num) {
            updateStates(dp, len)
        }

        return sumResults(dp, num)
    }

    private fun updateStates(dp: Array<Array<IntArray>>, len: Int) {
        for (totalAbsences in 0..1) {
            for (consecutiveLates in 0..2) {
                updateStateP(dp, len, totalAbsences, consecutiveLates)
                if (totalAbsences < 1) updateStateA(dp, len, totalAbsences, consecutiveLates)
                if (consecutiveLates < 2) updateStateL(dp, len, totalAbsences, consecutiveLates)
            }
        }
    }

    private fun updateStateP(dp: Array<Array<IntArray>>, len: Int, totalAbsences: Int, consecutiveLates: Int) {
        dp[len + 1][totalAbsences][0] = (
            dp[len + 1][totalAbsences][0] +
                dp[len][totalAbsences][consecutiveLates]
            ) % MOD
    }

    private fun updateStateA(dp: Array<Array<IntArray>>, len: Int, totalAbsences: Int, consecutiveLates: Int) {
        dp[len + 1][totalAbsences + 1][0] = (
            dp[len + 1][totalAbsences + 1][0] +
                dp[len][totalAbsences][consecutiveLates]
            ) % MOD
    }

    private fun updateStateL(dp: Array<Array<IntArray>>, len: Int, totalAbsences: Int, consecutiveLates: Int) {
        dp[len + 1][totalAbsences][consecutiveLates + 1] = (
            dp[len + 1][totalAbsences][consecutiveLates + 1] +
                dp[len][totalAbsences][consecutiveLates]
            ) % MOD
    }

    private fun sumResults(dp: Array<Array<IntArray>>, num: Int): Int {
        var count = 0
        for (totalAbsences in 0..1) {
            for (consecutiveLates in 0..2) {
                count = (count + dp[num][totalAbsences][consecutiveLates]) % MOD
            }
        }
        return count
    }
}

/**
 * # Intuition
 * <!-- Describe your first thoughts on how to solve this problem. -->
 * - The problem can be solved using dynamic programming.
 *
 * # Approach
 * <!-- Describe your approach to solving the problem. -->
 * - Let's define a state `dp[i][j][k]` as the number of possible attendance records of length `i`
 *     with `j` absent days and ending with `k` consecutive late days.
 * - The answer will be the sum of all `dp[n][j][k]` where `0 <= j <= 1` and `0 <= k <= 2`.
 * - The state transition can be defined as follows:
 * - `dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1] + dp[i - 1][j][2]`
 * - `dp[i][j][1] = dp[i - 1][j][0]`
 * - `dp[i][j][2] = dp[i - 1][j][1]`
 * - `dp[i][j + 1][0] = dp[i - 1][j][0] + dp[i - 1][j][1] + dp[i - 1][j][2]`
 * - `dp[i][j + 1][1] = dp[i - 1][j][0]`
 * - `dp[i][j + 1][2] = dp[i - 1][j][1]`
 * - The base case is `dp[0][0][0] = 1`.
 * - The final answer will be the sum of all `dp[n][j][k]` where `0 <= j <= 1` and `0 <= k <= 2`.
 * - The space complexity can be optimized by using two arrays `dpCurrState` and `dpNextState`.
 * - The time complexity remains the same.
 * - The space complexity is reduced to `O(1)` from `O(n)`.
 * - The space complexity is `O(n)` where `n` is the given number of days.
 *
 * # Complexity
 * - Time complexity:
 * <!-- Add your time complexity here, e.g. $$O(n)$$ -->
 * - The time complexity is `O(n)` where `n` is the given number of days.
 *
 * - Space complexity:
 * <!-- Add your space complexity here, e.g. $$O(n)$$ -->
 * - The space complexity is `O(n)` where `n` is the given number of days.
 */
class StudentAttendanceRecord2BottomUpSpaceOptimizedDP : StudentAttendanceRecord2 {
    override fun invoke(num: Int): Int {
        var dpCurrState = Array(2) { IntArray(3) }
        var dpNextState = Array(2) { IntArray(3) }
        dpCurrState[0][0] = 1

        for (len in 0 until num) {
            dpNextState = updateStates(dpCurrState, dpNextState)
            dpCurrState = dpNextState.also { dpNextState = Array(2) { IntArray(3) } }
        }

        return sumResults(dpCurrState)
    }

    private fun updateStates(dpCurrState: Array<IntArray>, dpNextState: Array<IntArray>): Array<IntArray> {
        for (totalAbsences in 0..1) {
            for (consecutiveLates in 0..2) {
                dpNextState[totalAbsences][0] = (
                    dpNextState[totalAbsences][0] +
                        dpCurrState[totalAbsences][consecutiveLates]
                    ) % MOD
                if (totalAbsences < 1) {
                    dpNextState[totalAbsences + 1][0] = (
                        dpNextState[totalAbsences + 1][0] +
                            dpCurrState[totalAbsences][consecutiveLates]
                        ) % MOD
                }
                if (consecutiveLates < 2) {
                    dpNextState[totalAbsences][consecutiveLates + 1] = (
                        dpNextState[totalAbsences][consecutiveLates + 1] +
                            dpCurrState[totalAbsences][consecutiveLates]
                        ) % MOD
                }
            }
        }
        return dpNextState
    }

    private fun sumResults(dpCurrState: Array<IntArray>): Int {
        var count = 0
        for (totalAbsences in 0..1) {
            for (consecutiveLates in 0..2) {
                count = (count + dpCurrState[totalAbsences][consecutiveLates]) % MOD
            }
        }
        return count
    }
}
