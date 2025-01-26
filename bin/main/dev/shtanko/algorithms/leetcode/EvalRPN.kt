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

import java.util.Stack
import java.util.function.BiFunction

/**
 * Evaluate Reverse Polish Notation
 * @see <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation">Source</a>
 */
fun interface EvalRPN {
    operator fun invoke(tokens: Array<String>): Int
}

/**
 * Approach 1: Reducing the List In-place
 */
class RPNInPlace : EvalRPN {
    override operator fun invoke(tokens: Array<String>): Int {
        var currentPosition = 0
        var length: Int = tokens.size // We need to keep track of this ourselves.

        while (length > 1) {
            // Move the position pointer to the next operator token.
            while (!OPERATIONS.containsKey(tokens[currentPosition])) {
                currentPosition++
            }

            // Extract the operation and numbers to apply operation too.
            val operation = tokens[currentPosition]
            val number1 = tokens[currentPosition - 2].toInt()
            val number2 = tokens[currentPosition - 1].toInt()

            // Calculate the result to overwrite the operator with.
            val operator: BiFunction<Int, Int, Int>? = OPERATIONS[operation]
            val value = operator?.apply(number1, number2)
            tokens[currentPosition] = value.toString()

            // Delete numbers and point pointers correctly.
            delete2AtIndex(tokens, currentPosition - 2, length)
            currentPosition--
            length -= 2
        }

        return tokens[0].toInt()
    }

    private fun delete2AtIndex(tokens: Array<String>, d: Int, length: Int) {
        for (i in d until length - 2) {
            tokens[i] = tokens[i + 2]
        }
    }

    // Ensure this only gets done once for ALL test cases.
    companion object {
        private val OPERATIONS: MutableMap<String, BiFunction<Int, Int, Int>> = HashMap()

        init {
            OPERATIONS["+"] = BiFunction { a: Int, b: Int -> a + b }
            OPERATIONS["-"] = BiFunction { a, b -> a - b }
            OPERATIONS["*"] = BiFunction { a, b -> a * b }
            OPERATIONS["/"] = BiFunction { a, b -> a / b }
        }
    }
}

/**
 * Approach 2: Evaluate with Stack
 */
class RPNStack : EvalRPN {
    override operator fun invoke(tokens: Array<String>): Int {
        val stack: Stack<Int> = Stack()
        for (token in tokens) {
            if (!"+-*/".contains(token)) {
                stack.push(Integer.valueOf(token))
                continue
            }
            val number2: Int = stack.pop()
            val number1: Int = stack.pop()
            var result = 0
            when (token) {
                "+" -> result = number1 + number2
                "-" -> result = number1 - number2
                "*" -> result = number1 * number2
                "/" -> result = number1 / number2
            }
            stack.push(result)
        }

        return stack.pop()
    }
}
