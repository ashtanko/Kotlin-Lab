/*
 * Copyright 2020 Oleksii Shtanko
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

class DesignHashMap {
    private val nodes = Array<ListNode?>(10_000) { null }

    fun put(key: Int, value: Int) {
        val i = idx(key)
        if (nodes[i] == null) {
            nodes[i] = ListNode(-1, -1)
        }
        val prev = find(nodes[i], key)
        if (prev?.next == null) {
            prev?.next = ListNode(key, value)
        } else {
            prev.next?.value = value
        }
    }

    fun get(key: Int): Int {
        val i = idx(key)
        if (nodes[i] == null) return -1
        val node = find(nodes[i], key)
        return if (node?.next == null) -1 else node.next?.value ?: -1
    }

    fun remove(key: Int) {
        val i = idx(key)
        if (nodes[i] == null) return
        val prev = find(nodes[i], key)
        if (prev?.next == null) return
        prev.next = prev.next?.next
    }

    private fun idx(key: Int): Int = Integer.hashCode(key) % nodes.size

    private fun find(bucket: ListNode?, key: Int): ListNode? {
        var node: ListNode? = bucket
        var prev: ListNode? = null
        while (node != null && node.key != key) {
            prev = node
            node = node.next
        }
        return prev
    }

    class ListNode(val key: Int, var value: Int) {
        var next: ListNode? = null
    }
}
