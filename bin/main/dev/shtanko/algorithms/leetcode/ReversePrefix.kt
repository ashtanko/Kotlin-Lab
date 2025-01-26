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

import java.util.Stack

/**
 * 2000. Reverse Prefix of Word
 * @see <a href="https://leetcode.com/problems/reverse-prefix-of-word/">Reverse Prefix of Word</a>
 */
fun interface ReversePrefix {
    operator fun invoke(word: String, ch: Char): String
}

class ReversePrefixStack : ReversePrefix {
    override fun invoke(word: String, ch: Char): String {
        val stack: Stack<Char> = Stack()
        val result = StringBuilder()

        var index = 0
        while (index < word.length) {
            stack.push(word[index])
            // Found ch
            if (word[index] == ch) {
                // Add characters through ch to the result in reverse order
                while (stack.isNotEmpty()) {
                    result.append(stack.pop())
                }
                index++
                // Add the rest of the characters to result
                while (index < word.length) {
                    result.append(word[index])
                    index++
                }
                return result.toString()
            }
            index++
        }

        return word
    }
}

class ReversePrefixSearchIndex : ReversePrefix {
    override fun invoke(word: String, ch: Char): String {
        val chIndex = word.indexOf(ch)
        if (chIndex == -1) {
            return word
        }

        val result = java.lang.StringBuilder()

        for (i in word.indices) {
            // Add characters through ch to the result in reverse order
            if (i <= chIndex) {
                result.append(word[chIndex - i])
            } else {
                result.append(word[i])
            }
        }

        return result.toString()
    }
}

class ReversePrefixTwoPointer : ReversePrefix {
    override fun invoke(word: String, ch: Char): String {
        // Add characters to the result in the original order
        val result = word.toCharArray()
        var left = 0
        var right = 0
        while (right < word.length) {
            // We found ch - reverse characters up to ch by swapping
            if (result[right] == ch) {
                while (left <= right) {
                    swap(result, left, right)
                    left++
                    right--
                }
                return String(result)
            }
            right++
        }
        return word
    }

    private fun swap(characters: CharArray, index1: Int, index2: Int) {
        val temp = characters[index2]
        characters[index2] = characters[index1]
        characters[index1] = temp
    }
}
