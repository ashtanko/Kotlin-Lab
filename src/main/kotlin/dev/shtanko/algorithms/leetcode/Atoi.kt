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
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 8. String to Integer (atoi)
 * @see <a href="https://leetcode.com/problems/string-to-integer-atoi/">Source</a>
 * @receiver String The string to convert to an integer.
 * @return Int The converted integer.
 */
@Medium(link = "https://leetcode.com/problems/string-to-integer-atoi")
fun String.atoi(): Int {
    val str = this.trim()
    var index = 0
    var sign = 1
    var total = 0

    if (str.isEmpty()) return 0

    if (str[index] == '-' || str[index] == '+') {
        sign = if (str[index] == '-') -1 else 1
        index++
    }

    while (index < str.length) {
        val digit = str[index] - '0'
        if (digit < 0 || digit > 9) break

        // check if total will be overflow after 10 times and add digit
        if (Int.MAX_VALUE / DECIMAL < total || Int.MAX_VALUE / DECIMAL == total && Int.MAX_VALUE % DECIMAL < digit) {
            return if (sign == 1) {
                Int.MAX_VALUE
            } else {
                Int.MIN_VALUE
            }
        }

        total = total * DECIMAL + digit
        index++
    }
    return total * sign
}
