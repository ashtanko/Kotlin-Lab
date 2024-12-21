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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StackTest {

    @Test
    fun pushIncreasesSize() {
        val stack = Stack<Int>()
        stack.push(1)
        assertEquals(1, stack.size)
    }

    @Test
    fun popDecreasesSize() {
        val stack = Stack<Int>()
        stack.push(1)
        stack.pop()
        assertEquals(0, stack.size)
    }

    @Test
    fun popReturnsLastPushedElement() {
        val stack = Stack<Int>()
        stack.push(1)
        stack.push(2)
        assertEquals(2, stack.pop())
    }

    @Test
    fun peekReturnsLastPushedElementWithoutRemoving() {
        val stack = Stack<Int>()
        stack.push(1)
        stack.push(2)
        assertEquals(2, stack.peek())
        assertEquals(2, stack.size)
    }

    @Test
    fun isEmptyReturnsTrueForEmptyStack() {
        val stack = Stack<Int>()
        assertTrue(stack.isEmpty())
    }

    @Test
    fun isNotEmptyReturnsTrueForNonEmptyStack() {
        val stack = Stack<Int>()
        stack.push(1)
        assertTrue(stack.isNotEmpty())
    }

    @Test
    fun clearEmptiesTheStack() {
        val stack = Stack<Int>()
        stack.push(1)
        stack.clear()
        assertTrue(stack.isEmpty())
    }

    @Test
    fun peekAtReturnsCorrectElement() {
        val stack = Stack<Int>()
        stack.push(1)
        stack.push(2)
        assertEquals(1, stack.peek(0))
    }

    @Test
    fun toArrayReturnsCorrectArray() {
        val stack = Stack<Any?>()
        stack.push(Node(1))
        stack.push(Node(2))
        val actual = stack.toArray()
        val expected = arrayOf(Node(1), Node(2))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun popThrowsExceptionWhenEmpty() {
        val stack = Stack<Int>()
        assertThrows<IndexOutOfBoundsException> {
            stack.pop()
        }
    }

    @Test
    fun peekThrowsExceptionWhenEmpty() {
        val stack = Stack<Int>()
        assertThrows<IndexOutOfBoundsException> {
            stack.peek()
        }
    }

    @Test
    fun peekAtThrowsExceptionForInvalidIndex() {
        val stack = Stack<Int>()
        stack.push(1)
        assertThrows<IndexOutOfBoundsException> {
            stack.peek(1)
        }
    }
}

private data class Node(
    val value: Int,
    var next: Node? = null,
)
