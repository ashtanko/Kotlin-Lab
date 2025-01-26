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

/**
 * 1047. Remove All Adjacent Duplicates In String
 * @see <a href="https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/">Source</a>
 */
fun interface RemoveAllAdjacentDuplicatesStrategy {
    operator fun invoke(s: String): String
}

class RemoveAllAdjacentDuplicatesArray : RemoveAllAdjacentDuplicatesStrategy {
    override operator fun invoke(s: String): String {
        var i = 0
        val n = s.length
        val res = s.toCharArray()
        var j = 0
        while (j < n) {
            res[i] = res[j]
            if (i > 0 && res[i - 1] == res[i]) {
                i -= 2
            }
            ++j
            ++i
        }
        return String(res, 0, i)
    }
}

class RemoveAllAdjacentDuplicatesStack : RemoveAllAdjacentDuplicatesStrategy {
    override operator fun invoke(s: String): String {
        val stack: Stack<Char> = Stack()
        for (c in s) {
            if (stack.isNotEmpty() && stack.peek() == c) {
                stack.pop()
            } else {
                stack.push(c)
            }
        }
        val sb = StringBuilder()
        while (stack.isNotEmpty()) sb.append(stack.pop())
        return sb.reverse().toString()
    }
}

// Using StringBuilder
class RemoveAllAdjacentDuplicatesSB : RemoveAllAdjacentDuplicatesStrategy {
    override operator fun invoke(s: String): String {
        val sb = StringBuilder()
        for (c in s) {
            val size = sb.length
            if (size > 0 && sb[size - 1] == c) {
                sb.deleteCharAt(size - 1)
            } else {
                sb.append(c)
            }
        }
        return sb.toString()
    }
}
