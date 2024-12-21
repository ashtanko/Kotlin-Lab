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

import dev.shtanko.algorithms.MOD
import kotlin.math.abs

/**
 * 1584. Min Cost to Connect All Points
 * @see <a href="https://leetcode.com/problems/min-cost-to-connect-all-points">Source page</a>
 */
fun interface MinCostConnectPoints {
    operator fun invoke(points: Array<IntArray>): Int
}

class MinCostConnectPointsPrims : MinCostConnectPoints {
    override fun invoke(points: Array<IntArray>): Int {
        val n = points.size
        var res = 0
        var i = 0
        var connected = 0
        val minD = IntArray(n) { MOD }

        while (++connected < n) {
            minD[i] = Int.MAX_VALUE
            var minJ = i

            for (j in 0 until n) {
                if (minD[j] != Int.MAX_VALUE) {
                    minD[j] = minOf(minD[j], abs(points[i][0] - points[j][0]) + abs(points[i][1] - points[j][1]))
                    minJ = if (minD[j] < minD[minJ]) j else minJ
                }
            }

            res += minD[minJ]
            i = minJ
        }

        return res
    }
}
