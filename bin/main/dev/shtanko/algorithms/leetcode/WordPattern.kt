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

/**
 * 290. Word Pattern
 * @see <a href="https://leetcode.com/problems/word-pattern/">Source</a>
 */
fun interface WordPattern {
    operator fun invoke(pattern: String, s: String): Boolean
}

class WordPatternSimple : WordPattern {
    override operator fun invoke(pattern: String, s: String): Boolean {
        val words: Array<String> = s.split(" ").toTypedArray()
        if (words.size != pattern.length) {
            return false
        }

        val charToWord: MutableMap<Char, String> = HashMap()
        val wordToChar: MutableMap<String, Char> = HashMap()

        for (i in pattern.indices) {
            val c: Char = pattern[i]
            val word = words[i]
            if (!charToWord.containsKey(c)) {
                charToWord[c] = word
            }
            if (!wordToChar.containsKey(word)) {
                wordToChar[word] = c
            }
            if (charToWord[c] != word || wordToChar[word] != c) {
                return false
            }
        }

        return true
    }
}
