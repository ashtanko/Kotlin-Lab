/*
 * Copyright 2021 Oleksii Shtanko
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

fun interface PalindromicSubstrings {
    operator fun invoke(str: String): Int
}

/**
 * Approach #1: Check All Substrings
 * Time Complexity: O(N^3)
 * Space Complexity: O(1)
 */
class PalindromicSubstringsCheckAllSubstrings : PalindromicSubstrings {
    override operator fun invoke(str: String): Int {
        var count = 0

        for (startIndex in str.indices) {
            for (endIndex in startIndex until str.length) {
                count += if (isPalindrome(str, startIndex, endIndex)) {
                    1
                } else {
                    0
                }
            }
        }

        return count
    }

    private fun isPalindrome(str: String, start: Int, end: Int): Boolean {
        var left = start
        var right = end
        while (left < right) {
            if (str[left] != str[right]) {
                return false
            }
            left++
            right--
        }
        return true
    }
}

/**
 * Approach #2: Dynamic Programming
 * Time Complexity: O(N^2)
 * Space Complexity: O(N^2)
 */
class PalindromicSubstringsDP : PalindromicSubstrings {
    override operator fun invoke(str: String): Int {
        val length: Int = str.length
        var count = 0

        if (length <= 0) {
            return 0
        }

        val dp = Array(length) { BooleanArray(length) }

        // Base case: single letter substrings
        var index = 0
        while (index < length) {
            dp[index][index] = true
            count++
            index++
        }

        // Base case: double letter substrings
        for (i in 0 until length - 1) {
            dp[i][i + 1] = str[i] == str[i + 1]
            count += if (dp[i][i + 1]) {
                1
            } else {
                0
            }
        }

        // All other cases: substrings of length 3 to n
        for (len in 3..length) {
            var start = 0
            var end = start + len - 1
            while (end < length) {
                dp[start][end] = dp[start + 1][end - 1] && str[start] == str[end]
                count += if (dp[start][end]) {
                    1
                } else {
                    0
                }
                start++
                end++
            }
        }

        return count
    }
}

/**
 * Approach #3: Expand Around Possible Centers
 * Time Complexity: O(N^2)
 * Space Complexity: O(1)
 */
class PalindromicSubstringsPossibleCenters : PalindromicSubstrings {
    override operator fun invoke(str: String): Int {
        var count = 0

        for (i in str.indices) {
            // Odd-length palindromes, single character center
            count += countPalindromesAroundCenter(str, i, i)

            // Even-length palindromes, consecutive characters center
            count += countPalindromesAroundCenter(str, i, i + 1)
        }

        return count
    }

    private fun countPalindromesAroundCenter(string: String, lo: Int, hi: Int): Int {
        var low = lo
        var high = hi
        var count = 0
        while (low >= 0 && high < string.length) {
            if (string[low] != string[high]) {
                break // The first and last characters don't match!
            }

            // Expand around the center
            low--
            high++
            count++
        }
        return count
    }
}
