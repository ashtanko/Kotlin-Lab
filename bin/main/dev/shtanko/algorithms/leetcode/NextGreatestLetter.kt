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
 * 744. Find Smallest Letter Greater Than Target
 * @see <a href="https://leetcode.com/problems/find-smallest-letter-greater-than-target/">Source</a>
 */
fun interface NextGreatestLetter {
    operator fun invoke(letters: CharArray, target: Char): Char
}

/**
 * Approach 1: Brute Force
 */
class NextGreatestLetterBruteForce : NextGreatestLetter {
    override operator fun invoke(letters: CharArray, target: Char): Char {
        for (letter in letters) {
            if (letter > target) {
                return letter
            }
        }
        return letters[0]
    }
}

/**
 * Approach 2: Binary Search
 */
class NextGreatestLetterBinarySearch : NextGreatestLetter {
    override operator fun invoke(letters: CharArray, target: Char): Char {
        var left = 0
        var right: Int = letters.size - 1
        var mid: Int
        while (left <= right) {
            mid = (left + right) / 2
            if (letters[mid] <= target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return if (left == letters.size) letters[0] else letters[left]
    }
}
