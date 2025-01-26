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
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class SkipListTest {

    @Test
    fun `insert and search should work correctly`() {
        val skipList = SkipList<Int, String>()

        skipList.insert(1, "One")
        skipList.insert(3, "Three")
        skipList.insert(7, "Seven")
        skipList.insert(5, "Five")

        assertEquals("One", skipList.search(1))
        assertEquals("Three", skipList.search(3))
        assertEquals("Seven", skipList.search(7))
        assertEquals("Five", skipList.search(5))

        // Non-existing key
        assertNull(skipList.search(10))
    }

    @Test
    fun `insert should update existing key value`() {
        val skipList = SkipList<Int, String>()

        skipList.insert(1, "One")
        skipList.insert(1, "Updated One")

        assertEquals("Updated One", skipList.search(1))
    }

    @Test
    fun `delete should remove the key and value`() {
        val skipList = SkipList<Int, String>()

        skipList.insert(1, "One")
        skipList.insert(3, "Three")
        skipList.insert(5, "Five")

        skipList.delete(3)

        assertNull(skipList.search(3))
        assertEquals("One", skipList.search(1))
        assertEquals("Five", skipList.search(5))
    }

    @Test
    fun `delete should not affect non-existing keys`() {
        val skipList = SkipList<Int, String>()

        skipList.insert(1, "One")
        skipList.insert(3, "Three")

        skipList.delete(5) // Deleting non-existing key

        assertEquals("One", skipList.search(1))
        assertEquals("Three", skipList.search(3))
    }

    @Test
    fun `search should return null for empty skip list`() {
        val skipList = SkipList<Int, String>()

        assertNull(skipList.search(1))
    }

    @Test
    fun `insertion order does not affect search correctness`() {
        val skipList = SkipList<Int, String>()

        skipList.insert(5, "Five")
        skipList.insert(3, "Three")
        skipList.insert(7, "Seven")
        skipList.insert(1, "One")

        assertEquals("One", skipList.search(1))
        assertEquals("Three", skipList.search(3))
        assertEquals("Five", skipList.search(5))
        assertEquals("Seven", skipList.search(7))
    }

    @Test
    fun `skip list should handle duplicate insertions and deletions correctly`() {
        val skipList = SkipList<Int, String>()

        skipList.insert(2, "Two")
        skipList.insert(2, "Updated Two") // Duplicate insertion

        assertEquals("Updated Two", skipList.search(2))

        skipList.delete(2)
        assertNull(skipList.search(2))

        skipList.delete(2) // Deleting non-existing key again
        assertNull(skipList.search(2))
    }

    @Test
    fun `test display on empty skip list`() {
        val skipList = SkipList<Int, String>()
        val expected = "Skip List:\nLevel 0: null\n" // No levels in an empty skip list
        println(skipList.display())
        assertEquals(expected, skipList.display())
    }

    @Test
    fun `test display after insertions`() {
        val skipList = SkipList<Int, String>()
        skipList.insert(1, "One")
        skipList.insert(3, "Three")
        skipList.insert(7, "Seven")

        val result = skipList.display()
        println(result) // Optional: for debugging

        // Verify the structure visually (example, may vary due to random levels)
        val expectedStart = "Skip List:\n"
        assert(result.startsWith(expectedStart)) // Ensures we are getting the correct format
    }
}
