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

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class DistanceKTreeTest<out T : DistanceKTree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(3).apply {
                    left = TreeNode(5).apply {
                        left = TreeNode(6)
                        right = TreeNode(2).apply {
                            left = TreeNode(7)
                            right = TreeNode(4)
                        }
                    }
                    right = TreeNode(1).apply {
                        left = TreeNode(0)
                        right = TreeNode(8)
                    }
                },
                TreeNode(5),
                2,
                listOf(7, 4, 1),
            ),
            Arguments.of(
                TreeNode(1),
                TreeNode(1),
                3,
                emptyList<Int>(),
            ),
            Arguments.of(
                TreeNode(0).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(1).apply {
                            left = TreeNode(3)
                        }
                        right = TreeNode(4)
                    }
                },
                TreeNode(2),
                3,
                emptyList<Int>(),
            ),
            Arguments.of(
                TreeNode(0).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(1).apply {
                            left = TreeNode(3)
                        }
                        right = TreeNode(4)
                    }
                },
                TreeNode(4),
                2,
                listOf(0, 1),
            ),
            Arguments.of(
                TreeNode(0).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(1).apply {
                            left = TreeNode(3)
                        }
                        right = TreeNode(4)
                    }
                },
                TreeNode(0),
                1,
                listOf(2),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `distance k test`(root: TreeNode, target: TreeNode, k: Int, expected: List<Int>) {
        val actual = strategy.distanceK(root, target, k)
        Assertions.assertThat(actual).containsExactlyInAnyOrder(*expected.toTypedArray())
    }
}

class DistanceKTreeParentPointersTest : DistanceKTreeTest<DistanceKTree>(DistanceKTreeParentPointers())
class DistanceKTreeDFSTest : DistanceKTreeTest<DistanceKTree>(DistanceKTreeDFS())
class DistanceKTreeBFSTest : DistanceKTreeTest<DistanceKTree>(DistanceKTreeBFS())
