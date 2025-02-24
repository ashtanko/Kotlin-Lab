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

import dev.shtanko.extensions.isEven

/**
 * Represents a node in a singly-linked list.
 * Each node has a value and a reference to the next node.
 */
data class ListNode(
    var value: Int = 0,
    var next: ListNode? = null,
) : Comparable<ListNode> {
    /**
     * Returns a string representation of the list node and its successors.
     */
    fun prettyPrinted(): String {
        val sb = StringBuilder()
        var node: ListNode? = this
        while (node?.next != null) {
            sb.append("${node.value}->")
            node = node.next
        }
        if (node != null) {
            sb.append(node.value)
        } else {
            sb.append("Empty LinkedList")
        }
        return sb.toString()
    }

    /**
     * Prints the string representation of the list node and its successors.
     */
    fun prettyPrint() {
        println(prettyPrinted())
    }

    override fun compareTo(other: ListNode): Int {
        return value.compareTo(other.value)
    }

    companion object {
        /**
         * Returns an empty list node.
         */
        fun empty(): ListNode = ListNode(0)
    }
}

/**
 * Converts an IntArray to a ListNode.
 */
fun IntArray.toListNode(): ListNode {
    if (isEmpty()) return ListNode()
    var current = ListNode()
    var startNode = ListNode()
    for (i in indices) {
        val node = ListNode(this[i])
        if (i > 0) {
            current.next = node
        } else {
            startNode = node
        }
        current = node
    }
    return startNode
}

/**
 * Converts an Array<Int> to a ListNode.
 */
fun Array<Int>.toListNode(): ListNode {
    return toIntArray().toListNode()
}

/**
 * Converts a List<Int> to a ListNode.
 */
fun List<Int>.toListNode(): ListNode {
    return toTypedArray().toListNode()
}

/**
 * Converts a ListNode to an IntArray.
 */
fun ListNode.toIntArray(): IntArray {
    val result = mutableListOf<Int>()
    var node: ListNode? = this
    while (node != null) {
        result.add(node.value)
        node = node.next
    }
    return result.toIntArray()
}

/**
 * Converts a ListNode to a List<Int>.
 */
fun ListNode.toList(): List<Int> {
    val result = mutableListOf<Int>()
    var node: ListNode? = this
    if (next == null) {
        result.add(value)
        return result
    }
    while (node != null) {
        result.add(node.value)
        node = node.next
    }
    return result
}

/**
 * Converts a ListNode to a List<Int> or returns an empty list if the ListNode is null.
 */
fun ListNode?.toListOrEmpty(): List<Int> {
    return this?.toList() ?: emptyList()
}

/**
 * Checks if a ListNode is not null.
 */
fun ListNode?.isNotNull(): Boolean {
    return this != null
}

/**
 * Adds a ListNode to a MutableCollection if the ListNode is not null.
 */
fun MutableCollection<ListNode>.addIfNotNull(node: ListNode?) {
    node?.let {
        add(it)
    }
}

/**
 * Reverses a ListNode.
 */
fun ListNode.reverseList(): ListNode? {
    var prev: ListNode? = null
    var curr: ListNode? = this
    while (curr != null) {
        val next = curr.next
        curr.next = prev
        prev = curr
        curr = next
    }
    return prev
}

/**
 * Zips two ListNodes together.
 */
fun ListNode.zip(head2: ListNode?): ListNode {
    var tail: ListNode? = this
    var current1 = this.next
    var current2 = head2
    var count = 0
    while (current1 != null && current2 != null) {
        if (count.isEven) {
            tail?.next = current2
            current2 = current2.next
        } else {
            tail?.next = current1
            current1 = current1.next
        }
        tail = tail?.next
        count++
    }
    if (current1 != null) {
        tail?.next = current1
    }
    if (current2 != null) {
        tail?.next = current2
    }

    return this
}
