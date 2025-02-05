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
 * A generic stack implementation using an ArrayList as the backing data structure.
 *
 * @param T the type of elements in the stack
 * @property backing the ArrayList used to store the stack elements
 */
@JvmInline
value class Stack<T>(private val backing: ArrayList<T> = ArrayList()) {

    /**
     * Returns the current size of the stack.
     */
    val size: Int
        get() = backing.size

    /**
     * Pushes an element onto the stack.
     *
     * @param value the element to be pushed onto the stack
     */
    fun push(value: T) = backing.add(value)

    /**
     * Pops the top element from the stack.
     *
     * @return the element that was popped from the stack
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    fun pop(): T = backing.removeAt(size - 1)

    /**
     * Peeks at the top element of the stack without removing it.
     *
     * @return the top element of the stack
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    fun peek(): T = backing[size - 1]

    /**
     * Peeks at the element at the specified index in the stack.
     *
     * @param index the index of the element to peek at
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    fun peek(index: Int): T = backing[index]

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    fun isEmpty() = backing.isEmpty

    /**
     * Checks if the stack is not empty.
     *
     * @return true if the stack is not empty, false otherwise
     */
    fun isNotEmpty() = !isEmpty()

    /**
     * Clears all elements from the stack.
     */
    fun clear() = backing.clear()

    /**
     * Converts the stack to an array.
     *
     * @return an array containing all elements in the stack
     */
    @Suppress("UNCHECKED_CAST")
    fun toArray(): Array<T> = Array<Any?>(backing.size) { backing[it] } as Array<T>
}
