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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.LinkedList

/**
 * 1381. Design a Stack With Increment Operation
 * @see <a href="https://leetcode.com/problems/design-a-stack-with-increment-operation">Source</a>
 */
@Medium("https://leetcode.com/problems/design-a-stack-with-increment-operation")
interface IncrementalStack {
    fun push(x: Int)
    fun pop(): Int
    fun increment(k: Int, value: Int)
}

class IncrementalStackArray(maxSize: Int) : IncrementalStack {

    private val stackArray = IntArray(maxSize)
    private var topIndex = -1

    override fun push(x: Int) {
        if (topIndex < stackArray.size - 1) {
            stackArray[++topIndex] = x
        }
    }

    override fun pop(): Int {
        if (topIndex >= 0) {
            return stackArray[topIndex--]
        }
        return -1
    }

    override fun increment(k: Int, value: Int) {
        val limit = kotlin.math.min(k, topIndex + 1)
        for (i in 0 until limit) {
            stackArray[i] += value
        }
    }
}

class IncrementalStackLinkedList(val maxSize: Int) : IncrementalStack {

    private val stack = LinkedList<Int>()

    override fun push(x: Int) {
        if (stack.size < maxSize) {
            stack.addLast(x)
        }
    }

    override fun pop(): Int {
        return if (stack.isEmpty()) -1 else stack.removeLast()
    }

    override fun increment(k: Int, value: Int) {
        for (i in 0 until minOf(k, stack.size)) {
            stack[i] += value
        }
    }
}

class IncrementalStackLazy(maxSize: Int) : IncrementalStack {
    // Array to store stack elements
    private val stackArray = IntArray(maxSize)

    // Array to store increments for lazy propagation
    private val incrementArray = IntArray(maxSize)

    // Current top index of the stack
    private var topIndex = -1

    override fun push(x: Int) {
        if (topIndex < stackArray.size - 1) {
            stackArray[++topIndex] = x
        }
    }

    override fun pop(): Int {
        if (topIndex < 0) {
            return -1
        }

        // Calculate the actual value with increment
        val result = stackArray[topIndex] + incrementArray[topIndex]

        // Propagate the increment to the element below
        if (topIndex > 0) {
            incrementArray[topIndex - 1] += incrementArray[topIndex]
        }

        // Reset the increment for this position
        incrementArray[topIndex] = 0

        topIndex--
        return result
    }

    override fun increment(k: Int, value: Int) {
        if (topIndex >= 0) {
            // Apply increment to the topmost element of the range
            val incrementIndex = minOf(topIndex, k - 1)
            incrementArray[incrementIndex] += value
        }
    }
}
