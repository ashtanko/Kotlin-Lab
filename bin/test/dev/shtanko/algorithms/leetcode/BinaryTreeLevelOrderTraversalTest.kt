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

class BinaryTreeLevelOrderTraversalTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(3).apply {
                    left = TreeNode(9)
                    right = TreeNode(20)
                    right?.apply {
                        left = TreeNode(15)
                        right = TreeNode(7)
                    }
                },
                listOf(
                    listOf(3),
                    listOf(9, 20),
                    listOf(15, 7),
                ),
            ),
            Arguments.of(
                TreeNode(3),
                listOf(
                    listOf(3),
                ),
            ),
            Arguments.of(
                TreeNode(3).apply {
                    left = TreeNode(9)
                    right = TreeNode(20)
                    right?.apply {
                        left = TreeNode(15)
                        right = TreeNode(7)
                    }
                    left?.apply {
                        left = TreeNode(1)
                        right = TreeNode(2)
                    }
                },
                listOf(
                    listOf(3),
                    listOf(9, 20),
                    listOf(1, 2, 15, 7),
                ),
            ),
            Arguments.of(
                TreeNode(3).apply {
                    left = TreeNode(9)
                    right = TreeNode(20)
                    right?.apply {
                        left = TreeNode(15)
                        right = TreeNode(7)
                    }
                    left?.apply {
                        left = TreeNode(1)
                        right = TreeNode(2)
                    }
                    right?.apply {
                        left = TreeNode(1)
                        right = TreeNode(2)
                    }
                },
                listOf(
                    listOf(3),
                    listOf(9, 20),
                    listOf(1, 2, 1, 2),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `level order traversal test`(root: TreeNode, expected: List<List<Int>>) {
        val actual = root.levelOrder()
        assertEquals(expected, actual)
    }
}
