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

/**
 * Generate Parentheses.
 * @see <a href="https://leetcode.com/problems/generate-parentheses/">Source</a>
 */
fun interface GenerateParentheses {
    operator fun invoke(n: Int): List<String>
}

/**
 * Approach 1: Brute Force
 */
class GenerateParenthesesBruteForce : GenerateParentheses {
    override operator fun invoke(n: Int): List<String> {
        val combinations: MutableList<String> = ArrayList()
        generateAll(CharArray(2 * n), 0, combinations)
        return combinations
    }

    private fun generateAll(current: CharArray, pos: Int, result: MutableList<String>) {
        if (pos == current.size) {
            if (valid(current)) result.add(String(current))
        } else {
            current[pos] = '('
            generateAll(current, pos + 1, result)
            current[pos] = ')'
            generateAll(current, pos + 1, result)
        }
    }

    private fun valid(current: CharArray): Boolean {
        var balance = 0
        for (c in current) {
            if (c == '(') balance++ else balance--
            if (balance < 0) return false
        }
        return balance == 0
    }
}

/**
 * Approach 2: Backtracking
 */
class GenerateParenthesesBacktracking : GenerateParentheses {
    override operator fun invoke(n: Int): List<String> {
        val ans: MutableList<String> = ArrayList()
        backtrack(ans, "", 0, 0, n)
        return ans
    }

    private fun backtrack(ans: MutableList<String>, cur: String, open: Int, close: Int, max: Int) {
        if (cur.length == max * 2) {
            ans.add(cur)
            return
        }
        if (open < max) backtrack(ans, "$cur(", open + 1, close, max)
        if (close < open) backtrack(ans, "$cur)", open, close + 1, max)
    }
}

/**
 * Approach 3: Closure Number
 */
class GenerateParenthesesClosureNumber : GenerateParentheses {
    override operator fun invoke(n: Int): List<String> {
        val ans: MutableList<String> = ArrayList()
        if (n == 0) {
            ans.add("")
        } else {
            for (c in 0 until n) for (left in invoke(c)) for (right in invoke(n - 1 - c)) ans.add(
                "($left)$right",
            )
        }
        return ans
    }
}
