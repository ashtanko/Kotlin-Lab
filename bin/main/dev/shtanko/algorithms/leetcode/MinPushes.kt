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

/**
 * 3016. Minimum Number of Pushes to Type Word II
 * @see <a href="https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii">Source</a>
 */
fun interface MinPushes {
    operator fun invoke(word: String): Int
}

/**
 * Approach 2: Using Heap
 */
class MinPushesHeap : MinPushes {
    override fun invoke(word: String): Int {
        val frequencyMap = mutableMapOf<Char, Int>()

        // Count occurrences of each letter
        for (c in word) {
            frequencyMap[c] = frequencyMap.getOrDefault(c, 0) + 1
        }
        // Priority queue to store frequencies in descending order
        val frequencyQueue = PriorityQueue<Int>(compareByDescending { it })
        frequencyQueue.addAll(frequencyMap.values)

        var totalPushes = 0
        var index = 0

        // Calculate total number of presses
        while (frequencyQueue.isNotEmpty()) {
            totalPushes += (index / 8 + 1) * frequencyQueue.poll()
            index++
        }

        return totalPushes
    }
}
