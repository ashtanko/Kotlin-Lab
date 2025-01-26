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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class SimpleHashMapTest {

    @Test
    fun `insert and lookup single item`() {
        val hashMap = SimpleHashMap<Int, String>(10)
        hashMap.insert(1, "One")
        assertEquals("One", hashMap.lookup(1))
    }

    @Test
    fun `lookup returns null for non-existent key`() {
        val hashMap = SimpleHashMap<Int, String>(10)
        hashMap.insert(2, "Two")
        assertNull(hashMap.lookup(3))
    }

    @Test
    fun `update value for existing key`() {
        val hashMap = SimpleHashMap<Int, String>(10)
        hashMap.insert(1, "One")
        hashMap.insert(1, "Updated One")
        assertEquals("Updated One", hashMap.lookup(1))
    }

    @Test
    fun `delete removes key-value pair`() {
        val hashMap = SimpleHashMap<Int, String>(10)
        hashMap.insert(1, "One")
        hashMap.delete(1)
        assertNull(hashMap.lookup(1))
    }

    @Test
    fun `delete on non-existent key does nothing`() {
        val hashMap = SimpleHashMap<Int, String>(10)
        hashMap.insert(1, "One")
        hashMap.delete(2) // Non-existent key
        assertEquals("One", hashMap.lookup(1))
    }

    @Test
    fun `collision handling with multiple items in the same bucket`() {
        val hashMap = SimpleHashMap<Int, String>(1) // Force all items into the same bucket
        hashMap.insert(1, "One")
        hashMap.insert(2, "Two")
        assertEquals("One", hashMap.lookup(1))
        assertEquals("Two", hashMap.lookup(2))
    }

    @Test
    fun `lookup after multiple insertions and deletions`() {
        val hashMap = SimpleHashMap<Int, String>(10)
        hashMap.insert(1, "One")
        hashMap.insert(2, "Two")
        hashMap.delete(1)
        hashMap.insert(3, "Three")
        assertNull(hashMap.lookup(1))
        assertEquals("Two", hashMap.lookup(2))
        assertEquals("Three", hashMap.lookup(3))
    }
}
