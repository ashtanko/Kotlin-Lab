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

import kotlin.math.max
import kotlin.math.min

/**
 * 223. Rectangle Area
 * @see <a href="https://leetcode.com/problems/rectangle-area/">Source</a>
 */
fun interface RectangleArea {
    // TODO wtf?
    fun computeArea(ax1: Int, ay1: Int, ax2: Int, ay2: Int, bx1: Int, by1: Int, bx2: Int, by2: Int): Int
}

class MathAndGeometry : RectangleArea {
    override fun computeArea(ax1: Int, ay1: Int, ax2: Int, ay2: Int, bx1: Int, by1: Int, bx2: Int, by2: Int): Int {
        val areaOfA = (ay2 - ay1) * (ax2 - ax1)
        val areaOfB = (by2 - by1) * (bx2 - bx1)

        // calculate x overlap
        val left = max(ax1, bx1)
        val right = min(ax2, bx2)
        val xOverlap = right - left

        // calculate y overlap
        val top = min(ay2, by2)
        val bottom = max(ay1, by1)
        val yOverlap = top - bottom

        var areaOfOverlap = 0
        // if the rectangles overlap each other, then calculate
        // the area of the overlap
        if (xOverlap > 0 && yOverlap > 0) {
            areaOfOverlap = xOverlap * yOverlap
        }

        // areaOfOverlap is counted twice when in the summation of
        // areaOfA and areaOfB, so we need to subtract it from the
        // total, to get the toal area covered by both the rectangles
        return areaOfA + areaOfB - areaOfOverlap
    }
}
