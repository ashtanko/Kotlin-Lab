/*
 * Copyright 2022 Oleksii Shtanko
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
 * 301. Remove Invalid Parentheses
 * @see <a href="https://leetcode.com/problems/remove-invalid-parentheses/">Source</a>
 */
fun interface RemoveInvalidParentheses {
    operator fun invoke(s: String): List<String>
}

/**
 * Approach 1: Backtracking
 */
class RemoveInvalidParenthesesBacktracking : RemoveInvalidParentheses {

    private val validExpressions: MutableSet<String> = HashSet()
    private var minimumRemoved = Int.MAX_VALUE
    private val expression = StringBuilder()

    override operator fun invoke(s: String): List<String> {
        recurse(s, 0, 0, 0, 0)
        return validExpressions.toList()
    }

    private fun recurse(
        s: String,
        index: Int,
        leftCount: Int,
        rightCount: Int,
        removedCount: Int,
    ) {
        // If we have reached the end of string.
        if (index == s.length) {
            // If the current expression is valid.
            when (leftCount) {
                rightCount -> {
                    // If the current count of removed parentheses is <= the current minimum count
                    if (removedCount <= minimumRemoved) {
                        // Convert StringBuilder to a String. This is an expensive operation.
                        // So we only perform this when needed.
                        val possibleAnswer = expression.toString()

                        // If the current count beats the overall minimum we have till now
                        if (removedCount < minimumRemoved) {
                            validExpressions.clear()
                            minimumRemoved = removedCount
                        }
                        validExpressions.add(possibleAnswer)
                    }
                }
            }
        } else {
            val currentCharacter = s[index]
            val length = expression.length

            // If the current character is neither an opening bracket nor a closing one,
            // simply recurse further by adding it to the expression StringBuilder
            if (currentCharacter != '(' && currentCharacter != ')') {
                expression.append(currentCharacter)
                recurse(s, index + 1, leftCount, rightCount, removedCount)
                expression.deleteCharAt(length)
            } else {
                // Recursion where we delete the current character and move forward
                recurse(s, index + 1, leftCount, rightCount, removedCount + 1)
                expression.append(currentCharacter)

                // If it's an opening parenthesis, consider it and recurse
                if (currentCharacter == '(') {
                    recurse(s, index + 1, leftCount + 1, rightCount, removedCount)
                } else if (rightCount < leftCount) {
                    // For a closing parenthesis, only recurse if right < left
                    recurse(s, index + 1, leftCount, rightCount + 1, removedCount)
                }

                // Undoing the append operation for other recursions.
                expression.deleteCharAt(length)
            }
        }
    }
}

/**
 * Approach 2: Limited Backtracking!
 */
class RemoveInvalidParenthesesLBacktracking : RemoveInvalidParentheses {

    private val validExpressions: MutableSet<String> = HashSet()

    override operator fun invoke(s: String): List<String> {
        var left = 0
        var right = 0

        // First, we find out the number of misplaced left and right parentheses.
        for (i in s.indices) {
            // Simply record the left one.
            if (s[i] == '(') {
                left++
            } else if (s[i] == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = if (left == 0) right + 1 else right

                // Decrement count of left parentheses because we have found a right
                // which CAN be a matching one for a left.
                left = if (left > 0) left - 1 else left
            }
        }

        recurse(s, 0, 0, 0, left, right, StringBuilder())
        return validExpressions.toList()
    }

    private fun recurse(
        s: String,
        index: Int,
        leftCount: Int,
        rightCount: Int,
        leftRem: Int,
        rightRem: Int,
        expression: StringBuilder,
    ) {
        // If we reached the end of the string, just check if the resulting expression is
        // valid or not and also if we have removed the total number of left and right
        // parentheses that we should have removed.
        if (index == s.length) {
            if (leftRem == 0 && rightRem == 0) {
                validExpressions.add(expression.toString())
            }
        } else {
            val character = s[index]
            val length = expression.length

            // The discard case. Note that here we have our pruning condition.
            // We don't recurse if the remaining count for that parenthesis is == 0.
            if (character == '(' && leftRem > 0 || character == ')' && rightRem > 0) {
                recurse(
                    s,
                    index + 1,
                    leftCount,
                    rightCount,
                    leftRem - if (character == '(') 1 else 0,
                    rightRem - if (character == ')') 1 else 0,
                    expression,
                )
            }
            expression.append(character)

            // Simply recurse one step further if the current character is not a parenthesis.
            when {
                character != '(' && character != ')' -> {
                    recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression)
                }

                character == '(' -> {
                    // Consider an opening bracket.
                    recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression)
                }

                rightCount < leftCount -> {
                    // Consider a closing bracket.
                    recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression)
                }
            }

            // Delete for backtracking.
            expression.deleteCharAt(length)
        }
    }
}

class RemoveInvalidParenthesesFast : RemoveInvalidParentheses {
    override operator fun invoke(s: String): List<String> {
        val ans: MutableList<String> = ArrayList()
        remove(s, ans, 0, 0, charArrayOf('(', ')'))
        return ans
    }

    private fun remove(s: String, ans: MutableList<String>, lastI: Int, lastJ: Int, par: CharArray) {
        var stack = 0
        var i = lastI
        while (i < s.length) {
            if (s[i] == par[0]) stack++
            if (s[i] == par[1]) stack--
            if (stack >= 0) {
                ++i
                continue
            }
            for (j in lastJ..i) if (s[j] == par[1] && (j == lastJ || s[j - 1] != par[1])) {
                remove(
                    s.substring(
                        0,
                        j,
                    ) + s.substring(j + 1, s.length),
                    ans,
                    i,
                    j,
                    par,
                )
            }
            ++i
            return
        }
        val reversed = StringBuilder(s).reverse().toString()
        if (par[0] == '(') {
            // finished left to right
            remove(reversed, ans, 0, 0, charArrayOf(')', '('))
        } else {
            // finished right to left
            ans.add(reversed)
        }
    }
}
