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
 * Recursion-1 > allStar
 * @see <a href="https://codingbat.com/prob/p183394">Source</a>
 */
fun interface AllStar {
    operator fun invoke(str: String): String
}

class AllStarIterative : AllStar {
    override fun invoke(str: String): String {
        val sb = StringBuilder()
        for (i in str.indices) {
            sb.append(str[i])
            if (i != str.length - 1) {
                sb.append("*")
            }
        }
        return sb.toString()
    }
}

class AllStarMap : AllStar {
    override fun invoke(str: String): String {
        return str.mapIndexed { index, char ->
            if (index < str.length - 1) "$char*" else char.toString()
        }.joinToString("")
    }
}

class AllStarRecursive : AllStar {
    override fun invoke(str: String): String {
        if (str.isEmpty() || str.length == 1) {
            return str
        }
        return str[0] + "*" + invoke(str.substring(1))
    }
}
