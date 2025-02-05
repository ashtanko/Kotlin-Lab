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
 * Recursion-1 > strDist
 * @see <a href="https://codingbat.com/prob/p195413">Source</a>
 */
fun interface StrDist {
    operator fun invoke(str: String, sub: String): Int
}

class StrDistRecursive : StrDist {
    override fun invoke(str: String, sub: String): Int {
        if (str.length < sub.length || !str.contains(sub)) {
            return 0
        }
        return if (str.startsWith(sub)) {
            if (str.endsWith(sub)) {
                str.length
            } else {
                invoke(str.substring(0, str.length - 1), sub)
            }
        } else {
            invoke(str.substring(1), sub)
        }
    }
}
