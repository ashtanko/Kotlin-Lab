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

fun <T : Comparable<T>> Stack<T>.sorted(): Stack<T> {
    val tmp = Stack<T>()
    while (this.isNotEmpty()) {
        val value = pop()
        while (tmp.isNotEmpty() && tmp.peek() > value) {
            push(tmp.pop())
        }
        tmp.push(value)
    }
    return tmp
}

internal fun String.reversed(): String {
    val stack = Stack<String>()
    val sb = StringBuilder()
    for (s in this) {
        stack.push(s.toString())
    }
    while (stack.isNotEmpty()) {
        val s = stack.pop()
        sb.append(s)
    }
    return sb.toString()
}
