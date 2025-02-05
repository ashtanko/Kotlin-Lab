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

import java.util.PriorityQueue

/**
 * 1921. Eliminate Maximum Number of Monsters
 * @see <a href="https://leetcode.com/problems/eliminate-maximum-number-of-monsters">Source</a>
 */
fun interface EliminateMaxNumOfMonsters {
    operator fun invoke(dist: IntArray, speed: IntArray): Int
}

sealed interface EliminateMaximumStrategy {

    /**
     * Approach 1: Sort By Arrival Time
     */
    data object Sort : EliminateMaxNumOfMonsters, EliminateMaximumStrategy {
        override fun invoke(dist: IntArray, speed: IntArray): Int {
            val arrival = DoubleArray(dist.size)
            for (i in dist.indices) {
                arrival[i] = dist[i].toDouble() / speed[i]
            }

            arrival.sort()
            var ans = 0

            for (i in arrival.indices) {
                if (arrival[i] <= i) {
                    break
                }
                ans++
            }

            return ans
        }
    }

    /**
     * Approach 2: Heap
     */
    data object Heap : EliminateMaxNumOfMonsters, EliminateMaximumStrategy {
        override fun invoke(dist: IntArray, speed: IntArray): Int {
            val heap: PriorityQueue<Double> = PriorityQueue()
            for (i in dist.indices) {
                heap.add(dist[i].toDouble() / speed[i])
            }

            var ans = 0
            while (heap.isNotEmpty()) {
                if (heap.remove() <= ans) {
                    break
                }
                ans++
            }

            return ans
        }
    }
}
