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

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class TrimBinarySearchTreeTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `trim BST test`(root: TreeNode, low: Int, high: Int, expected: List<List<Int>>) {
        val actual = trimBST(root, low, high).levelOrder()
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0)
                    right = TreeNode(2)
                },
                1,
                2,
                listOf(
                    listOf(1),
                    listOf(2),
                ),
            ),
            Arguments.of(
                TreeNode(3).apply {
                    left = TreeNode(0).apply {
                        right = TreeNode(2).apply {
                            left = TreeNode(1)
                        }
                    }
                    right = TreeNode(4)
                },
                1,
                3,
                listOf(
                    listOf(3),
                    listOf(2),
                    listOf(1),
                ),
            ),
            Arguments.of(

                TreeNode(1),
                1,
                2,
                listOf(
                    listOf(1),
                ),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    right = TreeNode(2)
                },
                1,
                3,
                listOf(
                    listOf(1),
                    listOf(2),
                ),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    right = TreeNode(2)
                },
                2,
                4,
                listOf(
                    listOf(2),
                ),
            ),
            Arguments.of(
                TreeNode(1),
                0,
                0,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                TreeNode(1),
                2,
                4,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0)
                    right = TreeNode(2)
                },
                3,
                4,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                TreeNode(3).apply {
                    left = TreeNode(0).apply {
                        right = TreeNode(2).apply {
                            left = TreeNode(1)
                        }
                    }
                    right = TreeNode(4)
                },
                4,
                5,
                listOf(
                    listOf(4),
                ),
            ),
        )
    }
}
