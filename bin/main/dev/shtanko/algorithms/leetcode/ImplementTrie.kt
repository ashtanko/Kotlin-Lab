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

interface Trie {
    fun insert(word: String)

    fun search(word: String): Boolean

    fun startsWith(prefix: String): Boolean
}

class TrieArray : Trie {

    private val root = TrieNode()

    override fun insert(word: String) {
        var node: TrieNode? = root
        for (ch in word) {
            if (node?.containsKey(ch) != true) {
                node?.put(ch, TrieNode())
            }
            node = node?.get(ch)
        }
        node?.isEnd = true
    }

    override fun search(word: String): Boolean {
        val node = searchPrefix(word)
        return node != null && node.isEnd
    }

    override fun startsWith(prefix: String): Boolean {
        val node = searchPrefix(prefix)
        return node != null
    }

    private fun searchPrefix(word: String): TrieNode? {
        var node: TrieNode? = root
        for (ch in word) {
            if (node?.containsKey(ch) == true) {
                node = node.get(ch)
            } else {
                return null
            }
        }
        return node
    }

    private data class TrieNode(var isEnd: Boolean = false) {

        private val links: Array<TrieNode?> = Array(ALPHABET_LETTERS_COUNT) { null }

        fun containsKey(ch: Char): Boolean {
            return links[ch - 'a'] != null
        }

        fun get(ch: Char): TrieNode? {
            return links[ch - 'a']
        }

        fun put(ch: Char, node: TrieNode?) {
            links[ch - 'a'] = node
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as TrieNode

            return links.contentEquals(other.links)
        }

        override fun hashCode(): Int {
            return links.contentHashCode()
        }
    }
}

class TrieHashMap : Trie {

    private val head = TrieNode()

    /**
     * Inserts a word into the trie
     */
    override fun insert(word: String) {
        var node: TrieNode? = head
        for (ch in word.toCharArray()) {
            if (node?.charToNode?.containsKey(ch)?.not() == true) {
                node.charToNode[ch] = TrieNode()
            }
            node = node?.charToNode?.get(ch)
        }
        node?.isEnd = true
    }

    /**
     * Returns if the word is in the trie
     */
    override fun search(word: String): Boolean {
        var node = head
        for (ch in word.toCharArray()) {
            if (node.charToNode.containsKey(ch).not()) {
                return false
            }
            node = node.charToNode[ch] ?: return false
        }
        return node.isEnd
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix
     */
    override fun startsWith(prefix: String): Boolean {
        var node = head
        for (ch in prefix.toCharArray()) {
            if (node.charToNode.containsKey(ch).not()) {
                return false
            }
            node = node.charToNode[ch] ?: return false
        }
        return true
    }

    private data class TrieNode(val charToNode: MutableMap<Char, TrieNode> = HashMap(), var isEnd: Boolean = false)
}
