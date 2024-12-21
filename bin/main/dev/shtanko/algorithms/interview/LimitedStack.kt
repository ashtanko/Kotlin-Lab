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

package dev.shtanko.algorithms.interview

import java.util.Stack

internal class LimitedStack {
    private val inner = Stack<Int>()

    fun push(value: Int) {
        require(!isFull())
        inner.push(value)
    }

    fun pop(): Int {
        return inner.pop()
    }

    fun isFull(): Boolean = inner.isNotEmpty()

    fun isEmpty(): Boolean = inner.isEmpty()
}

class StackOfStacks {
    private val stack = Stack<LimitedStack>()

    fun push(value: Int) {
        val topStack = if (stack.isEmpty() || stack.peek().isFull()) {
            val newStack = LimitedStack()
            stack.push(newStack)
            newStack
        } else {
            stack.pop()
        }

        topStack.push(value)
    }

    fun pop(): Int {
        val topStack = stack.peek()
        val topValue = topStack.pop()
        if (topStack.isEmpty()) {
            stack.pop()
        }

        return topValue
    }
}
