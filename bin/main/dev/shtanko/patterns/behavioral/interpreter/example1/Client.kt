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

package dev.shtanko.patterns.behavioral.interpreter.example1

import java.util.Stack

internal object Client {
    @JvmStatic
    fun main(vararg args: String) {
        val tokenString = "4 3 2 - 1 + *"
        // the stack holds the parsed expressions
        val stack = Stack<Expression>()

        // tokenize the string and go through them one by one
        val tokenList = tokenString.split(" ".toRegex()).toTypedArray()

        for (s in tokenList) {
            if (isOperator(s)) {
                // when an operator is encountered we expect that the numbers can be popped from the top of
                // the stack
                val rightExpression = stack.pop()
                val leftExpression = stack.pop()

                val operator: Expression = getOperatorInstance(s, leftExpression, rightExpression)
                val result = operator.interpret()
                // the operation result is pushed on top of the stack
                val resultExpression = NumberExpression(result)
                stack.push(resultExpression)
            } else {
                // numbers are pushed on top of the stack
                val i = NumberExpression(s)
                stack.push(i)
            }
        }
    }

    /**
     * Checks whether the input parameter is an operator.
     * @param s input string
     * @return true if the input parameter is an operator
     */
    private fun isOperator(s: String): Boolean {
        return s == "+" || s == "-" || s == "*"
    }

    /**
     * Returns correct expression based on the parameters.
     * @param s input string
     * @param left expression
     * @param right expression
     * @return expression
     */
    private fun getOperatorInstance(s: String?, left: Expression, right: Expression): Expression {
        return when (s) {
            "+" -> PlusExpression(left, right)
            "-" -> MinusExpression(left, right)
            else -> MultiplyExpression(left, right)
        }
    }
}
