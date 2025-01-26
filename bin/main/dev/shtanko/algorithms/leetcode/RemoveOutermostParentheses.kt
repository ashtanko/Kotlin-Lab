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

fun String.removeOuterParentheses(): String {
    val stack = Stack<Char>()
    val result = StringBuilder()
    for (element in this) {
        if (stack.isEmpty() && element == '(') {
            stack.push(element)
            continue
        }
        if (stack.size == 1 && element == ')') {
            stack.pop()
            continue
        }
        if (element == '(') {
            stack.push(element)
        }
        if (element == ')') {
            stack.pop()
        }
        result.append(element)
    }
    return result.toString()
}

fun String.removeOuterParentheses2(): String {
    val sb = StringBuilder()
    var opened = 0
    for (c in this.toCharArray()) {
        if (c == '(' && opened++ > 0) {
            sb.append(c)
        }
        if (c == ')' && opened-- > 1) {
            sb.append(c)
        }
    }
    return sb.toString()
}
