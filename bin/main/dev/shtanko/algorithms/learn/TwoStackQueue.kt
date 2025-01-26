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

class TwoStackQueue<T> : Collection<T> {

    private val stack1 = Stack<T>()
    private val stack2 = Stack<T>()

    override val size: Int
        get() = stack1.size

    override fun contains(element: T): Boolean {
        return stack1.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return stack1.containsAll(elements)
    }

    override fun isEmpty(): Boolean {
        return stack1.isEmpty()
    }

    override fun iterator(): Iterator<T> {
        return stack2.iterator()
    }

    fun peek(): T {
        if (size == 0) throw NoSuchElementException()
        if (stack1.isEmpty()) {
            throw NoSuchElementException()
        }
        val item = stack1.peek()
        stack1.pop()
        return item
    }

    fun add(item: T) {
        while (stack1.isNotEmpty()) {
            stack2.push(stack1.pop())
        }
        stack1.push(item)
        while (stack2.isNotEmpty()) {
            stack1.push(stack2.pop())
        }
    }
}
