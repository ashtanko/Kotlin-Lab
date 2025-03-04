/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.Deque
import java.util.LinkedList
import java.util.Stack

/**
 * 394. Decode String
 * @see <a href="https://leetcode.com/problems/decode-string/">Source</a>
 */
@Medium("https://leetcode.com/problems/decode-string")
fun interface DecodeString {
    operator fun invoke(input: String): String
}

class DecodeStringStack : DecodeString {
    override operator fun invoke(input: String): String {
        val numStack: Stack<Int> = Stack()
        val strBuild: Stack<StringBuilder> = Stack()
        var str = StringBuilder()
        var num = 0
        for (c in input.toCharArray()) {
            when (c) {
                in '0'..'9' -> {
                    num = num * 10 + c.code - '0'.code
                }

                '[' -> {
                    strBuild.push(str)
                    str = StringBuilder()
                    numStack.push(num)
                    num = 0
                }

                ']' -> {
                    val temp = str
                    str = strBuild.pop()
                    var count: Int = numStack.pop()
                    while (count-- > 0) {
                        str.append(temp)
                    }
                }

                else -> {
                    str.append(c)
                }
            }
        }
        return str.toString()
    }
}

class DecodeStringRecursive : DecodeString {
    override operator fun invoke(input: String): String {
        val charQueue: Deque<Char> = LinkedList()
        for (char in input.toCharArray()) {
            charQueue.offer(char)
        }
        return decodeHelper(charQueue)
    }

    private fun decodeHelper(charQueue: Deque<Char>): String {
        val decodedString = StringBuilder()
        var repeatCount = 0
        while (charQueue.isNotEmpty()) {
            val currentChar: Char = charQueue.poll()
            when {
                Character.isDigit(currentChar) -> {
                    repeatCount = repeatCount * 10 + currentChar.code - '0'.code
                }

                currentChar == '[' -> {
                    val subString = decodeHelper(charQueue)
                    for (i in 0 until repeatCount) decodedString.append(subString)
                    repeatCount = 0
                }

                currentChar == ']' -> {
                    break
                }

                else -> {
                    decodedString.append(currentChar)
                }
            }
        }
        return decodedString.toString()
    }
}
