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

abstract class TwoSum4Test<out T : TwoSum4>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(3).apply {
                        left = TreeNode(2)
                        right = TreeNode(4)
                    }
                    right = TreeNode(6).apply {
                        right = TreeNode(7)
                    }
                },
                9,
                true,
            ),
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(3).apply {
                        left = TreeNode(2)
                        right = TreeNode(4)
                    }
                    right = TreeNode(6).apply {
                        right = TreeNode(7)
                    }
                },
                28,
                false,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(1)
                    right = TreeNode(3)
                },
                4,
                true,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(1)
                    right = TreeNode(3)
                },
                1,
                false,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(1)
                    right = TreeNode(3)
                },
                3,
                true,
            ),
            Arguments.of(
                TreeNode(7).apply {
                    left = TreeNode(5).apply {
                        left = TreeNode(3)
                    }
                    right = TreeNode(9).apply {
                        right = TreeNode(12)
                    }
                },
                15,
                true,
            ),
            Arguments.of(
                TreeNode(7).apply {
                    left = TreeNode(5).apply {
                        left = TreeNode(3)
                    }
                    right = TreeNode(9).apply {
                        right = TreeNode(12)
                    }
                },
                16,
                true,
            ),
            Arguments.of(
                TreeNode(7).apply {
                    left = TreeNode(5).apply {
                        left = TreeNode(3)
                    }
                    right = TreeNode(9).apply {
                        right = TreeNode(12)
                    }
                },
                10,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find target test`(root: TreeNode, target: Int, expected: Boolean) {
        val actual = strategy.invoke(root, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class TwoSum4HashSetTest : TwoSum4Test<TwoSum4HashSet>(TwoSum4HashSet())
class TwoSum4BFSTest : TwoSum4Test<TwoSum4BFS>(TwoSum4BFS())
class TwoSum4BSTTest : TwoSum4Test<TwoSum4BST>(TwoSum4BST())
class TwoSum4DFSTest : TwoSum4Test<TwoSum4DFS>(TwoSum4DFS())
