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

package dev.shtanko.algorithms.leetcode

import java.util.Stack

/**
 * 232. Implement Queue using Stacks
 * @see <a href="https://leetcode.com/problems/implement-queue-using-stacks">Source</a>
 */
class MyQueue {

    private val addStack = Stack<Int>()
    private val delStack = Stack<Int>()
    private var front = 0

    /** Push element x to the back of queue. */
    fun push(x: Int) {
        if (addStack.isEmpty()) {
            front = x
        }
        addStack.push(x)
    }

    /** Removes the element from in front of queue and returns that element. */
    fun pop(): Int {
        return if (delStack.isNotEmpty()) {
            delStack.pop()
        } else {
            while (addStack.isNotEmpty()) {
                delStack.push(addStack.pop())
            }
            delStack.pop()
        }
    }

    /** Get the front element. */
    fun peek(): Int {
        if (delStack.isNotEmpty()) {
            return delStack.peek()
        }
        return front
    }

    /** Returns whether the queue is empty. */
    fun empty(): Boolean {
        return addStack.isEmpty() && delStack.isEmpty()
    }
}
