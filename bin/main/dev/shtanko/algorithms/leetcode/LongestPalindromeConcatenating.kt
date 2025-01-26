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
import kotlin.math.min

/**
 * 2131. Longest Palindrome by Concatenating Two-Letter Words
 * @see <a href="https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/">Source</a>
 */
fun interface LongestPalindromeConcatenating {
    fun longestPalindrome(words: Array<String>): Int
}

/**
 * Approach 1: A Hash Map Approach
 */
class LongestPalindromeConcatenatingMap : LongestPalindromeConcatenating {
    override fun longestPalindrome(words: Array<String>): Int {
        val count = HashMap<String, Int>()
        for (word in words) {
            val countOfTheWord = count[word]
            if (countOfTheWord == null) {
                count[word] = 1
            } else {
                count[word] = countOfTheWord + 1
            }
        }
        var answer = 0
        var central = false
        for ((word, countOfTheWord) in count) {
            // if the word is a palindrome
            if (word[0] == word[1]) {
                if (countOfTheWord % 2 == 0) {
                    answer += countOfTheWord
                } else {
                    answer += countOfTheWord - 1
                    central = true
                }
                // consider a pair of non-palindrome words such that one is the reverse of another
            } else if (word[0] < word[1]) {
                val reversedWord = "" + word[1] + word[0]
                if (count.containsKey(reversedWord)) {
                    answer += 2 * min(countOfTheWord, count.getOrDefault(reversedWord, 1))
                }
            }
        }
        if (central) {
            answer++
        }
        return 2 * answer
    }
}

/**
 * Approach 2: A Two-Dimensional Array Approach
 */
class LongestPalindromeConcatenatingArr : LongestPalindromeConcatenating {

    override fun longestPalindrome(words: Array<String>): Int {
        val count = Array(ALPHABET_LETTERS_COUNT) {
            IntArray(ALPHABET_LETTERS_COUNT)
        }
        for (word in words) {
            count[word[0].code - 'a'.code][word[1].code - 'a'.code]++
        }
        var answer = 0
        var central = false
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            if (count[i][i] % 2 == 0) {
                answer += count[i][i]
            } else {
                answer += count[i][i] - 1
                central = true
            }
            for (j in i + 1 until ALPHABET_LETTERS_COUNT) {
                answer += 2 * min(count[i][j], count[j][i])
            }
        }
        if (central) {
            answer++
        }
        return 2 * answer
    }
}
