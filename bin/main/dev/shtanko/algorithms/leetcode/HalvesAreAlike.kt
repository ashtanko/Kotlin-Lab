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

private const val VOWELS = "aeiouAEIOU"

/**
 * Determine if String Halves Are Alike
 * @see <a href="https://leetcode.com/problems/determine-if-string-halves-are-alike/">Source</a>
 */
fun interface HalvesAreAlike {
    operator fun invoke(str: String): Boolean
}

class HalvesAreAlikeBruteForce : HalvesAreAlike {
    override operator fun invoke(str: String): Boolean {
        val length = str.length
        val mid = length / 2
        val firstHalf = str.substring(0, mid)
        val secondHalf = str.substring(mid, length)
        var leftVowelCount = 0
        var rightVowelCount = 0
        for (char in firstHalf) {
            if (VOWELS.contains(char)) leftVowelCount++
        }
        for (char in secondHalf) {
            if (VOWELS.contains(char)) rightVowelCount++
        }
        return leftVowelCount == rightVowelCount
    }
}

class HalvesAreAlikeCount : HalvesAreAlike {
    override operator fun invoke(str: String): Boolean {
        val length = str.length
        val mid = length / 2
        return countVowels(0 until mid, str) == countVowels(mid until length, str)
    }

    private fun countVowels(range: IntRange, s: String): Int {
        var count = 0
        for (index in range) {
            if (VOWELS.indexOf(s[index]) != -1) {
                count++
            }
        }
        return count
    }
}
