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

/**
 * 3005. Count Elements With Maximum Frequency
 * @see <a href="https://leetcode.com/problems/count-elements-with-maximum-frequency">Source</a>
 */
fun interface MaxFrequencyElements {
    operator fun invoke(nums: IntArray): Int
}

val maxFrequencyElementsOnePass = MaxFrequencyElements { nums ->
    val frequencies: MutableMap<Int, Int> = HashMap()
    var maxFrequency = 0
    var totalFrequencies = 0

    // Find the frequency of each element
    // Determine the maximum frequency
    // Calculate the total frequencies of elements with the maximum frequency
    for (num in nums) {
        frequencies[num] = frequencies.getOrDefault(num, 0) + 1
        val frequency = frequencies[num]!!

        // If we discover a higher frequency element
        // Update maxFrequency
        // Re-set totalFrequencies to the new max frequency
        if (frequency > maxFrequency) {
            maxFrequency = frequency
            totalFrequencies = frequency
            // If we find an element with the max frequency, add it to the total
        } else if (frequency == maxFrequency) {
            totalFrequencies += frequency
        }
    }
    return@MaxFrequencyElements totalFrequencies
}
