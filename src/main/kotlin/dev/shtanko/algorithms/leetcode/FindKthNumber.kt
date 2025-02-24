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
 * 440. K-th Smallest in Lexicographical Order
 * @see <a href="https://leetcode.com/problems/k-th-smallest-in-lexicographical-order">Source</a>
 */
fun interface FindKthNumber {
    operator fun invoke(limit: Int, targetPosition: Int): Int
}

data object FindKthNumberPrefixTree : FindKthNumber {
    override fun invoke(limit: Int, targetPosition: Int): Int {
        var currentNumber = 1
        var remainingPosition = targetPosition - 1

        while (remainingPosition > 0) {
            val stepsBetweenPrefixes =
                countStepsBetweenPrefixes(limit, currentNumber.toLong(), (currentNumber + 1).toLong())
            if (stepsBetweenPrefixes <= remainingPosition) {
                currentNumber++
                remainingPosition -= stepsBetweenPrefixes
            } else {
                currentNumber *= 10
                remainingPosition--
            }
        }

        return currentNumber
    }

    private fun countStepsBetweenPrefixes(limit: Int, lowerPrefix: Long, upperPrefix: Long): Int {
        var steps = 0
        var currentLowerPrefix = lowerPrefix
        var currentUpperPrefix = upperPrefix
        while (currentLowerPrefix <= limit) {
            steps += (minOf(limit + 1L, currentUpperPrefix) - currentLowerPrefix).toInt()
            currentLowerPrefix *= 10
            currentUpperPrefix *= 10
        }
        return steps
    }
}
