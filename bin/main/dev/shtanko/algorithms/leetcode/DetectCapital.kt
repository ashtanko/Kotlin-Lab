/*
 * Copyright 2021 Oleksii Shtanko
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
 * 520. Detect Capital
 * @see <a href="https://leetcode.com/problems/detect-capital/">Source</a>
 */
fun interface DetectCapital {
    fun detectCapitalUse(word: String): Boolean
}

/**
 * Approach 1: Character by Character
 */
class DetectCapitalCharacter : DetectCapital {
    override fun detectCapitalUse(word: String): Boolean {
        val n: Int = word.length
        if (n == 1) {
            return true
        } else if (n == 0) {
            return false
        }

        // case 1: All capital
        if (word.first().isUpperCase() && word[1].isUpperCase()) {
            for (i in 2 until n) {
                if (word[i].isLowerCase()) {
                    return false
                }
            }
            // case 2 and case 3
        } else {
            for (i in 1 until n) {
                if (word[i].isUpperCase()) {
                    return false
                }
            }
        }

        // if pass one of the cases
        return true
    }
}

/**
 * Approach 2: Regex
 */
class DetectCapitalRegex : DetectCapital {
    override fun detectCapitalUse(word: String): Boolean {
        if (word.isEmpty()) return false
        return word.matches("[A-Z]*|.[a-z]*".toRegex())
    }
}
