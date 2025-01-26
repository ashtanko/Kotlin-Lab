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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class AverageOfLevelsInBinaryTreeStrategyTest<out T : AverageOfLevelsInBinaryTreeStrategy>(
    private val strategy: T,
) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(3).apply {
                    left = TreeNode(9)
                    right = TreeNode(20)
                    right?.apply {
                        left = TreeNode(15)
                        right = TreeNode(7)
                    }
                },
                doubleArrayOf(3.0, 14.5, 11.0),
            ),
            Arguments.of(
                TreeNode(3),
                doubleArrayOf(3.0),
            ),
            Arguments.of(
                TreeNode(0),
                doubleArrayOf(0.0),
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(1)
                },
                doubleArrayOf(2.0, 1.0),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(4)
                    }
                    right = TreeNode(3).apply {
                        right = TreeNode(5)
                    }
                },
                doubleArrayOf(1.0, 2.5, 4.5),
            ),
            Arguments.of(
                TreeNode(2147483647).apply {
                    left = TreeNode(2147483647)
                    right = TreeNode(2147483647)
                },
                doubleArrayOf(2147483647.0, 2147483647.0),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3).apply {
                            left = TreeNode(4).apply {
                                left = TreeNode(5)
                            }
                        }
                    }
                },
                doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        right = TreeNode(3)
                    }
                    right = TreeNode(2).apply {
                        right = TreeNode(3)
                    }
                },
                doubleArrayOf(1.0, 2.0, 3.0),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3).apply {
                            left = TreeNode(4).apply {
                                left = TreeNode(5)
                            }
                        }
                    }
                    right = TreeNode(2).apply {
                        right = TreeNode(3).apply {
                            right = TreeNode(4).apply {
                                right = TreeNode(5)
                            }
                        }
                    }
                },
                doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `average of levels in binary tree test`(tree: TreeNode, expected: DoubleArray) {
        val actual = strategy.invoke(tree)
        assertThat(actual, equalTo(expected))
    }
}

class AverageOfLevelsInBinaryTreeDFSTest :
    AverageOfLevelsInBinaryTreeStrategyTest<AverageOfLevelsInBinaryTreeDFS>(
        AverageOfLevelsInBinaryTreeDFS(),
    )

class AverageOfLevelsInBinaryTreeBFSTest :
    AverageOfLevelsInBinaryTreeStrategyTest<AverageOfLevelsInBinaryTreeBFS>(
        AverageOfLevelsInBinaryTreeBFS(),
    )
