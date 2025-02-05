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

/**
 * Prefix and Suffix Search
 * @see <a href="https://leetcode.com/problems/prefix-and-suffix-search/">Source</a>
 */
fun interface WordFilter {
    operator fun invoke(prefix: String, suffix: String): Int
}

/**
 * Approach #1: Trie + Set Intersection [Time Limit Exceeded]
 */
class WordFilterTrie(words: Array<String>) : WordFilter {

    private val trie1: WFTrieNode = WFTrieNode()
    private val trie2: WFTrieNode = WFTrieNode()
    private var wt = 0

    init {
        words.forEach { word ->
            val ca = word.toCharArray()
            insertWord(ca, trie1)
            insertWord(ca.reversedArray(), trie2)
            wt++
        }
    }

    private fun insertWord(charArray: CharArray, root: WFTrieNode) {
        var cur = root
        cur.weight.add(wt)

        for (letter in charArray) {
            val index = letter - 'a'
            if (cur.children[index] == null) {
                cur.children[index] = WFTrieNode()
            }
            cur = cur.children[index]!!
            cur.weight.add(wt)
        }
    }

    override operator fun invoke(prefix: String, suffix: String): Int {
        val cur1 = getNodeWithPrefix(prefix, trie1)
        val cur2 = getNodeWithPrefix(suffix.reversed(), trie2)

        return cur1.weight.intersect(cur2.weight).maxOrNull() ?: -1
    }

    private fun getNodeWithPrefix(prefix: String, root: WFTrieNode): WFTrieNode {
        var cur = root
        for (letter in prefix.toCharArray()) {
            val index = letter - 'a'
            cur = cur.children[index] ?: return WFTrieNode()
        }
        return cur
    }

    class WFTrieNode {
        var children: Array<WFTrieNode?> = arrayOfNulls(ALPHABET_LETTERS_COUNT)
        var weight: MutableSet<Int> = HashSet()
    }

    companion object {
        private const val ALPHABET_LETTERS_COUNT = 26
    }
}

class WordFilterWrappedWords(words: Array<String>) : WordFilter {

    private var trie: TrieNode = TrieNode()

    init {
        for (weight in words.indices) {
            val word: String = words[weight] + "{"
            for (i in word.indices) {
                var cur: TrieNode = trie
                cur.weight = weight
                for (j in i until 2 * word.length - 1) {
                    val k = word[j % word.length] - 'a'
                    if (cur.children[k] == null) {
                        cur.children[k] = TrieNode()
                    }
                    cur = cur.children[k]!!
                    cur.weight = weight
                }
            }
        }
    }

    override operator fun invoke(prefix: String, suffix: String): Int {
        var cur: TrieNode = trie
        for (letter in "$suffix{$prefix".toCharArray()) {
            if (cur.children[letter - 'a'] == null) return -1
            cur = cur.children[letter - 'a']!!
        }
        return cur.weight
    }

    class TrieNode {
        var children: Array<TrieNode?> = arrayOfNulls(27)
        var weight: Int = 0
    }
}
