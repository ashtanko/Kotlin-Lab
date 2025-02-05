/*
 * Copyright 2024 Oleksii Shtanko
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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class LinkedListInBinaryTreeTest<out T : LinkedListInBinaryTree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 2, 8).toListNode(),
                TreeNode(1).apply {
                    left = TreeNode(4).apply {
                        left = TreeNode(2).apply {
                            left = TreeNode(1)
                        }
                    }
                    right = TreeNode(4).apply {
                        left = TreeNode(2).apply {
                            left = TreeNode(6)
                            right = TreeNode(8).apply {
                                left = TreeNode(1)
                                right = TreeNode(3)
                            }
                        }
                    }
                },
                true,
            ),
            Arguments.of(
                intArrayOf(1, 4, 2, 6).toListNode(),
                TreeNode(1).apply {
                    left = TreeNode(4).apply {
                        left = TreeNode(2)
                    }
                    right = TreeNode(4).apply {
                        left = TreeNode(2).apply {
                            left = TreeNode(1)
                        }
                        right = TreeNode(8)
                    }
                },
                false,
            ),
            Arguments.of(
                intArrayOf(1, 4, 2, 6, 8).toListNode(),
                TreeNode(1).apply {
                    left = TreeNode(4).apply {
                        left = TreeNode(2).apply {
                            right = TreeNode(1)
                        }
                    }
                    right = TreeNode(4).apply {
                        left = TreeNode(2).apply {
                            left = TreeNode(1)
                        }
                        right = TreeNode(8)
                    }
                },
                false,
            ),
        )
    }

    @ParameterizedTest(name = "{index} => head = {0}, root = {1}, expected = {2}")
    @ArgumentsSource(InputArgumentsProvider::class)
    fun isSubPathTest(head: ListNode?, root: TreeNode?, expected: Boolean) {
        val actual = strategy(head, root)
        assertThat(actual).isEqualTo(expected)
    }
}

class LinkedListInBinaryTreeKMPTest : LinkedListInBinaryTreeTest<LinkedListInBinaryTree>(LinkedListInBinaryTreeKMP)
class LinkedListInBinaryTreeDFSTest : LinkedListInBinaryTreeTest<LinkedListInBinaryTree>(LinkedListInBinaryTreeDFS)
class LinkedListInBinaryTreeIterativeTest :
    LinkedListInBinaryTreeTest<LinkedListInBinaryTree>(LinkedListInBinaryTreeIterative)
