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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import kotlin.math.max

/**
 * Word Abbreviation.
 * @see <a href="https://leetcode.com/problems/word-abbreviation/">Source</a>
 */
fun interface WordAbbreviation {
    operator fun invoke(dict: List<String>): List<String>

    fun abbrev(word: String, i: Int): String {
        val n = word.length
        return if (n - i <= 3) word else word.substring(0, i + 1) + (n - i - 2) + word[n - 1]
    }
}

/**
 * Approach #1: Greedy.
 */
class WordAbbreviationGreedy : WordAbbreviation {
    override operator fun invoke(dict: List<String>): List<String> {
        val wordToAbbr: MutableMap<String?, String?> = HashMap()
        val groups: MutableMap<Int, MutableList<String>> = HashMap()

        // Try to group words by their length. Because no point to compare words with different length.
        // Also, no point to look at words with length < 4.
        for (word in dict) {
            val len = word.length
            if (len < 4) {
                wordToAbbr[word] = word
            } else {
                val g = groups.getOrDefault(len, ArrayList())
                g.add(word)
                groups[len] = g
            }
        }

        // For each group of words with same length, generate a result HashMap.
        for (len in groups.keys) {
            val res = getAbbr(groups.getOrDefault(len, emptyList()))
            for (word in res.keys) {
                wordToAbbr[word] = res[word]
            }
        }

        // Generate the result list
        val result: MutableList<String> = ArrayList()
        for (word in dict) {
            wordToAbbr[word]?.let { result.add(it) }
        }

        return result
    }

    private fun getAbbr(words: List<String>): Map<String?, String> {
        val res: MutableMap<String?, String> = HashMap()
        val len = words[0].length

        // Try to abbreviate a word from index 1 to len - 2
        for (i in 1 until len - 2) {
            val abbrToWord: MutableMap<String, String> = HashMap()
            for (s in words) {
                if (res.containsKey(s)) continue
                // Generate the current abbreviation
                val abbr = s.substring(0, i) + (len - 1 - i) + s[len - 1]
                // Tick: use reversed abbreviation to word map to check if there is any duplicated abbreviation
                if (!abbrToWord.containsKey(abbr)) {
                    abbrToWord[abbr] = s
                } else {
                    abbrToWord[abbr] = ""
                }
            }

            // Add unique abbreviations find during this round to result HashMap
            for (abbr in abbrToWord.keys) {
                val s = abbrToWord[abbr]
                // Not a unique abbreviation
                if (s!!.isEmpty()) continue
                res[s] = abbr
            }
        }

        // Add all words that can't be shortened.
        for (s in words) {
            if (!res.containsKey(s)) {
                res[s] = s
            }
        }
        return res
    }
}

class IndexedWord(var word: String, var index: Int)

class WordTrieNode {
    var children: Array<WordTrieNode?> = arrayOfNulls(ALPHABET_LETTERS_COUNT)
    var count: Int = 0
}

/**
 * Approach #2: Group + Least Common Prefix.
 */
class WordAbbreviationLCP : WordAbbreviation {
    override operator fun invoke(dict: List<String>): List<String> {
        val groups: MutableMap<String, MutableList<IndexedWord>> = HashMap()
        val ans = Array(dict.size) { "" }

        for ((index, word) in dict.withIndex()) {
            val ab = abbrev(word, 0)
            if (!groups.containsKey(ab)) groups[ab] = ArrayList()
            groups[ab]?.add(IndexedWord(word, index))
        }

        for (group in groups.values) {
            group.sortWith { a: IndexedWord, b: IndexedWord ->
                a.word.compareTo(b.word)
            }

            val lcp = IntArray(group.size)
            for (i in 1 until group.size) {
                val p = longestCommonPrefix(group[i - 1].word, group[i].word)
                lcp[i] = p
                lcp[i - 1] = max(lcp[i - 1], p)
            }
            for (i in group.indices) ans[group[i].index] = abbrev(group[i].word, lcp[i])
        }

        return ans.toList()
    }

    private fun longestCommonPrefix(word1: String, word2: String): Int {
        var i = 0
        while (i < word1.length && i < word2.length && word1[i] == word2[i]) i++
        return i
    }
}

/**
 * Approach #3: Group + Trie.
 */
class WordAbbreviationTrie : WordAbbreviation {
    override operator fun invoke(dict: List<String>): List<String> {
        val groups: MutableMap<String, MutableList<IndexedWord?>> = HashMap()
        val ans = Array(dict.size) { "" }

        for ((index, word) in dict.withIndex()) {
            val ab = abbrev(word, 0)
            if (!groups.containsKey(ab)) groups[ab] = ArrayList()
            groups[ab]?.add(IndexedWord(word, index))
        }

        for (group in groups.values) {
            val trie = WordTrieNode()
            for (iw in group) {
                var cur = trie
                if (iw!!.word.isNotBlank()) {
                    for (letter in iw.word.substring(1).toCharArray()) {
                        if (cur.children[letter - 'a'] == null) cur.children[letter - 'a'] = WordTrieNode()
                        cur.count++
                        cur = cur.children[letter - 'a']!!
                    }
                }
            }
            for (iw in group) {
                var cur = trie
                var i = 1
                if (iw!!.word.isNotBlank()) {
                    for (letter in iw.word.substring(1).toCharArray()) {
                        if (cur.count == 1) break
                        cur = cur.children[letter - 'a']!!
                        i++
                    }
                }
                ans[iw.index] = abbrev(iw.word, i - 1)
            }
        }
        return ans.toList()
    }
}
