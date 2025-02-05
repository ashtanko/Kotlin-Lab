/*
 * Copyright 2022 Oleksii Shtanko
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

fun interface VerbalArithmeticPuzzle {
    operator fun invoke(words: Array<String>, result: String): Boolean
}

class VerbalArithmeticPuzzleBacktracking : VerbalArithmeticPuzzle {

    companion object {
        private val POW_10 = intArrayOf(1, 10, 100, 1000, 10000, 100000, 1000000)
        private const val ARR_SIZE = 91
    }

    override fun invoke(words: Array<String>, result: String): Boolean {
        val charSet: MutableSet<Char> = HashSet()
        val charCount = IntArray(ARR_SIZE)
        val nonLeadingZero = BooleanArray(ARR_SIZE) // ASCII of `A..Z` chars are in range `65..90`

        for (word in words) {
            val cs = word.toCharArray()
            for (i in cs.indices) {
                if (i == 0 && cs.size > 1) {
                    nonLeadingZero[cs[i].code] = true
                }
                charSet.add(cs[i])
                charCount[cs[i].code] += POW_10[cs.size - i - 1] // charCount is calculated by units
            }
        }
        val cs = result.toCharArray()
        for (i in cs.indices) {
            if (i == 0 && cs.size > 1) {
                nonLeadingZero[cs[i].code] = true
            }
            charSet.add(cs[i])
            charCount[cs[i].code] -= POW_10[cs.size - i - 1] // charCount is calculated by units
        }
        val used = BooleanArray(10)
        val charList = CharArray(charSet.size)
        var i = 0
        for (c in charSet) {
            charList[i++] = c
        }
        return backtracking(used, charList, nonLeadingZero, 0, 0, charCount)
    }

    private fun backtracking(
        used: BooleanArray,
        charList: CharArray,
        nonLeadingZero: BooleanArray,
        step: Int,
        diff: Int,
        charCount: IntArray,
    ): Boolean {
        if (step == charList.size) return diff == 0 // difference between sum of words and result equal to 0
        for (d in 0..9) { // each character is decoded as one digit (0 - 9).
            val c = charList[step]
            // decoded as one number without leading zeros.
            if (!used[d] && (d > 0 || !nonLeadingZero[c.code])) {
                used[d] = true
                if (backtracking(
                        used,
                        charList,
                        nonLeadingZero,
                        step + 1,
                        diff + charCount[c.code] * d,
                        charCount,
                    )
                ) {
                    return true
                }
                used[d] = false
            }
        }
        return false
    }
}
