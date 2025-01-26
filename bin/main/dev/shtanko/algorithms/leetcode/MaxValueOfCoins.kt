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
 * 2218. Maximum Value of K Coins From Piles
 * @see <a href="https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/">Source</a>
 */
fun interface MaxValueOfCoins {
    operator fun invoke(piles: List<List<Int>>, k: Int): Int
}

class MaxValueOfCoinsTopDown : MaxValueOfCoins {
    override operator fun invoke(piles: List<List<Int>>, k: Int): Int {
        val memo = Array(piles.size + 1) {
            arrayOfNulls<Int>(k + 1)
        }
        return dp(piles, memo, 0, k)
    }

    private fun dp(piles: List<List<Int>>, memo: Array<Array<Int?>>, i: Int, k: Int): Int {
        if (k == 0 || i == piles.size) return 0
        if (memo[i][k] != null) return memo[i][k]!!
        var res = dp(piles, memo, i + 1, k)
        var cur = 0
        for (j in 0 until min(piles[i].size, k)) {
            cur += piles[i][j]
            res = max(res, cur + dp(piles, memo, i + 1, k - j - 1))
        }
        return res.also { memo[i][k] = it }
    }
}
