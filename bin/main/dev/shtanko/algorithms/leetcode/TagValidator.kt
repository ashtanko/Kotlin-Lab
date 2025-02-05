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

fun interface TagValidatorStrategy {
    operator fun invoke(code: String): Boolean
}

class TagValidatorStack : TagValidatorStrategy {

    private val stack: Stack<String> = Stack()
    private var containsTag = false

    override operator fun invoke(code: String): Boolean {
        if (code[0] != '<' || code[code.length - 1] != '>') {
            return false
        }
        var i = 0
        while (i < code.length) {
            var ending = false
            var closeIndex: Int
            if (stack.isEmpty() && containsTag) {
                return false
            }
            if (code[i] == '<') {
                if (stack.isNotEmpty() && code[i + 1] == '!') {
                    closeIndex = code.indexOf("]]>", i + 1)
                    if (closeIndex < 0 || !isValidCdata(code.substring(i + 2, closeIndex))) {
                        return false
                    }
                } else {
                    if (code[i + 1] == '/') {
                        i++
                        ending = true
                    }
                    closeIndex = code.indexOf('>', i + 1)
                    if (closeIndex < 0 || !isValidTagName(code.substring(i + 1, closeIndex), ending)) {
                        return false
                    }
                }
                i = closeIndex
            }
            i++
        }
        return stack.isEmpty() && containsTag
    }

    private fun isValidCdata(s: String): Boolean {
        return s.indexOf("[CDATA[") == 0
    }

    private fun isValidTagName(s: String, ending: Boolean): Boolean {
        if (s.isEmpty() || s.length > 9) {
            return false
        }
        for (element in s) {
            if (!Character.isUpperCase(element)) {
                return false
            }
        }
        if (ending) {
            if (stack.isNotEmpty() && stack.peek() == s) {
                stack.pop()
            } else {
                return false
            }
        } else {
            containsTag = true
            stack.push(s)
        }
        return true
    }
}

class TagValidatorRegex : TagValidatorStrategy {

    override operator fun invoke(code: String): Boolean {
        val st = Stack<String>()
        var i = 0
        while (i < code.length) {
            if (i > 0 && st.isEmpty()) {
                return false // check if double <![CDATA[
            }
            when {
                code.startsWith("<![CDATA[", i) -> {
                    val j = i + 9
                    i = code.indexOf("]]>", j)
                    if (i < 0) {
                        return false
                    }
                    i += 3
                }

                code.startsWith("</", i) -> {
                    val j = i + 2
                    i = code.indexOf(">", j)
                    if (i < 0 || i == j || i - j > 9) {
                        return false
                    }
                    if (st.isEmpty() || code.substring(j, i++) != st.pop()) {
                        return false
                    }
                }

                code.startsWith("<", i) -> {
                    val j = i + 1
                    i = code.indexOf(">", j)
                    if (i < 0 || i == j || i - j > 9) {
                        return false
                    }
                    for (k in j until i) {
                        if (!Character.isUpperCase(code[k])) {
                            return false
                        }
                    }
                    st.push(code.substring(j, i++))
                }

                else -> {
                    i++
                }
            }
        }
        return st.isEmpty()
    }
}
