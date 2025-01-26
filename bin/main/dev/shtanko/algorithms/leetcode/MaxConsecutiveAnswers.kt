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

import kotlin.math.max
import kotlin.math.min

private const val SYMBOL_T = 'T'
private const val SYMBOL_F = 'F'

/**
 * 2024. Maximize the Confusion of an Exam
 * @see <a href="https://leetcode.com/problems/maximize-the-confusion-of-an-exam/">Source</a>
 */
fun interface MaxConsecutiveAnswers {
    operator fun invoke(answerKey: String, k: Int): Int
}

/**
 * Approach 1: Binary Search + Fixed Size Sliding Window
 */
class MaxConsecutiveAnswersBS : MaxConsecutiveAnswers {
    override operator fun invoke(answerKey: String, k: Int): Int {
        val n: Int = answerKey.length
        var left = k
        var right = n

        while (left < right) {
            val mid = (left + right + 1) / 2
            if (isValid(answerKey, mid, k)) {
                left = mid
            } else {
                right = mid - 1
            }
        }

        return left
    }

    private fun isValid(answerKey: String, size: Int, k: Int): Boolean {
        val n = answerKey.length
        val counter: MutableMap<Char, Int> = HashMap()
        for (i in 0 until size) {
            val c = answerKey[i]
            counter[c] = counter.getOrDefault(c, 0) + 1
        }
        if (min(counter.getOrDefault(SYMBOL_T, 0), counter.getOrDefault(SYMBOL_F, 0)) <= k) {
            return true
        }
        for (i in size until n) {
            val c1 = answerKey[i]
            counter[c1] = counter.getOrDefault(c1, 0) + 1
            val c2 = answerKey[i - size]
            counter[c2] = counter.getOrDefault(c2, 0) - 1
            if (min(counter.getOrDefault(SYMBOL_T, 0), counter.getOrDefault(SYMBOL_F, 0)) <= k) {
                return true
            }
        }
        return false
    }
}

/**
 * Approach 2: Sliding Window
 */
class MaxConsecutiveAnswersSlidingWindow : MaxConsecutiveAnswers {
    override operator fun invoke(answerKey: String, k: Int): Int {
        var maxSize = k
        val count: MutableMap<Char, Int> = HashMap()
        for (i in 0 until k) {
            count[answerKey[i]] = count.getOrDefault(answerKey[i], 0) + 1
        }

        var left = 0
        for (right in k until answerKey.length) {
            count[answerKey[right]] = count.getOrDefault(answerKey[right], 0) + 1
            while (min(count.getOrDefault(SYMBOL_T, 0), count.getOrDefault(SYMBOL_F, 0)) > k) {
                count[answerKey[left]] = count[answerKey[left]]!! - 1
                left++
            }
            maxSize = max(maxSize, right - left + 1)
        }

        return maxSize
    }
}

/**
 * Approach 3: Advanced Sliding Window
 */
class MaxConsecutiveAnswersSlidingWindow2 : MaxConsecutiveAnswers {
    override operator fun invoke(answerKey: String, k: Int): Int {
        var maxSize = 0
        val count: MutableMap<Char, Int> = HashMap()

        for (right in answerKey.indices) {
            count[answerKey[right]] = count.getOrDefault(answerKey[right], 0) + 1
            val minor = min(count.getOrDefault(SYMBOL_T, 0), count.getOrDefault(SYMBOL_F, 0))
            if (minor <= k) {
                maxSize++
            } else {
                count[answerKey[right.minus(maxSize)]] = count[answerKey[right.minus(maxSize)]]!! - 1
            }
        }

        return maxSize
    }
}
