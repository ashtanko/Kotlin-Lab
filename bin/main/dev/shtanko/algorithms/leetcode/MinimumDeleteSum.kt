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

import kotlin.math.min

/**
 * 712. Minimum ASCII Delete Sum for Two Strings
 * @see <a href="https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/">Source</a>
 */
fun interface MinimumDeleteSum {
    operator fun invoke(s1: String, s2: String): Int
}

/**
 * Approach 1: Recursion
 */
class MinimumDeleteSumRecursion : MinimumDeleteSum {
    override operator fun invoke(s1: String, s2: String): Int {
        return computeCost(s1, s2, s1.length - 1, s2.length - 1)
    }

    // Return minimum cost to make s1[0...i] and s2[0...j] equal
    private fun computeCost(s1: String, s2: String, i: Int, j: Int): Int {
        // If s1 is empty, then we need to delete all characters of s2
        if (i < 0) {
            var deleteCost = 0
            for (pointer in 0..j) {
                deleteCost += s2[pointer].code
            }
            return deleteCost
        }

        // If s2 is empty, then we need to delete all characters of s1
        if (j < 0) {
            var deleteCost = 0
            for (pointer in 0..i) {
                deleteCost += s1[pointer].code
            }
            return deleteCost
        }

        // Check s1[i] and s2[j]
        return if (s1[i] == s2[j]) {
            computeCost(s1, s2, i - 1, j - 1) // Characters match, no cost, move to the next characters
        } else {
            // Calculate the minimum cost to make s1[0...i] and s2[0...j] equal by considering three operations:
            // 1. Delete s1[i] and move to the next character in s1.
            // 2. Delete s2[j] and move to the next character in s2.
            // 3. Replace s1[i] with s2[j] and move to the next characters in both s1 and s2.
            min(
                (s1[i].code + computeCost(s1, s2, i - 1, j)).toDouble(),
                min(
                    (s2[j].code + computeCost(s1, s2, i, j - 1)).toDouble(),
                    (s1[i].code + s2[j].code + computeCost(s1, s2, i - 1, j - 1)).toDouble(),
                ),
            ).toInt()
        }
    }
}

class MinimumDeleteSumTopDown : MinimumDeleteSum {
    // Hash Map to store the result of each sub-problem.
    private val savedResult: MutableMap<Pair<Int, Int>, Int> = HashMap()

    override operator fun invoke(s1: String, s2: String): Int {
        // Return minimum cost to make s1[0...i] and s2[0...j] equal
        return computeCost(s1, s2, s1.length - 1, s2.length - 1)
    }

    // Return minimum cost to make s1[0...i] and s2[0...j] equal
    private fun computeCost(s1: String, s2: String, i: Int, j: Int): Int {
        // If both strings are empty, then no deletion is required
        if (i < 0 && j < 0) {
            return 0
        }

        // If already computed, then return the result from the hash map
        val key = Pair(i, j)
        if (savedResult.containsKey(key)) {
            return savedResult[key]!!
        }

        // If any one string is empty, delete all characters of the other string
        if (i < 0) {
            savedResult[key] = s2[j].code + computeCost(s1, s2, i, j - 1)
            return savedResult[key]!!
        }
        if (j < 0) {
            savedResult[key] = s1[i].code + computeCost(s1, s2, i - 1, j)
            return savedResult[key]!!
        }

        // Call sub-problem depending on s1[i] and s2[j]
        // Save the computed result.
        if (s1[i] == s2[j]) {
            savedResult[key] =
                computeCost(s1, s2, i - 1, j - 1) // Characters match, no cost, move to the next characters
        } else {
            // Calculate the minimum cost to make s1[0...i] and s2[0...j] equal by considering two operations:
            // 1. Delete s1[i] and move to the next character in s1.
            // 2. Delete s2[j] and move to the next character in s2.
            savedResult[key] = min(
                (s1[i].code + computeCost(s1, s2, i - 1, j)).toDouble(),
                (s2[j].code + computeCost(s1, s2, i, j - 1)).toDouble(),
            ).toInt()
        }
        return savedResult[key]!!
    }
}

/**
 * Approach 3: Bottom-up Dynamic Programming
 */
class MinimumDeleteSumBottomUp : MinimumDeleteSum {
    override operator fun invoke(s1: String, s2: String): Int {
        // Prepare the two-dimensional array
        val m: Int = s1.length
        val n: Int = s2.length
        val computeCost = Array(m + 1) { IntArray(n + 1) }

        // Fill in the base case values
        for (i in 1..m) {
            computeCost[i][0] = computeCost[i - 1][0] + s1[i - 1].code
        }
        for (j in 1..n) {
            computeCost[0][j] = computeCost[0][j - 1] + s2[j - 1].code
        }

        // Fill the remaining cells using the Bellman Equation
        for (i in 1..m) {
            for (j in 1..n) {
                if (s1[i - 1] == s2[j - 1]) {
                    computeCost[i][j] = computeCost[i - 1][j - 1]
                } else {
                    computeCost[i][j] = min(
                        s1[i - 1].code + computeCost[i - 1][j],
                        s2[j - 1].code + computeCost[i][j - 1],
                    )
                }
            }
        }

        // Return the answer for entire input strings
        return computeCost[m][n]
    }
}

/**
 * Approach 4: Space-Optimized Bottom-up Dynamic Programming
 */
class MinimumDeleteSumBottomUpOtp : MinimumDeleteSum {
    override operator fun invoke(s1: String, s2: String): Int {
        // Make sure s2 is smaller string
        if (s1.length < s2.length) {
            return invoke(s2, s1)
        }

        // Case for empty s1
        val m = s1.length
        val n = s2.length
        val currRow = IntArray(n + 1)
        for (j in 1..n) {
            currRow[j] = currRow[j - 1] + s2[j - 1].code
        }

        // Compute answer row-by-row
        for (i in 1..m) {
            var diag = currRow[0]
            currRow[0] += s1[i - 1].code

            for (j in 1..n) {
                // If characters are the same, the answer is top-left-diagonal value
                val answer: Int = if (s1[i - 1] == s2[j - 1]) {
                    diag
                }

                // Otherwise, the answer is minimum of top and left values with
                // deleted character's ASCII value
                else {
                    min(
                        s1[i - 1].code + currRow[j],
                        s2[j - 1].code + currRow[j - 1],
                    )
                }

                // Before overwriting currRow[j] with answer, save it in diag
                // for the next column
                diag = currRow[j]
                currRow[j] = answer
            }
        }

        // Return the answer
        return currRow[n]
    }
}
