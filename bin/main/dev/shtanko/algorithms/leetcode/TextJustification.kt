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

import kotlin.math.max

/**
 * 68. Text Justification
 * @see <a href="https://leetcode.com/problems/text-justification/">Source</a>
 */
fun interface TextJustification {
    operator fun invoke(words: Array<String>, maxWidth: Int): List<String>
}

class TextJustificationImpl : TextJustification {

    override operator fun invoke(words: Array<String>, maxWidth: Int): List<String> {
        var curr = 0
        val out: MutableList<String> = ArrayList()
        while (curr < words.size) {
            var currLineLen = 0
            val start = curr
            var end = curr

            // Determine start and end positions
            while (end < words.size && currLineLen + words[end].length + (end - start) <= maxWidth) {
                currLineLen += words[end].length
                end++
            }
            currLineLen += end - start - 1
            val spaces = IntArray(end - start) { 1 }
            spaces[spaces.size - 1] = 0 // Last word does not appended with space unless it is at last line
            if (currLineLen < maxWidth) {
                if (end == words.size) {
                    // Add spaces to end at last line
                    spaces[spaces.size - 1] += maxWidth - currLineLen
                } else {
                    // Iterate through spaces until all spaces consumed
                    val arrSize = max(spaces.size - 1, 1)
                    val spaceNeeded = maxWidth - currLineLen
                    var i = 0
                    var j = 0
                    while (i < spaceNeeded) {
                        spaces[j]++
                        i++
                        j = (j + 1) % arrSize
                    }
                }
            }
            val sb = StringBuilder()
            for (i in start until end) {
                sb.append(words[i])

                // Add spaces from spaces array
                for (j in 0 until spaces[i - start]) sb.append(" ")
            }
            out.add(sb.toString())
            curr = end
        }
        return out
    }
}

class TextJustificationImpl2 : TextJustification {
    override operator fun invoke(words: Array<String>, maxWidth: Int): List<String> {
        var left = 0
        val result: MutableList<String> = ArrayList()
        while (left < words.size) {
            val right = findRight(left, words, maxWidth)
            result.add(justify(left, right, words, maxWidth))
            left = right + 1
        }
        return result
    }

    private fun justify(left: Int, right: Int, words: Array<String>, maxWidth: Int): String {
        if (left == right) {
            return padRight(words[left], maxWidth)
        }
        var sum = words[left].length
        for (i in left + 1..right) {
            sum += words[i].length
        }
        val isLastLine = right == words.size - 1
        val numWords = right - left
        val numWhitespace = maxWidth - sum
        val numSpacesBetween = if (isLastLine) 1 else numWhitespace / numWords
        var remainder = if (isLastLine) 0 else numWhitespace % numWords
        val result = java.lang.StringBuilder()
        for (i in left until right) {
            result.append(words[i])
            result.append(whitespace(numSpacesBetween))
            result.append(if (remainder-- > 0) " " else "")
        }
        result.append(words[right])
        return if (isLastLine) {
            padRight(result.toString(), maxWidth)
        } else {
            result.toString()
        }
    }

    private fun whitespace(numSpacesBetween: Int): String {
        val sb = java.lang.StringBuilder()
        for (i in 0 until numSpacesBetween) {
            sb.append(" ")
        }
        return sb.toString()
    }

    private fun padRight(s: String, maxWidth: Int): String {
        val sb = java.lang.StringBuilder(s)
        sb.append(whitespace(maxWidth - s.length))
        return sb.toString()
    }

    private fun findRight(left: Int, words: Array<String>, maxWidth: Int): Int {
        var right = left
        var sum = words[right++].length
        while (right < words.size && sum + 1 + words[right].length <= maxWidth) {
            sum += 1 + words[right++].length
        }
        return right - 1
    }
}
