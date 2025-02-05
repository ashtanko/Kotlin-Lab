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

package dev.shtanko.datastructures

class BinaryTrie {
    // TrieNode class to represent each node in the Binary Trie
    class TrieNode {
        val children = arrayOfNulls<TrieNode>(2) // Left and right children (binary 0 and 1)
        var count = 0 // To keep track of the number of times a number is inserted
    }

    private val root = TrieNode() // Root node of the Trie

    // Insert a binary number into the Binary Trie
    fun insert(binaryNumber: Int) {
        var currentNode = root
        for (i in MSB downTo 0) {
            val bit = (binaryNumber shr i) and 1 // Get the i-th bit (from the most significant bit)
            if (currentNode.children[bit] == null) {
                currentNode.children[bit] = TrieNode()
            }
            currentNode = currentNode.children[bit]!!
            currentNode.count++
        }
    }

    // Find the maximum XOR for a given number in the Binary Trie
    fun findMaxXor(query: Int): Int {
        var currentNode = root
        var maxXor = 0
        for (i in MSB downTo 0) {
            val bit = (query shr i) and 1
            val oppositeBit = 1 - bit // Try to find the opposite bit to maximize the XOR
            if (currentNode.children[oppositeBit] != null) {
                maxXor = maxXor or (1 shl i) // Set the i-th bit of the result
                currentNode = currentNode.children[oppositeBit]!!
            } else {
                currentNode = currentNode.children[bit]!!
            }
        }
        return maxXor
    }

    // Count the number of elements in the Trie
    fun count(binaryNumber: Int): Int {
        var currentNode = root
        for (i in MSB downTo 0) {
            val bit = (binaryNumber shr i) and 1
            currentNode = currentNode.children[bit] ?: return 0
        }
        return currentNode.count
    }

    companion object {
        private const val MSB = 31 // Most significant bit
    }
}
