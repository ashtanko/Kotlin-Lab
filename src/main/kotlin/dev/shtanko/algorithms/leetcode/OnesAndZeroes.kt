/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.extensions.countZeroesOnes
import kotlin.math.max

/**
 * Ones and Zeroes
 * @see <a href="https://leetcode.com/problems/ones-and-zeroes">Source</a>
 */
fun interface OnesAndZeroes {
    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int
}

/**
 * Approach #1 Brute Force [Time Limit Exceeded]
 * Time complexity : O(2 ^ l * x). 2^l possible subsets,
 * where l is the length of the list strs and x is the average string length.
 * Space complexity : O(1). Constant Space required.
 */
class OnesAndZeroesBF : OnesAndZeroes {
    override fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        var maxlen = 0
        for (i in 0 until (1 shl strs.size)) {
            var zeroes = 0
            var ones = 0
            var len = 0
            for (j in strs.indices) {
                if (i and (1 shl j) != 0) {
                    val count: IntArray = strs[j].countZeroesOnes()
                    zeroes += count[0]
                    ones += count[1]
                    len++
                }
            }
            if (zeroes <= m && ones <= n) maxlen = max(maxlen, len)
        }
        return maxlen
    }
}

/**
 * Approach #2 Better Brute Force [Time Limit Exceeded]
 * Time complexity : O(2 ^ l * x). 2^l possible subsets,
 * where l is the length of the list strs and x is the average string length.
 * Space complexity : O(1). Constant Space required.
 */
class OnesAndZeroesBetterBF : OnesAndZeroes {
    override fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        var maxlen = 0
        for (i in 0 until (1 shl strs.size)) {
            var zeroes = 0
            var ones = 0
            var len = 0
            for (j in 0 until INT_32_BITS_COUNT) {
                if (i and (1 shl j) != 0) {
                    val count: IntArray = strs[j].countZeroesOnes()
                    zeroes += count[0]
                    ones += count[1]
                    if (zeroes > m || ones > n) break
                    len++
                }
            }
            if (zeroes <= m && ones <= n) maxlen = max(maxlen, len)
        }
        return maxlen
    }

    companion object {
        private const val INT_32_BITS_COUNT = 32
    }
}

/**
 * Approach #3 Using Recursion [Time Limit Exceeded]
 * Time complexity : O(2 ^ l * x). 2^l possible subsets,
 * where l is the length of the list strs and x is the average string length.
 * Space complexity : O(l). Depth of recursion tree grows up to l.
 */
class OnesAndZeroesRecursion : OnesAndZeroes {
    override fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        return calculate(strs, 0, m, n)
    }

    fun calculate(strs: Array<String>, i: Int, zeroes: Int, ones: Int): Int {
        if (i == strs.size) return 0
        val count: IntArray = strs[i].countZeroesOnes()
        var taken = -1
        if (zeroes - count[0] >= 0 && ones - count[1] >= 0) {
            taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1]) + 1
        }
        val notTaken = calculate(strs, i + 1, zeroes, ones)
        return max(taken, notTaken)
    }
}

/**
 * Approach #4 Using Memoization
 * Time complexity : O(l*m*n). memo array of size l*m*n is filled, where ll is the length of strs,
 * mm and nn are the number of zeroes and ones respectively.
 * Space complexity : O(l*m*n). 3D array memo is used.
 */
class OnesAndZeroesMemoization : OnesAndZeroes {
    override fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        val memo: Array<Array<IntArray>> = Array(strs.size) {
            Array(m + 1) {
                IntArray(n + 1)
            }
        }
        return calculate(strs, 0, m, n, memo)
    }

    private fun calculate(strs: Array<String>, i: Int, zeroes: Int, ones: Int, memo: Array<Array<IntArray>>): Int {
        if (i == strs.size) {
            return 0
        }
        if (memo[i][zeroes][ones] != 0) {
            return memo[i][zeroes][ones]
        }
        val count: IntArray = strs[i].countZeroesOnes()
        var taken = -1
        if (zeroes - count[0] >= 0 && ones - count[1] >= 0) {
            taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1], memo) + 1
        }
        val notTaken = calculate(strs, i + 1, zeroes, ones, memo)
        memo[i][zeroes][ones] = max(taken, notTaken)
        return memo[i][zeroes][ones]
    }
}

/**
 * Approach #5 Dynamic Programming
 * Time complexity : O(l*m*n). Three nested loops are their,
 * where ll is the length of strs, m and n are the number of zeroes and ones respectively.
 * Space complexity : O(m*n). dp array of size m*n is used.
 */
class OnesAndZeroesDP : OnesAndZeroes {
    override fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (s in strs) {
            val count: IntArray = s.countZeroesOnes()
            for (zeroes in m downTo count[0]) for (ones in n downTo count[1]) dp[zeroes][ones] = max(
                1 + dp[zeroes - count[0]][ones - count[1]],
                dp[zeroes][ones],
            )
        }
        return dp[m][n]
    }
}
