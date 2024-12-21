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

package dev.shtanko.datastructures.sets

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MutableSetTest {
    @Test
    fun testMutableSetCreation() {
        val set = mutableSetOf(1, 2, 3)
        assertEquals(3, set.size)
        assertTrue(set.containsAll(listOf(1, 2, 3)))
    }

    @Test
    fun testAddElement() {
        val set = mutableSetOf(1, 2, 3)
        set.add(4)
        assertEquals(4, set.size)
        assertTrue(set.contains(4))
    }

    @Test
    fun testAddDuplicateElement() {
        val set = mutableSetOf(1, 2, 3)
        val wasAdded = set.add(3) // Set should not add duplicate
        assertFalse(wasAdded)
        assertEquals(3, set.size)
    }

    @Test
    fun testAddAllElements() {
        val set = mutableSetOf(1, 2, 3)
        set.addAll(listOf(4, 5, 6))
        assertEquals(6, set.size)
        assertTrue(set.containsAll(listOf(4, 5, 6)))
    }

    @Test
    fun testRemoveElement() {
        val set = mutableSetOf(1, 2, 3, 4)
        val wasRemoved = set.remove(3)
        assertTrue(wasRemoved)
        assertEquals(3, set.size)
        assertFalse(set.contains(3))
    }

    @Test
    fun testRemoveNonexistentElement() {
        val set = mutableSetOf(1, 2, 3, 4)
        val wasRemoved = set.remove(5)
        assertFalse(wasRemoved)
        assertEquals(4, set.size)
    }

    @Test
    fun testRemoveAllMatchingElements() {
        val set = mutableSetOf(1, 2, 3, 4, 5)
        set.removeAll(listOf(2, 4))
        assertEquals(3, set.size)
        assertTrue(set.containsAll(listOf(1, 3, 5)))
        assertFalse(set.contains(2))
        assertFalse(set.contains(4))
    }

    @Test
    fun testClearSet() {
        val set = mutableSetOf(1, 2, 3)
        set.clear()
        assertTrue(set.isEmpty())
    }

    @Test
    fun testSetUnion() {
        val set1 = mutableSetOf(1, 2, 3)
        val set2 = mutableSetOf(3, 4, 5)
        val unionSet = set1.union(set2)
        assertEquals(setOf(1, 2, 3, 4, 5), unionSet)
    }

    @Test
    fun testSetIntersection() {
        val set1 = mutableSetOf(1, 2, 3)
        val set2 = mutableSetOf(2, 3, 4)
        val intersectionSet = set1.intersect(set2)
        assertEquals(setOf(2, 3), intersectionSet)
    }

    @Test
    fun testSetDifference() {
        val set1 = mutableSetOf(1, 2, 3)
        val set2 = mutableSetOf(2, 3, 4)
        val differenceSet = set1.subtract(set2)
        assertEquals(setOf(1), differenceSet)
    }

    @Test
    fun testSetIteration() {
        val set = mutableSetOf(1, 2, 3)
        var sum = 0
        for (element in set) {
            sum += element
        }
        assertEquals(6, sum)
    }

    @Test
    fun testSetTransformation() {
        val set = mutableSetOf(1, 2, 3)
        val doubledSet = set.map { it * 2 }.toSet()
        assertEquals(setOf(2, 4, 6), doubledSet)
    }

    @Test
    fun testSetFiltering() {
        val set = mutableSetOf(1, 2, 3, 4, 5)
        val evenSet = set.filter { it % 2 == 0 }.toSet()
        assertEquals(setOf(2, 4), evenSet)
    }

    @Test
    fun testSetContains() {
        val set = mutableSetOf(1, 2, 3, 4, 5)
        assertTrue(set.contains(3))
        assertFalse(set.contains(6))
    }

    @Test
    fun testSetEquality() {
        val set1 = mutableSetOf(1, 2, 3)
        val set2 = mutableSetOf(1, 2, 3)
        assertEquals(set1, set2)

        val set3 = mutableSetOf(3, 2, 1)
        assertEquals(set1, set3) // Sets ignore element order

        val set4 = mutableSetOf(4, 5, 6)
        assertNotEquals(set1, set4)
    }

    @Test
    fun testSetPlusOperator() {
        val set = mutableSetOf(1, 2, 3)
        val newSet = set + 4
        assertEquals(setOf(1, 2, 3, 4), newSet)
    }

    @Test
    fun testSetMinusOperator() {
        val set = mutableSetOf(1, 2, 3, 4)
        val newSet = set - 3
        assertEquals(setOf(1, 2, 4), newSet)
    }

    @Test
    fun testSetCopying() {
        val set = mutableSetOf(1, 2, 3)
        val copy = set.toMutableSet()
        assertEquals(set, copy)
        assertTrue(copy !== set) // Ensure they are different instances
    }

    @Test
    fun testSetFlatten() {
        val set = mutableSetOf(setOf(1, 2), setOf(3, 4))
        val flattenedSet = set.flatten().toSet()
        assertEquals(setOf(1, 2, 3, 4), flattenedSet)
    }

    @Test
    fun testSetZip() {
        val set1 = mutableSetOf(1, 2, 3)
        val set2 = mutableSetOf("a", "b", "c")
        val zippedSet = set1.zip(set2).toSet()
        assertEquals(setOf(1 to "a", 2 to "b", 3 to "c"), zippedSet)
    }

    @Test
    fun testSetPartition() {
        val set = mutableSetOf(1, 2, 3, 4, 5)
        val (even, odd) = set.partition { it % 2 == 0 }
        assertEquals(setOf(2, 4), even.toSet())
        assertEquals(setOf(1, 3, 5), odd.toSet())
    }

    @Test
    fun testSetGrouping() {
        val set = mutableSetOf("apple", "banana", "avocado", "blueberry")
        val groupedByFirstLetter = set.groupBy { it.first() }
        assertEquals(
            mapOf('a' to listOf("apple", "avocado"), 'b' to listOf("banana", "blueberry")),
            groupedByFirstLetter,
        )
    }
}
