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

import dev.shtanko.algorithms.annotations.TwoPointers
import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 1455. Check If a Word Occurs As a Prefix of Any Word in a Sentence
 * @see <a href="https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/">Source</a>
 */
@Easy("https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/")
sealed interface IsPrefixOfWord {
    operator fun invoke(sentence: String, searchWord: String): Int

    @TwoPointers
    data object TwoPointersStrategy : IsPrefixOfWord {
        override fun invoke(sentence: String, searchWord: String): Int {
            // Check for invalid input
            if (searchWord.length > sentence.length) {
                return -1
            }

            // Initialize the word position counter
            var currentWordPosition = 1
            // Initialize the current index in the sentence
            var currentIndex = 0
            // Get the length of the sentence
            val sentenceLength = sentence.length

            // Loop through the sentence
            while (currentIndex < sentenceLength) {
                // Skip leading spaces
                while (currentIndex < sentenceLength && sentence[currentIndex] == ' ') {
                    currentWordPosition++
                    while (currentIndex < sentenceLength && sentence[currentIndex] == ' ') {
                        currentIndex++
                    }
                }

                // Check if the current word starts with searchWord
                var matchCount = 0
                while (
                    currentIndex < sentenceLength &&
                    matchCount < searchWord.length &&
                    sentence[currentIndex] == searchWord[matchCount]
                ) {
                    currentIndex++
                    matchCount++
                }

                // If the entire searchWord matches, return the current word position
                if (matchCount == searchWord.length) {
                    return currentWordPosition
                }

                // Move to the end of the current word
                while (currentIndex < sentenceLength && sentence[currentIndex] != ' ') {
                    currentIndex++
                }
            }

            // If no match is found, return -1
            return -1
        }
    }

    data object BuiltInFunctions : IsPrefixOfWord {
        override fun invoke(sentence: String, searchWord: String): Int {
            // Split the sentence into words
            val words = sentence.split(" ")
            // Iterate over the words with their positions
            for ((index, word) in words.withIndex()) {
                // Check if the word starts with the searchWord
                if (word.startsWith(searchWord)) {
                    // If a match is found, return the current word position (adjusted to 1-based index)
                    return index + 1
                }
            }
            // If no match is found, return -1
            return -1
        }
    }
}
