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

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SimpleConcurrentHashMapTest {

    private lateinit var map: SimpleConcurrentHashMap<Int, String>

    @BeforeEach
    fun setUp() {
        map = SimpleConcurrentHashMap(16)
    }

    @Test
    fun `insert and lookup single item`() {
        map.insert(1, "One")
        assertEquals("One", map.lookup(1))
    }

    @Test
    fun `lookup returns null for non-existent key`() {
        map.insert(2, "Two")
        assertNull(map.lookup(3))
    }

    @Test
    fun `update value for existing key`() {
        map.insert(1, "One")
        map.insert(1, "Updated One")
        assertEquals("Updated One", map.lookup(1))
    }

    @Test
    fun `delete removes key-value pair`() {
        map.insert(1, "One")
        map.delete(1)
        assertNull(map.lookup(1))
    }

    @Test
    fun `concurrent insertions maintain data integrity`() {
        val executor = Executors.newFixedThreadPool(4)
        for (i in 0 until 1000) {
            executor.submit { map.insert(i, "Value $i") }
        }
        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)

        for (i in 0 until 1000) {
            assertEquals("Value $i", map.lookup(i))
        }
    }

    @Test
    fun `concurrent deletions`() {
        for (i in 0 until 100) {
            map.insert(i, "Value $i")
        }

        val executor = Executors.newFixedThreadPool(4)
        for (i in 0 until 100) {
            executor.submit { map.delete(i) }
        }
        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)

        for (i in 0 until 100) {
            assertNull(map.lookup(i))
        }
    }
}
