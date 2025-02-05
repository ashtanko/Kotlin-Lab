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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import dev.shtanko.algorithms.MOD

/**
 * 1639. Number of Ways to Form a Target String Given a Dictionary
 * @see <a href="https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary">
 *     Source</a>
 */
fun interface NumWays {
    operator fun invoke(words: Array<String>, target: String): Int
}

class NumWaysDP : NumWays {

    override operator fun invoke(words: Array<String>, target: String): Int {
        val wLen: Int = words[0].length
        val tLen: Int = target.length
        val dp = Array(tLen) { LongArray(wLen) }
        val freq = Array(wLen) { IntArray(ALPHABET_LETTERS_COUNT) }
        for (w in words) {
            for (i in w.indices) {
                freq[i][w[i].code - 'a'.code]++
            }
        }
        dp[0][0] = freq[0][target[0] - 'a'].toLong()
        for (j in 1 until wLen) {
            dp[0][j] = dp[0][j - 1] + freq[j][target[0] - 'a']
        }
        for (i in 1 until tLen) {
            for (j in i until wLen) {
                val value = freq[j][target[i] - 'a']
                dp[i][j] = (dp[i - 1][j - 1] * value + dp[i][j - 1]) % MOD
            }
        }
        return dp[tLen - 1][wLen - 1].toInt()
    }
}
