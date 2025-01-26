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

package dev.shtanko.algorithms.facebook

import java.util.Stack

fun interface BalanceBrackets {
    fun isBalanced(args: String): Boolean
}

class BalanceBracketsStack : BalanceBrackets {
    override fun isBalanced(args: String): Boolean {
        val matchedBracketsMap = mapOf(')' to '(', '}' to '{', ']' to '[')
        val openingBrackets = setOf('(', '{', '[')
        val closingBrackets = setOf(')', '}', ']')
        val stk = Stack<Char>()

        for (ch in args) {
            if (ch in openingBrackets) {
                stk.add(ch)
            } else if (ch in closingBrackets) {
                if (stk.isEmpty()) return false
                if (stk.pop() != matchedBracketsMap.getOrDefault(ch, '0')) return false
            }
        }

        return stk.isEmpty()
    }
}
