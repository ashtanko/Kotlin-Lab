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

abstract class MaxAncestorDiffTest<out T : MaxAncestorDiff>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(8).apply {
                    left = TreeNode(3).apply {
                        left = TreeNode(1)
                        right = TreeNode(6).apply {
                            left = TreeNode(4)
                            right = TreeNode(7)
                        }
                    }
                    right = TreeNode(10).apply {
                        right = TreeNode(14).apply {
                            left = TreeNode(13)
                        }
                    }
                },
                7,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    right = TreeNode(2).apply {
                        right = TreeNode(0).apply {
                            left = TreeNode(3)
                        }
                    }
                },
                3,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max ancestor diff test`(root: TreeNode?, expected: Int) {
        val actual = strategy.invoke(root)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxAncestorDiffRecursionTest : MaxAncestorDiffTest<MaxAncestorDiff>(MaxAncestorDiffRecursion())
class MaxAncestorDiffMMTest : MaxAncestorDiffTest<MaxAncestorDiff>(MaxAncestorDiffMM())
