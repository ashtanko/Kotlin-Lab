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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * 336. Palindrome Pairs
 * @see <a href="https://leetcode.com/problems/palindrome-pairs/">Source</a>
 */
fun interface PalindromePairs {
    fun palindromePairs(words: Array<String>): List<List<Int>>
}

class PalindromePairsTrie : PalindromePairs {
    override fun palindromePairs(words: Array<String>): List<List<Int>> {
        val res: MutableList<List<Int>> = ArrayList()

        val root = TrieNode()

        for (i in words.indices) {
            addWord(root, words[i], i)
        }

        for (i in words.indices) {
            search(words, i, root, res)
        }

        return res
    }

    private class TrieNode {
        var next: Array<TrieNode?> = arrayOfNulls(ALPHABET_LETTERS_COUNT)
        var index: Int = -1
        var list: MutableList<Int> = ArrayList()
    }

    private fun addWord(root: TrieNode, word: String, index: Int) {
        var root0: TrieNode? = root
        for (i in word.length - 1 downTo 0) {
            val j = word[i].code - 'a'.code
            if (root0?.next?.get(j) == null) {
                root0?.next?.set(j, TrieNode())
            }
            if (isPalindrome(word, 0, i)) {
                root0?.list?.add(index)
            }
            root0 = root0?.next?.get(j)
        }
        root0?.list?.add(index)
        root0?.index = index
    }

    private fun search(
        words: Array<String>,
        i: Int,
        root: TrieNode,
        res: MutableList<List<Int>>,
    ) {
        var node: TrieNode? = root
        for (j in 0 until words[i].length) {
            val safeIdx = node?.index ?: -1
            if (safeIdx >= 0 && node?.index != i && isPalindrome(words[i], j, words[i].length - 1)) {
                res.add(mutableListOf(i, node?.index ?: -1))
            }
            node = node?.next?.get(words[i][j].code - 'a'.code)
            if (node == null) return
        }
        for (j in node?.list ?: emptyList()) {
            if (i == j) continue
            res.add(listOf(i, j))
        }
    }

    private fun isPalindrome(word: String, i: Int, j: Int): Boolean {
        var i0 = i
        var j0 = j
        while (i0 < j0) {
            if (word[i0++] != word[j0--]) return false
        }
        return true
    }
}

class PalindromePairsImpl : PalindromePairs {
    override fun palindromePairs(words: Array<String>): List<List<Int>> {
        val res: MutableList<List<Int>> = ArrayList()
        if (words.isEmpty()) {
            return res
        }
        val map: HashMap<String, Int> = HashMap()
        for (i in words.indices) {
            map[words[i]] = i
        }

        if (map.containsKey("")) {
            val blankIdx = map[""] ?: -1
            for (i in words.indices) {
                if (isPalindrome(words[i])) {
                    if (i == blankIdx) continue
                    res.add(listOf(blankIdx, i))
                    res.add(listOf(i, blankIdx))
                }
            }
        }

        for (i in words.indices) {
            val cutR = reverseStr(words[i])
            if (map.containsKey(cutR)) {
                val found = map[cutR] ?: -1
                if (found == i) continue
                res.add(listOf(i, found))
            }
        }

        reverse(words, map, res)

        return res
    }

    private fun reverse(words: Array<String>, map: HashMap<String, Int>, res: MutableList<List<Int>>) {
        for (i in words.indices) {
            val cur = words[i]
            for (cut in 1 until cur.length) {
                if (isPalindrome(cur.substring(0, cut))) {
                    val cutR = reverseStr(cur.substring(cut))
                    if (map.containsKey(cutR)) {
                        val found = map[cutR] ?: -1
                        if (found == i) continue
                        res.add(listOf(found, i))
                    }
                }
                if (isPalindrome(cur.substring(cut))) {
                    val cutR = reverseStr(cur.substring(0, cut))
                    if (map.containsKey(cutR)) {
                        val found = map[cutR] ?: -1
                        if (found == i) continue
                        res.add(listOf(i, found))
                    }
                }
            }
        }
    }

    private fun reverseStr(str: String?): String {
        val sb = StringBuilder(str)
        return sb.reverse().toString()
    }

    private fun isPalindrome(s: String): Boolean {
        var i = 0
        var j = s.length - 1
        while (i <= j) {
            if (s[i] != s[j]) {
                return false
            }
            i++
            j--
        }
        return true
    }
}
