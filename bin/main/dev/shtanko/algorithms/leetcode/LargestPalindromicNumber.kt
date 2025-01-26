/*
 * Copyright 2022 Oleksii Shtanko
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
 * 2384. Largest Palindromic Number
 * @see <a href="https://leetcode.com/problems/largest-palindromic-number/">Source</a>
 */
fun interface LargestPalindromicNumber {
    operator fun invoke(num: String): String
}

class LargestPalindromicNumberGreedy : LargestPalindromicNumber {
    override operator fun invoke(num: String): String {
        val count = IntArray(10)
        for (c in num.toCharArray()) {
            count[c.code - '0'.code]++
        }
        var odd = false
        val sb = StringBuilder()
        var middle = ' '
        for (i in 9 downTo 0) {
            if (i == 0 && sb.isEmpty() && !odd) {
                return "0"
            }
            val c = (i + '0'.code).toChar()
            if (!odd) {
                odd = count[i] % 2 == 1
                if (odd) {
                    middle = c
                }
            }

            // no leading zero
            if (i == 0 && sb.isEmpty()) {
                continue
            }
            for (j in 0 until count[i] / 2) {
                sb.append(c)
            }
        }

        val reverse = StringBuilder(sb).reverse()
        if (odd) {
            sb.append(middle)
        }

        sb.append(reverse)
        return sb.toString()
    }
}
