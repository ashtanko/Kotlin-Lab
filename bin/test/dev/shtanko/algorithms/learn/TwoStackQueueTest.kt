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

package dev.shtanko.algorithms.learn

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TwoStackQueueTest {

    @Test
    internal fun `empty test`() {
        val queue = TwoStackQueue<Int>()
        assertEquals(0, queue.size)
        assertTrue(queue.isEmpty())
    }

    @Test
    internal fun `exception test`() {
        assertThrows<NoSuchElementException> {
            val queue = TwoStackQueue<Int>()
            queue.peek()
        }
    }

    @Test
    internal fun `naive test`() {
        val queue = TwoStackQueue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }
        for (i in 0..10) {
            assertEquals(i, queue.peek())
        }
        assertEquals(0, queue.size)
    }

    @Test
    internal fun `naive iterator test`() {
        val queue = TwoStackQueue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }
        for ((k, i) in queue.withIndex()) {
            assertEquals(i, k)
        }
    }

    @Test
    internal fun `naive contains test`() {
        val queue = TwoStackQueue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }
        for (i in 0..10) {
            assertTrue(queue.contains(i))
        }

        assertFalse(queue.contains(100))
        assertFalse(queue.contains(101))
        assertFalse(queue.contains(103))
    }

    @Test
    internal fun `naive contains all test`() {
        val queue = TwoStackQueue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }
        val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 7)
        assertTrue(queue.containsAll(list))

        assertFalse(queue.containsAll(listOf(23, 42)))
    }
}
