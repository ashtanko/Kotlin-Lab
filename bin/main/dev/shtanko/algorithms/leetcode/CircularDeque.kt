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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 641. Design Circular Deque
 * @see <a href="https://leetcode.com/problems/design-circular-deque">Design Circular Deque</a>
 */
@Medium("https://leetcode.com/problems/design-circular-deque")
interface CircularDeque {
    fun insertFront(value: Int): Boolean
    fun insertLast(value: Int): Boolean
    fun deleteFront(): Boolean
    fun deleteLast(): Boolean
    fun getFront(): Int
    fun getRear(): Int
    fun isEmpty(): Boolean
    fun isFull(): Boolean
}

class CircularDequeLinkedList(private val capacity: Int) : CircularDeque {
    private var size = 0
    private var head: Node? = null
    private var rear: Node? = null

    override fun insertFront(value: Int): Boolean {
        if (isFull()) return false
        if (head == null) {
            head = Node(value)
            rear = head
        } else {
            val newHead = Node(value, next = head)
            head?.prev = newHead
            head = newHead
        }
        size++
        return true
    }

    override fun insertLast(value: Int): Boolean {
        if (isFull()) return false
        if (head == null) {
            head = Node(value)
            rear = head
        } else {
            rear?.next = Node(value, prev = rear)
            rear = rear?.next
        }
        size++
        return true
    }

    override fun deleteFront(): Boolean {
        if (isEmpty()) return false
        if (size == 1) {
            head = null
            rear = null
        } else {
            head = head?.next
            head?.prev = null
        }
        size--
        return true
    }

    override fun deleteLast(): Boolean {
        if (isEmpty()) return false
        if (size == 1) {
            head = null
            rear = null
        } else {
            rear = rear?.prev
            rear?.next = null
        }
        size--
        return true
    }

    override fun getFront(): Int {
        return head?.value ?: -1
    }

    override fun getRear(): Int {
        return rear?.value ?: -1
    }

    override fun isEmpty(): Boolean = size == 0

    override fun isFull(): Boolean = size == capacity

    private data class Node(val value: Int, var next: Node? = null, var prev: Node? = null) : Comparable<Node> {
        override fun compareTo(other: Node): Int {
            return value.compareTo(other.value)
        }
    }
}

class CircularDequeArray(k: Int) : CircularDeque {
    private val queue = IntArray(k)
    private var front = 0
    private var rear = k - 1
    private var size = 0
    private val capacity = k

    override fun insertFront(value: Int): Boolean {
        if (isFull()) return false
        front = (front - 1 + capacity) % capacity
        queue[front] = value
        size++
        return true
    }

    override fun insertLast(value: Int): Boolean {
        if (isFull()) return false
        rear = (rear + 1) % capacity
        queue[rear] = value
        size++
        return true
    }

    override fun deleteFront(): Boolean {
        if (isEmpty()) return false
        front = (front + 1) % capacity
        size--
        return true
    }

    override fun deleteLast(): Boolean {
        if (isEmpty()) return false
        rear = (rear - 1 + capacity) % capacity
        size--
        return true
    }

    override fun getFront(): Int {
        return if (isEmpty()) -1 else queue[front]
    }

    override fun getRear(): Int {
        return if (isEmpty()) -1 else queue[rear]
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun isFull(): Boolean {
        return size == capacity
    }
}
