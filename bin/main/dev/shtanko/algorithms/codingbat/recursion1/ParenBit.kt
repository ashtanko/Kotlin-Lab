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
 * Recursion-1 > parenBit
 * @see <a href="https://codingbat.com/prob/p137918">Source</a>
 */
fun interface ParenBit {
    operator fun invoke(str: String): String
}

class ParenBitIterative : ParenBit {
    override fun invoke(str: String): String {
        var index = 0
        while (index < str.length) {
            if (str[index] == '(') {
                val closingIndex = str.indexOf(')', index)
                if (closingIndex != -1) {
                    return str.substring(index, closingIndex + 1)
                }
            }
            index++
        }
        return ""
    }
}

class ParenBitRecursive : ParenBit {
    override fun invoke(str: String): String {
        if (str.isEmpty()) {
            return ""
        }

        if (str[0] == '(') {
            val closingIndex = str.indexOf(')')
            if (closingIndex != -1) {
                return str.substring(0, closingIndex + 1)
            }
        }
        return invoke(str.substring(1))
    }
}
