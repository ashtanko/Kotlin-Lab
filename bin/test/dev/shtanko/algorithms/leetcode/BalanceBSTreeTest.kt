/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class BalanceBSTreeTest<out T : BalanceBST>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    right = TreeNode(2).apply {
                        right = TreeNode(3).apply {
                            right = TreeNode(4)
                        }
                    }
                },
                TreeNode(2).apply {
                    right = TreeNode(3).apply {
                        right = TreeNode(4)
                    }
                    left = TreeNode(1)
                },
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(1)
                    right = TreeNode(3)
                },
                TreeNode(2).apply {
                    left = TreeNode(1)
                    right = TreeNode(3)
                },
            ),
            Arguments.of(
                TreeNode(2),
                TreeNode(2),
            ),
            Arguments.of(
                TreeNode(0),
                TreeNode(0),
            ),
            Arguments.of(
                TreeNode(1),
                TreeNode(1),
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(1)
                },
                TreeNode(2).apply {
                    left = TreeNode(1)
                },
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(0).apply {
                        left = TreeNode(0)
                    }
                },
                TreeNode(2).apply {
                    left = TreeNode(0).apply {
                        left = TreeNode(0)
                    }
                },
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0)
                    right = TreeNode(2)
                },
                TreeNode(1).apply {
                    left = TreeNode(0)
                    right = TreeNode(2)
                },
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0)
                    right = TreeNode(2).apply {
                        right = TreeNode(3)
                    }
                },
                TreeNode(2).apply {
                    right = TreeNode(3)
                    left = TreeNode(1).apply {
                        left = TreeNode(0)
                    }
                },
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `balance BST test`(root: TreeNode?, expected: TreeNode?) {
        val actual = strategy.invoke(root).postOrderTraversal()
        assertThat(actual).containsAll(expected.postOrderTraversal())
    }
}

class BalanceBSTInorderTest : BalanceBSTreeTest<BalanceBST>(BalanceBSTInorder())
class BalanceBSTreeDSWTest : BalanceBSTreeTest<BalanceBST>(BalanceBSTreeDSW())
