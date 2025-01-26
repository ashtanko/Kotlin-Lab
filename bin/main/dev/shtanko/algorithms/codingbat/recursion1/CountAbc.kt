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
 * Recursion-1 > countAbc
 * @see <a href="https://codingbat.com/prob/p161124">Source</a>
 */
fun interface CountAbc {
    operator fun invoke(str: String): Int
}

class CountAbcIterative : CountAbc {
    override fun invoke(str: String): Int {
        var count = 0
        for (i in 0 until str.length - 2) {
            val substring = "${str[i]}${str[i + 1]}${str[i + 2]}"
            if (substring == "abc" || substring == "aba") {
                count++
            }
        }
        return count
    }
}

class CountAbcRecursive : CountAbc {
    override fun invoke(str: String): Int {
        if (str.length < 3) {
            return 0
        }
        val substring = "${str[0]}${str[1]}${str[2]}"
        return if (substring == "abc" || substring == "aba") {
            1 + invoke(str.substring(1))
        } else {
            invoke(str.substring(1))
        }
    }
}
