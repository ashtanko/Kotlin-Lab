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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 2416. Sum of Prefix Scores of Strings
 * @see <a href="https://leetcode.com/problems/sum-of-prefix-scores-of-strings/">Source</a>
 */
@Hard("https://leetcode.com/problems/sum-of-prefix-scores-of-strings")
fun interface SumPrefixScores {
    operator fun invoke(words: Array<String>): IntArray
}

class SumPrefixScoresTrie : SumPrefixScores {
    private val root = TrieNode()

    override fun invoke(words: Array<String>): IntArray {
        val n = words.size
        // Insert words in the trie.
        for (word in words) {
            insert(word)
        }
        val scores = IntArray(n)
        for (i in words.indices) {
            // Get the count of all prefixes of the given string.
            scores[i] = count(words[i])
        }
        return scores
    }

    // Insert function for the word.
    private fun insert(word: String) {
        var node = root
        for (c in word) {
            val index = c - 'a'
            if (node.next[index] == null) {
                node.next[index] = TrieNode()
            }
            node.next[index]?.cnt = node.next[index]?.cnt?.plus(1) ?: 1
            node = node.next[index]!!
        }
    }

    // Calculate the prefix count using this function.
    private fun count(s: String): Int {
        var node = root
        var ans = 0
        for (c in s) {
            val index = c - 'a'
            node = node.next[index] ?: return ans
            ans += node.cnt
        }
        return ans
    }

    class TrieNode {
        val next = Array<TrieNode?>(ALPHABET_LETTERS_COUNT) { null }
        var cnt = 0
    }
}
