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
 * 87. Scramble String
 * @see <a href="https://leetcode.com/problems/scramble-string/">Source</a>
 */
fun interface ScrambleString {
    fun isScramble(s1: String, s2: String): Boolean
}

class ScrambleStringDP : ScrambleString {
    override fun isScramble(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false
        val len: Int = s1.length
        /**
         * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
         * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
         * Let q be the length of a cut (hence, q < k), then we are in the following situation:
         *
         * S1 [   x1    |         x2         ]
         *    i + q                i + k - 1
         *
         * here we have two possibilities:
         *
         * S2 [   y1    |         y2         ]
         *    j + q                j + k - 1
         *
         * or
         *
         * S2 [       y1        |     y2     ]
         *    j                 j + k - q    j + k - 1
         *
         * which in terms of F means:
         *
         * F(i, j, k) = for some 1 <= q < k we have:
         *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
         *
         * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal
         * */
        /**
         * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
         * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
         * Let q be the length of a cut (hence, q < k), then we are in the following situation:
         *
         * S1 [   x1    |         x2         ]
         * i + q                i + k - 1
         *
         * here we have two possibilities:
         *
         * S2 [   y1    |         y2         ]
         * j + q                j + k - 1
         *
         * or
         *
         * S2 [       y1        |     y2     ]
         * j                 j + k - q    j + k - 1
         *
         * which in terms of F means:
         *
         * F(i, j, k) = for some 1 <= q < k we have:
         * (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
         *
         * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal
         */
        val f = Array(len) {
            Array(len) {
                BooleanArray(
                    len + 1,
                )
            }
        }
        for (k in 1..len) {
            var i = 0
            while (i + k <= len) {
                var j = 0
                while (j + k <= len) {
                    if (k == 1) {
                        f[i][j][k] = s1[i] == s2[j]
                    } else {
                        var q = 1
                        while (q < k && !f[i][j][k]) {
                            f[i][j][k] =
                                f[i][j][q] && f[i + q][j + q][k - q] || f[i][j + k - q][q] && f[i + q][j][k - q]
                            ++q
                        }
                    }
                    ++j
                }
                ++i
            }
        }
        return f[0][0][len]
    }
}
