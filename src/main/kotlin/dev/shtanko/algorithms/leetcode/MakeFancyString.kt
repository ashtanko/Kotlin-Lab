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

import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 1957. Delete Characters to Make Fancy String
 * @see <a href="https://leetcode.com/problems/delete-characters-to-make-fancy-string/">Source</a>
 */
@Easy("https://leetcode.com/problems/delete-characters-to-make-fancy-string")
fun interface MakeFancyString {
    operator fun invoke(s: String): String
}

class MakeFancyStringTwoPointer : MakeFancyString {
    override fun invoke(s: String): String {
        if (s.length < 3) {
            return s
        }

        val sb = StringBuilder()
        // Start by appending the first two characters to StringBuilder.
        sb.append(s[0]).append(s[1])

        // Iterate from the 3rd character onwards.
        for (i in 2 until s.length) {
            // If the current character is not equal to the previously inserted
            // two characters, then we can add it to the StringBuilder.
            if (s[i] != sb[sb.length - 1] || s[i] != sb[sb.length - 2]) {
                sb.append(s[i])
            }
        }

        // Convert StringBuilder back to String and return.
        return sb.toString()
    }
}
