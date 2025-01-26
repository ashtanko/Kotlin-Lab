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

import java.util.Stack
import kotlin.math.min

/**
 * 1130. Minimum Cost Tree From Leaf Values
 * @see <a href="https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/">Source</a>
 */
fun interface MctFromLeafValues {
    operator fun invoke(arr: IntArray): Int
}

class MctFromLeafValuesDP : MctFromLeafValues {
    override operator fun invoke(arr: IntArray): Int {
        var res = 0
        val stack: Stack<Int> = Stack()
        stack.push(Int.MAX_VALUE)
        for (a in arr) {
            while (stack.peek() <= a) {
                val mid: Int = stack.pop()
                res += mid * min(stack.peek(), a)
            }
            stack.push(a)
        }
        while (stack.size > 2) {
            res += stack.pop() * stack.peek()
        }
        return res
    }
}
