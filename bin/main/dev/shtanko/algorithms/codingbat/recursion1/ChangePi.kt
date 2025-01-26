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
 * Recursion-1 > changePi
 * @see <a href="https://codingbat.com/prob/p170924">Source</a>
 */
fun interface ChangePi {
    operator fun invoke(str: String): String
}

class ChangePiIterative : ChangePi {
    override fun invoke(str: String): String {
        var result = ""
        var i = 0
        while (i < str.length) {
            if (i + 1 < str.length && str.substring(i, i + 2).lowercase() == "pi") {
                result += "3.14"
                i += 2
            } else {
                result += str[i]
                i++
            }
        }
        return result
    }
}

class ChangePiRecursive : ChangePi {
    override fun invoke(str: String): String {
        if (str.length < 2) {
            return str
        }
        return if (str.substring(0, 2).lowercase() == "pi") {
            "3.14" + invoke(str.substring(2))
        } else {
            str[0] + invoke(str.substring(1))
        }
    }
}
