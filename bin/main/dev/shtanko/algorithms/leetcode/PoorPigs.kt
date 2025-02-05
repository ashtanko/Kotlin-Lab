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
 * 458. Poor Pigs
 */
fun interface PoorPigsStrategy {
    operator fun invoke(buckets: Int, minutesToDie: Int, minutesToTest: Int): Int
}

sealed interface PoorPigs {
    class Solution : PoorPigsStrategy {
        override fun invoke(buckets: Int, minutesToDie: Int, minutesToTest: Int): Int {
            val testsPerPig: Int = minutesToTest / minutesToDie
            var numPigs = 0
            var states = 1 // Number of unique states a pig can represent

            while (states < buckets) {
                states *= testsPerPig + 1
                numPigs++
            }
            return numPigs
        }
    }
}
