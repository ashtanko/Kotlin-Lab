/*
 * Copyright 2022 Oleksii Shtanko
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

import kotlin.math.min

/**
 * 1494. Parallel Courses II
 * @see <a href="https://leetcode.com/problems/parallel-courses-ii/">Source</a>
 */
fun interface ParallelCourses2 {
    fun minNumberOfSemesters(n: Int, relations: Array<IntArray>, k: Int): Int
}

class ParallelCourses2DP : ParallelCourses2 {

    override fun minNumberOfSemesters(n: Int, relations: Array<IntArray>, k: Int): Int {
        val pres = IntArray(n)
        for (r in relations) {
            val prev = r[0] - 1
            val next = r[1] - 1
            pres[next] = pres[next] or (1 shl prev)
        }
        val dp = IntArray(1 shl n) { n }
        dp[0] = 0
        for (mask in dp.indices) {
            var canTake = 0
            for (i in 0 until n) {
                // already taken
                if (mask and (1 shl i) != 0) {
                    continue
                }
                // satisfy all pres
                if (mask and pres[i] == pres[i]) {
                    canTake = canTake or (1 shl i)
                }
            }

            // loop each sub-masks
            var take = canTake
            while (take > 0) {
                if (Integer.bitCount(take) > k) {
                    take = take - 1 and canTake
                    continue
                }
                dp[take or mask] = min(dp[take or mask], dp[mask] + 1)
                take = take - 1 and canTake
            }
        }
        return dp[(1 shl n) - 1]
    }
}
