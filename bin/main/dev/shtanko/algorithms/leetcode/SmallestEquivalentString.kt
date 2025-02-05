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

/**
 * 1061. Lexicographically The Smallest Equivalent String
 * @see <a href="https://leetcode.com/problems/lexicographically-smallest-equivalent-string/">Source</a>
 */
fun interface SmallestEquivalentString {
    operator fun invoke(s1: String, s2: String, baseStr: String): String
}

class SmallestEquivalentStringUnion : SmallestEquivalentString {
    override operator fun invoke(s1: String, s2: String, baseStr: String): String {
        val graph = IntArray(ALPHABET_LETTERS_COUNT)
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            graph[i] = i
        }
        for (i in s1.indices) {
            val a: Int = s1[i] - 'a'
            val b: Int = s2[i] - 'a'
            val end1 = find(graph, b)
            val end2 = find(graph, a)
            if (end1 < end2) {
                graph[end2] = end1
            } else {
                graph[end1] = end2
            }
        }
        val sb = StringBuilder()
        for (i in baseStr.indices) {
            val c: Char = baseStr[i]
            sb.append(('a'.code + find(graph, c.code - 'a'.code)).toChar())
        }
        return sb.toString()
    }

    private fun find(graph: IntArray, idx: Int): Int {
        var i = idx
        while (graph[i] != i) {
            i = graph[i]
        }
        return i
    }
}
