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

import dev.shtanko.algorithms.annotations.level.Easy
import java.util.Stack

/**
 * 682. Baseball Game
 * @see <a href="https://leetcode.com/problems/baseball-game">Source</a>
 */
@Easy("https://leetcode.com/problems/baseball-game")
class BaseballGame {
    operator fun invoke(ops: Array<String>): Int {
        val stack = Stack<Int>()
        for (op in ops) {
            when (op) {
                "+" -> {
                    val top = stack.pop()
                    val newTop = top + stack.peek()
                    stack.push(top)
                    stack.push(newTop)
                }

                "C" -> stack.pop()
                "D" -> stack.push(2 * stack.peek())
                "" -> return 0
                else -> stack.push(Integer.valueOf(op))
            }
        }
        var ans = 0
        for (score in stack) {
            ans += score
        }
        return ans
    }
}
