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
 * Recursion-1 > countX
 * @see <a href="https://codingbat.com/prob/p170371">Source</a>
 */
fun interface CountX {
    operator fun invoke(str: String): Int
}

class CountXRecursive : CountX {
    override fun invoke(str: String): Int {
        if (str.isEmpty()) {
            return 0
        }
        val size = str.length
        val lastChar = str[size - 1]
        val res = if (lastChar == 'x') {
            1
        } else {
            0
        }
        return res + invoke(str.substring(0, size - 1))
    }
}

class CountXIterative : CountX {
    override fun invoke(str: String): Int {
        var count = 0
        str.forEach {
            if (it == 'x') {
                count++
            }
        }
        return count
    }
}

class CountXRecursiveSimplified : CountX {
    override fun invoke(str: String): Int {
        if (str.isEmpty()) {
            return 0
        }
        return if (str[0] == 'x') {
            1 + invoke(str.substring(1))
        } else {
            invoke(str.substring(1))
        }
    }
}

class CountXMemo : CountX {
    private val memo: MutableMap<String, Int> = mutableMapOf()

    override fun invoke(str: String): Int {
        if (str.isEmpty()) {
            return 0
        }
        if (memo.containsKey(str)) {
            return memo.getOrDefault(str, 0)
        }
        val result = if (str[0] == 'x') {
            1 + invoke(str.substring(1))
        } else {
            invoke(str.substring(1))
        }
        memo[str] = result
        return result
    }
}
