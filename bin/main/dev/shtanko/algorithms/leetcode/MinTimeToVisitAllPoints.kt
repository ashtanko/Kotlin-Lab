/*
 * Copyright 2020 Oleksii Shtanko
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

import kotlin.math.abs

/**
 * Minimum Time Visiting All Points
 * @see <a href="https://leetcode.com/problems/minimum-time-visiting-all-points">Source</a>
 */
fun interface MinTimeToVisitAllPoints {
    operator fun invoke(points: Array<IntArray>): Int
}

class MinTimeToVisitAllPointsMoveDiagonally : MinTimeToVisitAllPoints {
    override fun invoke(points: Array<IntArray>): Int {
        var ans = 0
        for (i in 1 until points.size) {
            val prev = points[i - 1]
            val cur = points[i]
            ans += abs(cur[0] - prev[0]).coerceAtLeast(abs(cur[1] - prev[1]))
        }
        return ans
    }
}
