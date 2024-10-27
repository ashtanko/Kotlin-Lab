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
import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 212. Word Search II
 * @see <a href="https://leetcode.com/problems/word-search-ii/">Source</a>
 */
@Hard("https://leetcode.com/problems/word-search-ii")
fun interface WordSearch2 {
    operator fun invoke(board: Array<CharArray>, words: Array<String>): List<String>
}

/**
 * Backtracking + Trie
 */
class WordSearch2Trie : WordSearch2 {
    override fun invoke(board: Array<CharArray>, words: Array<String>): List<String> {
        val result: MutableList<String> = ArrayList()
        val root: TrieNode = buildTrie(words)
        for (row in board.indices) {
            for (col in board[0].indices) {
                search(board, row, col, root, result)
            }
        }
        return result
    }

    private fun search(board: Array<CharArray>, row: Int, col: Int, node: TrieNode, result: MutableList<String>) {
        var currentNode = node
        val char = board[row][col]
        if (char == '#' || currentNode.next[char.code - 'a'.code] == null) return
        currentNode = currentNode.next[char.code - 'a'.code]!!
        if (currentNode.word != null) {
            result.add(currentNode.word!!)
            currentNode.word = null
        }
        board[row][col] = '#'
        if (row > 0) {
            search(board, row - 1, col, currentNode, result)
        }
        if (col > 0) {
            search(board, row, col - 1, currentNode, result)
        }
        if (row < board.size - 1) {
            search(board, row + 1, col, currentNode, result)
        }
        if (col < board[0].size - 1) {
            search(board, row, col + 1, currentNode, result)
        }
        board[row][col] = char
    }

    private fun buildTrie(words: Array<String>): TrieNode {
        val root = TrieNode()
        for (word in words) {
            var currentNode: TrieNode? = root
            for (char in word.toCharArray()) {
                val index = char.code - 'a'.code
                if (currentNode?.next?.get(index) == null) currentNode?.next?.set(index, TrieNode())
                currentNode = currentNode?.next?.get(index)
            }
            currentNode?.word = word
        }
        return root
    }

    class TrieNode {
        var next = arrayOfNulls<TrieNode>(ALPHABET_LETTERS_COUNT)
        var word: String? = null
    }
}
