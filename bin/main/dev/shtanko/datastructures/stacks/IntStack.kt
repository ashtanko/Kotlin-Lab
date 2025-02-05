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

/**
 * A stack implementation for integers.
 */
class IntStack {
    private var elements = IntArray(10)
    private var topOfStack = 0

    /**
     * Returns the current size of the stack.
     */
    val size: Int
        get() = topOfStack

    /**
     * Pushes an integer value onto the stack.
     *
     * @param value the integer value to be pushed onto the stack
     */
    fun push(value: Int) {
        if (topOfStack >= elements.size) {
            elements = elements.copyOf(elements.size * 2)
        }
        elements[topOfStack++] = value
    }

    /**
     * Pops the top integer value from the stack.
     *
     * @return the integer value that was popped from the stack
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    fun pop(): Int = elements[--topOfStack]

    /**
     * Peeks at the top integer value of the stack or returns a default value if the stack is empty.
     *
     * @param default the default value to return if the stack is empty
     * @return the top integer value of the stack or the default value
     */
    fun peekOr(default: Int): Int = if (topOfStack > 0) peek() else default

    /**
     * Peeks at the top integer value of the stack without removing it.
     *
     * @return the top integer value of the stack
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    fun peek() = elements[topOfStack - 1]

    /**
     * Peeks at the second top integer value of the stack without removing it.
     *
     * @return the second top integer value of the stack
     * @throws IndexOutOfBoundsException if the stack has less than two elements
     */
    fun peekSecond() = elements[topOfStack - 2]

    /**
     * Peeks at the integer value at the specified index in the stack.
     *
     * @param index the index of the element to peek at
     * @return the integer value at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    fun peekAt(index: Int) = elements[index]

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    fun isEmpty() = topOfStack == 0

    /**
     * Checks if the stack is not empty.
     *
     * @return true if the stack is not empty, false otherwise
     */
    fun isNotEmpty() = topOfStack != 0

    /**
     * Clears all elements from the stack.
     */
    fun clear() {
        topOfStack = 0
    }

    /**
     * Returns the index of the first occurrence of the specified value in the stack.
     *
     * @param value the value to search for
     * @return the index of the first occurrence of the value, or -1 if the value is not found
     */
    fun indexOf(value: Int): Int {
        for (i in 0 until topOfStack) if (elements[i] == value) return i
        return -1
    }
}
