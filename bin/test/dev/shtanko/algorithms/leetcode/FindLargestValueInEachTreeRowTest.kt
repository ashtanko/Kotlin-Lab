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

abstract class FindLargestValueInEachTreeRowTest<out T : FindLargestValueInEachTreeRow>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(3).apply {
                        left = TreeNode(5)
                        right = TreeNode(3)
                    }
                    right = TreeNode(2).apply {
                        right = TreeNode(9)
                    }
                },
                listOf(1, 3, 9),
            ),
            Arguments.of(
                null,
                listOf<Int>(),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(4)
                    }
                    right = TreeNode(3).apply {
                        right = TreeNode(5).apply {
                            right = TreeNode(7)
                        }
                    }
                },
                listOf(1, 3, 5, 7),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(4).apply {
                            left = TreeNode(8)
                        }
                        right = TreeNode(5).apply {
                            right = TreeNode(9)
                        }
                    }
                    right = TreeNode(3).apply {
                        right = TreeNode(6).apply {
                            right = TreeNode(7)
                        }
                    }
                },
                listOf(1, 3, 6, 9),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `largest values test`(root: TreeNode?, expected: List<Int>) {
        val actual = strategy(root)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class LargestValueInEachTreeRowBFSTest :
    FindLargestValueInEachTreeRowTest<FindLargestValueInEachTreeRow>(LargestValueInEachTreeRowBFS())

class LargestValueInEachTreeRowDFSTest :
    FindLargestValueInEachTreeRowTest<FindLargestValueInEachTreeRow>(LargestValueInEachTreeRowDFS())

class LargestValueInEachTreeRowDFSIterTest :
    FindLargestValueInEachTreeRowTest<FindLargestValueInEachTreeRow>(LargestValueInEachTreeRowDFSIter())
