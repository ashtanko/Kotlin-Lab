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
 * Recursion-1 > count11
 * @see <a href="https://codingbat.com/prob/p167015">Source</a>
 */
fun interface Count11 {
    operator fun invoke(str: String): Int
}

class Count11Iterative : Count11 {
    override fun invoke(str: String): Int {
        var count = 0
        var i = 0
        while (i < str.length - 1) {
            if (str[i] == '1' && str[i + 1] == '1') {
                count++
                i += 2
            } else {
                i++
            }
        }
        return count
    }
}

class Count11Recursive : Count11 {
    override fun invoke(str: String): Int {
        if (str.length < 2) {
            return 0
        }
        return if (str[0] == '1' && str[1] == '1') {
            1 + invoke(str.substring(2))
        } else {
            invoke(str.substring(1))
        }
    }
}
