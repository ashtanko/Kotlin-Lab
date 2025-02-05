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

abstract class BinaryTreePathsTest<out T : BinaryTreePaths>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1),
                listOf("1"),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                },
                listOf("1->2"),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                listOf("1->2", "1->3"),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                    left?.right = TreeNode(5)
                },
                listOf("1->2->5", "1->3"),
            ),
            Arguments.of(
                t2(),
                listOf("1->2->4", "1->2->5", "1->3->6", "1->3->7"),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3).apply {
                            left = TreeNode(4)
                        }
                    }
                },
                listOf("1->2->3->4"),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3)
                        right = TreeNode(4)
                    }
                    right = TreeNode(5).apply {
                        left = TreeNode(6)
                        right = TreeNode(7)
                    }
                },
                listOf("1->2->3", "1->2->4", "1->5->6", "1->5->7"),
            ),
        )

        private fun t2(): TreeNode? {
            val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
            val tree: TreeNode? = null
            return insertLevelOrder(tree, arr, 0)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `binary tree paths test`(tree: TreeNode, expected: List<String>) {
        val actual = strategy.invoke(tree).sorted()
        assertEquals(expected, actual)
    }
}

class BinaryTreePathsRecursionTest : BinaryTreePathsTest<BinaryTreePathsRecursion>(BinaryTreePathsRecursion())

class BinaryTreePathsBFSQueueTest : BinaryTreePathsTest<BinaryTreePathsBFSQueue>(BinaryTreePathsBFSQueue())

class BinaryTreePathsBFSStackTest : BinaryTreePathsTest<BinaryTreePathsBFSStack>(BinaryTreePathsBFSStack())
