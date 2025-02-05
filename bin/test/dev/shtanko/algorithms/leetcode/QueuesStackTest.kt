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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class QueuesStackTest {

    @Test
    fun `stack using two queues test`() {
        val stack: QueuesStack = StackTwoQueues()
        test(stack)
    }

    @Test
    fun `stack using two queues 2 test`() {
        val stack: QueuesStack = StackTwoQueues2()
        test(stack)
    }

    @Test
    fun `stack using one queue test`() {
        val stack: QueuesStack = StackOneQueue()
        test(stack)
    }

    private fun test(stack: QueuesStack) {
        stack.push(1)
        stack.push(2)
        stack.push(3)
        assertEquals(3, stack.top())
        stack.pop()
        stack.pop()
        stack.pop()
        assertTrue(stack.empty())
    }
}
