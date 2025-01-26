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

import dev.shtanko.algorithms.annotations.DP
import dev.shtanko.algorithms.annotations.Memoization
import kotlin.math.max

/**
 * 312. Burst Balloons
 * @see <a href="https://leetcode.com/problems/burst-balloons/">Source</a>
 */
fun interface BurstBalloons {
    operator fun invoke(nums: IntArray): Int
}

@Memoization
class BurstBalloonsMemoization : BurstBalloons {
    override fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        val arr = IntArray(n + 2)
        arr[0] = 1.also { arr[n + 1] = it }
        for (i in 1..n) {
            arr[i] = nums[i - 1]
        }

        val memo = Array(n + 2) { IntArray(n + 2) }
        return burst(memo, arr, 0, n + 1)
    }

    fun burst(memo: Array<IntArray>, nums: IntArray, left: Int, right: Int): Int {
        if (left + 1 == right) return 0
        if (memo[left][right] > 0) return memo[left][right]
        var ans = 0
        for (i in left + 1 until right) {
            ans = max(
                ans,
                nums[left] * nums[i] * nums[right] + burst(memo, nums, left, i) + burst(memo, nums, i, right),
            )
        }
        memo[left][right] = ans
        return ans
    }
}

@DP
class BurstBalloonsDP : BurstBalloons {
    override fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        val arr = IntArray(n + 2)
        arr[0] = 1.also { arr[n + 1] = it } // Giving padding of 1 to the corner elements

        for (i in 1..n) {
            arr[i] = nums[i - 1] // final padded array
        }

        val dp = Array(n + 2) { IntArray(n + 2) }

        for (window in 1..n) { // window size
            for (left in 1..n - window + 1) { // left pointer
                val right = left + window - 1 // right pointer
                for (i in left..right) {
                    dp[left][right] = max(
                        dp[left][right],
                        arr[left - 1] * arr[i] * arr[right + 1] + dp[left][i - 1] + dp[i + 1][right],
                    )
                }
            }
        }
        return dp[1][n]
    }
}
