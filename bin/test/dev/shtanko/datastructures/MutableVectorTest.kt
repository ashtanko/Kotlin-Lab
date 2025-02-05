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

package dev.shtanko.datastructures

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MutableVectorTest {

    @Test
    fun addElementIncreasesSize() {
        val vector = MutableVector<Int>()
        vector.add(1)
        assertEquals(1, vector.size)
    }

    @Test
    fun addElementAtIndexShiftsElements() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.add(2)
        vector.add(1, 3)
        assertEquals(listOf(1, 3, 2), vector.asMutableList())
    }

    @Test
    fun addAllElementsIncreasesSize() {
        val vector = MutableVector<Int>()
        vector.addAll(listOf(1, 2, 3))
        assertEquals(3, vector.size)
    }

    @Test
    fun addAllElementsAtIndexShiftsElements() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.add(2)
        vector.addAll(1, listOf(3, 4))
        assertEquals(listOf(1, 3, 4, 2), vector.asMutableList())
    }

    @Test
    fun removeElementDecreasesSize() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.add(2)
        vector.remove(1)
        assertEquals(1, vector.size)
    }

    @Test
    fun removeElementAtIndexReturnsElement() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.add(2)
        val removed = vector.removeAt(0)
        assertEquals(1, removed)
    }

    @Test
    fun containsElementReturnsTrue() {
        val vector = MutableVector<Int>()
        vector.add(1)
        assertTrue(vector.contains(1))
    }

    @Test
    fun containsElementReturnsFalse() {
        val vector = MutableVector<Int>()
        assertFalse(vector.contains(1))
    }

    @Test
    fun getElementAtIndexReturnsElement() {
        val vector = MutableVector<Int>()
        vector.add(1)
        assertEquals(1, vector[0])
    }

    @Test
    fun clearEmptiesVector() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.clear()
        assertTrue(vector.isEmpty())
    }

    @Test
    fun isEmptyReturnsTrueForEmptyVector() {
        val vector = MutableVector<Int>()
        assertTrue(vector.isEmpty())
    }

    @Test
    fun isNotEmptyReturnsTrueForNonEmptyVector() {
        val vector = MutableVector<Int>()
        vector.add(1)
        assertTrue(vector.isNotEmpty())
    }

    @Test
    fun firstReturnsFirstElement() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.add(2)
        assertEquals(1, vector.first())
    }

    @Test
    fun firstThrowsExceptionForEmptyVector() {
        val vector = MutableVector<Int>()
        assertThrows<NoSuchElementException> {
            vector.first()
        }
    }

    @Test
    fun lastReturnsLastElement() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.add(2)
        assertEquals(2, vector.last())
    }

    @Test
    fun lastThrowsExceptionForEmptyVector() {
        val vector = MutableVector<Int>()
        assertThrows<NoSuchElementException> {
            vector.last()
        }
    }

    @Test
    fun indexOfReturnsCorrectIndex() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.add(2)
        assertEquals(1, vector.indexOf(2))
    }

    @Test
    fun indexOfReturnsMinusOneForNonExistentElement() {
        val vector = MutableVector<Int>()
        assertEquals(-1, vector.indexOf(1))
    }

    @Test
    fun mapTransformsElements() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.add(2)
        val mapped = vector.map { it * 2 }
        assertEquals(listOf(2, 4), mapped.toList())
    }

    @Test
    fun foldAccumulatesValues() {
        val vector = MutableVector<Int>()
        vector.add(1)
        vector.add(2)
        val sum = vector.fold(0) { acc, i -> acc + i }
        assertEquals(3, sum)
    }

    @Test
    fun mutableVectorOfCreatesEmptyVector() {
        val vector = mutableVectorOf<Int>()
        assertTrue(vector.isEmpty())
    }

    @Test
    fun mutableVectorOfCreatesVectorWithElements() {
        val vector = mutableVectorOf(1, 2, 3)
        assertEquals(3, vector.size)
        assertEquals(1, vector[0])
        assertEquals(2, vector[1])
        assertEquals(3, vector[2])
    }

    @Test
    fun removeIfRemovesMatchingElements() {
        val vector = mutableVectorOf(1, 2, 3, 4, 5)
        vector.removeIf { it % 2 == 0 }
        assertEquals(listOf(1, 3, 5), vector.asMutableList())
    }

    @Test
    fun removeIfKeepsNonMatchingElements() {
        val vector = mutableVectorOf(1, 2, 3, 4, 5)
        vector.removeIf { it > 5 }
        assertEquals(listOf(1, 2, 3, 4, 5), vector.asMutableList())
    }

    @Test
    fun removeIfOnEmptyVector() {
        val vector = mutableVectorOf<Int>()
        vector.removeIf { it % 2 == 0 }
        assertTrue(vector.isEmpty())
    }
}
