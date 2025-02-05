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

import java.util.TreeMap

/**
 * 1235. Maximum Profit in Job Scheduling
 * @see <a href="<link>">Source</a>
 */
interface JobScheduling {
    operator fun invoke(startTime: IntArray, endTime: IntArray, profit: IntArray): Int
}

class JobSchedulingDp : JobScheduling {
    override fun invoke(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val n: Int = startTime.size
        val jobs: Array<IntArray> = Array(n) { IntArray(3) }
        for (i in 0 until n) {
            jobs[i] = intArrayOf(startTime[i], endTime[i], profit[i])
        }
        jobs.sortWith { a: IntArray, b: IntArray ->
            a[1] - b[1]
        }
        val dp: TreeMap<Int, Int> = TreeMap()
        dp[0] = 0
        for (job: IntArray in jobs) {
            val cur: Int = dp.floorEntry(job[0]).value + job[2]
            if (cur > dp.lastEntry().value) dp[job[1]] = cur
        }
        return dp.lastEntry().value
    }
}
