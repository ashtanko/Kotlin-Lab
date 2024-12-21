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
 * 2452. Words Within Two Edits of Dictionary
 * @see <a href="https://leetcode.com/problems/words-within-two-edits-of-dictionary/">Source</a>
 */
fun interface TwoEditWords {
    operator fun invoke(queries: Array<String>, dictionary: Array<String>): List<String>
}

class TwoEditWordsBF : TwoEditWords {
    override operator fun invoke(queries: Array<String>, dictionary: Array<String>): List<String> {
        val ans: MutableList<String> = ArrayList()
        for (query in queries) {
            for (word in dictionary) {
                val diff = diff(query, word)
                if (diff <= 2) {
                    ans.add(query)
                    break
                }
            }
        }
        return ans.toList()
    }

    private fun diff(a: String, b: String): Int {
        val n = a.length
        var same = 0
        for (i in 0 until n) {
            if (a[i] == b[i]) {
                same++
            }
        }
        return n - same
    }
}

class TwoEditWordsTrie : TwoEditWords {
    override operator fun invoke(queries: Array<String>, dictionary: Array<String>): List<String> {
        val ans: MutableList<String> = ArrayList()
        for (word in dictionary) {
            insertWord(word)
        }
        for (query in queries) {
            val curr: TNode = root
            if (searchWord(curr, query, 0, 0)) {
                ans.add(query)
            }
        }
        return ans
    }

    private fun insertWord(word: String) {
        var curr: TNode = root
        for (i in word.indices) {
            val childIdx = word[i].code - 'a'.code
            if (curr.children[childIdx] == null) {
                curr.children[childIdx] = TNode(data = word[i])
            }
            curr = curr.children[childIdx]!!
        }
        curr.isEnd = true
    }

    private fun searchWord(root: TNode, word: String, count: Int, index: Int): Boolean {
        if (index == word.length) {
            return count <= 2
        }
        var ans = false
        for (i in 0 until root.children.size) {
            if (root.children[i] != null) {
                ans = ans or searchWord(
                    root.children[i]!!,
                    word,
                    count + if (word[index].code - 'a'.code == i) 0 else 1,
                    index + 1,
                )
            }
        }
        return ans
    }

    private data class TNode(private var data: Char, var isEnd: Boolean = false) {
        val children: Array<TNode?> = arrayOfNulls(ALPHABET_LETTERS_COUNT)
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as TNode

            return children.contentEquals(other.children)
        }

        override fun hashCode(): Int {
            return children.contentHashCode()
        }
    }

    private val root = TNode('/')
}
