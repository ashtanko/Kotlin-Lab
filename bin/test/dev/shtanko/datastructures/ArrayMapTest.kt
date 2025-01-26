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
import org.junit.jupiter.api.assertThrows

internal class ArrayMapTest {

    @Test
    internal fun `empty test`() {
        val map = ArrayMap<String, String>()
        assertTrue(map.isEmpty())
    }

    @Test
    internal fun `put test`() {
        val map = ArrayMap<String, String>()
        map.put("A", "Apple")
        map.put("B", "Banana")
        assertEquals("Apple", map["A"])
        assertEquals("Banana", map["B"])
        assertEquals(null, map["D"])
    }

    @Test
    internal fun `indexOfKey test`() {
        val map = ArrayMap<String, String>()
        map.put("A", "Apple")
        map.put("B", "Banana")
        assertEquals(0, map.indexOfKey("A"))
        assertEquals(1, map.indexOfKey("B"))
        assertEquals(-3, map.indexOfKey("D"))
    }

    @Test
    internal fun `containsKey test`() {
        val map = ArrayMap<String, String>()
        map.put("A", "Apple")
        map.put("B", "Banana")
        assertTrue(map.containsKey("A"))
        assertTrue(map.containsKey("B"))
        assertFalse(map.containsKey("D"))
    }

    @Test
    internal fun `valueAt test`() {
        val map = ArrayMap<String, String>()
        map.put("A", "Apple")
        map.put("B", "Banana")
        assertEquals("Apple", map.valueAt(0))
        assertEquals("Banana", map.valueAt(1))
        assertEquals(null, map.valueAt(2))
    }

    @Test
    internal fun `keyAt test`() {
        val map = ArrayMap<String, String>()
        map.put("A", "Apple")
        map.put("B", "Banana")
        assertEquals("A", map.keyAt(0))
        assertEquals("B", map.keyAt(1))
        assertEquals(null, map.keyAt(2))
    }

    @Test
    internal fun `setValueAt test`() {
        val map = ArrayMap<String, String>()
        map.put("A", "Apple")
        map.put("B", "Banana")

        map.setValueAt(0, "Array")
        assertEquals("Array", map["A"])
        assertEquals("Banana", map["B"])
    }

    @Test
    internal fun `remove test`() {
        val map = ArrayMap<String, String>()
        map.put("A", "Apple")
        map.put("B", "Banana")

        map.remove("A")
        assertFalse(map.containsKey("A"))
        assertTrue(map.containsKey("B"))
    }

    @Test
    internal fun `negative capacity test`() {
        val map = ArrayMap<String?, String>(-1)
        assertThat(map.isEmpty()).isTrue
    }

    @Test
    internal fun `capacity test`() {
        val map = ArrayMap<String?, String>(10_000)
        assertThat(map.isEmpty()).isTrue
        map.put(null, "")
        assertThat(map.isEmpty()).isFalse
        map.remove(null)
        assertThat(map.isEmpty()).isTrue
        assertThat(map.remove(null)).isNull()
        assertThat(map.containsKey(null)).isFalse
    }

    @Test
    internal fun `put int test`() {
        val map = ArrayMap<Int?, Int>(1000)
        map.put(null, -1)
        map.put(null, -2)
        assertThat(map.containsKey(null)).isTrue
        assertThat(map[null]).isEqualTo(-2)
        map.put(-100, 20_000)
        assertThat(map.containsKey(-100)).isTrue
        map.put(999, 200_000)
        assertThat(map[999]).isEqualTo(200_000)
        map.put(100_000, 50_000_000)
        assertThat(map[100_000]).isEqualTo(50_000_000)
        map.put(-100_000, -50_000_000)
        assertThat(map[-100_000]).isEqualTo(-50_000_000)
        assertThat(map[-100_00]).isNull()
        assertThat(map[400_00]).isNull()
        assertThat(map[400_000_000]).isNull()
        assertThat(map[-400_000_000]).isNull()
        map.setValueAt(0, 12)
        assertThat(map.valueAt(0)).isEqualTo(12)
        map.setValueAt(2, 1234567890)
        assertThat(map.valueAt(2)).isEqualTo(1234567890)
        assertThat(map.indexOfKey(1234567890)).isEqualTo(-6)
    }

    @Test
    internal fun `put error test`() {
        val map = ArrayMap<Int?, Int>(1000)
        assertThrows<ArrayIndexOutOfBoundsException> {
            map.valueAt(-2355)
        }
    }

    @Test
    internal fun `put string test`() {
        val map = ArrayMap<String?, List<String>>(1)
        map.put(null, listOf("A"))
        assertThat(map[null]).isEqualTo(listOf("A"))
        map.put("A", listOf("A", "B"))
        map.put("B", listOf("A", "B", "C"))
        assertThat(map["B"]).isEqualTo(listOf("A", "B", "C"))
    }
}
