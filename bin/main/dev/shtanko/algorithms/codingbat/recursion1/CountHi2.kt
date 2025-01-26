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
 * Recursion-1 > countHi2
 * @see <a href="https://codingbat.com/prob/p143900">Source</a>
 */
fun interface CountHi2 {
    operator fun invoke(str: String): Int
}

class CountHi2Iterative : CountHi2 {
    override fun invoke(str: String): Int {
        var res = 0
        var index = 0
        if (str.isEmpty() || str.length <= 1) {
            return 0
        }
        while (index <= str.length - 1) {
            if (str[index] == 'h' && str[index + 1] == 'i' && (index == 0 || str[index - 1] != 'x')) {
                res++
                index += 1
            }
            index++
        }
        return res
    }
}

class CountHi2Recursive : CountHi2 {
    override fun invoke(str: String): Int {
        return countHi2Helper(str, 0)
    }

    private fun countHi2Helper(str: String, index: Int): Int {
        if (index >= str.length - 1) {
            return 0
        }

        if (str[index] == 'h' && str[index + 1] == 'i' && (index == 0 || str[index - 1] != 'x')) {
            return 1 + countHi2Helper(str, index + 2)
        }

        return countHi2Helper(str, index + 1)
    }
}
