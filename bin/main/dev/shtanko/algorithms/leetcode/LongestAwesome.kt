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

import kotlin.math.max
import kotlin.math.min

/**
 * 1542. Find Longest Awesome Substring
 * @see <a href="https://leetcode.com/problems/find-longest-awesome-substring/">Source</a>
 */
fun interface LongestAwesome {
    operator fun invoke(s: String): Int
}

class LongestAwesomeImpl : LongestAwesome {
    override operator fun invoke(s: String): Int {
        val dp = IntArray(LIMIT) { s.length }
        var res = 0
        var mask = 0
        dp[0] = -1
        for (i in s.indices) {
            val ch = s[i]
            mask = mask.xor(1 shl ch - '0')
            res = max(res, i - dp[mask])
            for (j in 0..9) {
                res = max(res, i - dp[mask xor (1 shl j)])
            }
            dp[mask] = min(dp[mask], i)
        }
        return res
    }

    companion object {
        private const val LIMIT = 1024
    }
}
