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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * Find and Replace Pattern
 * @see <a href="https://leetcode.com/problems/find-and-replace-pattern/">Source</a>
 */
fun interface FindAndReplacePattern {
    operator fun invoke(words: Array<String>, pattern: String): List<String>
}

class FRPTwoMaps : FindAndReplacePattern {
    override operator fun invoke(words: Array<String>, pattern: String): List<String> {
        val ans: MutableList<String> = ArrayList()
        for (word in words) if (match(word, pattern)) ans.add(word)
        return ans
    }

    private fun match(word: String, pattern: String): Boolean {
        val m1: MutableMap<Char?, Char?> = HashMap()
        val m2: MutableMap<Char?, Char?> = HashMap()
        for (i in word.indices) {
            val w = word[i]
            val p = pattern[i]
            if (!m1.containsKey(w)) m1[w] = p
            if (!m2.containsKey(p)) m2[p] = w
            if (m1[w] != p || m2[p] != w) return false
        }
        return true
    }
}

class FRPOneMap : FindAndReplacePattern {
    override operator fun invoke(words: Array<String>, pattern: String): List<String> {
        val ans: MutableList<String> = ArrayList()
        for (word in words) if (match(word, pattern)) ans.add(word)
        return ans
    }

    private fun match(word: String, pattern: String): Boolean {
        val m: MutableMap<Char?, Char?> = HashMap()
        for (i in word.indices) {
            val w = word[i]
            val p = pattern[i]
            if (!m.containsKey(w)) m[w] = p
            if (m[w] != p) return false
        }
        val seen = BooleanArray(ALPHABET_LETTERS_COUNT)
        for (p in m.values) {
            if (seen[p?.minus('a')!!]) return false
            seen[p - 'a'] = true
        }
        return true
    }
}
