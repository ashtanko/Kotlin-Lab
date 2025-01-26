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

package dev.shtanko.algorithms.codingbat.recursion2

import java.util.Stack

/**
 * Recursion-2 > groupSumClump
 * @see <a href="https://codingbat.com/prob/p105136">Source</a>
 */
fun interface GroupSumClump {
    operator fun invoke(start: Int, nums: IntArray, target: Int): Boolean
}

class GroupSumClumpIterative : GroupSumClump {
    override fun invoke(start: Int, nums: IntArray, target: Int): Boolean {
        val n = nums.size
        val dp = Array(n + 1) { BooleanArray(target + 1) }

        // Base case: if target is 0, we can achieve it by not selecting any elements
        for (i in 0..n) {
            dp[i][0] = true
        }

        var i = 1
        while (i <= n) {
            // Find the end index of the current clump
            var end = i
            while (end < n && nums[end] == nums[i - 1]) {
                end++
            }

            // Calculate the sum of the current clump
            val clumpSum = nums[i - 1] * (end - i + 1)

            for (j in 0..target) {
                // Exclude the current clump
                dp[end][j] = dp[i - 1][j]

                // Include the current clump if it doesn't exceed the target
                if (j >= clumpSum) {
                    dp[end][j] = dp[end][j] || dp[i - 1][j - clumpSum]
                }
            }
            // Move to the end of the clump
            i = end + 1
        }

        return dp[n][target]
    }
}

class GroupSumClumpRecursion : GroupSumClump {
    override fun invoke(start: Int, nums: IntArray, target: Int): Boolean {
        if (start >= nums.size) {
            return target == 0
        }
        var end = start + 1
        while (end < nums.size && nums[end] == nums[start]) {
            end++
        }
        val clumpSum = nums[start].times(end.minus(start))
        return invoke(end, nums, target - clumpSum) || invoke(end, nums, target)
    }
}

class GroupSumClumpStack : GroupSumClump {
    override fun invoke(start: Int, nums: IntArray, target: Int): Boolean {
        val n = nums.size
        val stack = Stack<Pair<Int, Int>>() // Pair to store index and cumulative sum

        stack.push(0 to 0) // Initial state: index = 0, cumulative sum = 0

        while (stack.isNotEmpty()) {
            val (index, cumSum) = stack.pop()

            if (index == n) {
                if (cumSum == target) {
                    return true
                }
                continue
            }

            // Find the end index of the current clump
            var end = index + 1
            while (end < n && nums[end] == nums[index]) {
                end++
            }

            // Explore two possibilities: include or exclude the current clump
            stack.push(end to cumSum + (end - index) * nums[index]) // Include the current clump
            stack.push(end to cumSum) // Exclude the current clump
        }

        return false
    }
}
