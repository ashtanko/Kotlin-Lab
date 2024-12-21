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
import kotlin.math.min

/**
 * 514. Freedom Trail
 * @see <a href="https://leetcode.com/problems/freedom-trail/">Source</a>
 */
fun interface FreedomTrail {
    operator fun invoke(ring: String, key: String): Int
}

class FreedomTrailDP : FreedomTrail {
    override fun invoke(ring: String, key: String): Int {
        if (ring.isEmpty()) return 0
        val n: Int = ring.length
        val m: Int = key.length
        val dp = Array(m + 1) { IntArray(n) }
        val clock = preprocess(ring, 1)
        val anti = preprocess(ring, -1)
        for (i in m - 1 downTo 0) {
            val idx: Int = key[i] - 'a'
            for (j in 0 until n) { // fill dp[i][j]
                val p = clock[j][idx]
                val q = anti[j][idx]
                dp[i][j] = min(dp[i + 1][p] + (j + n - p) % n, dp[i + 1][q] + (q + n - j) % n)
            }
        }
        return dp[0][0] + m
    }

    private fun preprocess(r: String, inc: Int): Array<IntArray> {
        val n = r.length
        val ans = Array(n) { IntArray(ALPHABET_LETTERS_COUNT) }
        val map = IntArray(ALPHABET_LETTERS_COUNT)
        var i = 0
        var j = 0
        while (j < n * 2 - 1) {
            map[r[i] - 'a'] = i
            System.arraycopy(map, 0, ans[i], 0, ALPHABET_LETTERS_COUNT)
            i = (i + inc + n) % n
            ++j
        }
        return ans
    }
}
