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

abstract class TreeQueriesTest<out T : TreeQueries>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(3).apply {
                        left = TreeNode(2)
                    }
                    right = TreeNode(4).apply {
                        left = TreeNode(6)
                        right = TreeNode(5).apply {
                            right = TreeNode(7)
                        }
                    }
                },
                intArrayOf(4),
                intArrayOf(2),
            ),
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(8).apply {
                        left = TreeNode(2).apply {
                            left = TreeNode(4)
                            right = TreeNode(6)
                        }
                        right = TreeNode(1)
                    }
                    right = TreeNode(9).apply {
                        left = TreeNode(3)
                        right = TreeNode(7)
                    }
                },
                intArrayOf(3, 2, 4, 8),
                intArrayOf(3, 2, 3, 2),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3).apply {
                            left = TreeNode(4)
                        }
                    }
                },
                intArrayOf(5, 4, 3, 2, 1),
                intArrayOf(0, 2, 1, 0, 0),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3).apply {
                            left = TreeNode(4)
                        }
                    }
                },
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(0, 0, 1, 2, 0),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `tree queries test`(root: TreeNode, queries: IntArray, expected: IntArray) {
        val actual = strategy.invoke(root, queries)
        assertThat(actual).isEqualTo(expected)
    }
}

class TreeQueriesDPTest : TreeQueriesTest<TreeQueries>(TreeQueriesDP())
