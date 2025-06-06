/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

abstract class RemoveLeafNodesTest<out T : RemoveLeafNodes>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(2)
                    }
                    right = TreeNode(3).apply {
                        left = TreeNode(2)
                        right = TreeNode(4)
                    }
                },
                2,
                TreeNode(1).apply {
                    right = TreeNode(3).apply {
                        right = TreeNode(4)
                    }
                },
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(3).apply {
                        left = TreeNode(3)
                        right = TreeNode(2)
                    }
                    right = TreeNode(3).apply {
                        left = TreeNode(2)
                    }
                },
                3,
                TreeNode(1).apply {
                    left = TreeNode(3).apply {
                        right = TreeNode(2)
                    }
                },
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(2)
                    }
                    right = TreeNode(3)
                },
                2,
                TreeNode(1).apply {
                    right = TreeNode(3)
                },
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `remove leaf nodes test`(root: TreeNode?, target: Int, expected: TreeNode?) {
        val actual = strategy(root, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class RemoveLeafNodesRecursiveTest : RemoveLeafNodesTest<RemoveLeafNodes>(RemoveLeafNodesRecursive())
class RemoveLeafNodesIterativeTest : RemoveLeafNodesTest<RemoveLeafNodes>(RemoveLeafNodesIterative())
