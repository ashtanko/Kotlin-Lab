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

import kotlin.math.abs

/**
 * 624. Maximum Distance in Arrays
 * @see <a href="https://leetcode.com/problems/maximum-distance-in-arrays/">Maximum Distance in Arrays</a>
 */
fun interface MaxDistanceInArrays {
    operator fun invoke(arrays: List<List<Int>>): Int
}

class MaxDistanceInArraysGreedy : MaxDistanceInArrays {
    override fun invoke(arrays: List<List<Int>>): Int {
        var minElement = arrays[0].first()
        var maxElement = arrays[0].last()

        var maximumDifference = 0

        for (i in 1 until arrays.size) {
            val currentMinElement = arrays[i].first()
            val currentMaxElement = arrays[i].last()

            maximumDifference = maxOf(
                maximumDifference,
                abs(maxElement - currentMinElement),
                abs(minElement - currentMaxElement),
            )

            if (currentMinElement <= minElement) {
                minElement = currentMinElement
            }

            if (currentMaxElement >= maxElement) {
                maxElement = currentMaxElement
            }
        }

        return maximumDifference
    }
}
