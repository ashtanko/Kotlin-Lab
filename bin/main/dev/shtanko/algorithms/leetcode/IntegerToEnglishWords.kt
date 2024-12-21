/*
 * Copyright 2020 Oleksii Shtanko
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

import dev.shtanko.algorithms.DECIMAL

class IntegerToEnglishWords {

    private val belowTen = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
    private val belowTwenty = arrayOf(
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen",
    )
    private val belowHundred =
        arrayOf("", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

    fun numberToWords(num: Int): String {
        return if (num == 0) ZERO_STRING else helper(num)
    }

    private fun helper(num: Int): String {
        val result: String = when {
            num < DECIMAL -> belowTen[num]
            num < TWENTY -> belowTwenty[num - DECIMAL]
            num < HUNDRED -> String.format(HUNDRED_FORMAT, belowHundred[num / DECIMAL], helper(num % DECIMAL))
            num < THOUSAND -> String.format(
                THOUSAND_FORMAT,
                helper(num / HUNDRED),
                HUNDRED_STRING,
                helper(num % HUNDRED),
            )

            num < MILLION -> String.format(
                MILLION_FORMAT,
                helper(num / THOUSAND),
                THOUSAND_STRING,
                helper(num % THOUSAND),
            )

            num < BILLION -> String.format(
                BILLION_FORMAT,
                helper(num / MILLION),
                MILLION_STRING,
                helper(num % MILLION),
            )

            else -> String.format(FORMAT, helper(num / BILLION), BILLION_STRING, helper(num % BILLION))
        }
        return result.trim { it <= BLANK }
    }

    companion object {
        private const val TWENTY = 20
        private const val HUNDRED = 100
        private const val THOUSAND = 1000
        private const val MILLION = 1_000_000
        private const val BILLION = 1_000_000_000
        private const val ZERO_STRING = "Zero"
        private const val HUNDRED_STRING = "Hundred"
        private const val THOUSAND_STRING = "Thousand"
        private const val MILLION_STRING = "Million"
        private const val BILLION_STRING = "Billion"
        private const val HUNDRED_FORMAT = "%s %s"
        private const val THOUSAND_FORMAT = "%s %s %s"
        private const val MILLION_FORMAT = "%s %s %s"
        private const val BILLION_FORMAT = "%s %s %s"
        private const val FORMAT = "%s %s %s"
        private const val BLANK = ' '
    }
}
