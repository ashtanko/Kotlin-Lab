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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class QueueTest {

    @Test
    internal fun `empty test`() {
        val queue = Queue<Int>()
        assertEquals(0, queue.size)
        assertTrue(queue.isEmpty())
    }

    @Test
    internal fun `exception test`() {
        assertThrows<NoSuchElementException> {
            val queue = Queue<Int>()
            queue.peek()
        }
    }

    @Test
    internal fun `naive test`() {
        val queue = Queue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }
        for (i in 0..10) {
            assertEquals(i, queue.peek())
            assertEquals(i, queue.poll())
        }
        assertEquals(0, queue.size)
    }

    @Test
    internal fun `naive iterator test`() {
        val queue = Queue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }
        var k = 0
        for (i in queue) {
            assertEquals(i, k++)
        }
    }

    @Test
    internal fun `naive contains test`() {
        val queue = Queue<Int>()
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
        val queue = Queue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }
        val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 7)
        assertTrue(queue.containsAll(list))
        assertFalse(queue.containsAll(listOf(23, 42)))
    }

    @Test
    internal fun `poll error test`() {
        val queue = Queue<Int>()
        assertThrows<NoSuchElementException> {
            queue.poll()
        }
    }

    @Test
    internal fun `is empty test`() {
        val queue = Queue<Int>()
        assertThat(queue.isEmpty()).isTrue
        queue.add(0)
        assertThat(queue.isEmpty()).isFalse
    }

    @Test
    internal fun `iterator error test`() {
        val queue = Queue<Int>()
        queue.add(0)
        queue.add(1)
        val iterator = queue.iterator()
        assertThrows<NoSuchElementException> {
            repeat(3) {
                iterator.next()
            }
        }
    }

    @Test
    internal fun `peek null test`() {
        val queue = Queue<Int?>()
        queue.add(null)
        assertThat(queue.peek()).isNull()
    }

    @Test
    internal fun `peek test`() {
        val queue = Queue<Int?>()
        queue.add(1)
        queue.add(2)
        assertThat(queue.peek()).isEqualTo(1)
    }
}
