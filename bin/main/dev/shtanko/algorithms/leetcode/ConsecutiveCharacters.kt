/*
 * Copyright 2020 Oleksii Shtanko
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

fun interface ConsecutiveCharactersStrategy {
    operator fun invoke(s: String): Int
}

class MaxPower1 : ConsecutiveCharactersStrategy {
    override operator fun invoke(s: String): Int {
        val n = s.length
        var start = 0
        var end = 0
        var max = 0
        while (end < n) {
            while (end < n && s[end] == s[start]) {
                max = max.coerceAtLeast(end - start + 1)
                end++
            }
            if (end == n) return max
            start = end
        }
        return max
    }
}

class MaxPower2 : ConsecutiveCharactersStrategy {
    override operator fun invoke(s: String): Int {
        var res = 0
        val n = s.length
        var i = 0
        while (i < n) {
            val j = i
            while (i + 1 < n && s[i] == s[i + 1]) {
                i++
            }
            res = kotlin.math.max(i - j + 1, res)
            i++
        }
        return res
    }
}
