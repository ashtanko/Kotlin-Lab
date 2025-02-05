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
 * Recursion-1 > countHi
 * @see <a href="https://codingbat.com/prob/p184029">Source</a>
 */
fun interface CountHi {
    operator fun invoke(str: String): Int
}

class CountHiRecursive : CountHi {
    override fun invoke(str: String): Int {
        if (str.length <= 1) {
            return 0
        }
        val size = str.length
        val lastChar = str[size - 1].lowercase()
        val prevLastChar = str[size - 2].lowercase()
        val res = if (lastChar == "i" && prevLastChar == "h") {
            1
        } else {
            0
        }
        return res + invoke(str.substring(0, size - 1))
    }
}

class CountHiIterative : CountHi {
    override fun invoke(str: String): Int {
        var res = 0
        var i = 0
        while (i < str.length) {
            if (i + 1 < str.length && str.substring(i, i + 2).lowercase() == "hi") {
                res++
                i += 2
            } else {
                i++
            }
        }
        return res
    }
}

class CountHiRecursiveSimplified : CountHi {
    override fun invoke(str: String): Int {
        if (str.length < 2) {
            return 0
        }
        return if (str.substring(0, 2).lowercase() == "hi") {
            1 + invoke(str.substring(2))
        } else {
            invoke(str.substring(1))
        }
    }
}

class CountHiMemo : CountHi {
    private val memo = mutableMapOf<String, Int>()

    override fun invoke(str: String): Int {
        if (memo.containsKey(str)) {
            return memo.getOrDefault(str, 0)
        }

        if (str.length < 2) {
            memo[str] = 0
            return 0
        }

        val count = if (str.substring(0, 2).lowercase() == "hi") {
            1 + invoke(str.substring(2))
        } else {
            invoke(str.substring(1))
        }
        memo[str] = count
        return count
    }
}
