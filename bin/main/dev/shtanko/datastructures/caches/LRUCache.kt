/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.datastructures.caches

private const val LRU_CACHE_DEFAULT_CAP = 100

class LRUCache<K, V>(cap: Int = LRU_CACHE_DEFAULT_CAP) {

    private val data: MutableMap<K, Entry<K, V>> = HashMap()
    private var head: Entry<K, V>? = null
    private var tail: Entry<K, V>? = null
    private var capacity = cap

    operator fun get(key: K): V? {
        if (!data.containsKey(key)) {
            return null
        }
        val entry = data[key]
        moveNodeToLast(entry)
        return entry?.value
    }

    operator fun set(key: K, value: V) {
        if (data.containsKey(key)) {
            val existingEntry = data[key]
            existingEntry?.value = value
            moveNodeToLast(existingEntry)
            return
        }
        val newEntry: Entry<K, V>?
        if (data.size == capacity) {
            newEntry = evict()
            data.remove(newEntry?.key)
        } else {
            newEntry = Entry()
        }
        if (newEntry != null) {
            newEntry.key = key
            newEntry.value = value
            addNewEntry(newEntry)
            data[key] = newEntry
        }
    }

    fun setCapacity(newCapacity: Int) {
        checkCapacity(newCapacity)
        for (i in data.size downTo newCapacity + 1) {
            val evicted = evict()
            data.remove(evicted?.key)
        }
        capacity = newCapacity
    }

    private fun addNewEntry(newEntry: Entry<K, V>) {
        if (data.isEmpty()) {
            head = newEntry
            tail = newEntry
            return
        }
        tail?.nextEntry = newEntry
        newEntry.preEntry = tail
        newEntry.nextEntry = null
        tail = newEntry
    }

    private fun evict(): Entry<K, V>? {
        checkNotNull(head) {
            "cache cannot be empty!"
        }
        val evicted: Entry<K, V>? = head
        head = evicted?.nextEntry
        head?.preEntry = null
        evicted?.nextEntry = null
        return evicted
    }

    private fun checkCapacity(capacity: Int) {
        require(capacity > 0) {
            "capacity must greater than 0!"
        }
    }

    private fun moveNodeToLast(entry: Entry<K, V>?) {
        if (tail === entry) {
            return
        }
        val preEntry: Entry<K, V>? = entry?.preEntry
        val nextEntry: Entry<K, V>? = entry?.nextEntry
        preEntry?.nextEntry = nextEntry
        nextEntry?.preEntry = preEntry
        if (head === entry) {
            head = nextEntry
        }
        tail?.nextEntry = entry
        entry?.preEntry = tail
        entry?.nextEntry = null
        tail = entry
    }

    data class Entry<I, J>(
        var preEntry: Entry<I, J>? = null,
        var nextEntry: Entry<I, J>? = null,
        var key: I? = null,
        var value: J? = null,
    )
}
