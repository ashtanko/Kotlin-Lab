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

package dev.shtanko.algorithms.codingbat.recursion1

import java.util.Stack

/**
 * Recursion-1 > nestParen
 * @see <a href="https://codingbat.com/prob/p183174">Source</a>
 */
fun interface NestParen {
    operator fun invoke(str: String): Boolean
}

class NestParenIterative : NestParen {
    override fun invoke(str: String): Boolean {
        val stack = Stack<Char>()
        for (char in str) {
            when (char) {
                '(' -> {
                    stack.push(char)
                }

                ')' -> {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false
                    }
                }

                else -> return false
            }
        }
        return stack.isEmpty()
    }
}

class NestParenRecursive : NestParen {
    override fun invoke(str: String): Boolean {
        if (str.isEmpty()) {
            return true
        }
        if (str[0] == '(' && str[str.length - 1] == ')') {
            return invoke(str.substring(1, str.length - 1))
        }
        return false
    }
}
