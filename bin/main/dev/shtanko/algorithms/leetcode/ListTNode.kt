/*
 * Copyright 2023 Oleksii Shtanko
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

data class ListTNode<T>(val value: T? = null, var next: ListTNode<T>? = null)

fun <T> ListTNode<T>.prettyPrint(): String {
    val sb = StringBuilder()
    var node: ListTNode<T>? = this
    while (node?.next != null) {
        sb.append("${node.value}->")
        node = node.next
    }
    if (node != null) {
        sb.append(node.value)
    } else {
        sb.append("Empty LinkedList")
    }
    return sb.toString()
}

fun <T> List<T>.toListTNode(): ListTNode<T>? {
    if (isEmpty()) return null
    var current = ListTNode<T>(value = null)
    var startNode = ListTNode<T>(value = null)
    for (i in indices) {
        val v: T = this[i]
        val node = ListTNode(value = v)
        if (i > 0) {
            current.next = node
        } else {
            startNode = node
        }
        current = node
    }
    return startNode
}

fun <T> ListTNode<T>.toList(): List<T?> {
    val result = mutableListOf<T?>()
    var node: ListTNode<T>? = this
    if (next == null) {
        result.add(value)
        return result.toList()
    }
    while (node != null) {
        result.add(node.value)
        node = node.next
    }
    return result
}
