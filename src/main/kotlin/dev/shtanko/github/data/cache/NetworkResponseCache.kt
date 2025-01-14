/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.github.data.cache

import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface NetworkResponseCache<T> {
    suspend operator fun get(key: String): T?
    suspend fun put(key: String, value: T)
    fun invalidate(key: String)
    fun clear()
    fun size(): Int
}

class InMemoryNetworkResponseCache<T>(val expiration: Duration = 5.minutes) : NetworkResponseCache<T> {

    private val map = mutableMapOf<String, CacheEntry<T>>()
    private val mutex = Mutex()

    override suspend fun get(key: String): T? = mutex.withLock {
        val current = System.currentTimeMillis()
        val entry = map[key]
        if (entry != null && !entry.isExpired(current)) {
            return entry.value
        } else {
            map.remove(key)
        }
        return null
    }

    override suspend fun put(key: String, value: T) {
        mutex.withLock {
            val current = System.currentTimeMillis()
            value?.let {
                map.put(key, CacheEntry(it, current + expiration.inWholeMilliseconds))
                println(map)
            }
        }
    }

    override fun invalidate(key: String) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun size(): Int {
        return map.size
    }

    private data class CacheEntry<T>(val value: T, private val expiryTime: Long) {
        fun isExpired(current: Long): Boolean {
            return current > expiryTime
        }
    }
}
