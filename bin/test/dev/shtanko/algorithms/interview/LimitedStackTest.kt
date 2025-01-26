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

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class LimitedStackTest {

    @Test
    internal fun `stack of stacks test`() {
        val stacks = StackOfStacks()
        val nums = intArrayOf(1, 2, 3, 4, 5, 6)
        for (num in nums) {
            stacks.push(num)
        }

        for (num in nums.reversed()) {
            assertTrue(stacks.pop() == num)
        }
    }
}
