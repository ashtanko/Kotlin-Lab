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

import dev.shtanko.algorithms.annotations.Iterative

/**
 * 1232. Check If It Is a Straight Line
 * @see <a href="https://leetcode.com/problems/check-if-it-is-a-straight-line/">Source</a>
 */
fun interface CheckStraightLine {
    operator fun invoke(coordinates: Array<IntArray>): Boolean
}

@Iterative
class CheckStraightLineSlopeProperty : CheckStraightLine {
    override operator fun invoke(coordinates: Array<IntArray>): Boolean {
        val deltaY = getYDiff(coordinates[1], coordinates[0])
        val deltaX = getXDiff(coordinates[1], coordinates[0])

        for (i in 2 until coordinates.size) {
            // Check if the slope between points 0 and i, is the same as between 0 and 1.
            if (deltaY * getXDiff(coordinates[i], coordinates[0])
                != deltaX * getYDiff(coordinates[i], coordinates[0])
            ) {
                return false
            }
        }
        return true
    }

    // Returns the delta Y.
    private fun getYDiff(a: IntArray, b: IntArray): Int {
        return a[1] - b[1]
    }

    // Returns the delta X.
    private fun getXDiff(a: IntArray, b: IntArray): Int {
        return a[0] - b[0]
    }
}
