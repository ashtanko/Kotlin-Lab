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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class UniqueBST2Test<out T : UniqueBST2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                listOf(
                    TreeNode(1).apply {
                        right = TreeNode(3).apply {
                            left = TreeNode(2)
                        }
                    },
                    TreeNode(1).apply {
                        right = TreeNode(2).apply {
                            right = TreeNode(3)
                        }
                    },
                    TreeNode(2).apply {
                        left = TreeNode(1)
                        right = TreeNode(3)
                    },
                    TreeNode(3).apply {
                        left = TreeNode(2).apply {
                            left = TreeNode(1)
                        }
                    },
                    TreeNode(3).apply {
                        left = TreeNode(1).apply {
                            left = TreeNode(2)
                        }
                    },
                ),
            ),
            Arguments.of(
                1,
                listOf(
                    TreeNode(1),
                ),
            ),
            Arguments.of(
                2,
                listOf(
                    TreeNode(1).apply {
                        right = TreeNode(2)
                    },
                    TreeNode(2).apply {
                        left = TreeNode(1)
                    },
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `generate trees test`(num: Int, expected: List<TreeNode>) {
        val actual = strategy.invoke(num).map { it.preorderTraversal() }
        assertThat(actual).containsExactlyInAnyOrder(*expected.map { it.preorderTraversal() }.toTypedArray())
    }
}

class UniqueBST2RecursiveDPTest : UniqueBST2Test<UniqueBST2>(UniqueBST2RecursiveDP())
class UniqueBST2IterativeDPTest : UniqueBST2Test<UniqueBST2>(UniqueBST2IterativeDP())
class UniqueBST2DPSpaceOptTest : UniqueBST2Test<UniqueBST2>(UniqueBST2DPSpaceOpt())
