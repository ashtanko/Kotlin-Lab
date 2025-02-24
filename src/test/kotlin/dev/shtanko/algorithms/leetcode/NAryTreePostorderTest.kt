/*
 * Copyright 2024 Oleksii Shtanko
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

abstract class NAryTreePostorderTest<out T : NAryTreePostorder>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                NAryNode(1).apply {
                    children = listOf(
                        NAryNode(3).apply {
                            children = listOf(
                                NAryNode(5),
                                NAryNode(6),
                            )
                        },
                        NAryNode(2),
                        NAryNode(4),
                    )
                },
                listOf(5, 6, 3, 2, 4, 1),
            ),
            Arguments.of(
                NAryNode(1).apply {
                    children = listOf(
                        NAryNode(2),
                        NAryNode(3).apply {
                            children = listOf(
                                NAryNode(6),
                                NAryNode(7).apply {
                                    children = listOf(
                                        NAryNode(11).apply {
                                            children = listOf(NAryNode(14))
                                        },
                                    )
                                },
                            )
                        },
                        NAryNode(4).apply {
                            children = listOf(
                                NAryNode(8).apply {
                                    children = listOf(NAryNode(12))
                                },
                            )
                        },
                        NAryNode(5).apply {
                            children = listOf(
                                NAryNode(9).apply {
                                    children = listOf(NAryNode(13))
                                },
                                NAryNode(10),
                            )
                        },
                    )
                },
                listOf(2, 6, 14, 11, 7, 3, 12, 8, 4, 13, 9, 10, 5, 1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun postorderTest(root: NAryNode?, expected: List<Int>) {
        val actual = strategy(root)
        assertThat(actual).isEqualTo(expected)
    }
}

class NAryTreePostorderRecursiveTest :
    NAryTreePostorderTest<NAryTreePostorderRecursive>(NAryTreePostorderRecursive())

class NAryTreePostorderIterativeTest :
    NAryTreePostorderTest<NAryTreePostorderIterative>(NAryTreePostorderIterative())

class NAryTreePostorderTwoStacksTest :
    NAryTreePostorderTest<NAryTreePostorderTwoStacks>(NAryTreePostorderTwoStacks())

class NAryTreePostorderNoReverseTest :
    NAryTreePostorderTest<NAryTreePostorderNoReverse>(NAryTreePostorderNoReverse())
