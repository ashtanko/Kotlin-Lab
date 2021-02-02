/*
 * Copyright 2021 Alexey Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.extensions.isPrime
import kotlin.math.pow

/**
 * Prime Palindrome
 * @link https://leetcode.com/problems/prime-palindrome/
 */
interface PrimePalindrome {
    fun perform(n: Int): Int
}

/**
 * Approach 1: Iterate Palindromes
 * Time Complexity: O(N).
 * Space Complexity: O(logN).
 */
class IteratePalindromes : PrimePalindrome {
    override fun perform(n: Int): Int {
        for (L in 1..MAX) {
            // Check for odd-length palindromes
            for (root in DECIMAL.toDouble().pow((L - 1).toDouble()).toInt() until DECIMAL.toDouble().pow(L.toDouble())
                .toInt()) {
                val sb = StringBuilder(root.toString())
                for (k in L - 2 downTo 0) sb.append(sb[k])
                val x = Integer.valueOf(sb.toString())
                if (x >= n && x.isPrime()) return x
            }

            // Check for even-length palindromes
            for (root in DECIMAL.toDouble().pow((L - 1).toDouble()).toInt() until DECIMAL.toDouble().pow(L.toDouble())
                .toInt()) {
                val sb = StringBuilder(root.toString())
                for (k in L - 1 downTo 0) sb.append(sb[k])
                val x = Integer.valueOf(sb.toString())
                if (x >= n && x.isPrime()) return x
            }
        }
        return 0
    }

    companion object {
        private const val MAX = 5
    }
}

/**
 * Approach 2: Brute Force with Mathematical Shortcut
 * Time Complexity: O(N).
 * Space Complexity: O(1).
 */
class PrimePalindromeBruteForce : PrimePalindrome {
    override fun perform(n: Int): Int {
        var x = n
        while (true) {
            if (x == reverse(x) && x.isPrime()) {
                return x
            }
            x++
            if (x in RANGE_START..RANGE_END) {
                x = MAX
            }
        }
    }

    private fun reverse(n: Int): Int {
        var x = n
        var ans = 0
        while (x > 0) {
            ans = DECIMAL * ans + x % DECIMAL
            x /= DECIMAL
        }
        return ans
    }

    companion object {
        private const val RANGE_START = 10000001
        private const val RANGE_END = 99999999
        private const val MAX = 100_000_000
    }
}