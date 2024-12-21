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

import kotlin.math.min

/**
 * 1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
 * @see <a href="https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix">
 *     Source</a>
 */
fun interface MinMatrixFlips {
    operator fun invoke(mat: Array<IntArray>): Int
}

class MinMatrixFlipsBFS : MinMatrixFlips {

    override operator fun invoke(mat: Array<IntArray>): Int {
        val n = mat.size
        val m = mat[0].size
        val dp: HashMap<String, Int> = HashMap()
        val ans = func(mat, n, m, HashSet(), dp)
        return if (ans == Int.MAX_VALUE) -1 else ans
    }

    fun check(mat: Array<IntArray>, n: Int, m: Int): Boolean {
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (mat[i][j] == 1) return false
            }
        }
        return true
    }

    private fun flip(mat: Array<IntArray>, n: Int, m: Int, i: Int, j: Int) {
        mat[i][j] = mat[i][j] xor 1
        if (i - 1 >= 0) mat[i - 1][j] = mat[i - 1][j] xor 1
        if (j - 1 >= 0) mat[i][j - 1] = mat[i][j - 1] xor 1
        if (i + 1 < n) mat[i + 1][j] = mat[i + 1][j] xor 1
        if (j + 1 < m) mat[i][j + 1] = mat[i][j + 1] xor 1
    }

    private fun func(mat: Array<IntArray>, n: Int, m: Int, set: HashSet<String>, dp: HashMap<String, Int>): Int {
        if (check(mat, n, m)) return 0
        var t = ""
        for (i in 0 until n) {
            for (j in 0 until m) {
                t += mat[i][j].toString()
            }
        }
        if (dp.containsKey(t)) return dp[t]!!
        if (set.contains(t)) return Int.MAX_VALUE
        set.add(t)
        var min = Int.MAX_VALUE
        for (i in 0 until n) {
            for (j in 0 until m) {
                flip(mat, n, m, i, j)
                val small = func(mat, n, m, set, dp)
                if (small != Int.MAX_VALUE) min = min(min, 1 + small)
                flip(mat, n, m, i, j)
            }
        }
        set.remove(t)
        dp[t] = min
        return min
    }
}
