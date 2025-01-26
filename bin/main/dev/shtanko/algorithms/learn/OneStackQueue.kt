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

import java.util.Stack

class OneStackQueue<T> : Collection<T> {

    private val stack = Stack<T>()

    override val size: Int
        get() = stack.size

    override fun contains(element: T): Boolean {
        return stack.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return stack.containsAll(elements)
    }

    override fun isEmpty(): Boolean = stack.isEmpty()

    override fun iterator(): Iterator<T> {
        return stack.iterator()
    }

    fun add(item: T) {
        stack.push(item)
    }

    fun peek(): T {
        if (isEmpty()) throw NoSuchElementException()
        val x = stack.peek()
        stack.pop()
        if (stack.isEmpty()) return x
        val item = peek()
        stack.push(x)
        return item
    }
}
