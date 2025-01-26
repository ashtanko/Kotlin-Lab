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

abstract class PathSum2Test<out T : PathSum2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(4).apply {
                        left = TreeNode(11).apply {
                            left = TreeNode(7)
                            right = TreeNode(2)
                        }
                    }
                    right = TreeNode(8).apply {
                        left = TreeNode(13)
                        right = TreeNode(4).apply {
                            left = TreeNode(5)
                            right = TreeNode(1)
                        }
                    }
                },
                22,
                listOf(
                    listOf(5, 4, 11, 2),
                    listOf(5, 8, 4, 5),
                ),
            ),
            Arguments.of(
                TreeNode(1),
                2,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(4).apply {
                        left = TreeNode(1)
                    }
                    right = TreeNode(5)
                },
                10,
                listOf(
                    listOf(5, 4, 1),
                    listOf(5, 5),
                ),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                5,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                },
                0,
                emptyList<List<Int>>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `path sum test`(root: TreeNode?, targetSum: Int, expected: List<List<Int>>) {
        val actual = strategy.invoke(root, targetSum)
        assertThat(actual).containsAll(expected)
    }
}

class PathSum2DFSTest : PathSum2Test<PathSum2DFS>(PathSum2DFS())
