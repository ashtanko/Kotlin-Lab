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

fun String.isValidParentheses(): Boolean {
    val stack = Stack<Char>()
    for (character in this.toCharArray()) {
        when {
            character == '(' -> {
                stack.push(')')
            }

            character == '{' -> {
                stack.push('}')
            }

            character == '[' -> {
                stack.push(']')
            }

            stack.isEmpty() || stack.pop() != character -> {
                return false
            }
        }
    }
    return stack.isEmpty()
}

class ValidParentheses(private val mappings: MutableMap<Char, Char> = HashMap()) {

    init {
        mappings[')'] = '('
        mappings['}'] = '{'
        mappings[']'] = '['
    }

    operator fun invoke(s: String): Boolean {
        // Initialize a stack to be used in the algorithm.
        val stack = Stack<Char>()

        for (character: Char in s) {
            // If the current character is a closing bracket.
            if (mappings.containsKey(character)) {
                if (stack.isEmpty() || stack.pop() != mappings[character]) return false
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(character)
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty()
    }
}
