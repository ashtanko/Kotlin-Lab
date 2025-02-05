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
 * 1397. Find All Good Strings
 * @see <a href="https://leetcode.com/problems/find-all-good-strings/">Source</a>
 */
fun interface FindGoodStrings {
    operator fun invoke(num: Int, s1: String, s2: String, evil: String): Int
}

class FindGoodStringsDFS : FindGoodStrings {

    data class DfsParams(
        val i: Int,
        val evilMatched: Int,
        val leftBound: Boolean,
        val rightBound: Boolean,
        val n: Int,
        val s1: CharArray,
        val s2: CharArray,
        val evil: CharArray,
        val lps: IntArray,
        val dp: IntArray,
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as DfsParams

            if (i != other.i) return false
            if (evilMatched != other.evilMatched) return false
            if (leftBound != other.leftBound) return false
            if (rightBound != other.rightBound) return false
            if (n != other.n) return false
            if (!s1.contentEquals(other.s1)) return false
            if (!s2.contentEquals(other.s2)) return false
            if (!evil.contentEquals(other.evil)) return false
            if (!lps.contentEquals(other.lps)) return false
            if (!dp.contentEquals(other.dp)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = i
            result = 31 * result + evilMatched
            result = 31 * result + leftBound.hashCode()
            result = 31 * result + rightBound.hashCode()
            result = 31 * result + n
            result = 31 * result + s1.contentHashCode()
            result = 31 * result + s2.contentHashCode()
            result = 31 * result + evil.contentHashCode()
            result = 31 * result + lps.contentHashCode()
            result = 31 * result + dp.contentHashCode()
            return result
        }
    }

    override operator fun invoke(num: Int, s1: String, s2: String, evil: String): Int {
        val dp = IntArray(1 shl BITS)
        return dfs(
            DfsParams(
                0,
                0,
                true,
                true,
                num,
                s1.toCharArray(),
                s2.toCharArray(),
                evil.toCharArray(),
                computeLPS(evil.toCharArray()),
                dp,
            ),
        )
    }

    private fun dfs(params: DfsParams): Int {
        with(params) {
            if (evilMatched == evil.size) return 0
            if (i == n) return 1
            val key = getKey(i, evilMatched, leftBound, rightBound)
            if (dp[key] != 0) return dp[key]
            val from = if (leftBound) s1[i] else 'a'
            val to = if (rightBound) s2[i] else 'z'
            var res = 0
            var c = from
            while (c <= to) {
                val nextMatch = computeNextMatch(evil, c, lps, evilMatched)
                res += dfs(
                    DfsParams(
                        i + 1,
                        nextMatch,
                        leftBound && c == from,
                        rightBound && c == to,
                        n,
                        s1,
                        s2,
                        evil,
                        lps,
                        dp,
                    ),
                )
                res %= MOD
                c++
            }
            return res.also { dp[key] = it }
        }
    }

    private fun computeNextMatch(evil: CharArray, c: Char, lps: IntArray, evilMatched: Int): Int {
        var j = evilMatched
        while (j > 0 && evil[j] != c) {
            j = lps[j - 1]
        }
        return if (c == evil[j]) j + 1 else 0
    }

    private fun getKey(n: Int, m: Int, b1: Boolean, b2: Boolean): Int {
        return n shl 8 or (m shl 2) or ((if (b1) 1 else 0) shl 1) or if (b2) 1 else 0
    }

    private fun computeLPS(str: CharArray): IntArray {
        val n = str.size
        val lps = IntArray(n)
        var i = 1
        var j = 0
        while (i < n) {
            j = updateJ(str, i, j, lps)
            i++
        }
        return lps
    }

    private fun updateJ(str: CharArray, i: Int, j: Int, lps: IntArray): Int {
        var updatedJ = j
        while (updatedJ > 0 && str[i] != str[updatedJ]) {
            updatedJ = lps[updatedJ - 1]
        }
        if (str[i] == str[updatedJ]) {
            lps[i] = ++updatedJ
        }
        return updatedJ
    }

    companion object {
        private const val BITS = 17
    }
}
