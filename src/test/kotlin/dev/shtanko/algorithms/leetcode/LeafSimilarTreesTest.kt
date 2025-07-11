/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class LeafSimilarTreesTest<out T : LeafSimilarTrees>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `leaf similar trees test`(root1: TreeNode?, root2: TreeNode?, expected: Boolean) {
        val actual = strategy(root1, root2)
        assertThat(actual).isEqualTo(expected)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(3).apply {
                    left = TreeNode(5).apply {
                        left = TreeNode(6)
                        right = TreeNode(2)
                        right?.left = TreeNode(7)
                        right?.right = TreeNode(4)
                    }
                    right = TreeNode(1).apply {
                        left = TreeNode(9)
                        right = TreeNode(8)
                    }
                },
                TreeNode(3).apply {
                    left = TreeNode(5).apply {
                        left = TreeNode(6)
                        right = TreeNode(7)
                    }
                    right = TreeNode(1).apply {
                        left = TreeNode(4)
                        right = TreeNode(2).apply {
                            right = TreeNode(8)
                            left = TreeNode(9)
                        }
                    }
                },
                true,
            ),
            Arguments.of(
                TreeNode(1),
                TreeNode(1),
                true,
            ),
            Arguments.of(
                TreeNode(2),
                TreeNode(2),
                true,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                },
                TreeNode(2).apply {
                    left = TreeNode(2)
                },
                true,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                TreeNode(1).apply {
                    left = TreeNode(3)
                    right = TreeNode(2)
                },
                false,
            ),
        )
    }
}

class LeafSimilarDepthFirstSearchTest : LeafSimilarTreesTest<LeafSimilarTrees>(LeafSimilarDepthFirstSearch())
