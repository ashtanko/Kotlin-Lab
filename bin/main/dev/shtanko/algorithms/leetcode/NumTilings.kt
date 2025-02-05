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

import dev.shtanko.algorithms.MOD

/**
 * 790. Domino and Tromino Tiling
 * @see <a href="https://leetcode.com/problems/domino-and-tromino-tiling/">Source</a>
 */
fun interface NumTilings {
    operator fun invoke(n: Int): Int
}

class NumTilingsDP : NumTilings {
    override operator fun invoke(n: Int): Int {
        val dp = LongArray(n + 2)
        dp[0] = 1; dp[1] = 2
        val dpa = LongArray(n + 2)
        dpa[1] = 1
        for (i in 2 until n) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dpa[i - 1] * 2) % MOD
            dpa[i] = (dp[i - 2] + dpa[i - 1]) % MOD
        }
        return dp[n - 1].toInt()
    }
}
