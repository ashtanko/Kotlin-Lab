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

package dev.shtanko.algorithms.annotations

/**
 * The Trie annotation is used to indicate that a function or class implements or utilizes a Trie data structure.
 * A Trie, also known as a prefix tree, is a specialized tree used for storing associative data structures,
 * most commonly used for storing strings in a way that allows for fast lookups, insertions, and deletions.
 *
 * # How It Works
 *
 * A Trie is a tree-like data structure where each node represents a character of a string. Each path down the tree
 * represents a sequence of characters, and the complete string is formed by traversing from the root to a leaf or
 * other terminal node.
 *
 * The key operations of a Trie are:
 * 1. **Insertion**: Insert a string into the Trie by adding nodes for each character.
 * 2. **Search**: Search for a string in the Trie by traversing the nodes corresponding to the string's characters.
 * 3. **Deletion**: Delete a string from the Trie by removing nodes when no longer necessary.
 *
 * Example of Trie insertion and search:
 *
 * ```kotlin
 * class TrieNode {
 *     val children = mutableMapOf<Char, TrieNode>()
 *     var isEndOfWord = false
 * }
 *
 * class Trie {
 *     private val root = TrieNode()
 *
 *     fun insert(word: String) {
 *         var current = root
 *         for (char in word) {
 *             current = current.children.computeIfAbsent(char) { TrieNode() }
 *         }
 *         current.isEndOfWord = true
 *     }
 *
 *     fun search(word: String): Boolean {
 *         var current = root
 *         for (char in word) {
 *             current = current.children[char] ?: return false
 *         }
 *         return current.isEndOfWord
 *     }
 * }
 * ```
 *
 * # Advantages:
 * * **Efficient String Operations**: Tries provide very fast lookups, insertions, and deletions for string-based data.
 * * **Prefix-Based Operations**: Tries are particularly useful for prefix-based search, such as autocomplete or
 * dictionary-based tasks.
 * * **Space Efficiency**: While Tries use more memory than simple arrays or lists, they are space-efficient when
 * storing many strings with common prefixes.
 *
 * # Disadvantages:
 * * **Memory Overhead**: Each Trie node needs to store references to its children, which can consume significant
 * memory when storing many unique strings.
 * * **Complexity**: Implementing a Trie can be more complex than using simpler data structures like hash tables or
 * arrays.
 *
 * Tries are commonly used in applications such as autocomplete systems, dictionary implementations, and text
 * processing tasks,
 * where prefix-based searches and efficient lookups are required.
 *
 * @property info An optional string that provides additional information about the Trie data structure
 * implementation or its usage.
 * @constructor Creates a new Trie annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Trie(val info: String = "")
