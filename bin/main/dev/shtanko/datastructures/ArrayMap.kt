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

private const val ARRAY_MAP_SIZE_INDEX = 4

/**
 * An implementation of a Map that uses an array to store key-value pairs. This
 * implementation provides constant-time performance for the basic operations
 * ({@code put}, {@code get}, {@code remove}, {@code containsKey}) if the
 * number of collisions is low.
 * @param <K> The key type.
 * @param <V> The value type.
 */
@Suppress("UNCHECKED_CAST")
class ArrayMap<K, V>(capacity: Int = 0) {

    private lateinit var hashes: IntArray
    private lateinit var array: Array<Any?>

    private var size = 0

    init {
        if (capacity <= 0) {
            hashes = IntArray(0)
            array = arrayOf()
        } else {
            allocArrays(capacity)
        }
    }

    /**
     * Returns true if this map has no key-value mappings.
     *
     * @return True if this map has no key-value mappings.
     */
    fun isEmpty() = size <= 0

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    operator fun get(key: K): V? {
        val index = indexOfKey(key)
        return if (index >= 0) array[(index shl 1) + 1] as V? else null
    }

    /**
     * Returns the index of the specified key, or a negative number if the key
     * is not contained in the map.
     *
     * @param key The key to search for.
     * @return The index of the specified key, or a negative number if the key
     * is not contained in the map.
     */
    fun indexOfKey(key: K): Int {
        return if (key == null) indexOfNull() else indexOf(key)
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key The key to search for.
     * @return True if this map contains a mapping for the specified key.
     */
    fun containsKey(key: K) = indexOfKey(key) >= 0

    /**
     * Returns the key at the specified index.
     *
     * @param index The index of the key to return.
     * @return The key at the specified index.
     */
    fun keyAt(index: Int) = array[index shl 1] as K

    /**
     * Returns the value at the specified index.
     *
     * @param index The index of the value to return.
     * @return The value at the specified index.
     */
    fun valueAt(index: Int) = array[(index shl 1) + 1] as V

    /**
     * Sets the value at the specified index.
     *
     * @param index The index of the value to set.
     * @param value The new value.
     * @return The old value at the specified index.
     */
    fun setValueAt(index: Int, value: V): V? {
        val valueIndex = (index shl 1) + 1
        val old = array[valueIndex] as V
        array[valueIndex] = value
        return old
    }

    /**
     * Associates the specified value with the specified key in this ArrayMap.
     * If the ArrayMap previously contained a mapping for the key, the old value is replaced.
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.)
     */
    fun put(key: K, value: V): V? {
        val hash: Int
        var index: Int
        if (key == null) {
            hash = 0
            index = indexOfNull()
        } else {
            hash = key.hashCode()
            index = indexOf(key)
        }
        if (index >= 0) {
            // key-value pair already present
            index = (index shl 1) + 1
            val old = array[index] as V
            array[index] = value
            return old
        }

        index = index.inv()
        if (size >= hashes.size) {
            val shiftedSize = size shr 1
            val newSize = if (size < ARRAY_MAP_SIZE_INDEX) ARRAY_MAP_SIZE_INDEX else size + shiftedSize
            val tempHashes = hashes
            val tempArray = array
            allocArrays(newSize)

            System.arraycopy(tempHashes, 0, hashes, 0, tempHashes.size)
            System.arraycopy(tempArray, 0, array, 0, tempArray.size)
        }

        if (index < size) {
            System.arraycopy(hashes, index, hashes, index + 1, size - index)
            val increasedIndex = index + 1
            System.arraycopy(array, index shl 1, array, increasedIndex shl 1, size - index shl 1)
        }

        hashes[index] = hash
        array[index shl 1] = key
        array[(index shl 1) + 1] = value
        size++
        return null
    }

    /**
     * Removes the mapping for a key from this ArrayMap if it is present.
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.)
     */
    fun remove(key: K): V? {
        val index = indexOfKey(key)
        if (index >= 0) return removeAt(index)
        return null
    }

    /**
     * Removes the mapping at the specified index from this ArrayMap.
     * @param index the index of the mapping to be removed from the map
     * @return the previous value associated with the removed mapping
     */
    private fun removeAt(index: Int): V? {
        val oldVal = array[(index shl 1) + 1]
        val newSize = size - 1
        if (size <= 1) {
            // Empty Map
            hashes = IntArray(0)
            array = arrayOf()
        } else {
            System.arraycopy(hashes, index + 1, hashes, index, newSize - index)
            System.arraycopy(array, index + 1 shl 1, array, index shl 1, newSize - index shl 1)
            array[newSize shl 1] = null
            array[(newSize shl 1) + 1] = null
        }
        size = newSize
        return oldVal as V
    }

    /**
     * Returns the index of the specified key in this ArrayMap, or a negative value if the key is not in the ArrayMap.
     * @param key the key to search for
     * @return the index of the specified key, or a negative value if the key is not in the ArrayMap.
     */
    private fun indexOf(key: K): Int {
        val hash = key!!.hashCode()

        if (size == 0) return 0.inv()
        val index = Arrays.binarySearch(hashes, 0, size, hash)

        // Key not found, return -ve value
        if (index < 0) return index

        if (key == array[index shl 1]) return index

        // Search for a matching key after the index.
        var end = index + 1
        while (end < size && hashes[end] == hash) {
            if (key == array[end shl 1]) return end
            end++
        }

        // Search for a matching key before the index.
        var i = index - 1
        while (i >= 0 && hashes[i] == hash) {
            if (key == array[i shl 1]) return i
            i--
        }
        return end.inv()
    }

    /**
     * Returns the index of the null key in this ArrayMap, or a negative value if the null key is not in the ArrayMap.
     * @return the index of the null key, or a negative value if the null key is not in the ArrayMap.
     */
    private fun indexOfNull(): Int {
        if (size == 0) return 0.inv()
        val index = Arrays.binarySearch(hashes, 0, size, 0)

        // Key not found, return -ve value
        if (index < 0) return index

        if (null == array[index shl 1]) return index

        // Search for a matching key after the index.
        var end = index + 1
        while (end < size && hashes[end] == 0) {
            if (null == array[end shl 1]) return end
            end++
        }

        // Search for a matching key before the index.
        var i = index - 1
        while (i >= 0 && hashes[i] == 0) {
            if (null == array[i shl 1]) return i
            i--
        }
        return end.inv()
    }

    /**
     * Allocates the arrays needed to hold the data in this ArrayMap.
     * @param size the minimum size of the arrays
     */
    private fun allocArrays(size: Int) {
        hashes = IntArray(size)
        array = arrayOfNulls(size shl 1) // size * 2
    }
}
