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
 * 389. Find the Difference
 * @see <a href="https://leetcode.com/problems/find-the-difference/">Source</a>
 */
fun interface FindDifference {
    operator fun invoke(s: String, t: String): Char
}

class FindDifferenceSimple : FindDifference {
    override operator fun invoke(s: String, t: String): Char {
        var charCode = t[s.length]
        // Iterate through both strings and char codes
        for (i in s.indices) {
            charCode -= s[i].code
            charCode += t[i].code
        }
        return charCode
    }
}

class FindDifferenceBitwise : FindDifference {
    override operator fun invoke(s: String, t: String): Char {
        val n = t.length
        var c = t[n - 1].code
        for (i in 0 until n - 1) {
            c = c xor s[i].code
            c = c xor t[i].code
        }
        return c.toChar()
    }
}
