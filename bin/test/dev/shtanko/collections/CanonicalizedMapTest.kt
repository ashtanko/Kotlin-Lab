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

package dev.shtanko.collections

import dev.shtanko.collection.CanonicalizedMap
import dev.shtanko.collection.toCanonicalizedMap
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CanonicalizedMapTest {

    private lateinit var map: CanonicalizedMap<Int, String, String>

    @BeforeEach
    fun setUp() {
        map = CanonicalizedMap({ it.toInt() }, { Regex("^\\d+$").matches(it) })
    }

    @Test
    fun `canonicalizes keys on set and get`() {
        map["1"] = "value"
        assertEquals("value", map["01"])
    }

    @Test
    fun `get returns null for uncanonicalizable key`() {
        assertNull(map["foo"])
    }

    @Test
    fun `set affects nothing for uncanonicalizable key`() {
        map["foo"] = "value"
        assertNull(map["foo"])
        assertFalse(map.containsKey("foo"))
        assertEquals(0, map.size)
    }

    @Test
    fun `canonicalizes keys for addAll`() {
        map.putAll(mapOf("1" to "value 1", "2" to "value 2", "3" to "value 3"))
        assertEquals("value 1", map["01"])
        assertEquals("value 2", map["02"])
        assertEquals("value 3", map["03"])
    }

    @Test
    fun `uses the final value for addAll collisions`() {
        map.putAll(mapOf("1" to "value 1", "01" to "value 2", "001" to "value 3"))
        assertEquals(1, map.size)
        assertEquals("value 3", map["0001"])
    }

    @Test
    fun `clear clears the map`() {
        map.putAll(mapOf("1" to "value 1", "2" to "value 2", "3" to "value 3"))
        assertTrue(map.isNotEmpty())
        map.clear()
        assertTrue(map.isEmpty())
    }

    @Test
    fun `canonicalizes keys for containsKey`() {
        map["1"] = "value"
        assertTrue(map.containsKey("01"))
        assertFalse(map.containsKey("2"))
    }

    @Test
    fun `containsKey returns false for uncanonicalizable key`() {
        assertFalse(map.containsKey("foo"))
    }

    @Test
    fun `canonicalizes keys for putIfAbsent`() {
        map["1"] = "value"
        assertEquals("value", map.getOrPut("01") { throw Exception("shouldn't run") })
        assertEquals("new value", map.getOrPut("2") { "new value" })
    }

    @Test
    fun `canonicalizes keys for remove`() {
        map["1"] = "value"
        assertNull(map.remove("2"))
        assertEquals("value", map.remove("01"))
        assertTrue(map.isEmpty())
    }

    @Test
    fun `remove returns null for uncanonicalizable key`() {
        assertNull(map.remove("foo"))
    }

    @Test
    fun `containsValue returns whether a value is in the map`() {
        map["1"] = "value"
        assertTrue(map.containsValue("value"))
        assertFalse(map.containsValue("not value"))
    }

    @Test
    fun `isEmpty returns whether the map is empty`() {
        assertTrue(map.isEmpty())
        map["1"] = "value"
        assertFalse(map.isEmpty())
        map.remove("01")
        assertTrue(map.isEmpty())
    }

    @Test
    fun `isNotEmpty returns whether the map isn't empty`() {
        assertFalse(map.isNotEmpty())
        map["1"] = "value"
        assertTrue(map.isNotEmpty())
        map.remove("01")
        assertFalse(map.isNotEmpty())
    }

    @Test
    fun `length returns the number of pairs in the map`() {
        assertEquals(0, map.size)
        map["1"] = "value 1"
        assertEquals(1, map.size)
        map["01"] = "value 01"
        assertEquals(1, map.size)
        map["02"] = "value 02"
        assertEquals(2, map.size)
    }

    @Test
    fun `uses original keys for keys`() {
        map["001"] = "value 1"
        map["02"] = "value 2"
        assertEquals(setOf("001", "02"), map.keys)
    }

    @Test
    fun `uses original keys for forEach`() {
        map["001"] = "value 1"
        map["02"] = "value 2"

        val keys = mutableListOf<String>()
        map.forEach { (key, _) -> keys.add(key) }
        assertEquals(listOf("001", "02"), keys)
    }

    @Test
    fun `values returns all values in the map`() {
        map.putAll(mapOf("1" to "value 1", "01" to "value 01", "2" to "value 2", "03" to "value 03"))
        assertEquals(listOf("value 01", "value 2", "value 03"), map.values)
    }

    @Test
    fun `entries returns all key-value pairs in the map`() {
        map.putAll(mapOf("1" to "value 1", "01" to "value 01", "2" to "value 2"))
        val entries = map.entries.toList()
        assertEquals("01", entries[0].key)
        assertEquals("value 01", entries[0].value)
        assertEquals("2", entries[1].key)
        assertEquals("value 2", entries[1].value)
    }

    @Test
    fun `addEntries adds key-value pairs to the map`() {
        map.putAll(mapOf("1" to "value 1", "01" to "value 01", "2" to "value 2"))
        assertEquals(mapOf("01" to "value 01", "2" to "value 2"), map)
    }

    @Test
    fun `canonicalizes its keys`() {
        val map = mapOf("1" to "value 1", "2" to "value 2", "3" to "value 3").toCanonicalizedMap({ it.toInt() })
        assertEquals("value 1", map["01"])
        assertEquals("value 2", map["02"])
        assertEquals("value 3", map["03"])
    }

    @Test
    fun `uses the final value for collisions`() {
        val map = mapOf("1" to "value 1", "01" to "value 2", "001" to "value 3").toCanonicalizedMap({ it.toInt() })
        assertEquals(1, map.size)
        assertEquals("value 3", map["0001"])
    }

    @Test
    fun `convert to a MapOfCanonicalKeys`() {
        val map = mapOf("1" to "value 1", "2" to "value 2", "3" to "value 3").toCanonicalizedMap({ it.toInt() })

        val map2 = map.toMapOfCanonicalKeys()

        assert(map2 !is CanonicalizedMap<*, *, *>)

        assertEquals("value 1", map2[1])
        assertEquals("value 2", map2[2])
        assertEquals("value 3", map2[3])

        assertEquals(mapOf(1 to "value 1", 2 to "value 2", 3 to "value 3"), map2)
    }

    @Test
    fun `convert to a Map`() {
        val map = mapOf("1" to "value 1", "2" to "value 2", "3" to "value 3").toCanonicalizedMap({ it.toInt() })

        val map2 = map.toMap()

        assert(map2 !is CanonicalizedMap<*, *, *>)

        assertEquals("value 1", map2["1"])
        assertEquals("value 2", map2["2"])
        assertEquals("value 3", map2["3"])

        assertEquals(mapOf("1" to "value 1", "2" to "value 2", "3" to "value 3"), map2)
    }
}
