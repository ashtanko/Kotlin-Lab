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

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.algorithms.MOD
import dev.shtanko.algorithms.annotations.BottomUpDP
import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.TopDownDP
import kotlin.math.min

/**
 * 1416. Restore The Array
 * @see <a href="https://leetcode.com/problems/restore-the-array/">Source</a>
 */
fun interface RestoreTheArray {
    fun numberOfArrays(s: String, k: Int): Int
}

/**
 * Approach 1: Dynamic Programming (Top Down)
 */
@TopDownDP
class RestoreTheArrayTopDown : RestoreTheArray {
    override fun numberOfArrays(s: String, k: Int): Int {
        val m: Int = s.length
        val dp = IntArray(m + 1)
        return dfs(dp, 0, s, k)
    }

    // Number of possible splits for substring s[start ~ m-1].
    private fun dfs(dp: IntArray, start: Int, s: String, k: Int): Int {
        // If we have already updated dp[start], return it.
        if (dp[start] != 0) return dp[start]

        // There is only 1 split for an empty string.
        if (start == s.length) return 1

        // Number can't have leading zeros.
        if (s[start] == '0') return 0

        // For all possible starting number, add the number of arrays
        // that can be printed as the remaining string to count.
        var count = 0
        for (end in start until s.length) {
            val currNumber = s.substring(start, end + 1)
            if (currNumber.toLong() > k) break
            count = (count + dfs(dp, end + 1, s, k)) % MOD
        }

        // Update dp[start] so we don't recalculate it later.
        dp[start] = count
        return count
    }
}

/**
 * Approach 2: Dynamic Programming (Bottom Up)
 */
@BottomUpDP
class RestoreTheArrayBottomUp : RestoreTheArray {
    override fun numberOfArrays(s: String, k: Int): Int {
        val m: Int = s.length
        // dp[i] records the number of arrays that can be printed as
        // the prefix substring s[0 ~ i - 1]
        val dp = IntArray(m + 1)

        // Empty string has 1 valid split.
        dp[0] = 1
        // Iterate over every digit, for each digit s[start]
        for (start in 0 until m) {
            if (s[start] == '0') continue

            // Iterate over ending digit end and find all valid numbers
            // s[start ~ end].
            for (end in start until m) {
                val currNumber: String = s.substring(start, end + 1)
                if (currNumber.toLong() > k) break

                // If s[start ~ end] is valid, increment dp[end + 1] by dp[start].
                dp[end + 1] = (dp[end + 1] + dp[start]) % MOD
            }
        }
        return dp[m]
    }
}

@DFS
class RestoreTheArrayMemoization : RestoreTheArray {
    override fun numberOfArrays(s: String, k: Int): Int {
        val dp = arrayOfNulls<Int>(s.length) // dp[i] is number of ways to print valid arrays from string s start at i

        return dfs(s, k.toLong(), 0, dp)
    }

    private fun dfs(s: String, k: Long, i: Int, dp: Array<Int?>): Int {
        if (i == s.length) return 1 // base case -> Found a valid way
        if (s[i] == '0') return 0 // all numbers are in range [1, k] and there are no leading zeros ->
        // So numbers starting with 0 mean invalid!
        if (dp[i] != null) return dp[i] ?: -1
        var ans = 0
        var num: Long = 0
        for (j in i until s.length) {
            num = num * DECIMAL + s[j].code.toLong() - '0'.code.toLong() // num is the value of the substring s[i..j]
            if (num > k) break // num must be in range [1, k]
            ans += dfs(s, k, j + 1, dp)
            ans %= MOD
        }
        return ans.also { dp[i] = it }
    }
}

class RestoreTheArrayDP : RestoreTheArray {
    override fun numberOfArrays(s: String, k: Int): Int {
        val mod = MOD
        val n: Int = s.length
        val dp = IntArray(n + 1)
        dp[0] = 1

        // dp[i] =  count of  possible arrays for i length string
        for (i in 1..n) {
            var ans = 0
            val start = n - i // reading string from end

            // taking min because we can have maximum 9-digit number
            for (j in start until min(n, start + 9)) {
                val num = s.substring(start, j + 1)
                if (num[0] == '0') continue
                val value = num.toInt()
                ans = if (value <= k) { // if number is valid
                    (ans + dp[n - j - 1]) % mod
                } else {
                    // if current number is greater than k  , then number formed after this number
                    // also greater than k , so break loop
                    break
                }
            }
            dp[i] = ans % mod
        }

        return dp[s.length] // return answer for n len string
    }
}
