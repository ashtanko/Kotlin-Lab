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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * 211. Design Add and Search Words Data Structure
 * @see <a href="https://leetcode.com/problems/design-add-and-search-words-data-structure/">Source</a>
 */
interface WordDictionary {
    fun addWord(word: String)

    fun search(word: String): Boolean
}

class WordDictionaryImpl : WordDictionary {
    private val children: Array<WordDictionaryImpl?> = Array(ALPHABET_LETTERS_COUNT) { null }
    private var isEndOfWord = false

    // Adds a word into the data structure.
    override fun addWord(word: String) {
        var curr: WordDictionaryImpl? = this
        for (c in word.toCharArray()) {
            if (curr?.children?.get(c.code - 'a'.code) == null) {
                curr?.children?.set(c.code - 'a'.code, WordDictionaryImpl())
            }
            curr = curr?.children?.get(c.code - 'a'.code)
        }
        curr?.isEndOfWord = true
    }

    // Returns if the word is in the data structure.
    // A word could contain the dot character '.' to represent any one letter.
    override fun search(word: String): Boolean {
        var curr: WordDictionaryImpl = this
        for (i in word.indices) {
            val c: Char = word[i]
            if (c == '.') {
                for (ch in curr.children) {
                    if (ch != null && ch.search(word.substring(i + 1))) {
                        return true
                    }
                }
                return false
            }
            if (curr.children[c.code - 'a'.code] == null) {
                return false
            }
            curr = curr.children[c.code - 'a'.code] ?: return false
        }
        return curr.isEndOfWord
    }
}
