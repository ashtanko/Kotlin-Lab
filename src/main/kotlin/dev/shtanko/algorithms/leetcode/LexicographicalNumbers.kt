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
 * 386. Lexicographical Numbers
 * @see <a href="https://leetcode.com/problems/lexicographical-numbers/">Lexicographical Numbers</a>
 */
fun interface LexicographicalNumbers {
    operator fun invoke(num: Int): List<Int>
}

data object LexicographicalNumbersDFS : LexicographicalNumbers {
    override fun invoke(num: Int): List<Int> {
        val lexicographicalNumbers = mutableListOf<Int>()
        // Start generating numbers from 1 to 9
        for (start in 1..9) {
            generateLexicalNumbers(start, num, lexicographicalNumbers)
        }
        return lexicographicalNumbers
    }

    private fun generateLexicalNumbers(
        currentNumber: Int,
        limit: Int,
        result: MutableList<Int>,
    ) {
        // If the current number exceeds the limit, stop recursion
        if (currentNumber > limit) return

        // Add the current number to the result
        result.add(currentNumber)

        // Try to append digits from 0 to 9 to the current number
        for (nextDigit in 0..9) {
            val nextNumber = currentNumber * 10 + nextDigit
            // If the next number is within the limit, continue recursion
            if (nextNumber <= limit) {
                generateLexicalNumbers(nextNumber, limit, result)
            } else {
                break // No need to continue if nextNumber exceeds limit
            }
        }
    }
}
