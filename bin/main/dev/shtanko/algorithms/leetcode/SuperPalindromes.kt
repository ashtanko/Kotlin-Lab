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

import dev.shtanko.extensions.isPalindrome

enum class SPNumType(val value: Int) {
    EVEN(1),
    ODD(2),
}

/**
 * 906. Super Palindromes
 * @see <a href="https://leetcode.com/problems/super-palindromes/">Source</a>
 */
object SuperPalindromes {
    private const val MAX_ITERATIONS = 100_000

    fun superPalindromesInRange(leftBoundary: String, rightBoundary: String): Int {
        val leftLimit = leftBoundary.toLong()
        val rightLimit = rightBoundary.toLong()
        var palindromeCount = 0
        countPalindromes(leftLimit, rightLimit, SPNumType.ODD) {
            palindromeCount++
        }
        countPalindromes(leftLimit, rightLimit, SPNumType.EVEN) {
            palindromeCount++
        }
        return palindromeCount
    }

    private fun countPalindromes(
        leftLimit: Long,
        rightLimit: Long,
        palindromeType: SPNumType,
        incrementCount: () -> Unit,
    ) {
        for (iteration in 1 until MAX_ITERATIONS) {
            val stringBuilder = StringBuilder(iteration.toString())
            for (index in stringBuilder.length - palindromeType.value downTo 0) {
                stringBuilder.append(stringBuilder[index])
            }
            var palindromeCandidate = stringBuilder.toString().toLong()
            palindromeCandidate *= palindromeCandidate
            if (palindromeCandidate > rightLimit) break
            if (palindromeCandidate >= leftLimit && palindromeCandidate.isPalindrome()) incrementCount()
        }
    }
}
