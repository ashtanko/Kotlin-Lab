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

import java.util.Arrays

/**
 * The default capacity of the hash map.
 */
private const val INT_MAP_CAPACITY = 10

/**
 * The index of the array that specifies the minimum size of the hash map.
 */
private const val ARRAY_MAP_SIZE_INDEX = 4

/**
 * A simple hash map implementation that stores integer keys and arbitrary values of type [V].
 *
 * @property capacity the initial capacity of the hash map.
 */
@Suppress("UNCHECKED_CAST")
class IntMap<V>(capacity: Int = INT_MAP_CAPACITY) {

    /**
     * An array that stores the hash codes for the keys.
     */
    private var hashes: IntArray = IntArray(if (capacity < 0) 0 else capacity)

    /**
     * An array that stores the values for the keys.
     */
    private var array: Array<Any?> = arrayOfNulls(if (capacity < 0) 0 else capacity)

    /**
     * The number of elements in the hash map.
     */
    var size = 0

    /**
     * Returns `true` if the hash map is empty, `false` otherwise.
     */
    fun isEmpty() = size <= 0

    /**
     * Returns the value associated with the given key, or `null` if the key is not found in the hash map.
     *
     * @param key the key to look up in the hash map.
     * @return the value associated with the key, or `null` if the key is not found.
     */
    operator fun get(key: Int) = get(key, null)

    /**
     * Returns the value associated with the given key, or the specified default value if the key is not found
     * in the hash map.
     *
     * @param key the key to look up in the hash map.
     * @param valueIfKeyNotFound the default value to return if the key is not found.
     * @return the value associated with the key, or the default value if the key is not found.
     */
    fun get(key: Int, valueIfKeyNotFound: V?): V? {
        val index = Arrays.binarySearch(hashes, 0, size, key)
        return if (index < 0) valueIfKeyNotFound else array[index] as V
    }

    /**
     * Deletes the entry with the given key from the hash map.
     *
     * @param key the key of the entry to delete.
     */
    fun delete(key: Int) {
        val index = Arrays.binarySearch(hashes, 0, size, key)
        if (index >= 0) removeAt(index)
    }

    /**
     * Removes the entry at the given index from the hash map.
     *
     * @param index the index of the entry to remove.
     */
    fun removeAt(index: Int) {
        System.arraycopy(hashes, index + 1, hashes, index, size - (index + 1))
        System.arraycopy(array, index + 1, array, index, size - (index + 1))
        size--
    }

    /**
     * Associates the given value with the given key in the hash map.
     *
     * @param key the key to associate the value with.
     * @param value the value to associate with the key.
     */
    fun put(key: Int, value: V) {
        var index = Arrays.binarySearch(hashes, 0, size, key)
        if (index >= 0) {
            array[index] = value
        } else {
            index = index.inv()
            if (size >= hashes.size) {
                val newSize = if (size < ARRAY_MAP_SIZE_INDEX) ARRAY_MAP_SIZE_INDEX else size + (size shr 1)
                val tempHashes = hashes
                val tempArray = array
                allocArrays(newSize)

                System.arraycopy(tempHashes, 0, hashes, 0, tempHashes.size)
                System.arraycopy(tempArray, 0, array, 0, tempArray.size)
            }

            if (index < size) {
                System.arraycopy(hashes, index, hashes, index + 1, size - index)
                System.arraycopy(array, index, array, index + 1, size - index)
            }

            hashes[index] = key
            array[index] = value
            size++
        }
    }

    /**
     * Returns the index of the specified key, or a negative number if the key is not in the map.
     * The negative number returned is the bitwise complement of the index at which the key would
     * be inserted if it were present.
     */
    fun indexOfKey(key: Int): Int {
        return Arrays.binarySearch(hashes, 0, size, key)
    }

    /**
     * Returns `true` if the map contains the specified key, `false` otherwise.
     */
    fun containsKey(key: Int) = indexOfKey(key) >= 0

    /**
     * Returns the key at the specified index.
     */
    fun keyAt(index: Int) = hashes[index]

    /**
     * Returns the value at the specified index.
     */
    fun valueAt(index: Int) = array[index] as V

    /**
     * Sets the value at the specified index.
     */
    fun setValueAt(index: Int, value: V) {
        array[index] = value
    }

    private fun allocArrays(size: Int) {
        hashes = IntArray(size)
        array = arrayOfNulls(size)
    }
}
