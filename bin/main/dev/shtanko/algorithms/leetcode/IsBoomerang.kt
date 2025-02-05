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

/**
 * Determines if given three points form a valid boomerang.
 *
 * A boomerang is a set of three points that are not in a straight line.
 *
 * @param points Array of integer arrays representing the coordinates of three points.
 * @return True if the points form a valid boomerang, false otherwise.
 */
fun isBoomerang(points: Array<IntArray>): Boolean {
    require(points.size == 3) { "The input must contain exactly three points." }

    val f = (points[0][0] - points[1][0]) * (points[0][1] - points[2][1])
    val s = (points[0][0] - points[2][0]) * (points[0][1] - points[1][1])

    return f != s
}
