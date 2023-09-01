/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

/**
 * 1411. Number of Ways to Paint N × 3 Grid
 * @see <a href="https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/description/">leetcode page</a>
 */
interface NumOfWaysGrid {
    operator fun invoke(n: Int): Int
}

class NumOfWaysGridDP : NumOfWaysGrid {
    override operator fun invoke(n: Int): Int {
        val dp = Array(2) { LongArray(2) { 6 } }
        for (i in 1 until n) {
            dp[i % 2][0] = (dp[(i - 1) % 2][0] * 3 + dp[(i - 1) % 2][1] * 2) % MOD
            dp[i % 2][1] = (dp[(i - 1) % 2][0] * 2 + dp[(i - 1) % 2][1] * 2) % MOD
        }
        return ((dp[(n - 1) % 2][0] + dp[(n - 1) % 2][1]) % MOD).toInt()
    }
}
