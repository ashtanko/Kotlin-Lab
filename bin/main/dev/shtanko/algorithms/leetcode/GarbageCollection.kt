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
 * 2391. Minimum Amount of Time to Collect Garbage
 * @see <a href="https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage">Source</a>
 */
fun interface GarbageCollection {
    operator fun invoke(garbage: Array<String>, travel: IntArray): Int
}

sealed interface GarbageCollectionStrategy {
    data object HashMap : GarbageCollection, GarbageCollectionStrategy {
        override fun invoke(garbage: Array<String>, travel: IntArray): Int {
            // Array to store the prefix sum in travel.
            val prefixSum = IntArray(travel.size + 1)
            prefixSum[1] = travel[0]
            for (i in 1 until travel.size) {
                prefixSum[i + 1] = prefixSum[i] + travel[i]
            }

            // Map to store garbage type to the last house index.
            val garbageLastPos: MutableMap<Char, Int> = HashMap()

            // Map to store the total count of each type of garbage in all houses.
            val garbageCount: MutableMap<Char, Int> = HashMap()
            for (i in garbage.indices) {
                for (c in garbage[i].toCharArray()) {
                    garbageLastPos[c] = i
                    garbageCount[c] = garbageCount.getOrDefault(c, 0) + 1
                }
            }

            val garbageTypes = "MPG"
            var ans = 0
            for (c in garbageTypes.toCharArray()) {
                // Add only if there is at least one unit of this garbage.
                if (garbageCount.containsKey(c)) {
                    ans += prefixSum[garbageLastPos.getOrDefault(c, 0)] + garbageCount.getOrDefault(c, 0)
                }
            }

            return ans
        }
    }

    data object HashMapInPlace : GarbageCollection, GarbageCollectionStrategy {
        override fun invoke(garbage: Array<String>, travel: IntArray): Int {
            // Store the prefix sum in travel itself.
            for (i in 1 until travel.size) {
                travel[i] = travel[i - 1] + travel[i]
            }

            // Map to store garbage type to the last house index.
            val garbageLastPos: MutableMap<Char, Int> = HashMap()
            var ans = 0
            for (i in garbage.indices) {
                for (c in garbage[i].toCharArray()) {
                    garbageLastPos[c] = i
                }
                ans += garbage[i].length
            }

            val garbageTypes = "MPG"
            for (c in garbageTypes.toCharArray()) {
                // No travel time is required if the last house is at index 0.
                ans += if (garbageLastPos.getOrDefault(c, 0) == 0) {
                    0
                } else {
                    travel[garbageLastPos.getOrDefault(c, 0) - 1]
                }
            }

            return ans
        }
    }
}
