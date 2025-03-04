/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

/**
 * 151. Reverse Words in a String
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string">Source</a>
 */
fun interface ReverseWordsInString {
    operator fun invoke(str: String): String
}

class ReverseWordsInStringTwoPointers : ReverseWordsInString {
    override fun invoke(str: String): String {
        val trimmed = str.trim()
        val words = trimmed.split("\\s+".toRegex()).toMutableList()
        var left = 0
        var right = words.size - 1
        while (left < right) {
            val temp = words[left]
            words[left] = words[right]
            words[right] = temp
            left++
            right--
        }

        return words.joinToString(" ")
    }
}
