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
 * 443. String Compression
 * @see <a href="https://leetcode.com/problems/string-compression/">Source</a>
 */
fun interface StringCompression {
    operator fun invoke(chars: CharArray): Int
}

class StringCompressionSimple : StringCompression {
    /**
     * This function compresses the given character array in-place and returns the new length of the array.
     * It iterates over the array and for each character, it counts the number of times it appears consecutively.
     * Then it replaces the sequence of the same character with that character followed by the count.
     * If the character appears only once, it doesn't add the count after the character.
     * @param chars The character array to compress.
     * @return The new length of the compressed character array.
     */
    override fun invoke(chars: CharArray): Int {
        var indexAns = 0
        var index = 0
        while (index < chars.size) {
            val currentChar = chars[index]
            var count = 0
            while (index < chars.size && chars[index] == currentChar) {
                index++
                count++
            }
            chars[indexAns++] = currentChar
            if (count != 1) {
                for (character in count.toString().toCharArray()) {
                    chars[indexAns++] = character
                }
            }
        }
        return indexAns
    }
}
