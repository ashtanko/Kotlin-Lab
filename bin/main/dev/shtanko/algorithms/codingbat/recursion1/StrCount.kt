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
 * Recursion-1 > strCount
 * @see <a href="https://codingbat.com/prob/p186177">Source</a>
 */
fun interface StrCount {
    operator fun invoke(str: String, sub: String): Int
}

class StrCountIterative : StrCount {
    override fun invoke(str: String, sub: String): Int {
        if (str.isEmpty() || str.length < sub.length) {
            return 0
        }
        var index = 0
        var res = 0
        while (index <= str.length - sub.length) {
            if (str.substring(index, index + sub.length) == sub) {
                res++
                index += sub.length
            } else {
                index++
            }
        }
        return res
    }
}

class StrCountRecursive : StrCount {
    override fun invoke(str: String, sub: String): Int {
        if (str.isEmpty() || str.length < sub.length) {
            return 0
        }
        return if (str.startsWith(sub)) {
            1 + invoke(str.substring(sub.length), sub)
        } else {
            invoke(str.substring(1), sub)
        }
    }
}
