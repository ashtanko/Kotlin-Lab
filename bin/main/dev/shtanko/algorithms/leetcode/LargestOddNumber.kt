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
 * 1903. Largest Odd Number in String
 * @see <a href="https://leetcode.com/problems/largest-odd-number-in-string">Source</a>
 */
fun interface LargestOddNumber {
    operator fun invoke(num: String): String
}

class LargestOddNumberOddDigit : LargestOddNumber {
    override fun invoke(num: String): String {
        for (i in num.length - 1 downTo 0) {
            if (Character.getNumericValue(num[i]) % 2 != 0) {
                return num.substring(0, i + 1)
            }
        }

        return ""
    }
}
