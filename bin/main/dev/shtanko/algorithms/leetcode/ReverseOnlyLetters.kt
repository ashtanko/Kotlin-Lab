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

import java.util.Stack

/**
 * 917. Reverse Only Letters
 * @see <a href="https://leetcode.com/problems/reverse-only-letters/">Source</a>
 */
fun interface ReverseOnlyLetters {
    operator fun invoke(s: String): String
}

/**
 * Approach 1: Stack of Letters
 */
class ReverseOnlyLettersStack : ReverseOnlyLetters {
    override operator fun invoke(s: String): String {
        val letters: Stack<Char> = Stack()
        for (c in s.toCharArray()) if (Character.isLetter(c)) letters.push(c)

        val ans = StringBuilder()
        for (c in s.toCharArray()) {
            if (Character.isLetter(c)) ans.append(letters.pop()) else ans.append(c)
        }

        return ans.toString()
    }
}

/**
 * Approach 2: Reverse Pointer
 */
class ReversePointer : ReverseOnlyLetters {
    override operator fun invoke(s: String): String {
        val ans = StringBuilder()
        var j: Int = s.length - 1
        for (i in s.indices) {
            if (Character.isLetter(s[i])) {
                while (!Character.isLetter(s[j])) j--
                ans.append(s[j--])
            } else {
                ans.append(s[i])
            }
        }

        return ans.toString()
    }
}
