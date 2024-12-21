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
import kotlin.math.abs

private const val ASCII_CASE_DIFFERENCE = 32

/**
 * Make The String Great.
 * @see <a href="https://leetcode.com/problems/make-the-string-great/">Source</a>
 */
fun interface MakeTheStringGreat {
    operator fun invoke(str: String): String
}

/**
 * Approach 1: Iteration.
 */
class MakeTheStringGreatIteration : MakeTheStringGreat {

    override operator fun invoke(str: String): String {
        val newS = StringBuilder(str)

        // if s has less than 2 characters, we just return itself.
        while (newS.length > 1) {
            // 'find' records if we find any pair to remove.
            var find = false

            // Check every two adjacent characters, currChar and nextChar.
            for (i in 0 until newS.length - 1) {
                val currChar = newS[i]
                val nextChar = newS[i + 1]

                // If they make a pair, remove them from 's' and let 'find = true'.
                if (abs(currChar.code - nextChar.code) == ASCII_CASE_DIFFERENCE) {
                    newS.deleteCharAt(i)
                    newS.deleteCharAt(i)
                    find = true
                    break
                }
            }

            // If we cannot find any pair to remove, break the loop.
            if (!find) break
        }
        return newS.toString()
    }
}

/**
 * Approach 2: Recursion
 */
class MakeTheStringGreatRecursion : MakeTheStringGreat {

    override operator fun invoke(str: String): String {
        // If we find a pair in 's', remove this pair from 's'
        // and solve the remaining string recursively.
        for (i in 0 until str.length - 1) {
            if (abs(str[i] - str[i + 1]) == ASCII_CASE_DIFFERENCE) {
                return invoke(str.substring(0, i) + str.substring(i + 2))
            }
        }

        // Base case, if we can't find a pair, just return 's'.
        return str
    }
}

class MakeTheStringGreatStack : MakeTheStringGreat {

    companion object {
        private const val ASCII_UPPER_CASE = 97
        private const val ASCII_LOWER_CASE = 65
    }

    override operator fun invoke(str: String): String {
        val stack: Stack<Char> = Stack()
        for (i in str.indices) {
            if (stack.isNotEmpty() && abs(stack.peek() - str[i]) == ASCII_UPPER_CASE - ASCII_LOWER_CASE) {
                stack.pop()
            } else {
                stack.push(str[i])
            }
        }
        val res = CharArray(stack.size)
        var index: Int = stack.size - 1
        while (stack.isNotEmpty()) {
            res[index--] = stack.pop()
        }
        return String(res)
    }
}
