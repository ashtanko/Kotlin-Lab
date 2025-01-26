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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LinkedListRandomNodeTest {

    @Test
    fun `get random test`() {
        val linkedListRandomNode = LinkedListRandomNode(listOf(1, 2, 3).toListNode())
        repeat(500) {
            assertRandom(linkedListRandomNode.getRandom())
        }
    }

    private fun assertRandom(value: Int) {
        assertThat(listOf(1, 2, 3)).containsAnyOf(value)
    }
}
