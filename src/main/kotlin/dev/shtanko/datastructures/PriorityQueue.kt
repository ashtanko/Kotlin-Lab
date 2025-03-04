/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.datastructures

import dev.shtanko.extensions.swap

/**
 * A priority queue implementation using a binary heap.
 *
 * Priority queue is an abstract data type similar to regular queue or stack data structure in which each element
 * additionally has a "priority" associated with it. In a priority queue, an element with high priority is served
 * before an element with low priority. In some implementations, if two elements have the same priority, they are
 * served according to the order in which they were enqueued, while in other implementations, ordering of elements
 * with the same priority is undefined.
 *
 * @param T The type of elements in the priority queue.
 * @property size The number of elements in the priority queue.
 * @property comparator The comparator to compare elements in the queue (optional).
 */
@Suppress("UNCHECKED_CAST")
class PriorityQueue<T>(size: Int, private val comparator: Comparator<T>? = null) : Collection<T> {

    override var size: Int = 0
        private set

    private var arr: Array<T?> = Array<Comparable<T>?>(size) { null } as Array<T?>

    /**
     * Retrieves the element at the front of the queue without removing it.
     *
     * @return The element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    fun peek(): T {
        if (size == 0) throw NoSuchElementException()
        return arr[1]!!
    }

    /**
     * Retrieves and removes the element at the front of the queue.
     *
     * @return The element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    fun poll(): T {
        if (size == 0) throw NoSuchElementException()
        val res = peek()
        arr.swap(1, size--)
        sink()
        arr[size + 1] = null
        val last = arr.size - 1
        val local = size == last / SQUAD
        if (size > 0 && local) {
            resize()
        }
        return res
    }

    /**
     * Adds an element to the priority queue.
     *
     * @param element The element to be added.
     */
    fun add(element: T) {
        if (size + 1 == arr.size) {
            resize()
        }
        arr[++size] = element
        swim(size)
    }

    /**
     * Checks if the queue contains the specified element.
     *
     * @param element The element to check for.
     * @return `true` if the element is found, otherwise `false`.
     */
    override fun contains(element: T): Boolean {
        for (obj in this) {
            if (obj == element) return true
        }
        return false
    }

    /**
     * Checks if the queue contains all elements from the specified collection.
     *
     * @param elements The collection of elements to check for.
     * @return `true` if all elements are found, otherwise `false`.
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
     * @return `true` if the queue is empty, otherwise `false`.
     */
    override fun isEmpty(): Boolean = size == 0

    /**
     * Returns an iterator over the elements in the queue.
     *
     * @return An iterator over the elements in the queue.
     */
    override fun iterator(): Iterator<T> {
        return arr.copyOfRange(1, size + 1).map { it!! }.iterator()
    }

    /**
     * Moves an element up the heap to its proper position to maintain the heap property.
     *
     * @param n The index of the element to be moved.
     */
    private fun swim(n: Int) {
        swim(arr, n, comparator)
    }

    /**
     * Moves an element down the heap to its proper position to maintain the heap property.
     */
    private fun sink() {
        sink(arr, 1, size, comparator)
    }

    /**
     * Resizes the internal array to accommodate more elements.
     */
    private fun resize() {
        val old = arr
        arr = Array<Comparable<T>?>(size * 2) { null } as Array<T?>
        System.arraycopy(old, 0, arr, 0, size + 1)
    }

    companion object {
        private const val SQUAD = 4

        /**
         * Checks if one element is greater than another.
         *
         * @param arr The array containing the elements.
         * @param i The index of the first element.
         * @param j The index of the second element.
         * @param comparator The comparator for element comparison (optional).
         * @return `true` if the first element is greater, otherwise `false`.
         */
        private fun <T> greater(arr: Array<T?>, i: Int, j: Int, comparator: Comparator<T>? = null): Boolean {
            return if (comparator != null) {
                comparator.compare(arr[i], arr[j]) > 0
            } else {
                val left = arr[i] as Comparable<T>
                left > arr[j]!!
            }
        }

        /**
         * Moves an element down the heap to its proper position to maintain the heap property.
         *
         * @param arr The array containing the heap.
         * @param a The index of the element to be moved.
         * @param size The size of the heap.
         * @param comparator The comparator for element comparison (optional).
         */
        fun <T> sink(arr: Array<T?>, a: Int, size: Int, comparator: Comparator<T>? = null) {
            var k = a
            while (2 * k <= size) {
                var j = 2 * k
                if (j < size && greater(arr, j, j + 1, comparator)) j++
                if (!greater(arr, k, j, comparator)) break
                arr.swap(k, j)
                k = j
            }
        }

        /**
         * Moves an element up the heap to its proper position to maintain the heap property.
         *
         * @param arr The array containing the heap.
         * @param size The size of the heap.
         * @param comparator The comparator for element comparison (optional).
         */
        fun <T> swim(arr: Array<T?>, size: Int, comparator: Comparator<T>? = null) {
            var n = size
            while (n > 1 && greater(arr, n / 2, n, comparator)) {
                arr.swap(n, n / 2)
                n /= 2
            }
        }
    }
}
