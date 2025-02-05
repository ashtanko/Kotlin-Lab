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
 * Recursion-1 > pairStar
 * @see <a href="https://codingbat.com/prob/p158175">Source</a>
 */
fun interface PairStar {
    operator fun invoke(str: String): String
}

class PairStarIterative : PairStar {
    override fun invoke(str: String): String {
        if (str.length < 2) {
            return str
        }
        val sb = StringBuilder()
        for (i in 0 until str.length - 1) {
            if (str[i] == str[i + 1]) {
                sb.append(str[i]).append("*")
            } else {
                sb.append(str[i])
            }
        }
        if (str.isNotEmpty()) {
            sb.append(str.last())
        }
        return sb.toString()
    }
}

class PairStarIterative2 : PairStar {
    override fun invoke(str: String): String {
        return str.windowed(2, 1, true) { pair ->
            if (pair.length == 2 && pair[0] == pair[1]) "${pair[0]}*" else pair[0].toString()
        }.joinToString("")
    }
}

class PairStarRecursive : PairStar {
    override fun invoke(str: String): String {
        if (str.length < 2) {
            return str
        }
        if (str[0] == str[1]) {
            return str[0] + "*" + invoke(str.substring(1))
        }
        return str[0] + invoke(str.substring(1))
    }
}
