/*
 * Copyright 2021 Oleksii Shtanko
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
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class DynamicArrayTest {

    @Test
    fun `Should Set Value Then Get By Index`() {
        val array = DynamicArray<String>(3)

        assertThat(array.isEmpty()).isTrue

        array.set(0, "Kotlin")
        array.set(1, "is really")
        array.set(2, "awesome")

        assertThat(array.isEmpty()).isFalse

        assertThat(array.size()).isEqualTo(3)
        assertThat(array.get(0)).isEqualTo("Kotlin")
        assertThat(array.get(1)).isEqualTo("is really")
        assertThat(array.get(2)).isEqualTo("awesome")
    }

    @Test
    fun `Should Insert Into Array`() {
        val array = DynamicArray<Int>(4)
        array.set(0, 0)
        array.set(1, 1000)
        array.set(2, 2000)
        array.set(3, 3000)

        array.insert(1, 999)

        assertThat(array.size()).isEqualTo(5)
        assertThat(array.get(0)).isEqualTo(0)
        assertThat(array.get(1)).isEqualTo(999)
        assertThat(array.get(2)).isEqualTo(1000)
        assertThat(array.get(3)).isEqualTo(2000)
        assertThat(array.get(4)).isEqualTo(3000)
    }

    @Test
    @Disabled
    fun `Should Insert Then Add To Array`() {
        val array = DynamicArray<String>(0)
        array.insert(0, "insert 0")
        array.insert(1, "insert 1")

        array.add("add")

        assertThat(array.size()).isEqualTo(3)
    }

    @Test
    fun `Should Add To Array`() {
        val array = DynamicArray<String>(0)

        array.add("add")
        array.add("add2")

        assertThat(array.size()).isEqualTo(2)
    }

    @Test
    fun `Should Delete From Array`() {
        val array = DynamicArray<String>(0)

        array.add("will be deleted")
        array.add("won't be deleted")
        array.add("won't be deleted2")

        array.delete(0)

        assertThat(array.size()).isEqualTo(2)
        assertThat(array.get(0)).isEqualTo("won't be deleted")
        assertThat(array.get(1)).isEqualTo("won't be deleted2")
    }

    @Test
    fun `Should Verify If Contains Same Value`() {
        val array = DynamicArray<String>(0)

        array.add("text2")
        array.add("text1")

        assertThat(array.contains("text1")).isTrue
        assertThat(array.contains("unknown")).isFalse
    }

    @Test
    fun `Resize Capacity Test`() {
        val array = DynamicArray<String>(3)

        array.add("text2")
        array.add("text1")
        array.add("text3")
        array.add("text4")

        assertThat(array.size()).isEqualTo(4)
    }

    @Test
    fun testAddAndGet() {
        val array = DynamicArray<Int>(10)
        array.add(1)
        array.add(2)
        array.add(3)

        assertEquals(1, array.get(0))
        assertEquals(2, array.get(1))
        assertEquals(3, array.get(2))
    }

    @Test
    fun testSet() {
        val array = DynamicArray<Int>(10)
        array.set(0, 1)
        array.set(1, 2)
        array.set(2, 3)

        assertEquals(1, array.get(0))
        assertEquals(2, array.get(1))
        assertEquals(3, array.get(2))
    }

    @Test
    fun testInsert() {
        val array = DynamicArray<Int>(10)
        array.add(1)
        array.add(3)
        array.insert(1, 2)

        assertEquals(1, array.get(0))
        assertEquals(2, array.get(1))
        assertEquals(3, array.get(2))
    }

    @Test
    fun testDelete() {
        val array = DynamicArray<Int>(10)
        array.add(1)
        array.add(2)
        array.add(3)
        array.delete(1)

        assertEquals(1, array.get(0))
        assertEquals(3, array.get(1))
        assertEquals(null, array.get(2))
    }

    @Test
    fun testSize() {
        val array = DynamicArray<Int>(10)
        array.add(1)
        array.add(2)
        array.add(3)

        assertEquals(3, array.size())
    }

    @Test
    fun testIsEmpty() {
        val array = DynamicArray<Int>(10)
        assertTrue(array.isEmpty())

        array.add(1)
        assertFalse(array.isEmpty())
    }

    @Test
    fun testContains() {
        val array = DynamicArray<Int>(10)
        array.add(1)
        array.add(2)

        assertTrue(array.contains(1))
        assertFalse(array.contains(3))
    }
}
