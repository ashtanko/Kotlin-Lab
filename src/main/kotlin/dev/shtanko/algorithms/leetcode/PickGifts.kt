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

import dev.shtanko.algorithms.annotations.level.Easy
import java.util.PriorityQueue
import kotlin.math.sqrt

/**
 * 2558. Take Gifts From the Richest Pile
 * @see <a href="https://leetcode.com/problems/take-gifts-from-the-richest-pile/">Take Gifts From the Richest Pile</a>
 */
@Easy("https://leetcode.com/problems/take-gifts-from-the-richest-pile")
sealed interface PickGifts {
    operator fun invoke(gifts: IntArray, k: Int): Long

    data object SortedArray : PickGifts {
        override fun invoke(gifts: IntArray, k: Int): Long {
            val n = gifts.size

            // Create a mutable list from the gifts array and sort it
            val sortedGifts = gifts.sorted().toMutableList()

            // Perform the operation k times
            repeat(k) {
                // Get the largest element (last element in the sorted list)
                val maxElement = sortedGifts.removeAt(n - 1)

                // Calculate the square root of the max element
                val sqrtElement = sqrt(maxElement.toDouble()).toInt()

                // Find the index where the square root should be inserted
                val spotOfSqrt = sortedGifts.binarySearch(sqrtElement).let {
                    if (it < 0) -(it + 1) else it
                }

                // Insert the square root at the correct index
                sortedGifts.add(spotOfSqrt, sqrtElement)
            }

            // Calculate the sum of the remaining gifts in the list
            return sortedGifts.sumOf { it.toLong() }
        }
    }

    data object Heap : PickGifts {
        override fun invoke(gifts: IntArray, k: Int): Long {
            val giftsList = gifts.map { -it }.toMutableList()

            // Initialize PriorityQueue from giftsList
            val giftsHeap = PriorityQueue(giftsList)

            // Perform the operation 'k' times
            repeat(k) {
                // Get the maximum element from the heap (top element)
                val maxElement = -giftsHeap.poll()

                // Insert the floor of the square root of the maximum element back into the heap
                giftsHeap.offer(-(sqrt(maxElement.toDouble()).toInt()))
            }

            // Accumulate the sum of the elements in the heap
            var numberOfRemainingGifts = 0L
            while (giftsHeap.isNotEmpty()) {
                numberOfRemainingGifts -= giftsHeap.poll()
            }

            return numberOfRemainingGifts
        }
    }
}
