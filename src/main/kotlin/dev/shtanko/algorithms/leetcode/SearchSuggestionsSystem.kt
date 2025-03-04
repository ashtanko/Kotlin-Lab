/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import dev.shtanko.algorithms.annotations.level.Medium
import kotlin.math.abs
import kotlin.math.min

/**
 * 1268. Search Suggestions System
 * @see <a href="https://leetcode.com/problems/search-suggestions-system/">Search Suggestions System</a>
 */
@Medium("https://leetcode.com/problems/search-suggestions-system")
fun interface SearchSuggestionsSystem {
    operator fun invoke(products: Array<String>, searchWord: String): List<List<String>>
}

/**
 * Approach 1: Binary Search
 * Time complexity : O(n log(n))+O(m log(n)).
 * Space complexity : Varies between O(1)
 */
class SearchSuggestionsBinarySearch : SearchSuggestionsSystem {
    override operator fun invoke(products: Array<String>, searchWord: String): List<List<String>> {
        products.sort()
        val suggestions: MutableList<MutableList<String>> = ArrayList()
        var startIndex: Int
        var binarySearchStart = 0
        val productCount: Int = products.size
        var currentPrefix = String()
        for (char in searchWord.toCharArray()) {
            currentPrefix += char

            // Get the starting index of word starting with `currentPrefix`.
            startIndex = lowerBound(products, binarySearchStart, currentPrefix)

            // Add empty list to suggestions.
            suggestions.add(ArrayList())

            // Add the words with the same prefix to the suggestions.
            // Loop runs until `i` reaches the end of input or 3 times or till the
            // prefix is same for `products[i]` whichever comes first.
            for (i in startIndex until min(startIndex + 3, productCount)) {
                if (products[i].length < currentPrefix.length || products[i].substring(
                        0,
                        currentPrefix.length,
                    ) != currentPrefix
                ) {
                    break
                }
                suggestions[suggestions.size - 1].add(products[i])
            }

            // Reduce the size of elements to binary search on since we know
            binarySearchStart = abs(startIndex)
        }
        return suggestions
    }

    private fun lowerBound(products: Array<String>, start: Int, prefix: String?): Int {
        var left = start
        var right = products.size
        var mid: Int
        while (left < right) {
            mid = (left + right) / 2
            if (products[mid] >= prefix!!) right = mid else left = mid + 1
        }
        return left
    }
}

/**
 * Approach 2: Trie + DFS
 * Time complexity : O(M).
 * Space complexity : O(26n)=O(n).
 */
class SearchSuggestionsTrie : SearchSuggestionsSystem {
    override operator fun invoke(products: Array<String>, searchWord: String): List<List<String>> {
        val suggestions: MutableList<List<String>> = ArrayList()
        val trieNodes: MutableList<TrieNode> = ArrayList()
        var rootNode: TrieNode? = buildTrie(products)

        for (char in searchWord) {
            rootNode = rootNode?.children?.get(char - 'a')
            if (rootNode == null) break
            trieNodes.add(rootNode)
        }

        for (node in trieNodes) {
            val subList: MutableList<String> = ArrayList()
            collectWords(node, subList)
            suggestions.add(subList)
        }

        while (suggestions.size < searchWord.length) suggestions.add(ArrayList())
        return suggestions
    }

    private fun collectWords(node: TrieNode, result: MutableList<String>) {
        node.word?.let {
            result.add(it)
        }
        if (result.size >= 3) return
        for (child in node.children) {
            if (child != null) {
                collectWords(child, result)
                if (result.size >= 3) return
            }
        }
    }

    private fun buildTrie(words: Array<String>): TrieNode {
        val root = TrieNode()
        for (word in words) {
            var currentNode: TrieNode? = root
            for (char in word.toCharArray()) {
                val index = char - 'a'
                if (currentNode?.children?.get(index) == null) currentNode?.children?.set(index, TrieNode())
                currentNode = currentNode?.children?.get(index)
            }
            currentNode?.word = word
        }
        return root
    }

    private data class TrieNode(
        var word: String? = null,
        var children: Array<TrieNode?> = arrayOfNulls<TrieNode>(ALPHABET_LETTERS_COUNT),
    )
}
