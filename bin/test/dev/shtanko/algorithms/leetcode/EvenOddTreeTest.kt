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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class EvenOddTreeTest<out T : EvenOddTree>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1),
                true,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(10).apply {
                        left = TreeNode(3).apply {
                            left = TreeNode(12)
                            right = TreeNode(8)
                        }
                    }
                    right = TreeNode(4).apply {
                        left = TreeNode(7).apply {
                            left = TreeNode(6)
                        }
                        right = TreeNode(9).apply {
                            right = TreeNode(2)
                        }
                    }
                },
                true,
            ),
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(4).apply {
                        left = TreeNode(3)
                        right = TreeNode(3)
                    }
                    right = TreeNode(2).apply {
                        left = TreeNode(7)
                    }
                },
                false,
            ),
            Arguments.of(
                intArrayOf(11, 8, 6, 1, 3, 9, 11, 30, 20, 18, 16, 12, 10, 4, 2, 17).toTree(),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 10, 4, 3, 8, 7, 9, 2, 6, 12).toTree(),
                false,
            ),
            Arguments.of(
                intArrayOf(5, 4, 2, 3, 3, 7).toTree(),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `even odd tree test`(root: TreeNode, expected: Boolean) {
        val actual = strategy.invoke(root)
        assertEquals(expected, actual)
    }
}

class EvenOddTreeBSFTest : EvenOddTreeTest<EvenOddTree>(EvenOddTreeBSF())
class EvenOddTreeBFSTest : EvenOddTreeTest<EvenOddTree>(EvenOddTreeBFS())
