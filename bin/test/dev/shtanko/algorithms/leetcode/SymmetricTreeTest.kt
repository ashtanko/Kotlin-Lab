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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SymmetricTreeTest<out T : SymmetricTree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(2),
                true,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(1)
                },
                false,
            ),
            Arguments.of(
                TreeNode(5).apply {
                    right = TreeNode(6)
                },
                false,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(2)
                },
                true,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                false,
            ),
            Arguments.of(
                TreeNode(10).apply {
                    left = TreeNode(2).apply {
                        right = TreeNode(3)
                    }
                    right = TreeNode(3)
                },
                false,
            ),
            Arguments.of(
                symmetricTree,
                true,
            ),
            Arguments.of(
                asymmetricTree,
                false,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3)
                    }
                    right = TreeNode(2).apply {
                        right = TreeNode(3)
                    }
                },
                true,
            ),
        )

        private val symmetricTree = TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(3)
                right = TreeNode(4)
            }
            right = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(3)
            }
        }

        private val asymmetricTree = TreeNode(1).apply {
            left = TreeNode(2).apply {
                right = TreeNode(3)
            }
            right = TreeNode(2).apply {
                right = TreeNode(3)
            }
        }
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is symmetric tree test`(root: TreeNode, expected: Boolean) {
        val actual = strategy.invoke(root)
        assertThat(actual).isEqualTo(expected)
    }
}

class SymmetricTreeRecursiveTest : SymmetricTreeTest<SymmetricTree>(SymmetricTreeRecursive())
class SymmetricTreeIterativeTest : SymmetricTreeTest<SymmetricTree>(SymmetricTreeIterative())
