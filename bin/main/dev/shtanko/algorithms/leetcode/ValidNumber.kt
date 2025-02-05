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

import java.util.Locale

fun String.isNumberRegex(): Boolean {
    return trim().matches("[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?".toRegex())
}

fun String.isNumber(): Boolean {
    var str = this
    str = str.lowercase(Locale.ROOT).trim { it <= ' ' }
    var dotSeen = false
    var eSeen = false
    var numberBeforeE = false
    var numberAfterE = false
    for (i in str.indices) {
        val cur = str[i]
        when (cur) {
            in '0'..'9' -> {
                if (!eSeen) {
                    numberBeforeE = true
                } else {
                    numberAfterE = true
                }
            }

            '-', '+' -> {
                if (i != 0 && str[i - 1] != 'e') return false
            }

            '.' -> {
                if (eSeen || dotSeen) return false
                dotSeen = true
            }

            'e' -> {
                if (eSeen) return false
                eSeen = true
            }

            else -> { // invalid chars
                return false
            }
        }
    }
    return if (eSeen) numberBeforeE && numberAfterE else numberBeforeE
}
