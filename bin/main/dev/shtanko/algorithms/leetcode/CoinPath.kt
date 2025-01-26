/*
 * Copyright 2020 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.DP
import dev.shtanko.algorithms.annotations.Memoization

/**
 * Coin Path.
 * @see <a href="https://leetcode.com/problems/coin-path/">Source</a>
 */
fun interface CoinPath {
    operator fun invoke(a: IntArray, b: Int): List<Int>
}

/**
 * Approach #2 Using Memoization.
 * Time complexity : O(nB).
 * Space complexity : O(n).
 */
@Memoization
class CoinPathMemo : CoinPath {
    override fun invoke(a: IntArray, b: Int): List<Int> {
        val next = IntArray(a.size) { -1 }
        val memo = LongArray(a.size)
        jump(a, b, 0, next, memo)
        val res: MutableList<Int> = ArrayList()
        var i = 0
        while (i < a.size && next[i] > 0) {
            res.add(i + 1)
            i = next[i]
        }

        if (i == a.size - 1 && a[i] >= 0) res.add(a.size) else return ArrayList()
        return res
    }

    private fun jump(a: IntArray, b: Int, i: Int, next: IntArray, memo: LongArray): Long {
        if (a.isEmpty()) return 0L
        if (memo[i] > 0) return memo[i]
        if (i == a.size - 1 && a[i] >= 0) return a[i].toLong()
        var minCost = Int.MAX_VALUE.toLong()
        var j = i + 1
        while (j <= i + b && j < a.size) {
            if (a[j] >= 0) {
                val cost = a[i] + jump(a, b, j, next, memo)
                if (cost < minCost) {
                    minCost = cost
                    next[i] = j
                }
            }
            j++
        }
        memo[i] = minCost
        return minCost
    }
}

/**
 * Approach #3 Using Dynamic Programming.
 * Time complexity : O(nB).
 * Space complexity : O(n).
 */
@DP
class CoinPathDP : CoinPath {
    override fun invoke(a: IntArray, b: Int): List<Int> {
        val next = IntArray(a.size) { -1 }
        val dp = LongArray(a.size)
        val res: MutableList<Int> = ArrayList()
        for (i in a.size - 2 downTo 0) {
            var minCost = Int.MAX_VALUE.toLong()
            var j: Int = i + 1
            while (j <= i + b && j < a.size) {
                if (a[j] >= 0) {
                    val cost: Long = a[i] + dp[j]
                    if (cost < minCost) {
                        minCost = cost
                        next[i] = j
                    }
                }
                j++
            }
            dp[i] = minCost
        }
        var i = 0
        while (i < a.size && next[i] > 0) {
            res.add(i + 1)
            i = next[i]
        }
        if (i == a.size - 1 && a[i] >= 0) res.add(a.size) else return ArrayList()
        return res
    }
}
