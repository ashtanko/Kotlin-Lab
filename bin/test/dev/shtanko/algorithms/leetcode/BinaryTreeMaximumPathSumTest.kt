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

import dev.shtanko.algorithms.leetcode.BinaryTreeMaximumPathSum.maxPathSum
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class BinaryTreeMaximumPathSumTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(0),
                0,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                },
                3,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(2)
                },
                4,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                6,
            ),
            Arguments.of(
                TreeNode(-10).apply {
                    left = TreeNode(9)
                    right = TreeNode(20)
                    right?.apply {
                        right = TreeNode(7)
                        left = TreeNode(15)
                    }
                },
                42,
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
                18,
            ),
            Arguments.of(
                TreeNode(-3),
                -3,
            ),
            Arguments.of(
                TreeNode(-2).apply {
                    left = TreeNode(-1)
                },
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `binary tree maximum path test`(tree: TreeNode, expected: Int) {
        val actual = tree.maxPathSum()
        assertEquals(expected, actual)
    }
}
