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

package dev.shtanko.algorithms.mocks.interview

/**
 * Given a list of words, find all the pair of words for which one of them is a prefix or suffix of the other.
 */
fun findPrefixOrSuffixPairs(words: List<String>): List<Pair<String, String>> {
    val trie = Trie()
    val reverseTrie = Trie()

    for (word in words) {
        trie.insert(word, true)
        reverseTrie.insert(word, false)
    }

    val wordPairs = mutableListOf<Pair<String, String>>()
    getNextWords(trie.root, wordPairs)
    getNextWords(reverseTrie.root, wordPairs)

    return wordPairs
}

private fun getNextWords(node: TrieNode, wordPairs: MutableList<Pair<String, String>>): List<String> {
    val nextWords = mutableListOf<String>()

    val nextNonDelimiterChildren = node.children.filterKeys { it != DELIMITER }

    for ((_, child) in nextNonDelimiterChildren) {
        nextWords.addAll(getNextWords(child, wordPairs))
    }

    if (DELIMITER in node.children) {
        val wordEndingHere = node.children[DELIMITER]!!.word!!
        for (nextWord in nextWords) {
            wordPairs.add(wordEndingHere to nextWord)
        }
        nextWords.add(wordEndingHere)
    }

    return nextWords
}

const val DELIMITER = '*'

private class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
    var word: String? = null
}

private class Trie {
    val root = TrieNode()

    fun insert(word: String, leftToRight: Boolean) {
        var currentNode = root

        val range = if (leftToRight) word.indices else word.indices.reversed()

        for (idx in range) {
            val ch = word[idx]

            if (ch !in currentNode.children) {
                currentNode.children[ch] = TrieNode()
            }

            currentNode = currentNode.children[ch]!!
        }

        currentNode.children[DELIMITER] = TrieNode().also {
            it.word = word
        }
    }
}
