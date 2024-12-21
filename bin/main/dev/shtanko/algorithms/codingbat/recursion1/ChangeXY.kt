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

package dev.shtanko.algorithms.codingbat.recursion1

/**
 * Recursion-1 > changeXY
 * @see <a href="https://codingbat.com/prob/p101372">Source</a>
 */
fun interface ChangeXY {
    operator fun invoke(str: String): String
}

class ChangeXYIterative : ChangeXY {
    override fun invoke(str: String): String {
        val builder = StringBuilder()
        for (s in str) {
            builder.append(if (s == 'x') "y" else s)
        }
        return builder.toString()
    }
}

class ChangeXYIterative2 : ChangeXY {
    override fun invoke(str: String): String {
        var result = ""
        for (char in str) {
            result += if (char == 'x') {
                'y'
            } else {
                char
            }
        }

        return result
    }
}

class ChangeXYRecursive : ChangeXY {
    override fun invoke(str: String): String {
        if (str.isEmpty()) {
            return ""
        }
        if (str[0] == 'x') {
            return 'y' + invoke(str.substring(1))
        }
        return str[0] + invoke(str.substring(1))
    }
}
