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

import kotlin.math.max

/**
 * 1637. Widest Vertical Area Between Two Points Containing No Points
 * @see <a href="https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points">Source</a>
 */
fun interface MaxWidthOfVerticalArea {
    operator fun invoke(points: Array<IntArray>): Int
}

class MaxWidthOfVerticalAreaSort : MaxWidthOfVerticalArea {
    override fun invoke(points: Array<IntArray>): Int {
        points.sortWith(compareBy { it[0] })

        var ans = 0
        for (i in 1 until points.size) {
            ans = max(ans, points[i][0] - points[i - 1][0])
        }

        return ans
    }
}
