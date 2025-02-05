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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import kotlin.math.max

/**
 * 2272. Substring With Largest Variance
 * @see <a href="https://leetcode.com/problems/substring-with-largest-variance/">Source</a>
 */
fun interface LargestVariance {
    operator fun invoke(s: String): Int
}

/**
 * Approach: Kadane's Algorithm
 */
class LargestVarianceKadane : LargestVariance {

    override operator fun invoke(s: String): Int {
        val counter = IntArray(ALPHABET_LETTERS_COUNT)
        for (ch in s.toCharArray()) {
            counter[ch.code.minus('a'.code)]++
        }
        var globalMax = 0

        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            for (j in 0 until ALPHABET_LETTERS_COUNT) {
                // major and minor cannot be the same, and both must appear in s.
                if (i == j || counter[i] == 0 || counter[j] == 0) {
                    continue
                }

                // Find the maximum variance of major - minor.
                val major = ('a'.code + i).toChar()
                val minor = ('a'.code + j).toChar()
                var majorCount = 0
                var minorCount = 0

                // The remaining minor in the rest of s.
                var restMinor = counter[j]
                for (ch in s.toCharArray()) {
                    if (ch == major) {
                        majorCount++
                    }
                    if (ch == minor) {
                        minorCount++
                        restMinor--
                    }

                    // Only update the variance if there is at least one minor.
                    if (minorCount > 0) globalMax = max(globalMax, majorCount - minorCount)

                    // We can discard the previous string if there is at least one remaining minor.
                    if (majorCount < minorCount && restMinor > 0) {
                        majorCount = 0
                        minorCount = 0
                    }
                }
            }
        }

        return globalMax
    }
}
