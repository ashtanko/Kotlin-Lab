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
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PriorityQueueTest {

    @Test
    internal fun `empty test`() {
        val pq = PriorityQueue<Int>(3)
        assertEquals(0, pq.size)
        assertTrue(pq.isEmpty())
    }

    @Test
    internal fun `exception test`() {
        assertThrows<NoSuchElementException> {
            val pq = PriorityQueue<Int>(3)
            pq.peek()
        }
    }

    @Test
    internal fun `naive test`() {
        val pq = PriorityQueue<Int>(3)
        for (i in 10 downTo 1) {
            pq.add(i)
            assertEquals(i, pq.peek())
        }
        for (i in 1..10) {
            assertEquals(i, pq.poll())
        }
    }

    @Test
    internal fun `string order test`() {
        val pq = PriorityQueue<String>(6)
        pq.add("Lisa")
        pq.add("Robert")
        pq.add("John")
        pq.add("Chris")
        pq.add("Angelina")
        pq.add("Joe")

        val expectedOrder = mutableListOf<String>()
        while (pq.isNotEmpty()) {
            expectedOrder.add(pq.poll())
        }
        assertEquals(expectedOrder, listOf("Angelina", "Chris", "Joe", "John", "Lisa", "Robert"))
    }

    @Test
    internal fun `poll error test`() {
        val pq = PriorityQueue<String>(0)
        assertThrows<NoSuchElementException> {
            pq.poll()
        }
    }

    @Test
    internal fun `contains test`() {
        val pq = PriorityQueue<String>(10)
        pq.add("A")
        assertThat(pq.contains("A")).isTrue
        assertThat(pq.contains("B")).isFalse
    }

    @Test
    internal fun `contains all test`() {
        val pq = PriorityQueue<String>(10)
        pq.add("A")
        pq.add("B")
        assertThat(pq.containsAll(listOf("A"))).isTrue
        assertThat(pq.containsAll(listOf("A", "B", "C"))).isFalse
    }

    @Test
    internal fun `add test`() {
        val pq = PriorityQueue<Int>(10_001)
        repeat(10_000) {
            pq.add(it)
        }
        assertThat(pq.size).isEqualTo(10_000)
        repeat(10_000) {
            pq.poll()
        }
        assertThat(pq.size).isEqualTo(0)
    }
}
