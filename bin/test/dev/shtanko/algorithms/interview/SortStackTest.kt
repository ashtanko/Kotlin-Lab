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

package dev.shtanko.algorithms.interview

import java.util.Stack
import org.junit.jupiter.api.Test

internal class SortStackTest {

    @Test
    internal fun `sort stack test`() {
        val input = intArrayOf(34, 3, 31, 98, 92, 23)
        val sorted = intArrayOf(3, 23, 31, 34, 92, 98)
        val inputStack = Stack<Int>()

        for (i in input) {
            inputStack.push(i)
        }

        val sortedStack = sortStack(inputStack)

        for (i in sorted) {
            require(sortedStack.pop() == i)
        }
    }
}
