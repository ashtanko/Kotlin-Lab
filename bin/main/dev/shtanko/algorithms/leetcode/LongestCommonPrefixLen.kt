/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.Trie
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 3043. Find the Length of the Longest Common Prefix
 * @see <a href="https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/">Source</a>
 */
@Medium("https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix")
fun interface LongestCommonPrefixLen {
    operator fun invoke(arr1: IntArray, arr2: IntArray): Int
}

class LongestCommonPrefixLenHashTable : LongestCommonPrefixLen {
    override fun invoke(arr1: IntArray, arr2: IntArray): Int {
        val arr1Prefixes = mutableSetOf<Int>() // Set to store all prefixes from arr1

        // Step 1: Build all possible prefixes from arr1
        for (val1 in arr1) {
            var valCopy = val1
            while (valCopy !in arr1Prefixes && valCopy > 0) {
                // Insert current value as a prefix
                arr1Prefixes.add(valCopy)
                // Generate the next shorter prefix by removing the last digit
                valCopy /= 10
            }
        }

        var longestPrefix = 0

        // Step 2: Check each number in arr2 for the longest matching prefix
        for (val2 in arr2) {
            var valCopy = val2
            while (valCopy !in arr1Prefixes && valCopy > 0) {
                // Reduce val by removing the last digit if not found in the prefix set
                valCopy /= 10
            }
            if (valCopy > 0) {
                // Length of the matched prefix using log10 to determine the number of digits
                longestPrefix = maxOf(longestPrefix, valCopy.toString().length)
            }
        }

        return longestPrefix
    }
}

@Trie
class LongestCommonPrefixLenTrie : LongestCommonPrefixLen {
    override fun invoke(arr1: IntArray, arr2: IntArray): Int {
        val trie = Trie()

        // Step 1: Insert all numbers from arr1 into the Trie
        for (num in arr1) {
            trie.insert(num)
        }

        var longestPrefix = 0

        // Step 2: Find the longest prefix match for each number in arr2
        for (num in arr2) {
            val len = trie.findLongestPrefix(num)
            longestPrefix = maxOf(longestPrefix, len)
        }

        return longestPrefix
    }

    private class TrieNode {
        // Each node has up to 10 possible children (digits 0-9)
        val children = arrayOfNulls<TrieNode>(10)
    }

    private class Trie {
        private val root = TrieNode()

        // Insert a number into the Trie by treating it as a string of digits
        fun insert(num: Int) {
            var node = root
            val numStr = num.toString()
            for (digit in numStr) {
                val idx = digit - '0'
                if (node.children[idx] == null) {
                    node.children[idx] = TrieNode()
                }
                node = node.children[idx]!!
            }
        }

        // Find the longest common prefix for a number in arr2 with the Trie
        fun findLongestPrefix(num: Int): Int {
            var node = root
            val numStr = num.toString()
            var len = 0

            for (digit in numStr) {
                val idx = digit - '0'
                if (node.children[idx] != null) {
                    // Increase length if the current digit matches
                    len++
                    node = node.children[idx]!!
                } else {
                    // Stop if no match for the current digit
                    break
                }
            }
            return len
        }
    }
}
