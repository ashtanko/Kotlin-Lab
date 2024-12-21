/*
 * Copyright 2023 Oleksii Shtanko
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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * 91. Decode Ways
 * @see <a href="https://leetcode.com/problems/decode-ways">Source</a>
 */
fun interface DecodeWays {
    operator fun invoke(str: String): Int
}

class DecodeWaysDP : DecodeWays {
    override fun invoke(str: String): Int {
        if (str.isEmpty()) {
            return 0
        }
        val n: Int = str.length
        val dp = IntArray(n + 1)
        dp[0] = 1
        dp[1] = if (str.first() != '0') 1 else 0
        for (i in 2..n) {
            val first: Int = Integer.valueOf(str.substring(i - 1, i))
            val second: Int = Integer.valueOf(str.substring(i - 2, i))
            if (first in 1..9) {
                dp[i] += dp[i - 1]
            }
            if (second in 10..ALPHABET_LETTERS_COUNT) {
                dp[i] += dp[i - 2]
            }
        }
        return dp[n]
    }
}
