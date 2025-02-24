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

/**
 * 241. Different Ways to Add Parentheses
 * @see <a href="https://leetcode.com/problems/different-ways-to-add-parentheses/">Source</a>
 */
fun interface DiffWaysToCompute {
    operator fun invoke(expression: String): List<Int>
}

class DiffWaysToComputeRecursive : DiffWaysToCompute {

    // function to get the result of the operation
    operator fun invoke(x: Int, y: Int, op: Char): Int {
        if (op == '+') return x + y
        if (op == '-') return x - y
        return if (op == '*') x * y else 0
    }

    override fun invoke(expression: String): List<Int> {
        val results: MutableList<Int> = ArrayList()
        if (expression.isBlank()) return results
        var isNumber = true
        for (i in expression.indices) {
            // check if current character is an operator
            if (!Character.isDigit(expression[i])) {
                // if current character is not a digit then
                // exp is not purely a number
                isNumber = false

                // list of first operands
                val left = invoke(expression.substring(0, i))

                // list of second operands
                val right = invoke(expression.substring(i + 1))

                // performing operations
                for (x in left) {
                    for (y in right) {
                        val value = invoke(x, y, expression[i])
                        results.add(value)
                    }
                }
            }
        }
        if (isNumber) results.add(Integer.valueOf(expression))
        return results
    }
}

class DiffWaysToComputeDivideAndConquer : DiffWaysToCompute {
    override fun invoke(expression: String): List<Int> {
        if (expression.isEmpty()) return emptyList()
        return mutableListOf<Int>().apply {
            expression.toCharArray().forEachIndexed { index, char ->
                if (char in listOf('+', '-', '*')) {
                    val left = expression.substring(0, index)
                    val right = expression.substring(index + 1)

                    val leftNums = invoke(left)
                    val rightNums = invoke(right)

                    leftNums.forEach { num1 ->
                        rightNums.forEach { num2 ->
                            add(char.calculate(num1, num2))
                        }
                    }
                }
            }
            if (isEmpty()) add(expression.toInt())
        }
    }

    private fun Char.calculate(num1: Int, num2: Int) = when (this) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        else -> throw IllegalArgumentException("Symbol is not allowed")
    }
}
