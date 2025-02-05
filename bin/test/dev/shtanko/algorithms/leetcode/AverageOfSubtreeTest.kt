/*
 * Copyright 2023 Oleksii Shtanko
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

import dev.shtanko.algorithms.leetcode.AverageOfSubtreeStrategy.DFS
import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class AverageOfSubtreeTest<out T : AverageOfSubtree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(4).apply {
                    left = TreeNode(8).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                    right = TreeNode(5).apply {
                        right = TreeNode(6)
                    }
                },
                5,
            ),
            Arguments.of(
                TreeNode(1),
                1,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                2,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(4)
                        right = TreeNode(5)
                    }
                    right = TreeNode(3).apply {
                        left = TreeNode(6)
                        right = TreeNode(7)
                    }
                },
                4,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(4).apply {
                            left = TreeNode(8)
                            right = TreeNode(9)
                        }
                        right = TreeNode(5).apply {
                            left = TreeNode(10)
                            right = TreeNode(11)
                        }
                    }
                    right = TreeNode(3).apply {
                        left = TreeNode(6).apply {
                            left = TreeNode(12)
                            right = TreeNode(13)
                        }
                        right = TreeNode(7).apply {
                            left = TreeNode(14)
                            right = TreeNode(15)
                        }
                    }
                },
                8,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `average of subtree test`(root: TreeNode?, expected: Int) {
        val actual = strategy(root)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class AverageOfSubtreeDFSTest : AverageOfSubtreeTest<AverageOfSubtree>(DFS)
