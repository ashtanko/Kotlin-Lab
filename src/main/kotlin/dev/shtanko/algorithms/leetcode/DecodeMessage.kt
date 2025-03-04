/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import dev.shtanko.algorithms.annotations.level.Easy
import java.util.stream.Collectors

/**
 * 2325. Decode the Message
 * @see <a href="https://leetcode.com/problems/decode-the-message">Source</a>
 */
@Easy("https://leetcode.com/problems/decode-the-message")
fun interface DecodeMessage {
    operator fun invoke(key: String, message: String): String
}

class DecodeMessageBruteForce : DecodeMessage {
    override operator fun invoke(key: String, message: String): String {
        val charMapping: MutableMap<Char, Char> = HashMap()
        charMapping[' '] = ' '
        var currentChar = 'a'
        for (keyChar in key.toCharArray()) {
            if (!charMapping.containsKey(keyChar)) {
                charMapping[keyChar] = currentChar++
            }
        }
        return message.chars().mapToObj { char -> charMapping[char.toChar()].toString() }
            .collect(Collectors.joining(""))
    }
}

class DecodeMessageSB : DecodeMessage {
    override operator fun invoke(key: String, message: String): String {
        val charTable = CharArray(ALPHABET_LETTERS_COUNT)
        var currentIndex = 0
        for (char in key.toCharArray()) {
            if (currentIndex < ALPHABET_LETTERS_COUNT && char != ' ' && charTable[char.code - 'a'.code].code == 0) {
                charTable[char.code - 'a'.code] = (currentIndex + 'a'.code).toChar()
                currentIndex++
            }
        }
        val decodedMessage = StringBuilder()
        for (char in message.toCharArray()) {
            decodedMessage.append(if (char == ' ') ' ' else charTable[char.code - 'a'.code])
        }
        return decodedMessage.toString()
    }
}
