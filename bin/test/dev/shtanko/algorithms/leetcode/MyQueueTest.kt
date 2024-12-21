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
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MyQueueTest {

    @Test
    fun `queue using stacks test`() {
        val queue = MyQueue()
        queue.push(1)
        queue.push(2)
        queue.push(3)
        assertEquals(1, queue.pop())
        assertFalse(queue.empty())
        assertEquals(2, queue.pop())
        assertFalse(queue.empty())
        assertEquals(3, queue.pop())
        assertTrue(queue.empty())
    }
}
