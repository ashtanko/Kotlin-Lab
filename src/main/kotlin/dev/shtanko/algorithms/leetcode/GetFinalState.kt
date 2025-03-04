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

/**
 * 3264. Final Array State After K Multiplication Operations I
 * @see <a href="https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/">Source</a>
 */
@Easy("https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i")
sealed interface GetFinalState {
    operator fun invoke(nums: IntArray, k: Int, multiplier: Int): IntArray

    data object ArrayStrategy : GetFinalState {
        override fun invoke(nums: IntArray, k: Int, multiplier: Int): IntArray {
            val n = nums.size

            repeat(k) {
                // Find the index of the smallest element in the array
                var minIndex = 0
                for (i in 1 until n) {
                    if (nums[i] < nums[minIndex]) {
                        minIndex = i
                    }
                }

                // Multiply the smallest element by the multiplier
                nums[minIndex] *= multiplier
            }

            return nums
        }
    }

    data object HeapStrategy : GetFinalState {
        override fun invoke(nums: IntArray, k: Int, multiplier: Int): IntArray {
            // Create a priority queue with custom comparator
            val heap = PriorityQueue(compareBy<Pair<Int, Int>>({ it.first }, { it.second }))

            // Populate the priority queue with initial values and their indices
            nums.forEachIndexed { index, value ->
                heap.offer(value to index)
            }

            var remaining = k
            while (remaining-- > 0) {
                // Retrieve and remove the smallest element
                val (_, index) = heap.poll()

                // Update the array and re-insert the updated value with its index into the heap
                nums[index] *= multiplier
                heap.offer(nums[index] to index)
            }

            return nums
        }
    }
}
