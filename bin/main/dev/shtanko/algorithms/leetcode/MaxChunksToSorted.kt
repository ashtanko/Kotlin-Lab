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

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.Stack

/**
 * 769. Max Chunks To Make Sorted
 * @see <a href="https://leetcode.com/problems/max-chunks-to-make-sorted/">Max Chunks To Make Sorted</a>
 */
@Medium
sealed interface MaxChunksToSorted {
    operator fun invoke(arr: IntArray): Int

    data object PrefixArrays : MaxChunksToSorted {
        override fun invoke(arr: IntArray): Int {
            val n = arr.size
            val prefixMax = arr.clone()
            val suffixMin = arr.clone()

            // Fill the prefixMax array
            for (i in 1 until n) {
                prefixMax[i] = maxOf(prefixMax[i - 1], prefixMax[i])
            }

            // Fill the suffixMin array in reverse order
            for (i in n - 2 downTo 0) {
                suffixMin[i] = minOf(suffixMin[i + 1], suffixMin[i])
            }

            var chunks = 0
            for (i in 0 until n) {
                // A new chunk can be created
                if (i == 0 || suffixMin[i] > prefixMax[i - 1]) {
                    chunks++
                }
            }

            return chunks
        }
    }

    data object PrefixSums : MaxChunksToSorted {
        override fun invoke(arr: IntArray): Int {
            val n = arr.size
            var chunks = 0
            var prefixSum = 0
            var sortedPrefixSum = 0

            // Iterate over the array
            for (i in 0 until n) {
                // Update prefix sum of `arr`
                prefixSum += arr[i]
                // Update prefix sum of the sorted array
                sortedPrefixSum += i

                // If the two sums are equal, the two prefixes contain the same elements; a chunk can be formed
                if (prefixSum == sortedPrefixSum) {
                    chunks++
                }
            }
            return chunks
        }
    }

    data object StackSolution : MaxChunksToSorted {
        override fun invoke(arr: IntArray): Int {
            val monotonicStack = Stack<Int>()

            for (element in arr) {
                // Case 1: Current element is larger, starts a new chunk
                if (monotonicStack.isEmpty() || element > monotonicStack.peek()) {
                    monotonicStack.push(element)
                } else {
                    // Case 2: Merge chunks
                    val maxElement = monotonicStack.peek()
                    while (monotonicStack.isNotEmpty() && element < monotonicStack.peek()) {
                        monotonicStack.pop()
                    }
                    monotonicStack.push(maxElement)
                }
            }
            return monotonicStack.size
        }
    }

    data object MaximumElement : MaxChunksToSorted {
        override fun invoke(arr: IntArray): Int {
            var chunks = 0
            var maxElement = 0

            // Iterate over the array
            for (i in arr.indices) {
                // Update maxElement
                maxElement = maxOf(maxElement, arr[i])

                if (maxElement == i) {
                    // All values in range [0, i] belong to the prefix arr[0:i]; a new chunk can be formed
                    chunks++
                }
            }

            return chunks
        }
    }
}
