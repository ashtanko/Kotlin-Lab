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

/**
 * 688. Knight Probability in Chessboard
 * @see <a href="https://leetcode.com/problems/knight-probability-in-chessboard/">Source</a>
 */
fun interface KnightProbability {
    operator fun invoke(n: Int, k: Int, row: Int, column: Int): Double
}

/**
 * Approach #1: Dynamic Programming
 */
class KnightProbabilityDP : KnightProbability {
    override operator fun invoke(n: Int, k: Int, row: Int, column: Int): Double {
        var dp = Array(n) { DoubleArray(n) }
        val dr = intArrayOf(2, 2, 1, 1, -1, -1, -2, -2)
        val dc = intArrayOf(1, -1, 2, -2, 2, -2, 1, -1)
        var k0 = k

        dp[row][column] = 1.0
        while (k0 > 0) {
            val dp2 = Array(n) { DoubleArray(n) }
            for (r in 0 until n) {
                for (c in 0 until n) {
                    for (j in 0..7) {
                        val cr = r + dr[j]
                        val cc = c + dc[j]
                        if (cr in 0 until n && 0 <= cc && cc < n) {
                            dp2[cr][cc] += dp[r][c] / 8.0
                        }
                    }
                }
            }
            dp = dp2
            k0--
        }
        var ans = 0.0
        for (row0 in dp) {
            for (x in row0) {
                ans += x
            }
        }
        return ans
    }
}

/**
 * Approach #2: Matrix Exponentiation
 */
class KnightProbabilityMatrixExpo : KnightProbability {
    override operator fun invoke(n: Int, k: Int, row: Int, column: Int): Double {
        val dr = intArrayOf(-1, -1, 1, 1, -2, -2, 2, 2)
        val dc = intArrayOf(2, -2, 2, -2, 1, -1, 1, -1)

        val index = IntArray(n * n)
        var t = 0
        for (r in 0 until n) {
            for (c in 0 until n) {
                if (r * n + c == canonical(r, c, n)) {
                    index[r * n + c] = t
                    t++
                } else {
                    index[r * n + c] = index[canonical(r, c, n)]
                }
            }
        }

        val tArr = Array(t) { DoubleArray(t) }
        var curRow = 0
        for (r in 0 until n) {
            for (c in 0 until n) {
                if (r * n + c == canonical(r, c, n)) {
                    for (j in 0..7) {
                        val cr = r + dr[j]
                        val cc = c + dc[j]
                        if (cr in 0 until n && 0 <= cc && cc < n) {
                            tArr[curRow][index[canonical(cr, cc, n)]] += FRACTION
                        }
                    }
                    curRow++
                }
            }
        }

        val row0 = matrixExpo(tArr, k)[index[row * n + column]]
        var ans = 0.0
        for (x in row0) {
            ans += x
        }
        return ans
    }

    private fun canonical(r: Int, c: Int, n: Int): Int {
        var r0 = r
        var c0 = c
        if (2 * r0 > n) {
            r0 = n - 1 - r0
        }
        if (2 * c0 > n) {
            c0 = n - 1 - c0
        }
        if (r0 > c0) {
            val t = r0
            r0 = c0
            c0 = t
        }
        return r0 * n + c0
    }

    private fun matrixMult(a: Array<DoubleArray>, b: Array<DoubleArray>): Array<DoubleArray> {
        val ans = Array(a.size) { DoubleArray(a.size) }
        for (i in a.indices) {
            for (j in b.first().indices) {
                for (k in b.indices) {
                    ans[i][j] += a[i][k] * b[k][j]
                }
            }
        }
        return ans
    }

    private fun matrixExpo(a: Array<DoubleArray>, pow: Int): Array<DoubleArray> {
        val ans = Array(a.size) { DoubleArray(a.size) }
        for (i in a.indices) {
            ans[i][i] = 1.0
        }
        if (pow == 0) return ans
        if (pow == 1) return a
        if (pow % 2 == 1) {
            return matrixMult(matrixExpo(a, pow - 1), a)
        }
        val b = matrixExpo(a, pow / 2)
        return matrixMult(b, b)
    }

    companion object {
        private const val FRACTION = 0.125
    }
}
