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

internal interface Queue<T> {
    fun add(item: T)

    fun remove(): T

    fun peek(): T

    fun isEmpty(): Boolean
}

internal class StackQueue<T> : Queue<T> {
    /**
     * Sorted from newest elements at the top to oldest elements at the bottom.
     */
    private val pushStack = Stack<T>()

    /**
     * Sorted from oldest elements at the top to newest elements at the bottom.
     */
    private val popStack = Stack<T>()

    override fun add(item: T) {
        pushStack.push(item)
    }

    override fun remove(): T {
        movePushToPop()

        return popStack.pop()
    }

    override fun peek(): T {
        movePushToPop()

        return popStack.peek()
    }

    override fun isEmpty(): Boolean = pushStack.isEmpty() && popStack.isEmpty()

    private fun movePushToPop() {
        if (popStack.isEmpty()) {
            while (!pushStack.empty()) {
                popStack.push(pushStack.pop())
            }
        }
    }
}
