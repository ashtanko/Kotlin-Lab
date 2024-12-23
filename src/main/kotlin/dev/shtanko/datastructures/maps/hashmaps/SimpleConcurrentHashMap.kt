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

package dev.shtanko.datastructures.maps.hashmaps

import java.util.concurrent.locks.ReentrantLock

/**
 * A simple thread-safe concurrent hash map implementation.
 * This map uses separate chaining (linked lists) to handle collisions and provides thread-safety
 * using locks for each bucket.
 *
 * @param K The type of the keys in the map.
 * @param V The type of the values in the map.
 * @param size The number of buckets to use for the hash map. The larger the size, the fewer the collisions.
 */
class SimpleConcurrentHashMap<K, V>(private val size: Int) {

    // Array of mutable lists representing the buckets of the hash map
    // Each bucket is a list of key-value pairs (represented as Pair<K, V>).
    private val buckets: Array<MutableList<Pair<K, V>>> = Array(size) { mutableListOf<Pair<K, V>>() }

    // Array of locks, one for each bucket, used to ensure thread-safety during read/write operations
    private val locks: Array<ReentrantLock> = Array(size) { ReentrantLock() }

    /**
     * Inserts a key-value pair into the hash map.
     * If the key already exists, it updates the value for the key.
     * The operation is thread-safe and uses locks to prevent race conditions.
     *
     * @param key The key to insert.
     * @param value The value to associate with the key.
     */
    fun insert(key: K, value: V) {
        // Determine the index based on the hash of the key
        val index = hashFunction(key)

        // Lock the bucket at the computed index to ensure thread-safe access
        val lock = locks[index]
        lock.lock()
        try {
            val bucket = buckets[index]

            // Check if the key exists and update its value
            for (i in bucket.indices) {
                if (bucket[i].first == key) {
                    bucket[i] = key to value
                    return
                }
            }

            // If key doesn't exist, add the new key-value pair to the bucket
            bucket.add(key to value)
        } finally {
            // Ensure that the lock is unlocked, even if an exception occurs
            lock.unlock()
        }
    }

    /**
     * Looks up the value associated with a given key.
     * The operation is thread-safe and uses locks to prevent race conditions.
     *
     * @param key The key to search for.
     * @return The value associated with the key, or null if the key is not found.
     */
    fun lookup(key: K): V? {
        // Determine the index based on the hash of the key
        val index = hashFunction(key)

        // Lock the bucket at the computed index to ensure thread-safe access
        val lock = locks[index]
        lock.lock()
        try {
            val bucket = buckets[index]

            // Iterate through the bucket and return the value if the key is found
            for (pair in bucket) {
                if (pair.first == key) {
                    return pair.second
                }
            }
            return null
        } finally {
            // Ensure that the lock is unlocked, even if an exception occurs
            lock.unlock()
        }
    }

    /**
     * Deletes the key-value pair associated with the specified key.
     * The operation is thread-safe and uses locks to prevent race conditions.
     *
     * @param key The key to remove from the map.
     */
    fun delete(key: K) {
        // Determine the index based on the hash of the key
        val index = hashFunction(key)

        // Lock the bucket at the computed index to ensure thread-safe access
        val lock = locks[index]
        lock.lock()
        try {
            val bucket = buckets[index]

            // Iterate through the bucket and remove the entry if the key is found
            val iterator = bucket.iterator()
            while (iterator.hasNext()) {
                val pair = iterator.next()
                if (pair.first == key) {
                    iterator.remove()
                    return
                }
            }
        } finally {
            // Ensure that the lock is unlocked, even if an exception occurs
            lock.unlock()
        }
    }

    /**
     * A simple hash function to map a key to an index in the buckets array.
     * It computes the hash code of the key and uses modulo with the number of buckets to ensure
     * the index falls within the bounds of the array.
     *
     * @param key The key to hash.
     * @return The index of the bucket where the key-value pair should be stored.
     */
    private fun hashFunction(key: K): Int {
        // Use the hashCode of the key and apply modulo operation to fit the size of the array
        return key.hashCode() % size
    }
}
