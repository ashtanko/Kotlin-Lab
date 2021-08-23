/*
 * Copyright 2021 Alexey Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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
 * @link https://leetcode.com/problems/detect-capital/
 */
interface DetectCapital {
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
        if (Character.isUpperCase(word.first()) && Character.isUpperCase(word[1])) {
            for (i in 2 until n) {
                if (Character.isLowerCase(word[i])) {
                    return false
                }
            }
            // case 2 and case 3
        } else {
            for (i in 1 until n) {
                if (Character.isUpperCase(word[i])) {
                    return false
                }
            }
        }

        // if pass one of the cases
        return true
    }
}

/**
 * Use kotlin api
 */
class DetectCapitalKotlinApi : DetectCapital {
    override fun detectCapitalUse(word: String): Boolean {
        if (word.isEmpty()) return false
        return word.count {
            it.isUpperCase()
        } == word.length
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
