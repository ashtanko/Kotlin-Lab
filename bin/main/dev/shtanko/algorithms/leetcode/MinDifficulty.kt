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

import java.util.Deque
import java.util.LinkedList
import kotlin.math.max
import kotlin.math.min

/**
 * 1335. Minimum Difficulty of a Job Schedule
 * @see <a href="https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule">Source</a>
 */
fun interface MinDifficulty {
    operator fun invoke(jobDifficulty: IntArray, days: Int): Int
}

class MinDifficultyTopDown : MinDifficulty {
    override fun invoke(jobDifficulty: IntArray, days: Int): Int {
        val numJobs: Int = jobDifficulty.size
        if (numJobs < days) return -1

        val memo = Array(numJobs) { IntArray(days + 1) { -1 } }

        return dfs(days, 0, jobDifficulty, memo)
    }

    private fun dfs(remainingDays: Int, currentJobIndex: Int, jobDifficulty: IntArray, memo: Array<IntArray>): Int {
        val numJobs = jobDifficulty.size
        if (remainingDays == 0 && currentJobIndex == numJobs) return 0
        if (remainingDays == 0 || currentJobIndex == numJobs) return Int.MAX_VALUE
        if (memo[currentJobIndex][remainingDays] != -1) return memo[currentJobIndex][remainingDays]

        var currentMaxDifficulty = jobDifficulty[currentJobIndex]
        var minDifficulty = Int.MAX_VALUE

        for (scheduledJob in currentJobIndex until numJobs) {
            currentMaxDifficulty = max(currentMaxDifficulty.toDouble(), jobDifficulty[scheduledJob].toDouble()).toInt()
            val temp = dfs(remainingDays - 1, scheduledJob + 1, jobDifficulty, memo)
            if (temp != Int.MAX_VALUE) {
                minDifficulty = min(minDifficulty.toDouble(), (temp + currentMaxDifficulty).toDouble()).toInt()
            }
        }

        return minDifficulty.also { memo[currentJobIndex][remainingDays] = it }
    }
}

class MinDifficultyBottomUp : MinDifficulty {
    override fun invoke(jobDifficulty: IntArray, days: Int): Int {
        if (jobDifficulty.isEmpty()) return 0
        val numJobs: Int = jobDifficulty.size
        if (numJobs < days) return -1
        val dp = Array(days) { IntArray(numJobs) }

        dp[0][0] = jobDifficulty[0]
        for (jobIndex in 1 until numJobs) {
            dp[0][jobIndex] = max(jobDifficulty[jobIndex], dp[0][jobIndex - 1])
        }

        for (currentDay in 1 until days) {
            for (jobIndex in currentDay until numJobs) {
                var localMax = jobDifficulty[jobIndex]
                dp[currentDay][jobIndex] = Int.MAX_VALUE
                for (scheduledJob in jobIndex downTo currentDay) {
                    localMax = max(localMax, jobDifficulty[scheduledJob])
                    dp[currentDay][jobIndex] =
                        min(
                            dp[currentDay][jobIndex].toDouble(),
                            (dp[currentDay - 1][scheduledJob - 1] + localMax).toDouble(),
                        ).toInt()
                }
            }
        }

        return dp[days - 1][numJobs - 1]
    }
}

class MinDifficultyBottomUp1D : MinDifficulty {
    override fun invoke(jobDifficulty: IntArray, days: Int): Int {
        val numJobs: Int = jobDifficulty.size
        var maxDifficulty: Int

        if (numJobs < days) return -1

        val dp = IntArray(numJobs + 1)

        for (jobIndex in numJobs - 1 downTo 0) {
            dp[jobIndex] = maxOf(dp[jobIndex + 1], jobDifficulty[jobIndex])
        }

        for (currentDay in 2..days) {
            for (jobIndex in 0..numJobs - currentDay) {
                maxDifficulty = 0
                dp[jobIndex] = Int.MAX_VALUE
                for (scheduledJob in jobIndex..numJobs - currentDay) {
                    maxDifficulty = maxOf(maxDifficulty, jobDifficulty[scheduledJob])
                    dp[jobIndex] = minOf(dp[jobIndex], maxDifficulty + dp[scheduledJob + 1])
                }
            }
        }

        return dp[0]
    }
}

class MinDifficultyStack : MinDifficulty {

    companion object {
        private const val INITIAL_DP_VALUE = 1000
    }

    override fun invoke(jobDifficulty: IntArray, days: Int): Int {
        if (jobDifficulty.isEmpty()) return 0
        val numJobs = jobDifficulty.size
        if (numJobs < days) return -1

        var dp = IntArray(numJobs) { INITIAL_DP_VALUE }
        val dp2 = IntArray(numJobs)
        val stack: Deque<Int> = LinkedList()

        repeat(days) {
            stack.clear()
            updateDPValues(jobDifficulty, dp, dp2, stack, it, numJobs)
            dp = dp2.copyOf() // Swap arrays by copying elements
        }

        return dp[numJobs - 1]
    }

    private fun updateDPValues(
        jobDifficulty: IntArray,
        dp: IntArray,
        dp2: IntArray,
        stack: Deque<Int>,
        startDay: Int,
        numJobs: Int,
    ) {
        for (i in startDay until numJobs) {
            dp2[i] = if (i > 0) dp[i - 1] + jobDifficulty[i] else jobDifficulty[i]

            while (stack.isNotEmpty() && jobDifficulty[stack.peek()] <= jobDifficulty[i]) {
                val j = stack.pop()
                dp2[i] = minOf(dp2[i], dp2[j] - jobDifficulty[j] + jobDifficulty[i])
            }

            if (stack.isNotEmpty()) {
                dp2[i] = minOf(dp2[i], dp2[stack.peek()])
            }

            stack.push(i)
        }
    }
}
