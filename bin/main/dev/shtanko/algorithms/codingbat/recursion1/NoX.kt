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
 * Recursion-1 > noX
 * @see <a href="https://codingbat.com/prob/p118230">Source</a>
 */
fun interface NoX {
    operator fun invoke(str: String): String
}

class NoXIterative : NoX {
    override fun invoke(str: String): String {
        val sb = StringBuilder()
        for (s in str) {
            if (s != 'x') {
                sb.append(s)
            }
        }
        return sb.toString()
    }
}

class NoXFilter : NoX {
    override fun invoke(str: String) = str.filter { it != 'x' }
}

class NoXRecursive : NoX {
    override fun invoke(str: String): String {
        if (str.isEmpty()) {
            return ""
        }
        return if (str[0] == 'x') {
            invoke(str.substring(1))
        } else {
            str[0] + invoke(str.substring(1))
        }
    }
}
