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

abstract class SumOfRootToLeafBinaryNumbersTest<out T : SumOfRootToLeafBinaryNumbers>(
    private val strategy: T,
) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                    right = TreeNode(1).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                },
                22,
            ),
            Arguments.of(
                TreeNode(1),
                1,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0)
                },
                2,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    right = TreeNode(1)
                },
                3,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0)
                    right = TreeNode(1)
                },
                5,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0).apply {
                        left = TreeNode(0)
                    }
                    right = TreeNode(1).apply {
                        right = TreeNode(1)
                    }
                },
                11,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                    right = TreeNode(1).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                },
                22,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                    right = TreeNode(1).apply {
                        left = TreeNode(0)
                    }
                },
                15,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                    right = TreeNode(1).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                },
                22,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                    right = TreeNode(1).apply {
                        left = TreeNode(0)
                        right = TreeNode(1).apply {
                            left = TreeNode(0)
                            right = TreeNode(1)
                        }
                    }
                },
                44,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(0).apply {
                        left = TreeNode(0)
                        right = TreeNode(1)
                    }
                    right = TreeNode(1).apply {
                        left = TreeNode(0)
                        right = TreeNode(1).apply {
                            left = TreeNode(0)
                            right = TreeNode(1).apply {
                                left = TreeNode(0)
                                right = TreeNode(1)
                            }
                        }
                    }
                },
                90,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sum of root to leaf binary numbers test`(root: TreeNode, expected: Int) {
        val actual = strategy.invoke(root)
        assertEquals(expected, actual)
    }
}

class SumOfRootToLeafBinaryNumbersIPTTest :
    SumOfRootToLeafBinaryNumbersTest<SumOfRootToLeafBinaryNumbers>(SumOfRootToLeafBinaryNumbersIPT())

class SumOfRootToLeafBinaryNumbersRPTTest :
    SumOfRootToLeafBinaryNumbersTest<SumOfRootToLeafBinaryNumbers>(SumOfRootToLeafBinaryNumbersRPT())

class SumOfRootToLeafBinaryNumbersMPTTest :
    SumOfRootToLeafBinaryNumbersTest<SumOfRootToLeafBinaryNumbers>(SumOfRootToLeafBinaryNumbersMPT())

class SumOfRootToLeafBinaryNumbersBitwiseTest :
    SumOfRootToLeafBinaryNumbersTest<SumOfRootToLeafBinaryNumbers>(SumOfRootToLeafBinaryNumbersBitwise())
