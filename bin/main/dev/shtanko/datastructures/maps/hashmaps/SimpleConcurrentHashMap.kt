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

class SimpleConcurrentHashMap<K, V>(private val size: Int) {
    private val buckets: Array<MutableList<Pair<K, V>>> = Array(size) { mutableListOf<Pair<K, V>>() }
    private val locks: Array<ReentrantLock> = Array(size) { ReentrantLock() }

    fun insert(key: K, value: V) {
        val index = hashFunction(key)
        val lock = locks[index]
        lock.lock()
        try {
            val bucket = buckets[index]
            for (i in bucket.indices) {
                if (bucket[i].first == key) {
                    bucket[i] = key to value
                    return
                }
            }
            bucket.add(key to value)
        } finally {
            lock.unlock()
        }
    }

    fun lookup(key: K): V? {
        val index = hashFunction(key)
        val lock = locks[index]
        lock.lock()
        try {
            val bucket = buckets[index]
            for (pair in bucket) {
                if (pair.first == key) {
                    return pair.second
                }
            }
            return null
        } finally {
            lock.unlock()
        }
    }

    fun delete(key: K) {
        val index = hashFunction(key)
        val lock = locks[index]
        lock.lock()
        try {
            val bucket = buckets[index]
            val iterator = bucket.iterator()
            while (iterator.hasNext()) {
                val pair = iterator.next()
                if (pair.first == key) {
                    iterator.remove()
                    return
                }
            }
        } finally {
            lock.unlock()
        }
    }

    private fun hashFunction(key: K): Int {
        return key.hashCode() % size
    }
}
