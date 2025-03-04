/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Hard
import java.util.Stack

/**
 * 1106. Parsing A Boolean Expression
 * @see <a href="https://leetcode.com/problems/parsing-a-boolean-expression/">Source</a>
 */
@Hard("https://leetcode.com/problems/parsing-a-boolean-expression")
fun interface ParseBooleanExpression {
    operator fun invoke(expression: String): Boolean
}

class ParseBooleanExpressionRecursion : ParseBooleanExpression {
    private var index = 0

    override fun invoke(expression: String): Boolean {
        return evaluate(expression)
    }

    private fun evaluate(expr: String): Boolean {
        val currChar = expr[index++]

        // Base cases: true ('t') or false ('f')
        if (currChar == 't') return true
        if (currChar == 'f') return false

        // Handle NOT operation '!(...)'
        if (currChar == '!') {
            index++ // Skip '('
            val result = !evaluate(expr)
            index++ // Skip ')'
            return result
        }

        // Handle AND '&(...)' and OR '|(...)'
        val values = mutableListOf<Boolean>()
        index++ // Skip '('
        while (expr[index] != ')') {
            if (expr[index] != ',') {
                values.add(evaluate(expr)) // Collect results of subexpressions
            } else {
                index++ // Skip commas
            }
        }
        index++ // Skip ')'

        // Manual AND operation: returns false if any value is false
        if (currChar == '&') {
            return values.all { it }
        }

        // Manual OR operation: returns true if any value is true
        if (currChar == '|') {
            return values.any { it }
        }

        return false // This point should never be reached
    }
}

class ParseBooleanExpressionStack : ParseBooleanExpression {
    override fun invoke(expression: String): Boolean {
        val stack = Stack<Char>()

        // Traverse through the expression
        for (currChar in expression) {
            if (currChar == ',' || currChar == '(') continue // Skip commas and open parentheses

            // Push operators and boolean values to the stack
            if (currChar == 't' || currChar == 'f' || currChar == '!' || currChar == '&' || currChar == '|') {
                stack.push(currChar)
            } else if (currChar == ')') { // Handle closing parentheses and evaluate the subexpression
                var hasTrue = false
                var hasFalse = false

                // Process the values inside the parentheses
                while (stack.isNotEmpty() && stack.peek() != '!' && stack.peek() != '&' && stack.peek() != '|') {
                    val topValue = stack.pop()
                    if (topValue == 't') hasTrue = true
                    if (topValue == 'f') hasFalse = true
                }

                // Pop the operator and evaluate the subexpression
                val op = stack.pop()
                if (op == '!') {
                    stack.push(if (hasTrue) 'f' else 't')
                } else if (op == '&') {
                    stack.push(if (hasFalse) 'f' else 't')
                } else {
                    stack.push(if (hasTrue) 't' else 'f')
                }
            }
        }

        // The final result is at the top of the stack
        return stack.peek() == 't'
    }
}
