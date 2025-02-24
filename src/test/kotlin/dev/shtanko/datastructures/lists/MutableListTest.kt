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

package dev.shtanko.datastructures.lists

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MutableListTest {
    @Test
    fun testMutableListCreation() {
        val list = mutableListOf(1, 2, 3)
        assertEquals(3, list.size)
        assertEquals(listOf(1, 2, 3), list)
    }

    @Test
    fun testAddElement() {
        val list = mutableListOf(1, 2, 3)
        list.add(4)
        assertEquals(4, list.size)
        assertEquals(listOf(1, 2, 3, 4), list)
    }

    @Test
    fun testAddElementAtIndex() {
        val list = mutableListOf(1, 2, 3)
        list.add(1, 10)
        assertEquals(listOf(1, 10, 2, 3), list)
    }

    @Test
    fun testAddAllElements() {
        val list = mutableListOf(1, 2, 3)
        list.addAll(listOf(4, 5, 6))
        assertEquals(listOf(1, 2, 3, 4, 5, 6), list)
    }

    @Test
    fun testRemoveElement() {
        val list = mutableListOf(1, 2, 3, 4)
        list.remove(3)
        assertEquals(listOf(1, 2, 4), list)
    }

    @Test
    fun testRemoveElementAtIndex() {
        val list = mutableListOf(1, 2, 3, 4)
        list.removeAt(1)
        assertEquals(listOf(1, 3, 4), list)
    }

    @Test
    fun testRemoveAllMatchingElements() {
        val list = mutableListOf(1, 2, 3, 4, 2)
        list.removeAll(listOf(2, 4))
        assertEquals(listOf(1, 3), list)
    }

    @Test
    fun testUpdateElement() {
        val list = mutableListOf(1, 2, 3)
        list[1] = 10
        assertEquals(listOf(1, 10, 3), list)
    }

    @Test
    fun testClearList() {
        val list = mutableListOf(1, 2, 3)
        list.clear()
        assertTrue(list.isEmpty())
    }

    @Test
    fun testMutableListIteration() {
        val list = mutableListOf(1, 2, 3)
        var sum = 0
        for (element in list) {
            sum += element
        }
        assertEquals(6, sum)
    }

    @Test
    fun testMutableListTransformation() {
        val list = mutableListOf(1, 2, 3)
        val doubledList = list.map { it * 2 }
        assertEquals(listOf(2, 4, 6), doubledList)
    }

    @Test
    fun testMutableListFiltering() {
        val list = mutableListOf(1, 2, 3, 4, 5)
        val evenList = list.filter { it % 2 == 0 }
        assertEquals(listOf(2, 4), evenList)
    }

    @Test
    fun testMutableListSorting() {
        val list = mutableListOf(5, 2, 1, 4, 3)
        list.sort()
        assertEquals(listOf(1, 2, 3, 4, 5), list)
    }

    @Test
    fun testMutableListReversing() {
        val list = mutableListOf(1, 2, 3, 4, 5)
        list.reverse()
        assertEquals(listOf(5, 4, 3, 2, 1), list)
    }

    @Test
    fun testMutableListSearching() {
        val list = mutableListOf(1, 2, 3, 4, 5)
        val index = list.indexOf(3)
        assertEquals(2, index)
    }

    @Test
    fun testMutableListContains() {
        val list = mutableListOf(1, 2, 3, 4, 5)
        assertTrue(list.contains(3))
        assertFalse(list.contains(6))
    }

    @Test
    fun testMutableListCopying() {
        val list = mutableListOf(1, 2, 3)
        val copy = list.toMutableList()
        assertEquals(list, copy)

        val partialCopy = list.subList(1, 3).toMutableList()
        assertEquals(listOf(2, 3), partialCopy)
    }

    @Test
    fun testMutableListJoining() {
        val list = mutableListOf(1, 2, 3)
        val joinedString = list.joinToString(separator = ", ", prefix = "[", postfix = "]")
        assertEquals("[1, 2, 3]", joinedString)
    }

    @Test
    fun testMutableListEquality() {
        val list1 = mutableListOf(1, 2, 3)
        val list2 = mutableListOf(1, 2, 3)
        assertEquals(list1, list2)

        val list3 = mutableListOf(3, 2, 1)
        assertNotEquals(list1, list3)
    }

    @Test
    fun testMutableListPlusOperator() {
        val list = mutableListOf(1, 2, 3)
        val newList = list + 4
        assertEquals(listOf(1, 2, 3, 4), newList)
    }

    @Test
    fun testMutableListMinusOperator() {
        val list = mutableListOf(1, 2, 3, 4)
        val newList = list - 3
        assertEquals(listOf(1, 2, 4), newList)
    }

    @Test
    fun testMutableListFlatten() {
        val list = mutableListOf(mutableListOf(1, 2), mutableListOf(3, 4))
        val flattenedList = list.flatten()
        assertEquals(listOf(1, 2, 3, 4), flattenedList)
    }

    @Test
    fun testMutableListZip() {
        val list1 = mutableListOf(1, 2, 3)
        val list2 = mutableListOf("a", "b", "c")
        val zippedList = list1.zip(list2)
        assertEquals(listOf(1 to "a", 2 to "b", 3 to "c"), zippedList)
    }

    @Test
    fun testMutableListPartition() {
        val list = mutableListOf(1, 2, 3, 4, 5)
        val (even, odd) = list.partition { it % 2 == 0 }
        assertEquals(listOf(2, 4), even)
        assertEquals(listOf(1, 3, 5), odd)
    }

    @Test
    fun testMutableListGrouping() {
        val list = mutableListOf("apple", "banana", "avocado", "blueberry")
        val groupedByFirstLetter = list.groupBy { it.first() }
        assertEquals(
            mapOf('a' to listOf("apple", "avocado"), 'b' to listOf("banana", "blueberry")),
            groupedByFirstLetter,
        )
    }

    @Test
    fun testMutableListAggregateOperations() {
        val list = mutableListOf(1, 2, 3, 4, 5)
        val sum = list.sum()
        val product = list.reduce { acc, i -> acc * i }
        assertEquals(15, sum)
        assertEquals(120, product)
    }
}
