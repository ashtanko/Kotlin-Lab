/*
 * Copyright 2020 Oleksii Shtanko
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

/**
 * 1160. Find Words That Can Be Formed by Characters
 * @see <a href="https://leetcode.com/problems/find-words-that-can-be-formed-by-characters">Source</a>
 */
fun interface CountCharacters {
    operator fun invoke(words: Array<String>, chars: String): Int
}

class CountCharactersHashMap : CountCharacters {
    override fun invoke(words: Array<String>, chars: String): Int {
        val counts: MutableMap<Char, Int> = HashMap()
        for (c in chars.toCharArray()) {
            counts[c] = counts.getOrDefault(c, 0) + 1
        }

        var ans = 0
        for (word in words) {
            val wordCount: MutableMap<Char, Int> = HashMap()
            for (c in word.toCharArray()) {
                wordCount[c] = wordCount.getOrDefault(c, 0) + 1
            }

            var good = true
            for (c in wordCount.keys) {
                if (counts.getOrDefault(c, 0) < wordCount.getOrDefault(c, 0)) {
                    good = false
                    break
                }
            }

            if (good) {
                ans += word.length
            }
        }

        return ans
    }
}

class CountCharactersArray : CountCharacters {
    override fun invoke(words: Array<String>, chars: String): Int {
        val arr = IntArray(ALPHABET_LETTERS_COUNT)
        for (ch in chars.toCharArray()) {
            arr[ch - 'a']++
        }
        var ans = 0
        for (s in words) {
            val clone = arr.clone()
            for (i in s.indices) {
                clone[s[i] - 'a']--
                if (clone[s[i] - 'a'] < 0) {
                    break
                }
                if (i == s.length - 1) {
                    ans += s.length
                }
            }
        }
        return ans
    }
}

class CountCharactersStd : CountCharacters {
    override fun invoke(words: Array<String>, chars: String): Int {
        val countChars = chars.groupingBy { it }.eachCount()

        return words.filter { word ->
            word.groupingBy { it }.eachCount()
                .all { (w, c) -> c <= countChars.getOrDefault(w, 0) }
        }
            .sumOf { it.length }
    }
}
