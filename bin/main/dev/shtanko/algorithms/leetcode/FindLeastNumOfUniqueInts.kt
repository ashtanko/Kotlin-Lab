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

import java.util.PriorityQueue
import kotlin.math.min

/**
 * 1481. Least Number of Unique Integers after K Removals
 * @see <a href="https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals">Source</a>
 */
fun interface FindLeastNumOfUniqueInts {
    operator fun invoke(arr: IntArray, k: Int): Int
}

class FindLeastNumOfUniqueIntsSort : FindLeastNumOfUniqueInts {
    override fun invoke(arr: IntArray, k: Int): Int {
        // Map to track the frequencies of elements
        val map: MutableMap<Int, Int> = HashMap()
        for (i in arr) {
            map[i] = map.getOrDefault(i, 0) + 1
        }

        // List to track all the frequencies
        val frequencies: List<Int> = ArrayList(map.values).sorted()

        // Tracking the number of elements removed
        var elementsRemoved = 0

        for (i in frequencies.indices) {
            // Removing frequencies[i] elements, which equates to
            // removing one unique element
            elementsRemoved += frequencies[i]

            // If the number of elements removed exceeds k, return
            // the remaining number of unique elements
            if (elementsRemoved > k) {
                return frequencies.size - i
            }
        }

        // We have removed all elements, so no unique integers remain
        // Return 0 in this case
        return 0
    }
}

class FindLeastNumOfUniqueIntsHeap : FindLeastNumOfUniqueInts {
    override fun invoke(arr: IntArray, k: Int): Int {
        // Map to track the frequencies of elements
        val map: MutableMap<Int, Int> = HashMap()
        for (i in arr) {
            map[i] = map.getOrDefault(i, 0) + 1
        }

        // Min heap to track all the frequencies
        val frequencies: PriorityQueue<Int> = PriorityQueue(map.values)

        // Tracking the number of elements removed
        var elementsRemoved = 0

        // Traversing all frequencies
        while (frequencies.isNotEmpty()) {
            // Removing the least frequent element
            elementsRemoved += frequencies.peek()

            // If the number of elements removed exceeds k, return
            // the remaining number of unique elements
            if (elementsRemoved > k) {
                return frequencies.size
            }
            frequencies.poll()
        }

        // We have removed all elements, so no unique integers remain
        // Return 0 in this case
        return 0
    }
}

class FindLeastNumOfUniqueIntsCountingSort : FindLeastNumOfUniqueInts {
    override fun invoke(arr: IntArray, k: Int): Int {
        var k0 = k
        // Map to track the frequencies of elements
        val map: MutableMap<Int, Int> = HashMap()
        for (i in arr) {
            map[i] = map.getOrDefault(i, 0) + 1
        }

        val n: Int = arr.size
        // Array to track the frequencies of frequencies
        // The maximum possible frequency of any element
        // will be n, so we'll initialize this array with size n + 1
        val countOfFrequencies = IntArray(n + 1)

        // Populating countOfFrequencies array
        for (count in map.values) {
            countOfFrequencies[count]++
        }

        // Variable to track the remaining number of unique elements
        var remainingUniqueElements = map.size

        // Traversing over all possible frequencies
        for (i in 1..n) {
            // For each possible frequency i, we'd like to remove as
            // many elements with that frequency as possible.
            // k / i represents the number of maximum possible elements
            // we could remove with k elements left to remove.
            // countOfFrequencies[i] represents the actual number of elements
            // with frequency i.
            val numElementsToRemove = min(k0 / i, countOfFrequencies[i])

            // Removing maximum possible elements
            k0 -= (i * numElementsToRemove)

            // numElementsToRemove is the count of unique elements removed
            remainingUniqueElements -= numElementsToRemove

            // If the number of elements that can be removed is less
            // than the current frequency, we won't be able to remove
            // any more elements with a higher frequency so we can return
            // the remaining number of unique elements
            if (k0 < i) {
                return remainingUniqueElements
            }
        }

        // We have traversed all possible frequencies i.e.,
        // removed all elements. Returning 0 in this case.
        return 0
    }
}
