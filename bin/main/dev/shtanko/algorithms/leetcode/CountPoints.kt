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

import java.util.Arrays

/**
 * 1828. Queries on Number of Points Inside a Circle
 * @see <a href="https://leetcode.com/problems/queries-on-number-of-points-inside-a-circle/">Source</a>
 */
fun interface CountPoints {
    operator fun invoke(points: Array<IntArray>, queries: Array<IntArray>): IntArray
}

class CountPointsSort : CountPoints {
    override operator fun invoke(points: Array<IntArray>, queries: Array<IntArray>): IntArray {
        Arrays.sort(points) { a, b -> a[0] - b[0] }
        val n = queries.size
        val ans = IntArray(n)
        for (i in 0 until n) {
            val xCenter = queries[i][0]
            val yCenter = queries[i][1]
            val r = queries[i][2]
            val index = findLeftBoundaryIndex(points, xCenter, r)
            var count = 0
            var j = index
            while (j < points.size && points[j][0] <= xCenter + r) {
                val x = points[j][0]
                val y = points[j][1]
                // square of distance from center
                val dist: Int = sqr(xCenter - x) + sqr(yCenter - y)
                if (dist <= r * r) count++
                j++
            }
            ans[i] = count
        }
        return ans
    }

    // find lower bound of left boundary
    private fun findLeftBoundaryIndex(points: Array<IntArray>, xCenter: Int, r: Int): Int {
        var lo = 0
        var hi = points.size
        while (lo < hi) {
            val mi = lo + (hi - lo) / 2
            if (xCenter - r <= points[mi][0]) hi = mi else lo = mi + 1
        }
        return hi
    }

    private fun sqr(a: Int): Int {
        return a * a
    }
}
