/*
 * Copyright 2021 Oleksii Shtanko
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
 * 1762. Buildings With an Ocean View
 * @see <a href="https://leetcode.com/problems/buildings-with-an-ocean-view/">Source</a>
 */
fun interface FindBuildings {
    operator fun invoke(heights: IntArray): IntArray
}

sealed class FindBuildingStrategy {
    object BruteForce : FindBuildings, FindBuildingStrategy() {
        override operator fun invoke(heights: IntArray): IntArray {
            var last = Int.MIN_VALUE
            val indexes: MutableList<Int> = ArrayList()
            for (i in heights.size - 1 downTo 0) {
                val current = heights[i]
                if (current > last) {
                    indexes.add(i)
                    last = current
                }
            }
            var idx = 0
            val res = IntArray(indexes.size)
            for (i in indexes.size - 1 downTo 0) {
                res[idx++] = indexes[i]
            }
            return res
        }
    }
}
