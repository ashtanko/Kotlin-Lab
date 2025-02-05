/*
 * Copyright 2024 Oleksii Shtanko
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

import java.util.Deque

/**
 * 1653. Minimum Deletions to Make String Balanced
 * @see <a href="https://leetcode.com/problems/minimum-deletions-to-make-string-balanced">Source</a>
 */
fun interface MinDeletionsToMakeStrBalanced {
    operator fun invoke(str: String): Int
}

/**
 * Approach 1: Three-Pass Count Method
 *
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
class MinDeletionsThreePass : MinDeletionsToMakeStrBalanced {
    override fun invoke(str: String): Int {
        if (str.isEmpty()) {
            return 0
        }
        val n = str.length
        val countA = IntArray(n)
        val countB = IntArray(n)
        var bCount = 0

        // First pass: compute countB which stores the number of 'b' characters to the left of the current position.
        for (i in str.indices) {
            countB[i] = bCount
            if (str[i] == 'b') bCount++
        }

        var aCount = 0
        // Second pass: compute countA which stores the number of 'a' characters to the right of the current position.
        for (i in n - 1 downTo 0) {
            countA[i] = aCount
            if (str[i] == 'a') aCount++
        }

        // Third pass: iterate through the string to find the minimum deletions.
        return str.indices.minOf { countA[it] + countB[it] }
    }
}

/**
 * Approach 2: Combined Pass Method
 *
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
class MinDeletionsCombinedPass : MinDeletionsToMakeStrBalanced {
    override fun invoke(str: String): Int {
        val n = str.length
        val countA = IntArray(n)
        var aCount = 0

        // First pass: compute countA which stores the number of 'a' characters to the right of the current position
        for (i in n - 1 downTo 0) {
            countA[i] = aCount
            if (str[i] == 'a') aCount++
        }

        var minDeletions = n
        var bCount = 0

        // Second pass: compute minimum deletions on the fly
        str.forEachIndexed { i, char ->
            minDeletions = minOf(countA[i] + bCount, minDeletions)
            if (char == 'b') bCount++
        }

        return minDeletions
    }
}

/**
 * Approach 3: Two-Variable Method
 *
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
class MinDeletionsTwoVariable : MinDeletionsToMakeStrBalanced {
    override fun invoke(str: String): Int {
        var aCount = str.count { it == 'a' }
        var bCount = 0
        var minDeletions = str.length

        str.forEach { char ->
            if (char == 'a') {
                minDeletions = minOf(minDeletions, --aCount + bCount)
            } else if (char == 'b') {
                minDeletions = minOf(minDeletions, aCount + bCount++)
            }
        }

        return minDeletions
    }
}

/**
 * Approach 4: Using stack (one pass)
 *
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
class MinDeletionsStack : MinDeletionsToMakeStrBalanced {
    override fun invoke(str: String): Int {
        val charStack: Deque<Char> = java.util.ArrayDeque<Char>()
        var deleteCount = 0

        str.forEach { char ->
            if (charStack.isNotEmpty() && charStack.peek() == 'b' && char == 'a') {
                charStack.pop() // Remove 'b' from stack
                deleteCount++ // Increment deletion count
            } else {
                charStack.push(char) // Push current character onto stack
            }
        }

        return deleteCount
    }
}

/**
 * Approach 5: Using DP (One Pass)
 *
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
class MinDeletionsDP : MinDeletionsToMakeStrBalanced {
    override fun invoke(str: String): Int {
        val n = str.length
        val dp = IntArray(n + 1)
        var bCount = 0

        str.forEachIndexed { i, char ->
            if (char == 'b') {
                dp[i + 1] = dp[i]
                bCount++
            } else {
                dp[i + 1] = minOf(dp[i] + 1, bCount)
            }
        }

        return dp[n]
    }
}

/**
 * Approach 6: Optimized DP
 *
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
class MinDeletionsOptimizedDP : MinDeletionsToMakeStrBalanced {
    override fun invoke(str: String): Int {
        return str.fold(Pair(0, 0)) { (minDeletions, bCount), char ->
            if (char == 'b') {
                Pair(minDeletions, bCount + 1)
            } else {
                Pair(minOf(minDeletions + 1, bCount), bCount)
            }
        }.first
    }
}
