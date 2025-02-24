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

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.algorithms.annotations.level.Hard
import java.util.Stack
import kotlin.math.pow

/**
 * 224. Basic Calculator
 * @see <a href="https://leetcode.com/problems/basic-calculator">Source</a>
 */
@Hard("https://leetcode.com/problems/basic-calculator")
fun interface CalculationStrategy {
    operator fun invoke(str: String): Int
}

class StackAndStringReversal : CalculationStrategy {

    private fun evaluateExpr(stack: Stack<Any?>): Int {
        var res = 0
        if (!stack.empty()) {
            res = stack.pop() as Int
        }

        // Evaluate the expression till we get corresponding ')'
        while (!stack.empty() && stack.peek() as Char != ')') {
            val sign = stack.pop() as Char
            if (sign == '+') {
                res += stack.pop() as Int
            } else {
                res -= stack.pop() as Int
            }
        }
        return res
    }

    override fun invoke(str: String): Int {
        var operand = 0
        var n = 0
        val stack = Stack<Any?>()

        for (i in str.length - 1 downTo 0) {
            val ch: Char = str[i]
            if (Character.isDigit(ch)) {
                // Forming the operand - in reverse order.
                operand += DECIMAL.toDouble().pow(n.toDouble()).toInt() * (ch - '0')
                n += 1
            } else if (ch != ' ') {
                if (n != 0) {
                    // Save the operand on the stack
                    // As we encounter some non-digit.
                    stack.push(operand)
                    n = 0
                    operand = 0
                }
                if (ch == '(') {
                    val res = evaluateExpr(stack)
                    stack.pop()

                    // Append the evaluated result to the stack.
                    // This result could be of a sub-expression within the parenthesis.
                    stack.push(res)
                } else {
                    // For other non-digits just push onto the stack.
                    stack.push(ch)
                }
            }
        }

        // Push the last operand to stack, if any.
        if (n != 0) {
            stack.push(operand)
        }

        // Evaluate any left overs in the stack.
        return evaluateExpr(stack)
    }
}

class StackAndNoStringReversal : CalculationStrategy {
    override fun invoke(str: String): Int {
        val stack = Stack<Int>()
        var operand = 0
        var result = 0 // For the on-going result

        var sign = 1 // 1 means positive, -1 means negative

        for (i in str.indices) {
            val ch: Char = str[i]
            when {
                Character.isDigit(ch) -> {
                    // Forming operand, since it could be more than one digit
                    operand = DECIMAL * operand + ch.minus('0')
                }

                ch == '+' -> {
                    // Evaluate the expression to the left,
                    // with result, sign, operand
                    result += sign * operand

                    // Save the recently encountered '+' sign
                    sign = 1

                    // Reset operand
                    operand = 0
                }

                ch == '-' -> {
                    result += sign * operand
                    sign = -1
                    operand = 0
                }

                ch == '(' -> {
                    // Push the result and sign on to the stack, for later
                    // We push the result first, then sign
                    stack.push(result)
                    stack.push(sign)

                    // Reset operand and result, as if new evaluation begins for the new sub-expression
                    sign = 1
                    result = 0
                }

                ch == ')' -> {
                    // Evaluate the expression to the left
                    // with result, sign and operand
                    result += sign * operand

                    // ')' marks end of expression within a set of parenthesis
                    // Its result is multiplied with sign on top of stack
                    // as stack.pop() is the sign before the parenthesis
                    result *= stack.pop()

                    // Then add to the next operand on the top.
                    // as stack.pop() is the result calculated before this parenthesis
                    // (operand on stack) + (sign on stack * (result from parenthesis))
                    result += stack.pop()

                    // Reset the operand
                    operand = 0
                }
            }
        }
        return result + sign * operand
    }
}
