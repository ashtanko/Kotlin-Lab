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

class PersistentSegmentTreeTest {
    @Test
    fun testInitialTree() {
        val pst = PersistentSegmentTree(8)

        // Query the initial version (all zeros)
        assertEquals(0, pst.query(0, 0, 7)) // Full range query
        assertEquals(0, pst.query(0, 3, 5)) // Partial range query
    }

    @Test
    fun testSingleUpdate() {
        val pst = PersistentSegmentTree(8)

        // Update index 3 in version 0
        val version1 = pst.update(0, 3, 7)

        // Verify the update
        assertEquals(7, pst.query(version1, 3, 3)) // Query the updated index
        assertEquals(7, pst.query(version1, 0, 7)) // Full range query
        assertEquals(0, pst.query(0, 3, 3)) // Original version remains unchanged
    }

    @Test
    fun testMultipleUpdates() {
        val pst = PersistentSegmentTree(8)

        // Update in sequence
        val version1 = pst.update(0, 3, 7) // Version 1: Set index 3 to 7
        val version2 = pst.update(version1, 5, 4) // Version 2: Set index 5 to 4
        val version3 = pst.update(version2, 2, 10) // Version 3: Set index 2 to 10

        // Verify queries on different versions
        assertEquals(7, pst.query(version1, 3, 3)) // Version 1
        assertEquals(7 + 4, pst.query(version2, 3, 5)) // Version 2
        assertEquals(7 + 4 + 10, pst.query(version3, 2, 5)) // Version 3
        assertEquals(0, pst.query(0, 2, 5)) // Original version unchanged
    }

    @Test
    fun testRangeQuery() {
        val pst = PersistentSegmentTree(8)

        // Perform updates
        val version1 = pst.update(0, 1, 5)
        val version2 = pst.update(version1, 4, 10)
        val version3 = pst.update(version2, 7, 3)

        // Verify range queries
        assertEquals(0, pst.query(0, 0, 7)) // Initial version
        assertEquals(5, pst.query(version1, 1, 1)) // Single element range
        assertEquals(15, pst.query(version2, 0, 4)) // Range with updates
        assertEquals(13, pst.query(version3, 4, 7)) // Range including new update
    }

    @Test
    fun testPersistence() {
        val pst = PersistentSegmentTree(8)

        // Perform updates
        val version1 = pst.update(0, 1, 3) // Version 1: Update index 1
        val version2 = pst.update(version1, 3, 5) // Version 2: Update index 3
        val version3 = pst.update(version2, 7, 2) // Version 3: Update index 7

        // Ensure previous versions remain unaffected
        assertEquals(0, pst.query(0, 0, 7)) // Initial version
        assertEquals(3, pst.query(version1, 1, 1)) // Version 1
        assertEquals(8, pst.query(version2, 1, 3)) // Version 2: Sum of updated indices
        assertEquals(7, pst.query(version3, 3, 7)) // Version 3: Includes the last update
    }

    @Test
    fun testOutOfRangeQuery() {
        val pst = PersistentSegmentTree(8)

        // Perform updates
        val version1 = pst.update(0, 2, 4)
        val version2 = pst.update(version1, 5, 6)

        // Query out of range
        assertEquals(0, pst.query(version2, 8, 10)) // Out of range
        assertEquals(0, pst.query(version1, 6, 7)) // No updates in this range
    }
}
