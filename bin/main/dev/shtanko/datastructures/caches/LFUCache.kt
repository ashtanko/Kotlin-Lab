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

package dev.shtanko.datastructures.caches

/**
 * Solution of LFU Cache
 * https://en.wikipedia.org/wiki/Least_frequently_used
 */
class LFUCache<K, V>(val capacity: Int = 100) {

    private var head: Node<K, V>? = null
    private var tail: Node<K, V>? = null
    private val map: MutableMap<K, Node<K, V>> = HashMap()

    /**
     * This method returns value present in the cache corresponding to the key passed as parameter
     *
     * @param <K> key for which value is to be retrieved
     * @returns <V> object corresponding to the key passed as parameter, returns null if <K> key is not present in
     * the cache
     */
    operator fun get(key: K): V? {
        if (map[key] == null) {
            return null
        }
        val node = map[key]
        if (node != null) {
            removeNode(node)
            node.frequency += 1
            addNodeWithUpdatedFrequency(node)
        }
        return node?.value
    }

    /**
     * This method stores <K> key and <V> value in the cache
     *
     * @param <K> key which is to be stored in the cache
     * @param <V> value which is to be stored in the cache
     */
    operator fun set(key: K, value: V) {
        if (map.containsKey(key)) {
            val node = map[key]
            if (node != null) {
                node.value = value
                node.frequency += 1
                removeNode(node)
                addNodeWithUpdatedFrequency(node)
            }
        } else {
            if (map.size >= capacity) {
                map.remove(head?.key)
                removeNode(head)
            }
            val node = Node(key, value, 1)
            addNodeWithUpdatedFrequency(node)
            map[key] = node
        }
    }

    /**
     * This method stores the node in the cache with updated frequency
     *
     * @param Node node which is to be updated in the cache
     */
    private fun addNodeWithUpdatedFrequency(node: Node<K, V>) {
        if (tail != null && head != null) {
            var temp = head
            while (temp != null) {
                if (temp.frequency > node.frequency) {
                    if (temp === head) {
                        node.next = temp
                        temp.previous = node
                        head = node
                        break
                    } else {
                        node.next = temp
                        node.previous = temp.previous
                        temp.previous?.next = node
                        node.previous = temp.previous
                        break
                    }
                } else {
                    temp = temp.next
                    if (temp == null) {
                        tail?.next = node
                        node.previous = tail
                        node.next = null
                        tail = node
                        break
                    }
                }
            }
        } else {
            tail = node
            head = tail
        }
    }

    /**
     * This method removes node from the cache
     *
     * @param node which is to be removed in the cache
     */
    private fun removeNode(node: Node<K, V>?) {
        if (node?.previous != null) {
            node.previous?.next = node.next
        } else {
            head = node?.next
        }
        if (node?.next != null) {
            node.next?.previous = node.previous
        } else {
            tail = node?.previous
        }
    }

    private data class Node<K, V>(
        val key: K,
        var value: V,
        var frequency: Int,
        var previous: Node<K, V>? = null,
        var next: Node<K, V>? = null,
    )
}
