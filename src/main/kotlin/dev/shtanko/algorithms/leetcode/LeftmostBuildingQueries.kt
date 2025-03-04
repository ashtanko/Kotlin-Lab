/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Hard
import java.util.PriorityQueue

/**
 * 2940. Find Building Where Alice and Bob Can Meet
 * @see <a href="https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/">Source</a>
 */
@Hard
sealed interface LeftmostBuildingQueries {
    operator fun invoke(heights: IntArray, queries: Array<IntArray>): IntArray

    data object MonotonicStack : LeftmostBuildingQueries {
        override fun invoke(heights: IntArray, queries: Array<IntArray>): IntArray {
            val monoStack = mutableListOf<Pair<Int, Int>>()
            val result = IntArray(queries.size) { -1 }
            val newQueries = List(heights.size) { mutableListOf<Pair<Int, Int>>() }

            // Process queries
            for ((i, query) in queries.withIndex()) {
                var (a, b) = query
                if (a > b) {
                    a = b.also { b = a }
                }
                if (heights[b] > heights[a] || a == b) {
                    result[i] = b
                } else {
                    newQueries[b].add(heights[a] to i)
                }
            }

            // Process monoStack and resolve queries
            for (i in heights.indices.reversed()) {
                val monoStackSize = monoStack.size
                for ((height, queryIndex) in newQueries[i]) {
                    val position = search(height, monoStack)
                    if (position in 0 until monoStackSize) {
                        result[queryIndex] = monoStack[position].second
                    }
                }

                while (monoStack.isNotEmpty() && monoStack.last().first <= heights[i]) {
                    monoStack.removeAt(monoStack.size - 1)
                }

                monoStack.add(heights[i] to i)
            }

            return result
        }

        private fun search(height: Int, monoStack: List<Pair<Int, Int>>): Int {
            var left = 0
            var right = monoStack.size - 1
            var ans = -1
            while (left <= right) {
                val mid = (left + right) / 2
                if (monoStack[mid].first > height) {
                    ans = maxOf(ans, mid)
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
            return ans
        }
    }

    data object PriorityQueueStrategy : LeftmostBuildingQueries {
        override fun invoke(heights: IntArray, queries: Array<IntArray>): IntArray {
            val storeQueries = List(heights.size) { mutableListOf<MutableList<Int>>() }
            val maxIndex = PriorityQueue<List<Int>>(compareBy { it[0] })
            val result = IntArray(queries.size) { -1 }

            // Store the mappings for all queries in storeQueries
            for ((currQuery, query) in queries.withIndex()) {
                val (a, b) = query
                when {
                    a < b && heights[a] < heights[b] -> result[currQuery] = b
                    a > b && heights[a] > heights[b] -> result[currQuery] = a
                    a == b -> result[currQuery] = a
                    else -> storeQueries[maxOf(a, b)].add(mutableListOf(maxOf(heights[a], heights[b]), currQuery))
                }
            }

            // If the priority queue's minimum pair value is less than the current index of height, it is an answer
            // to the query
            for (index in heights.indices) {
                while (maxIndex.isNotEmpty() && maxIndex.peek()[0] < heights[index]) {
                    val (_, currQuery) = maxIndex.poll()
                    result[currQuery] = index
                }
                // Push the pairs with their maximum index as the current index in the priority queue
                for (element in storeQueries[index]) {
                    maxIndex.offer(element)
                }
            }

            return result
        }
    }
}
