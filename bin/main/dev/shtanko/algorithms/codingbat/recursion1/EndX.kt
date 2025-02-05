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
 * Recursion-1 > endX
 * @see <a href="https://codingbat.com/prob/p105722">Source</a>
 */
fun interface EndX {
    operator fun invoke(str: String): String
}

class EndXIterative : EndX {
    override fun invoke(str: String): String {
        if (!str.contains("x".lowercase())) {
            return ""
        }
        val sb = StringBuilder()
        val sbx = StringBuilder()
        for (s in str) {
            if (s.lowercase() == "x") {
                sbx.append(s)
            } else {
                sb.append(s)
            }
        }
        return sb.toString() + sbx.toString()
    }
}

class EndXRecursive : EndX {
    override fun invoke(str: String): String {
        if (str.isEmpty()) {
            return ""
        }
        if (str[0] == 'x'.lowercaseChar()) {
            return invoke(str.substring(1)) + str[0]
        }
        return str[0] + invoke(str.substring(1))
    }
}
