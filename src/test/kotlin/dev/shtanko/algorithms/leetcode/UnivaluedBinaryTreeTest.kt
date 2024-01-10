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

abstract class UnivaluedBinaryTreeTest<out T : UnivaluedBinaryTree>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `univalued binary tree test`(tree: TreeNode, expected: Boolean) {
        val actual = strategy.invoke(tree)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(1).apply {
                        left = TreeNode(1)
                        right = TreeNode(1)
                    }
                    right = TreeNode(1).apply {
                        right = TreeNode(1)
                    }
                },
                true,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(5)
                        right = TreeNode(2)
                    }
                    right = TreeNode(2)
                },
                false,
            ),
        )
    }
}

class UnivaluedBinaryTreeDFSTest : UnivaluedBinaryTreeTest<UnivaluedBinaryTreeDFS>(UnivaluedBinaryTreeDFS())

class UnivaluedBinaryTreeRecursiveTest :
    UnivaluedBinaryTreeTest<UnivaluedBinaryTreeRecursive>(UnivaluedBinaryTreeRecursive())
