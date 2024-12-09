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

import kotlin.random.Random

/**
 * Skip list is a data structure that allows fast search within an ordered sequence of elements.
 * It is a probabilistic alternative to balanced trees.
 * The elements are ordered in a hierarchy of linked lists, with the top level being the entire list.
 * Each subsequent level is a sublist of the previous one, with the bottom level being the individual elements.
 * The search time complexity is O(log n) on average.
 * The insert and delete time complexity is O(log n) on average.
 * The space complexity is O(n).
 *
 * Use Case: An alternative to balanced trees, supports fast search, insert, and delete operations.
 * Features: Probabilistic balancing; used in in-memory databases like Redis.
 *
 * @param K the type of keys
 * @param V the type of values
 * @property maxLevel the maximum level of the skip list
 * @property head the head node of the skip list
 * @property level the current level of the skip list
 */
class SkipList<K : Comparable<K>, V> {
    private val maxLevel = 16
    private val head = Node<K, V>(null, null, Array(maxLevel) { null })
    private var level = 0

    /**
     * Node class for the skip list.
     *
     * @property key the key of the node
     * @property value the value of the node
     * @property next the next nodes in the skip list
     * @constructor Creates a new node with the given key, value, and next nodes.
     */
    private data class Node<K, V>(
        val key: K?,
        var value: V?,
        val next: Array<Node<K, V>?>,
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Node<*, *>

            if (key != other.key) return false
            if (value != other.value) return false
            if (!next.contentEquals(other.next)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = key?.hashCode() ?: 0
            result = 31 * result + (value?.hashCode() ?: 0)
            result = 31 * result + next.contentHashCode()
            return result
        }
    }

    /**
     * Generate a random level for a new node.
     *
     * @return the new level
     */
    private fun randomLevel(): Int {
        var newLevel = 0
        while (Random.nextBoolean() && newLevel < maxLevel - 1) {
            newLevel++
        }
        return newLevel
    }

    /**
     * Insert a new key-value pair into the skip list.
     *
     * @param key the key to insert
     * @param value the value to insert
     */
    fun insert(key: K, value: V) {
        val (update, current) = findNodeAndUpdatePath(key)

        if (current?.key == key) {
            // Key exists, update value
            current.value = value
        } else {
            // Key doesn't exist, insert new node
            val newLevel = randomLevel()
            if (newLevel > level) {
                for (i in level + 1..newLevel) {
                    update[i] = head
                }
                level = newLevel
            }

            val newNode = Node(key, value, Array(newLevel + 1) { null })
            for (i in 0..newLevel) {
                newNode.next[i] = update[i]?.next?.get(i)
                update[i]?.next?.set(i, newNode)
            }
        }
    }

    /**
     * Search for a key in the skip list.
     *
     * @param key the key to search for
     * @return the value of the key, or null if the key is not found
     */
    fun search(key: K): V? {
        var current: Node<K, V>? = head
        for (i in level downTo 0) {
            while (current?.next[i]?.key != null && current.next[i]!!.key!! < key) {
                current = current.next[i]!!
            }
        }
        current = current?.next[0]

        return if (current?.key == key) current.value else null
    }

    /**
     * Delete a key from the skip list.
     *
     * @param key the key to delete
     */
    fun delete(key: K) {
        val (update, current) = findNodeAndUpdatePath(key)

        if (current?.key == key) {
            // Remove the node
            for (i in 0..level) {
                if (update[i]?.next?.get(i) != current) break
                update[i]?.next?.set(i, current.next[i])
            }

            // Decrease the level of the skip list if necessary
            while (level > 0 && head.next[level] == null) {
                level--
            }
        }
    }

    /**
     * Find the node with the given key and update the path to it.
     * Returns the array of nodes leading to the node and the node itself.
     *
     * @param key the key to find
     * @return the array of nodes leading to the node and the node itself
     */
    private fun findNodeAndUpdatePath(key: K): Pair<Array<Node<K, V>?>, Node<K, V>?> {
        val update = arrayOfNulls<Node<K, V>>(maxLevel)
        var current: Node<K, V>? = head

        // Find the node to delete
        for (i in level downTo 0) {
            while (current?.next[i]?.key != null && current.next[i]!!.key!! < key) {
                current = current.next[i]!!
            }
            update[i] = current
        }

        current = current?.next[0]
        return Pair(update, current)
    }

    /**
     * Display the skip list as a string.
     *
     * @return the string representation of the skip list
     */
    fun display(): String {
        val builder = StringBuilder()
        builder.append("Skip List:\n")
        for (i in level downTo 0) {
            var current = head.next[i]
            builder.append("Level $i: ")
            while (current != null) {
                builder.append("${current.key} -> ")
                current = current.next[i]
            }
            builder.append("null\n")
        }
        return builder.toString()
    }
}
