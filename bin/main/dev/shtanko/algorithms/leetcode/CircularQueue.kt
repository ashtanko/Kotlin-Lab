/*
 * Copyright 2021 Oleksii Shtanko
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

class CircularQueue(val k: Int) {

    private var head: Node? = null
    private var tail: Node? = null
    private var count = 0
    private val capacity = k

    fun enQueue(value: Int): Boolean {
        if (count == capacity) return false
        val newNode = Node(value)
        if (count == 0) {
            tail = newNode
            head = tail
        } else {
            tail?.nextNode = newNode
            tail = newNode
        }
        count += 1
        return true
    }

    fun deQueue(): Boolean {
        if (this.count == 0) {
            return false
        }
        this.head = this.head?.nextNode
        this.count -= 1
        return true
    }

    fun rear(): Int {
        return if (this.count == 0) {
            -1
        } else {
            this.tail?.value ?: -1
        }
    }

    fun isEmpty(): Boolean {
        return this.count == 0
    }

    fun isFull(): Boolean {
        return this.count == this.capacity
    }

    private data class Node(var value: Int, var nextNode: Node? = null)
}
