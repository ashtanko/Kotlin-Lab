/*
 * Copyright 2023 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.math.gcd

/**
 * 1071. Greatest Common Divisor of Strings
 * @link https://leetcode.com/problems/greatest-common-divisor-of-strings/
 */
interface GcdOfStrings {
    fun perform(str1: String, str2: String): String
}

class GcdOfStringsImpl : GcdOfStrings {
    override fun perform(str1: String, str2: String): String {
        if ((str1 + str2) != str2 + str1) return ""
        val gcdVal: Int = gcd(str1.length, str2.length)
        return str2.substring(0, gcdVal)
    }
}
