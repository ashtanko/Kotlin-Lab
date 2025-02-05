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

import kotlin.math.min

class MinStack {

    private var head: Node? = null

    fun push(x: Int) {
        if (head == null) {
            head = Node(x, x)
        } else {
            val n = Node(x, min(x, head!!.min))
            n.next = head
            head = n
        }
    }

    fun pop() {
        if (head != null) head = head?.next
    }

    fun top(): Int {
        return if (head != null) {
            head!!.value
        } else {
            -1
        }
    }

    fun getMin(): Int {
        return if (head != null) {
            head!!.min
        } else {
            -1
        }
    }

    data class Node(val value: Int, val min: Int) {
        var next: Node? = null
    }
}
