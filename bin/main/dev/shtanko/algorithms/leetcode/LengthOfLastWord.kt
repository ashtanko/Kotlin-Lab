/*
 * Copyright 2021 Oleksii Shtanko
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
 * 58. Length of Last Word
 * @see <a href="https://leetcode.com/problems/length-of-last-word/">Source</a>
 */
object LengthOfLastWord {
    fun stringIndexManipulation(s: String): Int {
        // trim the trailing spaces
        var p: Int = s.length - 1
        while (p >= 0 && s[p] == ' ') {
            p--
        }

        // compute the length of last word
        var length = 0
        while (p >= 0 && s[p] != ' ') {
            p--
            length++
        }
        return length
    }

    fun oneLoopIteration(s: String): Int {
        var p: Int = s.length
        var length = 0
        while (p > 0) {
            p--
            // we're in the middle of the last word
            if (s[p] != ' ') {
                length++
            } else if (length > 0) {
                return length
            }
        }
        return length
    }

    fun builtinString(s: String): Int {
        val trimmed = s.trim() // trim the trailing spaces in the string
        return trimmed.length - trimmed.lastIndexOf(" ") - 1
    }
}
