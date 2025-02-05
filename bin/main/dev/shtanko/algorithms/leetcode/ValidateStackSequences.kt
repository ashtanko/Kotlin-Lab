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

import java.util.Stack

/**
 * 946. Validate Stack Sequences
 * @see <a href="https://leetcode.com/problems/validate-stack-sequences/">Source</a>
 */
fun interface ValidateStackSequences {
    operator fun invoke(pushed: IntArray, popped: IntArray): Boolean
}

class ValidateStackSequencesGreedy : ValidateStackSequences {
    override operator fun invoke(pushed: IntArray, popped: IntArray): Boolean {
        val n: Int = pushed.size
        val stack: Stack<Int> = Stack()

        var j = 0
        for (x in pushed) {
            stack.push(x)
            while (stack.isNotEmpty() && j < n && stack.peek() == popped[j]) {
                stack.pop()
                j++
            }
        }
        return j == n
    }
}
