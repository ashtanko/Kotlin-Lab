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
import kotlin.math.max

/**
 * 32. Longest Valid Parentheses
 * https://leetcode.com/problems/longest-valid-parentheses/
 */
fun interface LongestValidParenthesesStrategy {
    operator fun invoke(s: String): Int
}

class LongestValidParenthesesBruteForce : LongestValidParenthesesStrategy {
    override operator fun invoke(s: String): Int {
        var maxLen = 0
        for (i in s.indices) {
            var j = i + 2
            while (j <= s.length) {
                if (isValid(s.substring(i, j))) {
                    maxLen = max(maxLen, j - i)
                }
                j += 2
            }
        }
        return maxLen
    }

    private fun isValid(s: String): Boolean {
        val stack: Stack<Char> = Stack<Char>()
        val sym = '('
        for (element in s) {
            if (element == sym) {
                stack.push(sym)
            } else if (!stack.empty() && stack.peek() == sym) {
                stack.pop()
            } else {
                return false
            }
        }
        return stack.empty()
    }
}

class LongestValidParenthesesDP : LongestValidParenthesesStrategy {
    override operator fun invoke(s: String): Int {
        var maxAns = 0
        val dp = IntArray(s.length)
        for (i in 1 until s.length) {
            if (s[i] == ')') {
                if (s[i - 1] == '(') {
                    dp[i] = (if (i >= 2) dp[i - 2] else 0) + 2
                } else if (i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + (if (i - dp[i - 1] >= 2) dp[i - dp[i - 1] - 2] else 0) + 2
                }
                maxAns = max(maxAns, dp[i])
            }
        }
        return maxAns
    }
}

class LongestValidParenthesesStack : LongestValidParenthesesStrategy {
    override operator fun invoke(s: String): Int {
        var maxAns = 0
        val stack = Stack<Int>()
        stack.push(-1)
        for (i in s.indices) {
            if (s[i] == '(') {
                stack.push(i)
            } else {
                stack.pop()
                if (stack.empty()) {
                    stack.push(i)
                } else {
                    maxAns = max(maxAns, i - stack.peek())
                }
            }
        }
        return maxAns
    }
}

class LongestValidParenthesesWithoutExtraSpace : LongestValidParenthesesStrategy {
    override operator fun invoke(s: String): Int {
        var left = 0
        var right = 0
        var maxLength = 0
        for (i in s.indices) {
            if (s[i] == '(') {
                left++
            } else {
                right++
            }
            if (left == right) {
                maxLength = max(maxLength, 2 * right)
            } else if (right >= left) {
                right = 0
                left = right
            }
        }
        left = 0.also { right = it }
        for (i in s.length - 1 downTo 0) {
            if (s[i] == '(') {
                left++
            } else {
                right++
            }
            if (left == right) {
                maxLength = max(maxLength, 2 * left)
            } else if (left >= right) {
                right = 0
                left = right
            }
        }
        return maxLength
    }
}
