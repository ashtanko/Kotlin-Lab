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
 * Recursion-1 > stringClean
 * @see <a href="https://codingbat.com/prob/p104029">Source</a>
 */
fun interface StringClean {
    operator fun invoke(str: String): String
}

class StringCleanIterative : StringClean {
    override fun invoke(str: String): String {
        if (str.length < 2) {
            return str
        }
        val sb = StringBuilder()
        var i = 0
        while (i < str.length) {
            sb.append(str[i])
            while (i + 1 < str.length && str[i] == str[i + 1]) {
                i++
            }
            i++
        }
        return sb.toString()
    }
}

class StringCleanRecursive : StringClean {
    override fun invoke(str: String): String {
        if (str.length < 2) {
            return str
        }
        return if (str[0] == str[1]) {
            invoke(str.substring(1))
        } else {
            str[0] + invoke(str.substring(1))
        }
    }
}
