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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class IntMapTest {

    @Test
    internal fun `empty test`() {
        val map = IntMap<String>()
        assertTrue(map.isEmpty())
        map.put(1, "Apple")
        assertFalse(map.isEmpty())
    }

    @Test
    internal fun `get test`() {
        val map = IntMap<String>()
        map.put(1, "Apple")
        map.put(2, "Banana")
        assertEquals("Apple", map[1])
        assertEquals("Banana", map[2])
        assertEquals(null, map[3])
    }

    @Test
    internal fun `indexOfKey test`() {
        val map = IntMap<String>()
        map.put(1, "Apple")
        map.put(2, "Banana")
        assertEquals(0, map.indexOfKey(1))
        assertEquals(1, map.indexOfKey(2))
        assertEquals(-3, map.indexOfKey(3))
    }

    @Test
    internal fun `containsKey test`() {
        val map = IntMap<String>()
        map.put(1, "Apple")
        map.put(2, "Banana")
        assertTrue(map.containsKey(1))
        assertTrue(map.containsKey(2))
        assertFalse(map.containsKey(3))
    }

    @Test
    internal fun `valueAt test`() {
        val map = IntMap<String>()
        map.put(1, "Apple")
        map.put(2, "Banana")
        assertEquals("Apple", map.valueAt(0))
        assertEquals("Banana", map.valueAt(1))
        assertEquals(null, map.valueAt(3))
    }

    @Test
    internal fun `keyAt test`() {
        val map = IntMap<String>()
        map.put(1, "Apple")
        map.put(2, "Banana")
        assertEquals(1, map.keyAt(0))
        assertEquals(2, map.keyAt(1))
        assertEquals(0, map.keyAt(3))
    }

    @Test
    internal fun `setValueAt test`() {
        val map = IntMap<String>()
        map.put(1, "Apple")
        map.put(2, "Banana")

        map.setValueAt(0, "Array")
        assertEquals("Array", map[1])
        assertEquals("Banana", map[2])
    }

    @Test
    internal fun `delete test`() {
        val map = IntMap<String>()
        map.delete(1)
        assertTrue(map.isEmpty())
        map.put(1, "Apple")
        map.put(2, "Banana")
        map.delete(1)

        map.setValueAt(0, "Array")
        assertFalse(map.containsKey(1))
        assertTrue(map.containsKey(2))
    }

    @Test
    internal fun `negative capacity test`() {
        val map = IntMap<String>(-1)
        assertThat(map.isEmpty()).isTrue
    }

    @Test
    internal fun `negative capacity put test`() {
        val map = IntMap<String>(-1)
        map.put(-2, "")
        map.put(-1, "")
        map.put(0, "")
        map.put(1, "")
        assertThat(map.isEmpty()).isFalse
    }

    @Test
    internal fun `put test`() {
        val map = IntMap<String>(-1)
        map.put(-1, "-1")
        assertTrue(map.containsKey(-1))
    }

    @Test
    internal fun `put 2 test`() {
        val arr = (0..12).sorted()
        val map = IntMap<String>()
        for (i in arr) {
            map.put(i, "$i")
        }
        val arr2 = (13..50).sorted()
        for (i in arr2) {
            map.put(i, "$i")
        }
        assertTrue(map.containsKey(1))
    }

    @Test
    internal fun `remove at test`() {
        val map = IntMap<String>(10)
        map.put(-1, "-1")
        map.put(1, "1")
        map.removeAt(1)
        assertFalse(map.containsKey(1))
    }

    @Test
    fun testEmptyMap() {
        val map = IntMap<String>()
        assertTrue(map.isEmpty())
        assertEquals(null, map[1])
        assertFalse(map.containsKey(1))
        assertEquals(-1, map.indexOfKey(1))
    }

    @Test
    fun testPutAndGet() {
        val map = IntMap<String>()
        map.put(1, "one")
        map.put(2, "two")
        assertEquals("one", map[1])
        assertEquals("two", map[2])
        assertEquals(null, map[3])
        assertEquals(0, map.indexOfKey(1))
        assertEquals(1, map.indexOfKey(2))
        assertFalse(map.isEmpty())
        assertTrue(map.containsKey(1))
        assertFalse(map.containsKey(3))
        assertEquals(1, map.keyAt(0))
        assertEquals("one", map.valueAt(0))
        map.setValueAt(1, "newTwo")
        assertEquals("newTwo", map.valueAt(1))
    }

    @Test
    fun testDelete() {
        val map = IntMap<String>()
        map.put(1, "one")
        map.put(2, "two")
        map.put(3, "three")
        assertEquals(3, map.size)
        map.delete(1)
        assertEquals(2, map.size)
        assertEquals(-1, map.indexOfKey(1))
        assertEquals(0, map.indexOfKey(2))
        assertEquals(1, map.indexOfKey(3))
        assertEquals(null, map[1])
        assertEquals("two", map[2])
        assertEquals("three", map[3])
        assertTrue(map.containsKey(3))
    }

    @Test
    fun testLargeMap() {
        val map = IntMap<String>()
        val count = 100000
        for (i in 0 until count) {
            map.put(i, "value$i")
        }
        assertEquals(count, map.size)
        for (i in 0 until count) {
            assertEquals("value$i", map[i])
        }
    }
}
