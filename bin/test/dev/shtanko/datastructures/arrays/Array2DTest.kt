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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Array2DTest {

    @Test
    fun `test default state`() {
        val array2D = Array2D<Int>(30, 30, 0)
        array2D.printArray()

        assertEquals(900, array2D.toList().size) // After setting, it should be 100
    }

    @Test
    fun `test get and set element`() {
        val array2D = Array2D<Int>(3, 4, 0)
        array2D[1, 1] = 100
        array2D.printArray()

        assertEquals(100, array2D[1, 1]) // After setting, it should be 100
    }

    @Test
    fun `test getRow`() {
        val array2D = Array2D<Int>(3, 4, 0)
        for (i in 0 until 4) {
            array2D[1, i] = i + 4
        }
        array2D.printArray()

        // Test getting a specific row
        assertEquals(listOf(4, 5, 6, 7), array2D.getRow(1)) // Row 1: 4, 5, 6, 7
    }

    @Test
    fun `test getColumn`() {
        val array2D = Array2D<Int>(3, 4, 0)
        for (i in 0 until 3) {
            array2D[i, 1] = i * 4 + 1
        }
        array2D.printArray()

        // Test getting a specific column
        assertEquals(listOf(1, 5, 9), array2D.getColumn(1)) // Column 1: 1, 5, 9
    }

    @Test
    fun `test iteration over all elements`() {
        val array2D = Array2D<Int>(3, 4, 0)
        for (i in 0 until 3) {
            for (j in 0 until 4) {
                array2D[i, j] = i * 4 + j
            }
        }
        // Verify that the iteration works correctly
        val expected = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        val result = mutableListOf<Int>()
        for (value in array2D) {
            result.add(value)
        }
        array2D.printArray()

        assertEquals(expected, result)
    }

    @Test
    fun `test full array print`() {
        val array2D = Array2D<Int>(3, 4, 0)
        for (i in 0 until 4) {
            array2D[1, i] = i + 4
        }
        // Verify that the printArray() works correctly (a side effect test)
        // We won't check the output directly, but you can run this manually
        array2D.printArray()
    }
}
