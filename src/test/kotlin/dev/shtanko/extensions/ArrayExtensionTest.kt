/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.extensions

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class ArrayExtensionTest {

    @Test
    internal fun `simple swap test`() {
        val arr = arrayOf(4, 8)
        arr.swap(1, 0)
        assertArrayEquals(arrayOf(8, 4), arr)
    }

    @Test
    internal fun `loop swap test`() {
        val arr = arrayOf(4, 8, 15, 16)
        for (i in 0 until arr.size - 1) {
            arr.swap(i, i + 1)
        }
        assertArrayEquals(arrayOf(8, 15, 16, 4), arr)
    }

    @Test
    internal fun `simple reverse test`() {
        val arr = arrayOf(4, 8, 15, 16, 23, 42)
        arr.reverse()
        assertArrayEquals(arrayOf(42, 23, 16, 15, 8, 4), arr)
    }

    @Test
    internal fun `two pointers technique reverse test`() {
        val arr = arrayOf(4, 8, 15, 16, 23, 42)
        arr.reverse2()
        assertArrayEquals(arrayOf(42, 23, 16, 15, 8, 4), arr)
    }

    @Test
    internal fun `flip test`() {
        val arr = arrayOf(4, 8, 15, 16, 23, 42)
        arr.flip(0, arr.size - 1)
        val expected = arrayOf(42, 23, 16, 15, 8, 4)
        assertArrayEquals(expected, arr)
    }

    @Test
    internal fun `char swap test`() {
        val arr = charArrayOf('A', 'B')
        arr.swap(1, 0)
        assertArrayEquals(charArrayOf('B', 'A'), arr)
    }

    @Test
    internal fun `int array swap test`() {
        val arr = intArrayOf(4, 8)
        arr.swap(1, 0)
        assertArrayEquals(intArrayOf(8, 4), arr)
    }

    @Test
    internal fun `second function on empty array test`() {
        val array = intArrayOf()
        assertThrows(NoSuchElementException::class.java) {
            array.second()
        }
    }

    @Test
    internal fun `second function on single item array test`() {
        val array = intArrayOf(1)
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            array.second()
        }
    }

    @Test
    internal fun `second function on array test`() {
        val array = intArrayOf(1, 2)
        assertEquals(2, array.second())
    }
}
