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

import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 796. Rotate String
 * @see <a href="https://leetcode.com/problems/rotate-string/">Source</a>
 */
@Easy("https://leetcode.com/problems/rotate-string")
fun interface RotateString {
    operator fun invoke(str: String, goal: String): Boolean
}

class RotateStringConcatenation : RotateString {
    override fun invoke(str: String, goal: String): Boolean {
        if (str.length != goal.length) {
            return false
        }

        // Create a new string by concatenating 's' with itself
        val doubledString = str + str

        // Use contains to search for 'goal' in 'doubledString'
        // If contains returns true, 'goal' is a substring
        return doubledString.contains(goal)
    }
}

class RotateStringKMP : RotateString {
    override fun invoke(str: String, goal: String): Boolean {
        // Check if the lengths of both strings are different; if so, they can't be rotations
        if (str.length != goal.length) return false

        // Concatenate 's' with itself to create a new string containing all possible rotations
        val doubledString = str + str

        // Perform KMP substring search to check if 'goal' is a substring of 'doubledString'
        return kmpSearch(doubledString, goal)
    }

    private fun kmpSearch(text: String, pattern: String): Boolean {
        // Precompute the LPS (Longest Prefix Suffix) array for the pattern
        val lps = computeLPS(pattern)
        var textIndex = 0
        var patternIndex = 0
        val textLength = text.length
        val patternLength = pattern.length

        // Loop through the text to find the pattern
        while (textIndex < textLength) {
            // If characters match, move both indices forward
            if (text[textIndex] == pattern[patternIndex]) {
                textIndex++
                patternIndex++
                // If we've matched the entire pattern, return true
                if (patternIndex == patternLength) return true
            }
            // If there's a mismatch after some matches, use the LPS array to skip unnecessary comparisons
            else if (patternIndex > 0) {
                patternIndex = lps[patternIndex - 1]
            }
            // If no matches, move to the next character in text
            else {
                textIndex++
            }
        }
        // Pattern not found in text
        return false
    }

    private fun computeLPS(pattern: String): IntArray {
        val patternLength = pattern.length
        val lps = IntArray(patternLength)
        var length = 0
        var index = 1

        // Build the LPS array
        while (index < patternLength) {
            // If characters match, increment length and set lps value
            if (pattern[index] == pattern[length]) {
                length++
                lps[index++] = length
            }
            // If there's a mismatch, update length using the previous LPS value
            else if (length > 0) {
                length = lps[length - 1]
            }
            // No match and length is zero
            else {
                lps[index++] = 0
            }
        }
        return lps
    }
}
