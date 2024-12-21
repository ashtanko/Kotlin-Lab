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

/**
 * 2264. Largest 3-Same-Digit Number in String
 * @see <a href="https://leetcode.com/problems/largest-3-same-digit-number-in-string">Source</a>
 */
fun interface LargestGoodInt {
    operator fun invoke(num: String): String
}

class LargestGoodIntSingleIteration : LargestGoodInt {

    companion object {
        private const val SMALLEST_ASCII = '\u0000'
    }

    override fun invoke(num: String): String {
        // Assign 'maxDigit' to the NUL character (smallest ASCII value character)
        var maxDigit = SMALLEST_ASCII

        // Iterate on characters of the num string.
        for (index in 0..num.length - 3) {
            // If 3 consecutive characters are the same,
            // store the character in 'maxDigit' if it's bigger than what it already stores.
            if (num[index] == num[index + 1] && num[index] == num[index + 2]) {
                maxDigit = maxOf(maxDigit, num[index])
            }
        }

        // If 'maxDigit' is NUL, return an empty string; otherwise, return a string of size 3 with 'maxDigit'
        // characters.
        return if (maxDigit == SMALLEST_ASCII) "" else String(charArrayOf(maxDigit, maxDigit, maxDigit))
    }
}

class LargestGoodIntCompare : LargestGoodInt {
    override fun invoke(num: String): String {
        return (2 until num.length).maxOfOrNull { i ->
            if (num[i] == num[i - 1] && num[i] == num[i - 2]) {
                num.substring(i - 2, i + 1)
            } else {
                ""
            }
        } ?: ""
    }
}
