/*
 * Copyright 2021 Oleksii Shtanko
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
 * A dynamic array implementation that can grow or shrink as needed.
 * @param initialCapacity the initial size of the array
 * @param T the type of elements to store in the array
 */
class DynamicArray<T>(initialCapacity: Int) {
    /**
     * The underlying array of elements.
     */
    private var data: Array<Any?>

    /**
     * The current size of the array.
     */
    private var size: Int = 0

    /**
     * The initial capacity of the array.
     */
    private var initialCapacity: Int

    // Init over constructor
    init {
        data = arrayOfNulls(initialCapacity)
        this.initialCapacity = initialCapacity
    }

    /**
     * Gets the element at the specified index.
     * @param index the index of the element to get
     * @return the element at the specified index
     */
    fun get(index: Int): Any? = data[index]

    /**
     * Sets the element at the specified index to the given value.
     * @param index the index of the element to set
     * @param value the new value for the element
     */
    fun set(index: Int, value: T) {
        data[index] = value
        size++
    }

    /**
     * Inserts the given value at the specified index.
     * @param index the index to insert the value at
     * @param value the value to insert
     */
    fun insert(index: Int, value: T) {
        // Check size
        if (size == initialCapacity) {
            resize()
        }
        // Copy up | Shift
        for (i in size downTo index) {
            data[i + 1] = data[i]
        }

        // Insert
        data[index] = value
        size++
    }

    /**
     * Prints all the elements of the array to the console.
     */
    fun print() {
        data.forEach { println("data[i] = $it") }
    }

    /**
     * Adds the given value to the end of the array.
     * @param value the value to add
     */
    fun add(value: T) {
        if (size == initialCapacity) {
            resize()
        }
        data[size] = value
        size++
    }

    /**
     * Deletes the element at the specified index.
     * @param index the index of the element to delete
     */
    fun delete(index: Int) {
        // Copy down
        for (i in index until size - 1) data[i] = data[i + 1]

        // Clear if last element in array
        if (index == size) data[index] = null else data[size - 1] = null

        size--
    }

    /**
     * Gets the current size of the array.
     * @return the size of the array
     */
    fun size() = size

    /**
     * Checks if the array is empty.
     * @return true if the array is empty, false otherwise
     */
    fun isEmpty() = size == 0

    /**
     * Checks if the array contains the given value.
     * @param value the value to check for
     * @return true if the array contains the value, false otherwise
     */
    fun contains(value: T): Boolean {
        data.forEach {
            if (it == value) return true
        }
        return false
    }

    /**
     * Resizes the array to twice its current capacity.
     */
    private fun resize() {
        val newCapacity = if (initialCapacity == 0) 1 else initialCapacity * 2
        val newData = arrayOfNulls<Any?>(newCapacity)
        if (initialCapacity >= 0) System.arraycopy(data, 0, newData, 0, initialCapacity)
        // manual copy array
        /* for (int i = 0; i < initialCapacity; i++) {
            newData[i] = data[i];
        }*/
        data = newData
        initialCapacity = newCapacity
    }
}
