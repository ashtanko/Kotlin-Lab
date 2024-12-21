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
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StackTest {

    @Test
    @DisplayName("when stack is empty")
    internal fun `empty test`() {
        val stack = Stack<Int>()
        assertEquals(0, stack.size)
        assertTrue(stack.isEmpty())
    }

    @Test
    @DisplayName("when trying peek on empty stack")
    internal fun `exception test`() {
        assertThrows<NoSuchElementException> {
            val stack = Stack<Int>()
            stack.peek()
        }
    }

    @Test
    @DisplayName("when add 10 elements and poll them should be empty")
    internal fun `naive test`() {
        val stack = Stack<Int>()

        for (i in 0..10) {
            stack.push(i)
        }
        for (i in 10 downTo 0) {
            assertEquals(i, stack.peek())
            assertEquals(i, stack.poll())
        }
        assertEquals(0, stack.size)
    }

    @Test
    internal fun `test pop`() {
        val stack = Stack<Int>()
        stack.push(1)
        assertFalse(stack.isEmpty())
    }

    @Test
    internal fun `naive iterator test`() {
        val stack = Stack<Int>()
        for (i in 0..10) {
            stack.push(i)
        }
        var k = 10
        for (i in stack) {
            assertEquals(i, k--)
        }
    }

    @Test
    internal fun `naive contains test`() {
        val stack = Stack<Int>()
        for (i in 0..10) {
            stack.push(i)
        }

        for (i in 0..10) {
            assertTrue(stack.contains(i))
        }

        assertFalse(stack.contains(100))
        assertFalse(stack.contains(101))
        assertFalse(stack.contains(103))
    }

    @Test
    internal fun `naive contains all test`() {
        val stack = Stack<Int>()
        for (i in 0..10) {
            stack.push(i)
        }

        val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 7)
        assertTrue(stack.containsAll(list))
        assertFalse(stack.containsAll(listOf(23, 42)))
    }

    @Test
    internal fun `poll error test`() {
        val stack = Stack<Int>()
        assertThrows<NoSuchElementException> {
            stack.poll()
        }
    }

    @Test
    internal fun `iterator next error test`() {
        val stack = Stack<Int>()
        assertThrows<NoSuchElementException> {
            stack.iterator().next()
        }
    }
}
