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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * 648. Replace Words
 * @see <a href="https://leetcode.com/problems/replace-words/">Source</a>
 */
fun interface ReplaceWords {
    operator fun invoke(dictionary: List<String>, sentence: String): String
}

/**
 * # Intuition
 * The idea is to replace words in the given sentence with their corresponding roots from the dictionary.
 * This can be efficiently achieved by checking prefixes of each word in the sentence against the roots in the
 * dictionary.
 *
 * # Approach
 * 1. Convert the dictionary of roots into a set for quick look-up.
 * 2. Split the sentence into individual words.
 * 3. For each word, find the shortest prefix that exists in the set of roots.
 * 4. Construct the resulting sentence using these prefixes.
 *
 * # Complexity
 * - Time complexity:
 * The time complexity is $$O(n \cdot m)$$, where \(n\) is the number of words in the sentence and \(m\) is the
 * maximum length of a word.
 * In the worst case, for each word, we might check every prefix (up to the length of the word) against the set
 * of roots.
 *
 * - Space complexity:
 * The space complexity is $$O(k)$$, where \(k\) is the total number of characters in the dictionary.
 * This space is used to store the roots in the set. Additionally, we use $$O(1)$$ extra space for the result
 * construction.
 */
class PrefixHash : ReplaceWords {
    override operator fun invoke(dictionary: List<String>, sentence: String): String {
        val rootSet = dictionary.toHashSet()

        return sentence.split("\\s+".toRegex()).joinToString(" ") { word ->
            (1..word.length)
                .map { word.substring(0, it) }
                .find { it in rootSet } ?: word
        }
    }
}

/**
 * # Intuition
 * The goal is to replace words in the given sentence with the shortest root from the dictionary.
 * A Trie (prefix tree) is well-suited for this task as it efficiently stores the roots and allows for quick
 * prefix searches.
 *
 * # Approach
 * 1. Build a Trie from the given dictionary of root words.
 * 2. Split the sentence into individual words.
 * 3. For each word, traverse the Trie to find the shortest root that matches a prefix of the word.
 * 4. Construct the resulting sentence using these roots or the original words if no root is found.
 *
 * # Complexity
 * - Time complexity:
 * The time complexity is $$O(n \cdot m)$$, where \(n\) is the number of words in the sentence and \(m\)
 * is the average length of the words. Building the Trie takes $$O(k)$$ time, where \(k\) is the total number
 * of characters in the dictionary.
 *
 * - Space complexity:
 * The space complexity is $$O(k)$$, where \(k\) is the total number of characters in the dictionary.
 * This space is used to store the Trie. Additionally, we use $$O(1)$$ extra space for the result construction.
 */
class ReplaceWordsTrie : ReplaceWords {
    override operator fun invoke(dictionary: List<String>, sentence: String): String {
        val trie = buildTrieFromDictionary(dictionary)
        return replaceWordsInSentence(trie, sentence)
    }

    private fun buildTrieFromDictionary(dictionary: List<String>): TrieNode {
        val trie = TrieNode()
        for (root in dictionary) {
            insertWordIntoTrie(trie, root)
        }
        return trie
    }

    private fun insertWordIntoTrie(trie: TrieNode, word: String) {
        var cur = trie
        for (letter in word.toCharArray()) {
            if (cur.children[letter.code - 'a'.code] == null) {
                cur.children[letter.code - 'a'.code] = TrieNode()
            }
            cur = cur.children[letter.code - 'a'.code] ?: TrieNode()
        }
        cur.word = word
    }

    private fun replaceWordsInSentence(trie: TrieNode, sentence: String): String {
        val ans = StringBuilder()
        for (word in sentence.split("\\s+".toRegex())) {
            if (ans.isNotEmpty()) ans.append(" ")
            val replacedWord = findLongestPrefixMatch(trie, word)
            ans.append(replacedWord)
        }
        return ans.toString()
    }

    private fun findLongestPrefixMatch(trie: TrieNode, word: String): String {
        var cur = trie
        for (letter in word.toCharArray()) {
            if (cur.children[letter.code - 'a'.code] == null || cur.word != null) break
            cur = cur.children[letter.code - 'a'.code] ?: TrieNode()
        }
        return if (cur.word != null) {
            cur.word ?: ""
        } else {
            word
        }
    }

    private class TrieNode {
        var children: Array<TrieNode?> = arrayOfNulls(ALPHABET_LETTERS_COUNT)
        var word: String? = null
    }
}
