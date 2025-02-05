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
 * 1255. Maximum Score Words Formed by Letters
 * @see <a href="https://leetcode.com/problems/maximum-score-words-formed-by-letters/">Source</a>
 */
fun interface MaxScoreWords {
    operator fun invoke(words: Array<String>, letters: CharArray, score: IntArray): Int
}

/**
 * # Intuition
 *
 * - The problem can be solved using backtracking.
 * - We can iterate over all the words and check if we can form the word using the given letters.
 * - If we can form the word, we can calculate the score and recursively call the function for the next word.
 * - We can return the maximum score.
 *
 * # Approach
 *
 * - Create a helper function that takes the words, letter counts, scores, and the current word index.
 * - Iterate over all the words.
 * - For each word, check if we can form the word using the given letters.
 * - If we can form the word, calculate the score and recursively call the function for the next word.
 * - Return the maximum score.
 *
 * # Complexity
 *
 * - Time complexity:
 * - The time complexity of the backtracking solution is $$O(2^n)$$.
 *
 * - Space complexity:
 *
 * - The space complexity of the backtracking solution is $$O(n)$$.
 */
class MaxScoreWordsBacktracking : MaxScoreWords {
    override operator fun invoke(words: Array<String>, letters: CharArray, score: IntArray): Int {
        if (words.isEmpty() || letters.isEmpty() || score.isEmpty()) {
            return 0
        }
        val letterCounts = IntArray(score.size)
        for (letter in letters) {
            letterCounts[letter.code - 'a'.code]++
        }
        return backtrack(words, letterCounts, score, 0)
    }

    private fun backtrack(words: Array<String>, letterCounts: IntArray, score: IntArray, wordIndex: Int): Int {
        var maxScore = 0
        for (i in wordIndex until words.size) {
            var currentScore = 0
            var isWordValid = true
            for (character in words[i].toCharArray()) {
                letterCounts[character.code - 'a'.code]--
                currentScore += score[character.code - 'a'.code]
                if (letterCounts[character.code - 'a'.code] < 0) isWordValid = false
            }
            if (isWordValid) {
                currentScore += backtrack(words, letterCounts, score, i + 1)
                maxScore = kotlin.math.max(currentScore, maxScore)
            }
            for (character in words[i].toCharArray()) {
                letterCounts[character.code - 'a'.code]++
            }
        }
        return maxScore
    }
}

/**
 * # Intuition
 * - The problem can be solved using depth-first search.
 * - We can iterate over all the words and check if we can form the word using the given letters.
 * - If we can form the word, we can calculate the score and recursively call the function for the next word.
 * - We can return the maximum score.
 *
 * # Approach
 *
 * - Create a helper function that takes the words, letter counts, scores, and the current word index.
 * - Iterate over all the words.
 * - For each word, check if we can form the word using the given letters.
 * - If we can form the word, calculate the score and recursively call the function for the next word.
 * - Return the maximum score.
 *
 * # Complexity
 *
 * - Time complexity:
 * - The time complexity of the depth-first search solution is $$O(2^n)$$.
 *
 * - Space complexity:
 * - The space complexity of the depth-first search solution is $$O(n)$$.
 */
class MaxScoreWordsDFS : MaxScoreWords {
    override operator fun invoke(words: Array<String>, letters: CharArray, score: IntArray): Int {
        val letterCounts = IntArray(ALPHABET_LETTERS_COUNT)
        for (letter in letters) {
            letterCounts[letter.code - 'a'.code]++
        }
        return depthFirstSearch(0, letterCounts, score, words)
    }

    private fun depthFirstSearch(wordIndex: Int, letterCounts: IntArray, score: IntArray, words: Array<String>): Int {
        if (wordIndex == words.size) {
            return 0
        }
        var maxScore = 0
        val currentWord = words[wordIndex]
        var currentScore = 0
        var letterIndex = 0
        val tempLetterCounts: IntArray = letterCounts.copyOf(letterCounts.size)
        while (letterIndex < currentWord.length) {
            val currentLetter = currentWord[letterIndex]
            currentScore += if (tempLetterCounts[currentLetter.code - 'a'.code] > 0) {
                tempLetterCounts[currentLetter.code - 'a'.code]--
                score[currentLetter.code - 'a'.code]
            } else {
                break
            }
            letterIndex++
        }
        if (letterIndex == currentWord.length) {
            maxScore = kotlin.math.max(
                maxScore,
                currentScore + depthFirstSearch(wordIndex + 1, tempLetterCounts, score, words),
            )
        }
        maxScore = kotlin.math.max(depthFirstSearch(wordIndex + 1, letterCounts, score, words), maxScore)
        return maxScore
    }
}
