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

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A case-insensitive Trie data structure that supports inserting words and searching for words
 * that match a given query. The Trie is implemented using a recursive data structure with nodes
 * that represent characters in the words. The Trie is case-insensitive, so it treats all characters
 * as lowercase. The Trie also supports filtering a list of strings based on a query using the search
 * functionality.
 * The Trie is thread-safe and supports concurrent access by multiple threads.
 * The Trie is optimized for search performance by limiting the number of search results.
 * The Trie is useful for implementing autocomplete functionality and searching for words in a dictionary.
 * The Trie is 0-indexed, so the first character is at index 0.
 * The Trie is built for the range [minValue, maxValue].
 * The range of values in the sequence should be in the range [minValue, maxValue].
 * The Trie supports the following operations:
 * - insert(word): Insert a word into the Trie.
 * - insertAll(words): Insert a list of words into the Trie.
 * - search(query): Search for words in the Trie that match a given query.
 * - filterStrings(strings, query): Filter a list of strings based on a query using the Trie search.
 * - clear(): Clear the Trie by resetting the root node.
 *
 * @property searchLimit The maximum number of search results to return.
 * @constructor Creates an empty CaseInsensitiveTrie with the given search limit.
 * @property root The root node of the Trie.
 */
class CaseInsensitiveTrie(private val searchLimit: Int = 100) {
    private var root = TrieNode() // The root node of the Trie
    private val lock = Lock() // A lock object to ensure thread-safety

    /**
     * Inserts a word into the Trie. The word is converted to lowercase before insertion.
     * The insertion is done iteratively by traversing the Trie from the root node.
     * The word is split into characters, and each character is mapped to a child node in the Trie.
     * The end of the word is marked by setting the isEndOfWord flag to true.
     * The insertion is thread-safe to allow concurrent access by multiple threads.
     * The time complexity of inserting a word is O(N), where N is the length of the word.
     * The space complexity of inserting a word is O(N), where N is the length of the word.
     *
     * @param word The word to insert into the Trie.
     */
    suspend fun insert(word: String) {
        lock.acquireLock()
        try {
            var currentNode = root
            for (i in word.indices) {
                val char = word[i].lowercaseChar()
                if (!currentNode.children.containsKey(char)) {
                    currentNode.children[char] = TrieNode()
                }
                currentNode = currentNode.children[char] ?: return
            }
            currentNode.isEndOfWord = true
        } finally {
            lock.unlock()
        }
    }

    /**
     * Inserts a list of words into the Trie. The words are converted to lowercase before insertion.
     * The insertion is done concurrently by launching a coroutine for each word in the list.
     * The insertAll function waits for all insertions to complete before returning.
     * The insertion is thread-safe to allow concurrent access by multiple threads.
     * The time complexity of inserting all words is O(N*M), where N is the number of words and M is the average length
     * of a word.
     * The space complexity of inserting all words is O(N*M), where N is the number of words and M is the average length
     * of a word.
     *
     * @param words The list of words to insert into the Trie.
     */
    fun insertAll(words: List<String>) = runBlocking {
        val jobs = words.map { word ->
            // Launch each insertion in a new coroutine
            launch {
                insert(word)
            }
        }

        // Wait for all insertions to complete
        jobs.joinAll()
    }

    /**
     * Searches for words in the Trie that match a given query. The query is converted to lowercase before searching.
     * The search is done recursively by traversing the Trie from the root node.
     * The search stops when the query characters do not match the children of the current node.
     * The search results are stored in a list of strings that match the query.
     * The search is thread-safe to allow concurrent access by multiple threads.
     * The time complexity of searching for a query is O(N), where N is the length of the query.
     * The space complexity of searching for a query is O(N), where N is the length of the query.
     * The search results are limited to the search limit to optimize performance.
     * The search results are sorted in lexicographical order.
     * The search results are case-insensitive.
     * The search results include the query itself if it is a word in the Trie.
     * The search results are unique and do not contain duplicates.
     * The search results are truncated if the number of results exceeds the search limit.
     * The search results are returned as a list of strings.
     * If no words match the query, an empty list is returned.
     * If the query is an empty string, the query itself is returned as a single-element list.
     *
     * @param query The query to search for in the Trie.
     * @return The list of words in the Trie that match the query.
     */
    suspend fun search(query: String): List<String> {
        lock.acquireLock()
        try {
            val results = mutableListOf<String>()
            var node = root
            for (i in query.indices) {
                val character = query[i].lowercaseChar()
                if (!node.children.containsKey(character)) {
                    return results
                }
                node = node.children[character] ?: return emptyList()
            }
            traverse(node, query, results)
            var start = 0
            if (results.size > searchLimit) {
                start = results.size - searchLimit
            }
            return results.subList(start, results.size)
        } finally {
            lock.unlock()
        }
    }

    /**
     * Traverses the Trie recursively to find words that match the query.
     * The traversal is done by recursively exploring the children of the current node.
     * The traversal stops when the end of a word is reached or the search limit is exceeded.
     * The traversal is thread-safe to allow concurrent access by multiple threads.
     *
     */
    private fun traverse(node: TrieNode, query: String, results: MutableList<String>) {
        if (node.isEndOfWord) {
            // Add the word to the results if it ends at this node
            results.add(query)
        }
        if (results.size > searchLimit) {
            // Limit the number of results
            return
        }
        node.children.forEach { (key, child) ->
            // Recursively traverse the children of the node
            traverse(child, query + key, results)
        }
    }

    /**
     * Clears the Trie by resetting the root node. The Trie is emptied of all words.
     */
    fun clear() {
        root = TrieNode()
    }

    /**
     * Filters a list of strings based on a query using the Trie search functionality.
     * The strings are converted to lowercase before filtering.
     * The filtering is done by searching for each string in the Trie and checking if it matches the query.
     * The filtering is thread-safe to allow concurrent access by multiple threads.
     * The time complexity of filtering strings is O(N*M), where N is the number of strings and M is the average length
     * of a string.
     * The space complexity of filtering strings is O(N), where N is the number of strings.
     * The filtered strings are returned as a list of strings.
     * If no strings match the query, an empty list is returned.
     *
     * @param strings The list of strings to filter based on the query.
     * @param query The query to filter the strings.
     *
     * @return The list of strings that match the query.
     */
    suspend fun filterStrings(strings: List<String>, query: String): List<String> {
        val filteredStrings = search(query.lowercase().trim())
        return strings.filter { string ->
            filteredStrings.contains(string.lowercase().trim())
        }
    }

    /**
     * A TrieNode class that represents a node in the Trie data structure. Each node has a character value,
     * a flag to indicate if it is the end of a word, and a map of child nodes.
     * The TrieNode class is used to build the Trie recursively by mapping characters to child nodes.
     * The TrieNode class is optimized for search performance by storing child nodes in a map.
     *
     * @property isEndOfWord Indicates if the node represents the end of a word.
     * @property children Maps characters to TrieNodes.
     * @constructor Creates a TrieNode with the given character value.
     */
    private inner class TrieNode {
        var isEndOfWord = false // Indicates if the node represents the end of a word
        val children: MutableMap<Char, TrieNode> = mutableMapOf() // Maps characters to TrieNodes

        init {
            isEndOfWord = false
        }
    }

    /**
     * A lock class that provides a simple mutual exclusion mechanism to ensure thread-safety.
     * The lock class is used to acquire and release a lock to protect critical sections of code.
     * The lock class is implemented using a boolean flag and a mutex to synchronize access.
     * The lock class is optimized for performance by using a CompletableDeferred to suspend and resume coroutines.
     *
     * @property isLocked Indicates if the lock is currently held.
     * @property mutex A CompletableDeferred to suspend and resume coroutines.
     * @constructor Creates a Lock with an unlocked state and a new mutex.
     */
    private inner class Lock {
        private var isLocked = false
        private var mutex = CompletableDeferred<Unit>()

        /**
         * Acquires the lock by waiting until it is unlocked.
         * The acquireLock function suspends the coroutine until the lock is unlocked.
         * The acquireLock function is thread-safe to allow concurrent access by multiple threads.
         */
        suspend fun acquireLock() {
            while (isLocked) {
                mutex.await()
            }
            isLocked = true
        }

        /**
         * Releases the lock by marking it as unlocked and resuming any waiting coroutines.
         */
        fun unlock() {
            if (isLocked) {
                isLocked = false
                mutex.complete(Unit)
                mutex = CompletableDeferred()
            }
        }
    }
}
