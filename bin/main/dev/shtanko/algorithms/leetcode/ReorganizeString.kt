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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import java.util.PriorityQueue

/**
 * 767. Reorganize String
 * @see <a href="https://leetcode.com/problems/reorganize-string/">Source</a>
 */
internal fun interface ReorganizeString {
    operator fun invoke(s: String): String
}

/**
 * Approach 1: Counting and Priority Queue
 */
class ReorganizeStringPQ : ReorganizeString {
    override operator fun invoke(s: String): String {
        val charCounts = IntArray(ALPHABET_LETTERS_COUNT)
        for (c in s.toCharArray()) {
            charCounts[c.code - 'a'.code] = charCounts[c.code - 'a'.code] + 1
        }

        // Max heap ordered by character counts
        val pq: PriorityQueue<IntArray> = PriorityQueue<IntArray> { a, b ->
            b[1].compareTo(a[1])
        }
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            if (charCounts[i] > 0) {
                pq.offer(intArrayOf(i + 'a'.code, charCounts[i]))
            }
        }

        val sb = StringBuilder()
        while (pq.isNotEmpty()) {
            val first = pq.poll()
            if (sb.isEmpty() || first[0] != sb[sb.length - 1].code) {
                sb.append(first[0].toChar())
                if (--first[1] > 0) {
                    pq.offer(first)
                }
            } else {
                if (pq.isEmpty()) {
                    return ""
                }
                val second = pq.poll()
                sb.append(second[0])
                if (--second[1] > 0) {
                    pq.offer(second)
                }
                pq.offer(first)
            }
        }

        return sb.toString()
    }
}

/**
 * Approach 2: Counting and Odd/Even
 */
class ReorganizeStringCounting : ReorganizeString {
    override operator fun invoke(s: String): String {
        val charCounts = IntArray(ALPHABET_LETTERS_COUNT)
        for (c in s.toCharArray()) {
            charCounts[c.code - 'a'.code]++
        }
        var maxCount = 0
        var letter = 0
        for (i in charCounts.indices) {
            if (charCounts[i] > maxCount) {
                maxCount = charCounts[i]
                letter = i
            }
        }
        if (maxCount > (s.length + 1) / 2) {
            return ""
        }
        val ans = CharArray(s.length)
        var index = 0

        // Place the most frequent letter
        while (charCounts[letter] != 0) {
            ans[index] = (letter + 'a'.code).toChar()
            index += 2
            charCounts[letter]--
        }

        // Place rest of the letters in any order
        for (i in charCounts.indices) {
            while (charCounts[i] > 0) {
                if (index >= s.length) {
                    index = 1
                }
                ans[index] = (i + 'a'.code).toChar()
                index += 2
                charCounts[i]--
            }
        }

        return String(ans)
    }
}
