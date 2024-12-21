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

package dev.shtanko.datastructures

/**
 * @see <a href="https://www.geeksforgeeks.org/queue-data-structure/">leetcode page</a>
 * A Queue is a linear structure which follows a particular order in which the operations are performed.
 * The order is First In First Out (FIFO). A good example of a queue is any queue of consumers for
 * a resource where the consumer that came first is served first.
 *
 * A simple implementation of a generic Queue using a singly linked list.
 *
 * @param T the type of elements held in this queue.
 */
class Queue<T> : Collection<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    /**
     * A private inner class representing a Node in the queue.
     *
     * @property value the value stored in the node.
     */
    private class Node<T>(val value: T) {
        var next: Node<T>? = null
    }

    override var size: Int = 0
        private set

    /**
     * Checks if the queue contains the specified element.
     *
     * @param element the element to be checked for containment.
     * @return `true` if the element is present in the queue, `false` otherwise.
     */
    override fun contains(element: T): Boolean {
        for (obj in this) {
            if (obj == element) return true
        }
        return false
    }

    /**
     * Checks if the queue contains all elements in the specified collection.
     *
     * @param elements the collection of elements to be checked for containment.
     * @return `true` if all elements in the collection are present in the queue, `false` otherwise.
     */
    override fun containsAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            if (!contains(element)) return false
        }
        return true
    }

    /**
     * Checks if the queue is empty.
     *
     * @return `true` if the queue is empty, `false` otherwise.
     */
    override fun isEmpty(): Boolean = size == 0

    /**
     * Returns an iterator over the elements in the queue.
     *
     * @return an iterator.
     */
    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            var node = head

            override fun hasNext(): Boolean = node != null

            override fun next(): T {
                if (!hasNext()) throw NoSuchElementException()

                val current = node!!
                node = current.next
                return current.value
            }
        }
    }

    /**
     * Retrieves, but does not remove, the head of the queue.
     *
     * @return the head of the queue, or `null` if the queue is empty.
     */
    fun peek(): T? {
        if (size == 0) throw NoSuchElementException()
        return head?.value
    }

    /**
     * Retrieves and removes the head of the queue.
     *
     * @return the head of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    fun poll(): T {
        if (size == 0) throw NoSuchElementException()
        val old = head!!
        head = old.next
        size--
        return old.value
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param item the element to be added to the queue.
     */
    fun add(item: T) {
        val newNode = Node(item)
        val tail = this.tail
        if (tail == null) {
            head = newNode
            this.tail = newNode
        } else {
            tail.next = newNode
            this.tail = newNode
        }
        size++
    }
}
