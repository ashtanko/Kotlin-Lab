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

import kotlin.math.min

/**
 * 2707. Extra Characters in a String
 * @see <a href="https://leetcode.com/problems/extra-characters-in-a-string">Source</a>
 */
fun interface ExtraCharactersInString {
    operator fun invoke(str: String, dictionary: Array<String>): Int
}

/**
 * Approach 1: Top Down Dynamic Programming with Substring Method
 */
class ExtraCharactersInStringTopDown : ExtraCharactersInString {
    private lateinit var memo: Array<Int>
    private lateinit var dictionarySet: HashSet<String>

    override fun invoke(str: String, dictionary: Array<String>): Int {
        val n = str.length
        memo = Array(n) { 0 }
        dictionarySet = HashSet(dictionary.toList())
        return dp(0, n, str)
    }

    private fun dp(start: Int, n: Int, s: String): Int {
        if (start == n) {
            return 0
        }
        // To count this character as a leftover character
        // move to index 'start + 1'
        var ans = dp(start + 1, n, s) + 1
        for (end in start until n) {
            val curr = s.substring(start, end + 1)
            if (dictionarySet.contains(curr)) {
                ans = min(ans.toDouble(), dp(end + 1, n, s).toDouble()).toInt()
            }
        }
        return ans.also { memo[start] = it }
    }
}

/**
 * Approach 2: Bottom Up Dynamic Programming with Substring Method
 */
class ExtraCharactersInStringBottomUp : ExtraCharactersInString {
    override fun invoke(str: String, dictionary: Array<String>): Int {
        val n = str.length
        val dictionarySet = HashSet(dictionary.toList())
        val dp = IntArray(n + 1)

        for (start in n - 1 downTo 0) {
            dp[start] = dp[start + 1] + 1
            for (end in start until n) {
                val curr = str.substring(start, end + 1)
                if (dictionarySet.contains(curr)) {
                    dp[start] = dp[start].coerceAtMost(dp[end + 1])
                }
            }
        }

        return dp[0]
    }
}

/**
 * Approach 3: Top Down Dynamic Programming with Trie
 */
class ExtraCharactersInStringTopDownTrie : ExtraCharactersInString {
    private var root: TrieNode? = null
    private lateinit var memo: Array<Int>

    override fun invoke(str: String, dictionary: Array<String>): Int {
        val n: Int = str.length
        root = dictionary.buildTrie()
        memo = Array(n + 1) { 0 }
        return dp(0, n, str)
    }

    private fun dp(start: Int, n: Int, s: String): Int {
        if (start == n) {
            return 0
        }
        var node = root
        // To count this character as a leftover character
        // move to index 'start + 1'
        var ans = dp(start + 1, n, s) + 1
        for (end in start until n) {
            val c = s[end]
            if (node?.children?.containsKey(c) == false) {
                break
            }
            node = node?.children?.get(c)
            if (node?.isWord == true) {
                ans = min(ans.toDouble(), dp(end + 1, n, s).toDouble()).toInt()
            }
        }
        return ans.also { memo[start] = it }
    }
}

/**
 * Approach 4: Bottom Up Dynamic Programming with Trie
 */
class ExtraCharactersInStringBottomUpTrie : ExtraCharactersInString {
    override fun invoke(str: String, dictionary: Array<String>): Int {
        val n: Int = str.length
        val root: TrieNode = dictionary.buildTrie()
        val dp = IntArray(n + 1)

        for (start in n - 1 downTo 0) {
            dp[start] = dp[start + 1] + 1
            var node: TrieNode? = root
            for (end in start until n) {
                if (node?.children?.containsKey(str[end]) == false) {
                    break
                }
                node = node?.children?.get(str[end])
                if (node?.isWord == true) {
                    dp[start] = min(dp[start].toDouble(), dp[end + 1].toDouble()).toInt()
                }
            }
        }

        return dp[0]
    }
}

private fun Array<String>.buildTrie(): TrieNode {
    val root = TrieNode()
    for (word in this) {
        var node: TrieNode? = root
        for (c in word.toCharArray()) {
            node?.children?.putIfAbsent(c, TrieNode())
            node = node?.children?.get(c)
        }
        node?.isWord = true
    }
    return root
}

private data class TrieNode(
    val children: MutableMap<Char?, TrieNode?> = HashMap(),
    var isWord: Boolean = false,
)
