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

import dev.shtanko.algorithms.annotations.BinarySearch
import dev.shtanko.algorithms.annotations.Greedy
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.PriorityQueue

/**
 * 2064. Minimized Maximum of Products Distributed to Any Store
 * @see <a href="https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/")
fun interface MinimizedMax {
    operator fun invoke(n: Int, quantities: IntArray): Int
}

@BinarySearch
data object MinimizedMaxBinarySearch : MinimizedMax {
    override fun invoke(n: Int, quantities: IntArray): Int {
        var left = 0
        var right = quantities.maxOrNull() ?: 0

        while (left < right) {
            val middle = (left + right) / 2
            if (canDistribute(middle, quantities, n)) {
                right = middle
            } else {
                left = middle + 1
            }
        }

        return left
    }

    private fun canDistribute(x: Int, quantities: IntArray, n: Int): Boolean {
        var j = 0
        var remaining = quantities[j]

        for (i in 0 until n) {
            if (remaining <= x) {
                j++
                if (j == quantities.size) return true
                remaining = quantities[j]
            } else {
                remaining -= x
            }
        }

        return false
    }
}

@Greedy
data object MinimizedMaxHeap : MinimizedMax {
    override fun invoke(n: Int, quantities: IntArray): Int {
        val m = quantities.size

        // Create a PriorityQueue with pairs (-ratio, quantity, storesAssigned)
        val typeStorePairs = PriorityQueue(compareBy<Pair<Double, Pair<Int, Int>>> { it.first })

        // Populate the heap with initial pairs
        for (q in quantities) {
            val initialRatio = -q.toDouble() // Use negative for max-heap behavior
            typeStorePairs.add(Pair(initialRatio, Pair(q, 1)))
        }

        // Distribute the remaining stores
        repeat(n - m) {
            val (_, quantityAndStores) = typeStorePairs.poll()
            val (totalQuantity, storesAssigned) = quantityAndStores

            // Calculate the new ratio after assigning one more store
            val newStoresAssigned = storesAssigned + 1
            val newRatio = -totalQuantity.toDouble() / newStoresAssigned

            // Add the updated pair back to the heap
            typeStorePairs.add(Pair(newRatio, Pair(totalQuantity, newStoresAssigned)))
        }

        // Get the final ratio for the most distributed type
        val (_, quantityAndStores) = typeStorePairs.poll()
        val (totalQuantity, storesAssigned) = quantityAndStores

        // Return the maximum minimum ratio
        return kotlin.math.ceil(totalQuantity.toDouble() / storesAssigned).toInt()
    }
}
