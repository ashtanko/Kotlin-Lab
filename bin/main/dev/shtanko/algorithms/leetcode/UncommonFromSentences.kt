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

/**
 * 884. Uncommon Words from Two Sentences
 * @see <a href="https://leetcode.com/problems/uncommon-words-from-two-sentences/">Source</a>
 */
fun interface UncommonFromSentences {
    operator fun invoke(s1: String, s2: String): Array<String>
}

data object UncommonFromSentencesSolution : UncommonFromSentences {
    override fun invoke(s1: String, s2: String): Array<String> {
        return "$s1 $s2".split(" ")
            .groupingBy { it }
            .eachCount()
            .filter { it.value == 1 }
            .map { it.key }
            .toTypedArray()
    }
}

data object UncommonFromSentencesMap : UncommonFromSentences {
    override fun invoke(s1: String, s2: String): Array<String> {
        val wordCount = mutableMapOf<String, Int>()
        val words = "$s1 $s2".split(" ")

        // Count the occurrences of each word
        for (word in words) {
            wordCount[word] = wordCount.getOrDefault(word, 0) + 1
        }

        // Create a list to store the uncommon words (those with a count of 1)
        val result = mutableListOf<String>()

        for ((word, count) in wordCount) {
            if (count == 1) {
                result.add(word)
            }
        }

        return result.toTypedArray()
    }
}
