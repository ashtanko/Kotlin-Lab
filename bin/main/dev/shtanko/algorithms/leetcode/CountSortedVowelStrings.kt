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

/**
 * Count Sorted Vowel Strings.
 * @see <a href="https://leetcode.com/problems/count-sorted-vowel-strings">Source</a>
 */
fun interface CountSortedVowelStrings {
    operator fun invoke(n: Int): Int
}

private const val VOWEL_COUNT = 5

/**
 * Approach 1: Brute Force Using Backtracking.
 * Time Complexity : O(n5).
 * Space Complexity: O(n).
 */
class CountSortedVowelBruteForce : CountSortedVowelStrings {
    override operator fun invoke(n: Int): Int {
        return countVowelStringUtil(n, 1)
    }

    private fun countVowelStringUtil(n: Int, vowels: Int): Int {
        if (n == 0) return 1
        var result = 0
        for (i in vowels..VOWEL_COUNT) {
            result += countVowelStringUtil(n - 1, i)
        }
        return result
    }
}

/**
 * Approach 2: Decoding the Pattern, Using Recursion.
 * Time Complexity : O(n5).
 * Space Complexity: O(n).
 */
class CountSortedVowelRecursion : CountSortedVowelStrings {
    override operator fun invoke(n: Int): Int {
        return countVowelStringUtil(n, VOWEL_COUNT)
    }

    private fun countVowelStringUtil(n: Int, vowels: Int): Int {
        if (n == 1) return vowels
        return if (vowels == 1) {
            1
        } else {
            countVowelStringUtil(n - 1, vowels) + countVowelStringUtil(n, vowels - 1)
        }
    }
}

/**
 * Approach 3: Using Recursion + Memoization, Top Down Dynamic Programming.
 * Time Complexity : O(n).
 * Space Complexity: O(n).
 */
class CountSortedVowelTopDynamic : CountSortedVowelStrings {

    // Override the invoke operator function to calculate and return the count of sorted vowel strings
    override operator fun invoke(n: Int): Int {
        // Initialize a memoization array to store computed values for dynamic programming
        val memo = Array(n + 1) { IntArray(VOWEL_COUNT + 1) }

        // Call the helper function to perform the actual calculation using dynamic programming
        return countVowelStringUtil(n, VOWEL_COUNT, memo)
    }

    // Helper function to recursively calculate the count of sorted vowel strings
    private fun countVowelStringUtil(n: Int, vowels: Int, memo: Array<IntArray>): Int {
        // Base case: If n is 1, return the count of vowels
        if (n == 1) return vowels

        // Base case: If there is only 1 vowel, return 1
        if (vowels == 1) return 1

        // If the result for the current parameters is already computed, return it
        if (memo[n][vowels] != 0) return memo[n][vowels]

        // Calculate the result for the current state (length of string 'n' and remaining vowels 'vowels').

        // Recursive call 1: Consider the case where a consonant is added to the end of the string.
        val resultConsonant = countVowelStringUtil(n - 1, vowels, memo)

        // Recursive call 2: Consider the case where a vowel is added to the end of the string.
        val resultVowel = countVowelStringUtil(n, vowels - 1, memo)

        // Combine the results of the two recursive calls to get the total count of sorted vowel strings.
        val res = resultConsonant + resultVowel

        // Store the computed result in the memoization array
        memo[n][vowels] = res

        // Return the final result
        return res
    }
}

class CountSortedVowelBottomUp : CountSortedVowelStrings {
    override operator fun invoke(n: Int): Int {
        val dp = Array(n + 1) { IntArray(VOWEL_COUNT + 1) }
        for (vowels in 1..VOWEL_COUNT) dp[1][vowels] = vowels
        for (nValue in 2..n) {
            dp[nValue][1] = 1
            for (vowels in 2..VOWEL_COUNT) {
                dp[nValue][vowels] = dp[nValue][vowels - 1] + dp[nValue - 1][vowels]
            }
        }
        return dp[n][VOWEL_COUNT]
    }
}

/**
 * Approach 5: Math.
 * Time Complexity : O(1).
 * Space Complexity: O(1).
 */
class CountSortedVowelMath : CountSortedVowelStrings {
    override operator fun invoke(n: Int): Int {
        return (n + 4) * (n + 3) * (n + 2) * (n + 1) / DENOMINATOR
    }

    companion object {
        private const val DENOMINATOR = 24
    }
}
