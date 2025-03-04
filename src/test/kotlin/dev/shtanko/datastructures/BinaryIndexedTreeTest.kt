/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.datastructures

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BinaryIndexedTreeTest {
    @Test
    fun testUpdateAndQuery() {
        val bit = BinaryIndexedTree(10)

        // Initialize with some values
        val array = listOf(3, 2, -1, 6, 5, 4, -3, 3, 7, 2)
        for (i in array.indices) {
            bit.update(i + 1, array[i])
        }

        // Test prefix sums
        assertEquals(3, bit.query(1)) // Only the first element
        assertEquals(5, bit.query(2)) // 3 + 2
        assertEquals(15, bit.query(5)) // 3 + 2 - 1 + 6 + 5

        // Test full sum
        assertEquals(28, bit.query(10)) // Sum of all elements
    }

    @Test
    fun testRangeQuery() {
        val bit = BinaryIndexedTree(10)

        // Initialize with some values
        val array = listOf(3, 2, -1, 6, 5, 4, -3, 3, 7, 2)
        for (i in array.indices) {
            bit.update(i + 1, array[i])
        }

        // Test range sums
        assertEquals(7, bit.rangeQuery(2, 4)) // 2 + (-1) + 6
        assertEquals(14, bit.rangeQuery(3, 8)) // -1 + 6 + 5 + 4 + (-3) + 3
        assertEquals(12, bit.rangeQuery(8, 10)) // 3 + 7 + 2
    }

    @Test
    fun testUpdateAffectsQuery() {
        val bit = BinaryIndexedTree(10)

        // Initialize with some values
        val array = listOf(3, 2, -1, 6, 5, 4, -3, 3, 7, 2)
        for (i in array.indices) {
            bit.update(i + 1, array[i])
        }

        // Update a value
        bit.update(4, 2) // Add 2 to the value at index 4

        // Verify that queries reflect the update
        assertEquals(17, bit.query(5)) // 3 + 2 - 1 + (6+2) + 5
        assertEquals(30, bit.query(10)) // Updated total sum
    }

    @Test
    fun testEmptyTree() {
        val bit = BinaryIndexedTree(5)

        // All queries should return 0 for an empty tree
        assertEquals(0, bit.query(1))
        assertEquals(0, bit.query(5))
        assertEquals(0, bit.rangeQuery(1, 5))
    }
}
