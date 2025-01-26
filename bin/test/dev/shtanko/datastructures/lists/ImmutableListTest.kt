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

class ImmutableListTest {
    @Test
    fun testListCreation() {
        val list = listOf(1, 2, 3)
        assertEquals(3, list.size)
        assertEquals(listOf(1, 2, 3), list)
    }

    @Test
    fun testListAccess() {
        val list = listOf(1, 2, 3)
        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(3, list[2])
    }

    @Test
    fun testListIteration() {
        val list = listOf(1, 2, 3)
        var sum = 0
        for (element in list) {
            sum += element
        }
        assertEquals(6, sum)
    }

    @Test
    fun testListTransformation() {
        val list = listOf(1, 2, 3)
        val doubledList = list.map { it * 2 }
        assertEquals(listOf(2, 4, 6), doubledList)
    }

    @Test
    fun testListFiltering() {
        val list = listOf(1, 2, 3, 4, 5)
        val evenList = list.filter { it % 2 == 0 }
        assertEquals(listOf(2, 4), evenList)
    }

    @Test
    fun testListSorting() {
        val list = listOf(5, 2, 1, 4, 3)
        val sortedList = list.sorted()
        assertEquals(listOf(1, 2, 3, 4, 5), sortedList)
    }

    @Test
    fun testListReversing() {
        val list = listOf(1, 2, 3, 4, 5)
        val reversedList = list.reversed()
        assertEquals(listOf(5, 4, 3, 2, 1), reversedList)
    }

    @Test
    fun testListSearching() {
        val list = listOf(1, 2, 3, 4, 5)
        val index = list.indexOf(3)
        assertEquals(2, index)
    }

    @Test
    fun testListContains() {
        val list = listOf(1, 2, 3, 4, 5)
        assertTrue(list.contains(3))
        assertFalse(list.contains(6))
    }

    @Test
    fun testListJoining() {
        val list = listOf(1, 2, 3)
        val joinedString = list.joinToString(separator = ", ", prefix = "[", postfix = "]")
        assertEquals("[1, 2, 3]", joinedString)
    }

    @Test
    fun testListEquality() {
        val list1 = listOf(1, 2, 3)
        val list2 = listOf(1, 2, 3)
        assertEquals(list1, list2)

        val list3 = listOf(3, 2, 1)
        assertNotEquals(list1, list3)
    }

    @Test
    fun testListPlusOperator() {
        val list = listOf(1, 2, 3)
        val newList = list + 4
        assertEquals(listOf(1, 2, 3, 4), newList)
    }

    @Test
    fun testListMinusOperator() {
        val list = listOf(1, 2, 3, 4)
        val newList = list - 3
        assertEquals(listOf(1, 2, 4), newList)
    }

    @Test
    fun testListFlatten() {
        val list = listOf(listOf(1, 2), listOf(3, 4))
        val flattenedList = list.flatten()
        assertEquals(listOf(1, 2, 3, 4), flattenedList)
    }

    @Test
    fun testListZip() {
        val list1 = listOf(1, 2, 3)
        val list2 = listOf("a", "b", "c")
        val zippedList = list1.zip(list2)
        assertEquals(listOf(1 to "a", 2 to "b", 3 to "c"), zippedList)
    }

    @Test
    fun testDiffListZip() {
        val list1 = listOf(1, 2, 3)
        val list2 = listOf("a", "b", "c", "d")
        val zippedList = list1.zip(list2)
        assertEquals(listOf(1 to "a", 2 to "b", 3 to "c"), zippedList)
    }

    @Test
    fun testListPartition() {
        val list = listOf(1, 2, 3, 4, 5)
        val (even, odd) = list.partition { it % 2 == 0 }
        assertEquals(listOf(2, 4), even)
        assertEquals(listOf(1, 3, 5), odd)
    }

    @Test
    fun testListGrouping() {
        val list = listOf("apple", "banana", "avocado", "blueberry")
        val groupedByFirstLetter = list.groupBy { it.first() }
        assertEquals(
            mapOf('a' to listOf("apple", "avocado"), 'b' to listOf("banana", "blueberry")),
            groupedByFirstLetter,
        )
    }

    @Test
    fun testListAggregateOperations() {
        val list = listOf(1, 2, 3, 4, 5)
        val sum = list.sum()
        val product = list.reduce { acc, i -> acc * i }
        assertEquals(15, sum)
        assertEquals(120, product)
    }
}
