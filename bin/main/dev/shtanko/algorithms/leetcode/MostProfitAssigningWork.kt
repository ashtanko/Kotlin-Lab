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

/**
 * 826. Most Profit Assigning Work
 * @see <a href="https://leetcode.com/problems/most-profit-assigning-work/">Most Profit Assigning Work</a>
 */
fun interface MostProfitAssigningWork {
    operator fun invoke(difficulty: IntArray, profit: IntArray, worker: IntArray): Int
}

class MostProfitAssigningWorkByDifficulty : MostProfitAssigningWork {
    override fun invoke(difficulty: IntArray, profit: IntArray, worker: IntArray): Int {
        val jobProfiles = difficulty.zip(profit).toMutableList()
        // Add a dummy job with difficulty 0 and profit 0
        jobProfiles.add(0, 0 to 0)

        // Sort job profiles by difficulty and then normalize the profits
        jobProfiles.sortBy { it.first }
        for (i in 1 until jobProfiles.size) {
            jobProfiles[i] = jobProfiles[i].first to maxOf(jobProfiles[i].second, jobProfiles[i - 1].second)
        }

        return worker.sumOf { ability ->
            // Binary search to find the best job a worker can do
            var l = 0
            var r = jobProfiles.size - 1
            var bestProfit = 0
            while (l <= r) {
                val mid = (l + r) / 2
                if (jobProfiles[mid].first <= ability) {
                    bestProfit = jobProfiles[mid].second
                    l = mid + 1
                } else {
                    r = mid - 1
                }
            }
            bestProfit
        }
    }
}

class MostProfitAssigningWorkByProfit : MostProfitAssigningWork {
    override fun invoke(difficulty: IntArray, profit: IntArray, worker: IntArray): Int {
        val jobProfiles = difficulty.zip(profit).toMutableList()
        jobProfiles.sortByDescending { it.second }

        return worker.sumOf { ability ->
            jobProfiles.firstOrNull { it.first <= ability }?.second ?: 0
        }
    }
}

class MostProfitAssigningWorkTwoPointers : MostProfitAssigningWork {
    override fun invoke(difficulty: IntArray, profit: IntArray, worker: IntArray): Int {
        val jobProfiles = difficulty.zip(profit).sortedBy { it.first }

        worker.sort()

        var netProfit = 0
        var maxProfit = 0
        var index = 0

        for (ability in worker) {
            // While the index has not reached the end and worker can pick a job with greater difficulty move ahead.
            while (index < jobProfiles.size && ability >= jobProfiles[index].first) {
                maxProfit = maxOf(maxProfit, jobProfiles[index].second)
                index++
            }
            netProfit += maxProfit
        }

        return netProfit
    }
}

class MostProfitAssigningWorkMemoization : MostProfitAssigningWork {
    override fun invoke(difficulty: IntArray, profit: IntArray, worker: IntArray): Int {
        val jobProfiles = difficulty.zip(profit).toMutableList()
        jobProfiles.sortBy { it.first }

        val memo = mutableMapOf<Pair<Int, Int>, Int>()

        fun dp(index: Int, ability: Int): Int {
            if (index == jobProfiles.size) return 0
            if (ability == 0) return 0

            val key = index to ability
            if (key in memo) {
                return memo.getOrDefault(key, 0)
            }

            val (jobDifficulty, jobProfit) = jobProfiles[index]
            val maxProfit = if (jobDifficulty <= ability) {
                maxOf(jobProfit + dp(index + 1, ability - jobDifficulty), dp(index + 1, ability))
            } else {
                dp(index + 1, ability)
            }

            memo[key] = maxProfit
            return maxProfit
        }

        return worker.sumOf { ability -> dp(0, ability) }
    }
}
