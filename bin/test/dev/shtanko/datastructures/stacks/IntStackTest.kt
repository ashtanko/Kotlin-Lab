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

package dev.shtanko.datastructures.stacks

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IntStackTest {

    @Test
    fun pushIncreasesSize() {
        val stack = IntStack()
        stack.push(1)
        assertEquals(1, stack.size)
    }

    @Test
    fun popDecreasesSize() {
        val stack = IntStack()
        stack.push(1)
        stack.pop()
        assertEquals(0, stack.size)
    }

    @Test
    fun popReturnsLastPushedElement() {
        val stack = IntStack()
        stack.push(1)
        stack.push(2)
        assertEquals(2, stack.pop())
    }

    @Test
    fun peekReturnsLastPushedElementWithoutRemoving() {
        val stack = IntStack()
        stack.push(1)
        stack.push(2)
        assertEquals(2, stack.peek())
        assertEquals(2, stack.size)
    }

    @Test
    fun peekOrReturnsDefaultWhenEmpty() {
        val stack = IntStack()
        assertEquals(42, stack.peekOr(42))
    }

    @Test
    fun peekOrReturnsLastPushedElementWhenNotEmpty() {
        val stack = IntStack()
        stack.push(1)
        assertEquals(1, stack.peekOr(42))
    }

    @Test
    fun peekSecondReturnsSecondLastPushedElement() {
        val stack = IntStack()
        stack.push(1)
        stack.push(2)
        assertEquals(1, stack.peekSecond())
    }

    @Test
    fun peekAtReturnsCorrectElement() {
        val stack = IntStack()
        stack.push(1)
        stack.push(2)
        assertEquals(1, stack.peekAt(0))
    }

    @Test
    fun isEmptyReturnsTrueForEmptyStack() {
        val stack = IntStack()
        assertTrue(stack.isEmpty())
    }

    @Test
    fun isNotEmptyReturnsTrueForNonEmptyStack() {
        val stack = IntStack()
        stack.push(1)
        assertTrue(stack.isNotEmpty())
    }

    @Test
    fun clearEmptiesTheStack() {
        val stack = IntStack()
        stack.push(1)
        stack.clear()
        assertTrue(stack.isEmpty())
    }

    @Test
    fun indexOfReturnsCorrectIndex() {
        val stack = IntStack()
        stack.push(1)
        stack.push(2)
        assertEquals(1, stack.indexOf(2))
    }

    @Test
    fun indexOfReturnsMinusOneForNonExistentElement() {
        val stack = IntStack()
        assertEquals(-1, stack.indexOf(1))
    }

    @Test
    fun popThrowsExceptionWhenEmpty() {
        val stack = IntStack()
        assertThrows<IndexOutOfBoundsException> {
            stack.pop()
        }
    }

    @Test
    fun peekThrowsExceptionWhenEmpty() {
        val stack = IntStack()
        assertThrows<IndexOutOfBoundsException> {
            stack.peek()
        }
    }

    @Test
    fun peekSecondThrowsExceptionWhenLessThanTwoElements() {
        val stack = IntStack()
        stack.push(1)
        assertThrows<IndexOutOfBoundsException> {
            stack.peekSecond()
        }
    }
}
