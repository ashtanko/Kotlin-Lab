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

import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 432. All O`one Data Structure
 * @see <a href="https://leetcode.com/problems/all-oone-data-structure">All O`one Data Structure</a>
 */
@Hard("https://leetcode.com/problems/all-oone-data-structure")
interface AllOne {
    fun inc(key: String)

    fun dec(key: String)

    fun getMaxKey(): String

    fun getMinKey(): String
}

class AllOneLinkedList : AllOne {
    private val head = Node(0)
    private val tail = Node(0)
    private val map = mutableMapOf<String, Node>()

    init {
        head.next = tail
        tail.prev = head
    }

    override fun inc(key: String) {
        if (key in map) {
            val node = map[key]!!
            val freq = node.freq
            node.keys.remove(key) // Remove key from current node

            val nextNode = node.next
            if (nextNode == tail || nextNode!!.freq != freq + 1) {
                // Create a new node if next node does not exist or freq is not freq + 1
                val newNode = Node(freq + 1)
                newNode.keys.add(key)
                newNode.prev = node
                newNode.next = nextNode
                node.next = newNode
                nextNode?.prev = newNode
                map[key] = newNode
            } else {
                // Increment the existing next node
                nextNode.keys.add(key)
                map[key] = nextNode
            }

            // Remove the current node if it has no keys left
            if (node.keys.isEmpty()) {
                removeNode(node)
            }
        } else { // Key does not exist
            val firstNode = head.next
            if (firstNode == tail || firstNode!!.freq > 1) {
                // Create a new node
                val newNode = Node(1)
                newNode.keys.add(key)
                newNode.prev = head
                newNode.next = firstNode
                head.next = newNode
                firstNode?.prev = newNode
                map[key] = newNode
            } else {
                firstNode.keys.add(key)
                map[key] = firstNode
            }
        }
    }

    override fun dec(key: String) {
        if (key !in map) return // Key does not exist

        val node = map.getOrDefault(key, Node(0))
        node.keys.remove(key)
        val freq = node.freq

        if (freq == 1) {
            // Remove the key from the map if freq is 1
            map.remove(key)
        } else {
            val prevNode = node.prev
            if (prevNode == head || prevNode!!.freq != freq - 1) {
                // Create a new node if the previous node does not exist or freq is not freq - 1
                val newNode = Node(freq - 1)
                newNode.keys.add(key)
                newNode.prev = prevNode
                newNode.next = node
                prevNode?.next = newNode
                node.prev = newNode
                map[key] = newNode
            } else {
                // Decrement the existing previous node
                prevNode.keys.add(key)
                map[key] = prevNode
            }
        }

        // Remove the node if it has no keys left
        if (node.keys.isEmpty()) {
            removeNode(node)
        }
    }

    override fun getMaxKey(): String {
        return if (tail.prev == head) {
            "" // No keys exist
        } else {
            tail.prev?.keys?.firstOrNull() ?: "" // Return one of the keys from the tail's previous node
        }
    }

    override fun getMinKey(): String {
        return if (head.next == tail) {
            "" // No keys exist
        } else {
            head.next?.keys?.firstOrNull() ?: "" // Return one of the keys from the head's next node
        }
    }

    private fun removeNode(node: Node) {
        val prevNode = node.prev
        val nextNode = node.next

        prevNode?.next = nextNode // Link previous node to next node
        nextNode?.prev = prevNode // Link next node to previous node
    }

    private data class Node(
        val freq: Int,
        var prev: Node? = null,
        var next: Node? = null,
        val keys: MutableSet<String> = mutableSetOf(),
    )
}
