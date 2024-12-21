/*
 * Copyright 2020 Oleksii Shtanko
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

internal class DisjointSetTest {

    @Test
    internal fun `size test`() {
        val set = DisjointSet(10)
        assertEquals(10, set.count)
        assertEquals(10, set.size)
        set.union(0, 1)
        assertEquals(9, set.count)
        assertEquals(10, set.size)
        set.union(0, 2)
        assertEquals(8, set.count)
        assertEquals(10, set.size)
    }

    @Test
    internal fun `naive test`() {
        val set = DisjointSet(10)
        for (i in 1 until set.size) {
            assertFalse(set.connected(i, i - 1))
        }
        set.union(1, 2)
        assertTrue(set.connected(1, 2))
        set.union(1, 3)
        assertTrue(set.connected(1, 3))
        assertTrue(set.connected(2, 3))
        set.union(3, 4)
        assertTrue(set.connected(2, 4))
        assertFalse(set.connected(0, 1))
        assertFalse(set.connected(0, 5))
        assertFalse(set.connected(4, 5))
    }

    @Test
    fun `connected test`() {
        val disjointSet = DisjointSet(5)
        assertFalse(disjointSet.connected(0, 1))
        assertFalse(disjointSet.connected(2, 3))

        disjointSet.union(0, 1)
        disjointSet.union(2, 3)
        assertTrue(disjointSet.connected(0, 1))
        assertTrue(disjointSet.connected(2, 3))
        assertFalse(disjointSet.connected(0, 3))
    }

    @Test
    fun `union test`() {
        val disjointSet = DisjointSet(6)
        assertEquals(6, disjointSet.count)

        disjointSet.union(0, 1)
        disjointSet.union(2, 3)
        disjointSet.union(4, 5)
        assertEquals(3, disjointSet.count)

        disjointSet.union(1, 3)
        disjointSet.union(3, 5)
        assertEquals(1, disjointSet.count)
    }
}
