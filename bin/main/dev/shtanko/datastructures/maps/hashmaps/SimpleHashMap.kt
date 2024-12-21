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

/**
 * A simple implementation of a hash map that supports basic operations such as insert, lookup, and delete.
 * This class demonstrates a fundamental approach to building a hash map using an array of buckets,
 * where each bucket is a list of key-value pairs. The hash map is designed to handle collisions
 * by chaining, where multiple entries with the same hash are stored in the same bucket.
 *
 * @param Key The type of the keys stored in the map.
 * @param Value The type of the values stored in the map.
 * @param size The initial size of the hash map, specifically the number of buckets.
 */
class SimpleHashMap<Key, Value>(private val size: Int) {
    private val buckets: Array<MutableList<Pair<Key, Value>>> = Array(size) { mutableListOf<Pair<Key, Value>>() }

    fun insert(key: Key, value: Value) {
        val index = hashFunction(key)
        val bucket = buckets[index]

        for (i in bucket.indices) {
            if (bucket[i].first == key) {
                bucket[i] = key to value
                return
            }
        }
        bucket.add(key to value)
    }

    fun lookup(key: Key): Value? {
        val index = hashFunction(key)
        val bucket = buckets[index]

        for (pair in bucket) {
            if (pair.first == key) {
                return pair.second
            }
        }
        return null
    }

    fun delete(key: Key) {
        val index = hashFunction(key)
        val bucket = buckets[index]

        val iterator = bucket.iterator()
        while (iterator.hasNext()) {
            val pair = iterator.next()
            if (pair.first == key) {
                iterator.remove()
                return
            }
        }
    }

    private fun hashFunction(key: Key) = key.hashCode() % size
}
