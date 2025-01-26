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

import kotlin.math.max

/**
 * 1048. Longest String Chain
 * @see <a href="https://leetcode.com/problems/longest-string-chain/">Source</a>
 */
fun interface LongestStringChain {
    operator fun invoke(words: Array<String>): Int
}

/**
 * Approach 1: Top-Down Dynamic Programming (Recursion + Memoization)
 * Time complexity: O(L2⋅N)
 * Space complexity: O(N).
 */
class LSCTopDown : LongestStringChain {
    override operator fun invoke(words: Array<String>): Int {
        val memo: MutableMap<String, Int> = HashMap()
        val wordsPresent: MutableSet<String> = HashSet()
        wordsPresent.addAll(words)
        var ans = 0
        for (word in words) {
            ans = max(ans, dfs(wordsPresent, memo, word))
        }
        return ans
    }

    private fun dfs(words: Set<String>, memo: MutableMap<String, Int>, currentWord: String): Int {
        // If the word is encountered previously we just return its value present in the map (memoization).
        if (memo.containsKey(currentWord)) {
            return memo[currentWord]!!
        }
        // This stores the maximum length of word sequence possible with the 'currentWord' as the
        var maxLength = 1
        val sb = StringBuilder(currentWord)

        // creating all possible strings taking out one character at a time from the `currentWord`
        for (i in currentWord.indices) {
            sb.deleteCharAt(i)
            val newWord = sb.toString()
            // If the new word formed is present in the list, we do a dfs search with this newWord.
            if (words.contains(newWord)) {
                val currentLength = 1 + dfs(words, memo, newWord)
                maxLength = max(maxLength, currentLength)
            }
            sb.insert(i, currentWord[i])
        }
        memo[currentWord] = maxLength
        return maxLength
    }
}

/**
 * Approach 2: Bottom-Up Dynamic Programming
 * Time complexity: O(N⋅(logN+L2)).
 * Space complexity: O(N).
 */
class LSCBottomUp : LongestStringChain {
    override operator fun invoke(words: Array<String>): Int {
        val dp: MutableMap<String, Int> = HashMap()

        // Sorting the list in terms of the word length.
        words.sortWith { a, b -> a.length - b.length }

        var longestWordSequenceLength = 1

        for (word in words) {
            var presentLength = 1
            // Find all possible predecessors for the current word by removing one letter at a time.
            for (i in word.indices) {
                val temp = StringBuilder(word)
                temp.deleteCharAt(i)
                val predecessor = temp.toString()
                val previousLength = dp.getOrDefault(predecessor, 0)
                presentLength = max(presentLength, previousLength + 1)
            }
            dp[word] = presentLength
            longestWordSequenceLength = max(longestWordSequenceLength, presentLength)
        }
        return longestWordSequenceLength
    }
}
