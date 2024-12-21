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

import dev.shtanko.algorithms.E_9
import dev.shtanko.algorithms.MOD
import java.util.Arrays

/**
 * 935. Knight Dialer
 * @see <a href="https://leetcode.com/problems/knight-dialer/">Source</a>
 */
fun interface KnightDialer {
    operator fun invoke(n: Int): Int
}

/**
 * Naive Recursive Code
 */
class KnightDialerBottomUp : KnightDialer {

    override operator fun invoke(n: Int): Int {
        val dirs = arrayOf(
            intArrayOf(4, 6),
            intArrayOf(6, 8),
            intArrayOf(7, 9),
            intArrayOf(4, 8),
            intArrayOf(0, 3, 9),
            intArrayOf(),
            intArrayOf(0, 1, 7),
            intArrayOf(2, 6),
            intArrayOf(1, 3),
            intArrayOf(2, 4),
        )
        val mod = E_9.toInt() + 7
        val dp = Array(n) { IntArray(10) }
        Arrays.fill(dp[0], 1)
        var res = 0

        for (i in 1 until n) {
            for (j in 0..9) {
                for (next in dirs[j]) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][next]) % mod
                }
            }
        }

        for (i in 0..9) {
            res = (res + dp[n - 1][i]) % mod
        }

        return res
    }
}

/**
 * Top down Dynamic programming solution
 */
class KnightDialerDP : KnightDialer {
    override operator fun invoke(n: Int): Int {
        if (n == 1) return 10
        val jumpMap = arrayOf(
            intArrayOf(4, 6),
            intArrayOf(6, 8),
            intArrayOf(7, 9),
            intArrayOf(4, 8),
            intArrayOf(3, 9, 0),
            intArrayOf(),
            intArrayOf(1, 7, 0),
            intArrayOf(2, 6),
            intArrayOf(1, 3),
            intArrayOf(2, 4),
        )
        var dp = IntArray(10) { 1 }
        for (n0 in n downTo 2) {
            val temp = IntArray(10)
            for (i in jumpMap.indices) {
                for (j in jumpMap[i].indices) {
                    val position = jumpMap[i][j] // jump from number i, to position
                    temp[position] = (temp[position] + dp[i] % MOD) % MOD
                }
            }
            dp = temp
        }
        var ans = 0
        for (num in dp) ans = (ans + num % MOD) % MOD
        return ans
    }
}
