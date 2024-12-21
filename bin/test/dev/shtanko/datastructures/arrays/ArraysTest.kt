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

package dev.shtanko.datastructures.arrays

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ArraysTest {
    @Test
    fun testArrayCreation() {
        val array = arrayOf(1, 2, 3)
        assertEquals(3, array.size)
        assertArrayEquals(arrayOf(1, 2, 3), array)
    }

    @Test
    fun testSpecializedArrayCreation() {
        val intArray = intArrayOf(1, 2, 3)
        val doubleArray = doubleArrayOf(1.0, 2.0, 3.0)

        assertEquals(3, intArray.size)
        assertEquals(3, doubleArray.size)
        assertArrayEquals(intArrayOf(1, 2, 3), intArray)
        assertArrayEquals(doubleArrayOf(1.0, 2.0, 3.0), doubleArray, 0.0)
    }

    @Test
    fun testArrayAccessAndModification() {
        val array = arrayOf(1, 2, 3)
        assertEquals(1, array[0])
        assertEquals(2, array[1])
        assertEquals(3, array[2])

        array[0] = 10
        assertEquals(10, array[0])
    }

    @Test
    fun testArrayIteration() {
        val array = arrayOf(1, 2, 3)
        var sum = 0
        for (element in array) {
            sum += element
        }
        assertEquals(6, sum)
    }

    @Test
    fun testArrayTransformation() {
        val array = arrayOf(1, 2, 3)
        val doubledArray = array.map { it * 2 }.toTypedArray()
        assertArrayEquals(arrayOf(2, 4, 6), doubledArray)
    }

    @Test
    fun testArrayFiltering() {
        val array = arrayOf(1, 2, 3, 4, 5)
        val evenArray = array.filter { it % 2 == 0 }.toTypedArray()
        assertArrayEquals(arrayOf(2, 4), evenArray)
    }

    @Test
    fun testArraySorting() {
        val array = arrayOf(5, 2, 1, 4, 3)
        array.sort()
        assertArrayEquals(arrayOf(1, 2, 3, 4, 5), array)
    }

    @Test
    fun testArrayReversing() {
        val array = arrayOf(1, 2, 3, 4, 5)
        array.reverse()
        assertArrayEquals(arrayOf(5, 4, 3, 2, 1), array)
    }

    @Test
    fun testArraySearching() {
        val array = arrayOf(1, 2, 3, 4, 5)
        val index = array.indexOf(3)
        assertEquals(2, index)
    }

    @Test
    fun testArrayContains() {
        val array = arrayOf(1, 2, 3, 4, 5)
        assertTrue(array.contains(3))
        assertFalse(array.contains(6))
    }

    @Test
    fun testArrayCopying() {
        val array = arrayOf(1, 2, 3)
        val copy = array.copyOf()
        assertArrayEquals(array, copy)

        val partialCopy = array.copyOfRange(1, 3)
        assertArrayEquals(arrayOf(2, 3), partialCopy)
    }

    @Test
    fun testArrayFilling() {
        val array = arrayOf(0, 0, 0)
        array.fill(5)
        assertArrayEquals(arrayOf(5, 5, 5), array)
    }

    @Test
    fun testArrayJoining() {
        val array = arrayOf(1, 2, 3)
        val joinedString = array.joinToString(separator = ", ", prefix = "[", postfix = "]")
        assertEquals("[1, 2, 3]", joinedString)
    }

    @Test
    fun testArrayEquality() {
        val array1 = arrayOf(1, 2, 3)
        val array2 = arrayOf(1, 2, 3)
        assertArrayEquals(array1, array2)

        val array3 = arrayOf(3, 2, 1)
        assertFalse(array1.contentEquals(array3))
    }
}
