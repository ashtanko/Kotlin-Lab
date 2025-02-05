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
 * Recursion-1 > strCopies
 * @see <a href="https://codingbat.com/prob/p118182">Source</a>
 */
fun interface StrCopies {
    operator fun invoke(str: String, sub: String, times: Int): Boolean
}

class StrCopiesIterative : StrCopies {
    override fun invoke(str: String, sub: String, times: Int): Boolean {
        var remainingStr = str
        var remainingTimes = times

        while (remainingTimes >= 0 && remainingStr.isNotEmpty() && remainingStr.length >= sub.length) {
            if (remainingStr.startsWith(sub)) {
                remainingStr = remainingStr.substring(1)
                remainingTimes--
            } else {
                remainingStr = remainingStr.substring(1)
            }
        }

        return remainingTimes == 0
    }
}

class StrCopiesRecursive : StrCopies {
    override fun invoke(str: String, sub: String, times: Int): Boolean {
        if (str.isEmpty() || str.length < sub.length) {
            return times == 0
        }
        return if (str.startsWith(sub)) {
            invoke(str.substring(1), sub, times - 1) || invoke(str.substring(1), sub, times)
        } else {
            invoke(str.substring(1), sub, times)
        }
    }
}
