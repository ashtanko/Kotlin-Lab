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

internal class StackQueueTest {

    @Test
    internal fun `stack queue test`() {
        val queue = StackQueue<Int>()

        queue.add(1)
        queue.add(2)
        queue.add(3)
        queue.add(4)

        assertTrue(1 == queue.remove())

        queue.add(5)

        assertTrue(2 == queue.remove())
        assertTrue(3 == queue.remove())
        assertTrue(4 == queue.remove())
        assertTrue(5 == queue.remove())
    }
}
