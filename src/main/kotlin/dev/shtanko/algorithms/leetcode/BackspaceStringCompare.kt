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

import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.TwoPointers
import java.util.Stack

/**
 * 844. Backspace String Compare
 * @see <a href="https://leetcode.com/problems/backspace-string-compare">Source</a>
 */
fun interface BackspaceStringCompare {
    operator fun invoke(source: String, target: String): Boolean
}

@Iterative
class BackspaceStringCompareStack : BackspaceStringCompare {
    override fun invoke(source: String, target: String): Boolean {
        return build(source) == build(target)
    }

    private fun build(str: String): String {
        val ans: Stack<Char> = Stack()
        for (character in str.toCharArray()) {
            if (character != '#') {
                ans.push(character)
            } else if (!ans.empty()) {
                ans.pop()
            }
        }
        return java.lang.String.valueOf(ans)
    }
}

@TwoPointers
class BackspaceStringCompareTwoPointer : BackspaceStringCompare {
    override fun invoke(source: String, target: String): Boolean {
        var sourceIndex: Int = source.length - 1
        var targetIndex: Int = target.length - 1
        var sourceSkip = 0
        var targetSkip = 0

        while (sourceIndex >= 0 || targetIndex >= 0) { // While there may be chars in build(source) or build(target)
            while (sourceIndex >= 0) { // Find position of next possible char in build(source)
                if (source[sourceIndex] == '#') {
                    sourceSkip++
                    sourceIndex--
                } else if (sourceSkip > 0) {
                    sourceSkip--
                    sourceIndex--
                } else {
                    break
                }
            }
            while (targetIndex >= 0) { // Find position of next possible char in build(target)
                if (target[targetIndex] == '#') {
                    targetSkip++
                    targetIndex--
                } else if (targetSkip > 0) {
                    targetSkip--
                    targetIndex--
                } else {
                    break
                }
            }
            // If two actual characters are different
            if (sourceIndex >= 0 && targetIndex >= 0 && source[sourceIndex] != target[targetIndex]) return false
            // If expecting to compare char vs nothing
            if (sourceIndex >= 0 != targetIndex >= 0) return false
            sourceIndex--
            targetIndex--
        }
        return true
    }
}
