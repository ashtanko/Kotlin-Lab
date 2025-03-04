/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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

internal class MinStackTest {

    @Test
    internal fun `test push min`() {
        val stack = MinStack()
        stack.push(10)
        stack.push(11)
        stack.push(12)

        assertTrue(stack.min() == 10)

        stack.push(3)
        assertTrue(stack.min() == 3)

        stack.pop()
        assertTrue(stack.min() == 10)
    }
}
