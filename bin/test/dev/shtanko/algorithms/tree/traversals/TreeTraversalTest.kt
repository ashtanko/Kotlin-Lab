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

package dev.shtanko.algorithms.tree.traversals

import dev.shtanko.algorithms.tree.traversals.inorder.BinaryTreeInorderIterativeTraversal
import dev.shtanko.algorithms.tree.traversals.inorder.BinaryTreeInorderStackTraversal
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal abstract class TreeTraversalTest<out T : BinaryTreeTraversalStrategy>(private val strategy: T) {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                bstRoot,
                listOf(1, 3, 4, 6, 7, 8, 10, 13, 14),
            ),
        )

        //          8
        //         / \
        //        3   10
        //       / \    \
        //      1   6    14
        //         / \   /
        //        4   7 13
        private val bstRoot = BinaryTreeNode(
            data = 8,
            left = BinaryTreeNode(
                data = 3,
                left = BinaryTreeNode(data = 1),
                right = BinaryTreeNode(
                    data = 6,
                    left = BinaryTreeNode(data = 4),
                    right = BinaryTreeNode(data = 7),
                ),
            ),
            right = BinaryTreeNode(
                data = 10,
                right = BinaryTreeNode(
                    data = 14,
                    left = BinaryTreeNode(data = 13),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `in order iterative test`(root: BinaryTreeNode, expected: List<Int>) {
        val actual = strategy(root)
        assertEquals(expected, actual)
    }
}

internal class BinaryTreeInorderIterativeTraversalTest : TreeTraversalTest<BinaryTreeTraversalStrategy>(
    BinaryTreeInorderIterativeTraversal(),
)

internal class BinaryTreeInorderStackTraversalTest : TreeTraversalTest<BinaryTreeTraversalStrategy>(
    BinaryTreeInorderStackTraversal(),
)
