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

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 * @see <a href="https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/">Source</a>
 */
fun interface FindMinArrowShots {
    operator fun invoke(points: Array<IntArray>): Int
}

val findMinArrowShotsGreedy = FindMinArrowShots { points ->
    if (points.isEmpty()) {
        return@FindMinArrowShots 0
    }
    points.sortWith { a, b ->
        a[1].compareTo(b[1])
    }

    var ans = 0
    var arrow = 0
    for (i in points.indices) {
        if (ans == 0 || points[i][0] > arrow) {
            ans++
            arrow = points[i][1]
        }
    }
    return@FindMinArrowShots ans
}

val findMinArrowShots = FindMinArrowShots { points ->
    points.sortBy { it[1] }
    var last = 0
    return@FindMinArrowShots 1 + (1..points.lastIndex).count { e ->
        (points[e][0] > points[last][1])
            .also { if (it) last = e }
    }
}
