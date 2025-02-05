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
 * Recursion-1 > countPairs
 * @see <a href="https://codingbat.com/prob/p105722">Source</a>
 */
internal fun interface CountPairs {
    operator fun invoke(str: String): Int
}

class CountPairsIterative : CountPairs {
    override fun invoke(str: String): Int {
        var pairCount = 0
        for (i in 0 until str.length - 2) {
            if (str[i] == str[i + 2]) {
                pairCount++
            }
        }
        return pairCount
    }
}

class CountPairsRecursive : CountPairs {
    override fun invoke(str: String): Int {
        if (str.length < 3) {
            return 0
        }
        return if (str[0] == str[2]) {
            1 + invoke(str.substring(1))
        } else {
            invoke(str.substring(1))
        }
    }
}
