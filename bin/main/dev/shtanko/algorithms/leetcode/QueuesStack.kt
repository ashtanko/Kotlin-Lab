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

import java.util.LinkedList
import java.util.Queue

//  Implement Stack using Queues
interface QueuesStack {

    /** Push element x onto stack. */
    fun push(x: Int)

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int

    /** Get the top element. */
    fun top(): Int

    /** Returns whether the stack is empty. */
    fun empty(): Boolean
}

// Two Queues, push - O(1), pop O(n)
class StackTwoQueues : QueuesStack {

    private var q1: Queue<Int> = LinkedList()
    private var q2: Queue<Int> = LinkedList()
    var top = 0

    override fun push(x: Int) {
        q1.add(x)
        top = x
    }

    override fun pop(): Int {
        while (q1.size > 1) {
            top = q1.remove()
            q2.add(top)
        }
        q1.remove()
        val tmp = q1
        q1 = q2
        q2 = tmp
        return top
    }

    override fun top(): Int {
        return top
    }

    override fun empty(): Boolean {
        return q1.isEmpty() && q2.isEmpty()
    }
}

class StackTwoQueues2 : QueuesStack {

    private var q1: Queue<Int> = LinkedList()
    private var q2: Queue<Int> = LinkedList()

    override fun push(x: Int) {
        while (q1.isNotEmpty()) {
            q2.add(q1.poll())
        }
        q1.add(x)
        while (q2.isNotEmpty()) {
            q1.add(q2.poll())
        }
    }

    override fun pop(): Int {
        return q1.poll()
    }

    override fun top(): Int {
        return q1.peek()
    }

    override fun empty(): Boolean {
        return q1.isEmpty()
    }
}

class StackOneQueue : QueuesStack {

    private var queue: Queue<Int> = LinkedList()

    override fun push(x: Int) {
        queue.add(x)
        var sz = queue.size
        while (sz > 1) {
            queue.add(queue.remove())
            sz--
        }
    }

    override fun pop(): Int {
        return queue.remove()
    }

    override fun top(): Int {
        return queue.peek()
    }

    override fun empty(): Boolean {
        return queue.isEmpty()
    }
}
