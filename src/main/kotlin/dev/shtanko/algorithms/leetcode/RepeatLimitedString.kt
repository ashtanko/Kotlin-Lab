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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.PriorityQueue

/**
 * 2182. Construct String With Repeat Limit
 * @see <a href="https://leetcode.com/problems/construct-string-with-repeat-limit/">Source</a>
 */
@Medium
sealed interface RepeatLimitedString {
    operator fun invoke(s: String, repeatLimit: Int): String

    @dev.shtanko.algorithms.annotations.Greedy
    data object Greedy : RepeatLimitedString {
        override fun invoke(s: String, repeatLimit: Int): String {
            val freq = IntArray(ALPHABET_LETTERS_COUNT)
            for (ch in s) {
                freq[ch - 'a']++
            }

            val result = StringBuilder()
            var currentCharIndex = ALPHABET_LETTERS_COUNT - 1 // Start from the largest character
            while (currentCharIndex >= 0) {
                if (freq[currentCharIndex] == 0) {
                    currentCharIndex--
                    continue
                }

                val use = minOf(freq[currentCharIndex], repeatLimit)
                repeat(use) {
                    result.append('a' + currentCharIndex)
                }
                freq[currentCharIndex] -= use

                if (freq[currentCharIndex] > 0) { // Need to add a smaller character
                    var smallerCharIndex = currentCharIndex - 1
                    while (smallerCharIndex >= 0 && freq[smallerCharIndex] == 0) {
                        smallerCharIndex--
                    }
                    if (smallerCharIndex < 0) {
                        break
                    }
                    result.append('a' + smallerCharIndex)
                    freq[smallerCharIndex]--
                }
            }

            return result.toString()
        }
    }

    data object HeapOptimizedGreedy : RepeatLimitedString {
        override fun invoke(s: String, repeatLimit: Int): String {
            val freq = mutableMapOf<Char, Int>()
            for (ch in s) {
                freq[ch] = freq.getOrDefault(ch, 0) + 1
            }

            val maxHeap = PriorityQueue<Char> { a, b -> b.compareTo(a) }
            freq.keys.forEach { maxHeap.offer(it) }

            val result = StringBuilder()

            while (maxHeap.isNotEmpty()) {
                val ch = maxHeap.poll()
                val count = freq[ch] ?: 0

                val use = minOf(count, repeatLimit)
                repeat(use) { result.append(ch) }

                freq[ch] = count - use

                if (freq[ch]!! > 0 && maxHeap.isNotEmpty()) {
                    val nextCh = maxHeap.poll()
                    result.append(nextCh)
                    freq[nextCh] = freq[nextCh]!! - 1
                    if (freq[nextCh]!! > 0) {
                        maxHeap.offer(nextCh)
                    }
                    maxHeap.offer(ch)
                }
            }

            return result.toString()
        }
    }

    @dev.shtanko.algorithms.annotations.TwoPointers
    data object TwoPointers : RepeatLimitedString {
        override fun invoke(s: String, repeatLimit: Int): String {
            val chars = s.toCharArray()
            chars.sort()
            val result = StringBuilder()

            var freq = 1
            var pointer = chars.size - 1

            for (i in chars.size - 1 downTo 0) {
                if (result.isNotEmpty() && result.last() == chars[i]) {
                    if (freq < repeatLimit) {
                        result.append(chars[i])
                        freq++
                    } else {
                        while (pointer >= 0 && (chars[pointer] == chars[i] || pointer > i)) {
                            pointer--
                        }

                        if (pointer >= 0) {
                            result.append(chars[pointer])
                            val temp = chars[i]
                            chars[i] = chars[pointer]
                            chars[pointer] = temp
                            freq = 1
                        } else {
                            break
                        }
                    }
                } else {
                    result.append(chars[i])
                    freq = 1
                }
            }

            return result.toString()
        }
    }
}
