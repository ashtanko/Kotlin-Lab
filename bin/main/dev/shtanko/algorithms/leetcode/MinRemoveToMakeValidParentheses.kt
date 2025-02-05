/*
 * Copyright 2024 Oleksii Shtanko
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

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 */
fun interface MinRemoveToMakeValidParentheses {
    operator fun invoke(str: String): String
}

class MinRemoveToMakeValidParenthesesSb : MinRemoveToMakeValidParentheses {
    override fun invoke(str: String): String {
        val sb = StringBuilder(str)
        val st: Stack<Int> = Stack()
        for (i in sb.indices) {
            if (sb[i] == '(') {
                st.add(i + 1)
            }
            if (sb[i] == ')') {
                if (!st.empty() && st.peek() >= 0) {
                    st.pop()
                } else {
                    st.add(-(i + 1))
                }
            }
        }
        while (!st.empty()) {
            sb.deleteCharAt(abs(st.pop()) - 1)
        }
        return sb.toString()
    }
}
