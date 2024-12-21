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

/**
 * 131. Palindrome Partitioning
 * @see <a href="https://leetcode.com/problems/palindrome-partitioning/">Source</a>
 */
fun interface PalindromePartitioning {
    operator fun invoke(str: String): List<List<String>>
}

/**
 * Approach 1: Backtracking
 */
class PPBacktracking : PalindromePartitioning {
    override operator fun invoke(str: String): List<List<String>> {
        val partitions: MutableList<List<String>> = ArrayList()
        generatePartitions(0, partitions, ArrayList(), str)
        return partitions
    }

    private fun generatePartitions(
        startIdx: Int,
        partitions: MutableList<List<String>>,
        currentPartition: MutableList<String>,
        str: String,
    ) {
        if (startIdx >= str.length) partitions.add(ArrayList(currentPartition))
        for (endIdx in startIdx until str.length) {
            if (isPalindrome(str, startIdx, endIdx)) {
                currentPartition.add(str.substring(startIdx, endIdx + 1))
                generatePartitions(endIdx + 1, partitions, currentPartition, str)
                currentPartition.removeAt(currentPartition.size - 1)
            }
        }
    }

    private fun isPalindrome(inputString: String, startIdx: Int, endIdx: Int): Boolean {
        var lowIdx = startIdx
        var highIdx = endIdx
        while (lowIdx < highIdx) {
            if (inputString[lowIdx++] != inputString[highIdx--]) {
                return false
            }
        }
        return true
    }
}

/**
 * Approach 2: Backtracking with Dynamic Programming
 */
class PPBacktrackingDP : PalindromePartitioning {
    override operator fun invoke(str: String): List<List<String>> {
        val len: Int = str.length
        val dp = Array(len) { BooleanArray(len) }
        val result: MutableList<List<String>> = ArrayList()
        generatePartitions(result, str, 0, ArrayList(), dp)
        return result
    }

    private fun generatePartitions(
        partitions: MutableList<List<String>>,
        inputString: String,
        startIndex: Int,
        currentPartition: MutableList<String>,
        palindromeCheckMatrix: Array<BooleanArray>,
    ) {
        if (startIndex >= inputString.length) partitions.add(ArrayList(currentPartition))
        for (endIndex in startIndex until inputString.length) {
            val isPalindrome = (endIndex - startIndex <= 2 || palindromeCheckMatrix[startIndex + 1][endIndex - 1])
            if (inputString[startIndex] == inputString[endIndex] && isPalindrome) {
                palindromeCheckMatrix[startIndex][endIndex] = true
                currentPartition.add(inputString.substring(startIndex, endIndex + 1))
                generatePartitions(partitions, inputString, endIndex + 1, currentPartition, palindromeCheckMatrix)
                currentPartition.removeAt(currentPartition.size - 1)
            }
        }
    }
}
