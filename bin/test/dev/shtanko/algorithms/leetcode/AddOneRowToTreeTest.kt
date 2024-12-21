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

abstract class AddOneRowToTreeTest<out T : AddOneRowToTree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(4).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3)
                        right = TreeNode(1)
                    }
                    right = TreeNode(6).apply {
                        left = TreeNode(5)
                    }
                },
                1,
                2,
                TreeNode(4).apply {
                    left = TreeNode(1).apply {
                        left = TreeNode(2).apply {
                            left = TreeNode(3)
                            right = TreeNode(1)
                        }
                    }
                    right = TreeNode(1).apply {
                        right = TreeNode(6).apply {
                            left = TreeNode(5)
                        }
                    }
                },
            ),
            Arguments.of(
                TreeNode(4).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3)
                        right = TreeNode(1)
                    }
                },
                1,
                3,
                TreeNode(4).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(1).apply {
                            left = TreeNode(3)
                        }
                        right = TreeNode(1).apply {
                            right = TreeNode(1)
                        }
                    }
                },
            ),
            Arguments.of(
                TreeNode(4).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3)
                        right = TreeNode(1)
                    }
                },
                1,
                1,
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3)
                        right = TreeNode(1)
                    }
                    right = TreeNode(4)
                },
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `add one row test`(tree: TreeNode, value: Int, depth: Int, expected: TreeNode) {
        val actual = strategy.invoke(tree, value, depth)
        assertThat(actual.postOrderTraversal()).isEqualTo(expected.postOrderTraversal())
    }
}

class AddOneRowToTreeRecTest : AddOneRowToTreeTest<AddOneRowToTreeRec>(AddOneRowToTreeRec())
class AddOneRowToTreeStackTest : AddOneRowToTreeTest<AddOneRowToTreeStack>(AddOneRowToTreeStack())
class AddOneRowToTreeQueueTest : AddOneRowToTreeTest<AddOneRowToTreeQueue>(AddOneRowToTreeQueue())
