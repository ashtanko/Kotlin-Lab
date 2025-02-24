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
import org.junit.jupiter.api.Test

abstract class NAryTreePreorderTraversalTest<out T : NAryTreePreorderTraversalStrategy>(
    private val strategy: T,
) {

    @Test
    fun `NAry tree preorder traversal test`() {
        val root = NAryNode(1).apply {
            children = listOf(
                NAryNode(3).apply {
                    children = listOf(NAryNode(5), NAryNode(6))
                },
                NAryNode(2),
                NAryNode(4),
            )
        }
        val actual = strategy.preorder(root)
        assertEquals(listOf(1, 3, 5, 6, 2, 4), actual)
    }

    @Test
    fun `NAry tree preorder traversal 2 test`() {
        val root = NAryNode(1).apply {
            children = listOf(
                NAryNode(2),
                NAryNode(3).apply {
                    children = listOf(
                        NAryNode(6),
                        NAryNode(7).apply {
                            children = listOf(
                                NAryNode(11).apply {
                                    children = listOf(NAryNode(14))
                                },
                            )
                        },
                    )
                },
                NAryNode(4).apply {
                    children = listOf(
                        NAryNode(8).apply {
                            children = listOf(NAryNode(12))
                        },
                    )
                },
                NAryNode(5).apply {
                    children = listOf(
                        NAryNode(9).apply {
                            children = listOf(NAryNode(13))
                        },
                        NAryNode(10),
                    )
                },
            )
        }
        val actual = strategy.preorder(root)
        assertEquals(listOf(1, 2, 3, 6, 7, 11, 14, 4, 8, 12, 5, 9, 13, 10), actual)
    }
}

class NAryTreePreorderTraversalIterativeTest :
    NAryTreePreorderTraversalTest<NAryTreePreorderTraversalIterative>(NAryTreePreorderTraversalIterative())

class NAryTreePreorderTraversalRecursiveTest :
    NAryTreePreorderTraversalTest<NAryTreePreorderTraversalRecursive>(NAryTreePreorderTraversalRecursive())
