/*
 * Copyright 2021 Oleksii Shtanko
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

import kotlin.math.min

fun interface EditDistance {
    operator fun invoke(word1: String, word2: String): Int
}

class EditDistanceDP : EditDistance {
    override operator fun invoke(word1: String, word2: String): Int {
        val n: Int = word1.length
        val m: Int = word2.length

        // if one of the strings is empty
        if (n * m == 0) return n + m

        // array to store the convertion history
        val d = Array(n + 1) { IntArray(m + 1) }

        // init boundaries
        for (i in 0 until n + 1) {
            d[i][0] = i
        }
        for (j in 0 until m + 1) {
            d[0][j] = j
        }

        // DP compute
        for (i in 1 until n + 1) {
            for (j in 1 until m + 1) {
                val left = d[i - 1][j] + 1
                val down = d[i][j - 1] + 1
                var leftDown = d[i - 1][j - 1]
                if (word1[i - 1] != word2[j - 1]) leftDown += 1
                d[i][j] = min(left, min(down, leftDown))
            }
        }
        return d[n][m]
    }
}
