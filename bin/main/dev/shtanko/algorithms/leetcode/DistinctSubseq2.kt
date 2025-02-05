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
 * 940. Distinct Subsequences II
 * @see <a href="https://leetcode.com/problems/distinct-subsequences-ii/">Source</a>
 */
fun interface DistinctSubseq2 {
    operator fun invoke(str: String): Int
}

/**
 * Approach 1: Dynamic Programming
 */
class DistinctSubseq2DP : DistinctSubseq2 {
    override fun invoke(str: String): Int {
        val n: Int = str.length
        val dp = IntArray(n + 1)
        dp[0] = 1

        val last = IntArray(ALPHABET_LETTERS_COUNT) { -1 }

        for (i in 0 until n) {
            val x: Int = str[i] - 'a'
            dp[i + 1] = dp[i] * 2 % MOD
            if (last[x] >= 0) dp[i + 1] -= dp[last[x]]
            dp[i + 1] %= MOD
            last[x] = i
        }

        dp[n]--
        if (dp[n] < 0) dp[n] += MOD
        return dp[n]
    }
}

class DistinctSubseq2DPConstSpace : DistinctSubseq2 {
    override fun invoke(str: String): Int {
        var pre = 1 // The number of subsequences till previous-location. Include empty string: ""

        var cur = 1 // The number of subsequences till now. Include empty string: ""

        // The number of subsequences that end with a character till now. Not include empty string: ""
        val lastCount = IntArray(ALPHABET_LETTERS_COUNT)

        for (element in str) {
            val charIndex: Int = element - 'a'
            cur = pre * 2 % MOD // include-current-character  +  not-include-current-character
            // Remove duplicated characters: previous subsequences that end with current character.
            cur -= lastCount[charIndex]
            cur = if (cur >= 0) cur % MOD else cur + MOD
            lastCount[charIndex] = pre // The number of subsequences that end with current character.
            pre = cur
        }
        --cur // remove the empty string: ""

        return cur
    }
}
