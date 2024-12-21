/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class FlattenBinaryTreeTest<out T : FlattenBinaryTree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3)
                        right = TreeNode(4)
                    }
                    right = TreeNode(5).apply {
                        right = TreeNode(6)
                    }
                },
                listOf(1, 2, 3, 4, 5, 6),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3).apply {
                            left = TreeNode(4)
                            right = TreeNode(5)
                        }
                        right = TreeNode(6).apply {
                            left = TreeNode(7)
                            right = TreeNode(8)
                        }
                    }
                    right = TreeNode(9).apply {
                        left = TreeNode(10)
                        right = TreeNode(11)
                    }
                },
                listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `flatten test`(root: TreeNode?, expected: List<Int>) {
        strategy.flatten(root)
        assertThat(root.preorderTraversal()).hasSameElementsAs(expected)
    }
}

class FlattenRecursionTest : FlattenBinaryTreeTest<FlattenRecursion>(FlattenRecursion())
class FlattenStackTest : FlattenBinaryTreeTest<FlattenStack>(FlattenStack())
