/*
 * Copyright 2023 Oleksii Shtanko
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
import java.util.Arrays
import kotlin.math.min

/**
 * 920. Number of Music Playlists
 * @see <a href="https://leetcode.com/problems/number-of-music-playlists">Source</a>
 */
fun interface NumMusicPlaylists {
    operator fun invoke(n: Int, goal: Int, k: Int): Int
}

/**
 * Approach 1: Bottom-up Dynamic Programming
 */
class NumMusicPlaylistsBottomUp : NumMusicPlaylists {
    override operator fun invoke(n: Int, goal: Int, k: Int): Int {
        // Initialize the DP table
        val dp = Array(goal + 1) { LongArray(n + 1) }
        dp[0][0] = 1

        for (i in 1..goal) {
            for (j in 1..min(i, n)) {
                // The i-th song is a new song
                dp[i][j] = dp[i - 1][j - 1] * (n - j + 1) % MOD
                // The i-th song is a song we have played before
                if (j > k) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j] * (j - k)) % MOD
                }
            }
        }

        return dp[goal][n].toInt()
    }
}

/**
 * Approach 2: Top-down Dynamic Programming (Memoization)
 */
class NumMusicPlaylistsTopDown : NumMusicPlaylists {
    private lateinit var dp: Array<LongArray>

    override operator fun invoke(n: Int, goal: Int, k: Int): Int {
        dp = Array(goal + 1) { LongArray(n + 1) }
        for (row in dp) {
            Arrays.fill(row, -1L)
        }
        return numberOfPlaylists(goal, n, k, n).toInt()
    }

    private fun numberOfPlaylists(i: Int, j: Int, k: Int, n: Int): Long {
        // Base cases
        if (i == 0 && j == 0) {
            return 1
        }
        if (i == 0 || j == 0) {
            return 0
        }
        if (dp[i][j] != -1L) {
            return dp[i][j]
        }
        // DP transition: add a new song or replay an old one
        dp[i][j] = numberOfPlaylists(i - 1, j - 1, k, n) * (n - j + 1) % MOD
        if (j > k) {
            dp[i][j] = (dp[i][j] + numberOfPlaylists(i - 1, j, k, n) * (j - k) % MOD) % MOD
        }
        return dp[i][j]
    }
}

/**
 * Approach 3: Combinatorics
 */
class NumMusicPlaylistsCombinatorics : NumMusicPlaylists {
    // Pre-calculated factorials and inverse factorials
    private lateinit var factorial: LongArray
    private lateinit var invFactorial: LongArray

    override operator fun invoke(n: Int, goal: Int, k: Int): Int {
        // Pre-calculate factorials and inverse factorials
        precalculateFactorials(n)

        // Initialize variables for calculation
        var sign = 1
        var answer: Long = 0

        // Loop from 'n' down to 'k'
        for (i in n downTo k) {
            // Calculate temporary result for this iteration
            var temp = power(i - k.toLong(), goal - k)
            temp = temp * invFactorial[n - i] % MOD
            temp = temp * invFactorial[i - k] % MOD

            // Add or subtract temporary result to/from answer
            answer = (answer + sign * temp + MOD) % MOD

            // Flip sign for next iteration
            sign *= -1
        }

        // Final result is n! * answer, all under modulo
        return (factorial[n] * answer % MOD).toInt()
    }

    // Method to pre-calculate factorials and inverse factorials up to 'n'
    private fun precalculateFactorials(n: Int) {
        factorial = LongArray(n + 1)
        invFactorial = LongArray(n + 1)
        factorial[0] = 1
        invFactorial[0] = 1

        // Calculate factorials and inverse factorials for each number up to 'n'
        for (i in 1..n) {
            factorial[i] = factorial[i - 1] * i % MOD
            // Inverse factorial calculated using Fermat's Little Theorem
            invFactorial[i] = power(factorial[i], MOD - 2)
        }
    }

    // Method to calculate power of a number under modulo using binary exponentiation
    private fun power(base: Long, exponent: Int): Long {
        var result = 1L

        // Loop until exponent is not zero
        var exp = exponent
        var b = base
        while (exp > 0) {
            // If exponent is odd, multiply result with base
            if (exp and 1 == 1) {
                result = result * b % MOD
            }
            // Divide the exponent by 2 and square the base
            exp = exp shr 1
            b = b * b % MOD
        }

        return result
    }
}
