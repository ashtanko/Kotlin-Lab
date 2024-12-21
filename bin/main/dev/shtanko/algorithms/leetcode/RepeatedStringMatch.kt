/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.algorithms.leetcode

/**
 * 686. Repeated String Match
 * @see <a href="https://leetcode.com/problems/repeated-string-match">Source</a>
 */
fun interface RepeatedStringMatch {
    operator fun invoke(a: String, b: String): Int
}

class RepeatedStringMatchSB : RepeatedStringMatch {
    override operator fun invoke(a: String, b: String): Int {
        val sb = StringBuilder().apply {
            append(a)
        }
        var count = 1
        while (sb.indexOf(b) == -1) {
            if (sb.length - a.length > b.length) {
                return -1
            }
            sb.append(a)
            count++
        }
        return count
    }
}
