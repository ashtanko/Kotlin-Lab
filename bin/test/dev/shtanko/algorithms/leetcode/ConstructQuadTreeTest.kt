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

abstract class ConstructQuadTreeTest<out T : ConstructQuadTree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 0),
                ),
                QuadTreeNode(
                    isLeaf = false,
                    value = false,
                ).apply {
                    topLeft = QuadTreeNode(
                        isLeaf = true,
                        value = false,
                    )
                    topRight = QuadTreeNode(
                        isLeaf = true,
                        value = true,
                    )
                    bottomLeft = QuadTreeNode(
                        isLeaf = false,
                        value = true,
                    )
                    bottomRight = QuadTreeNode(
                        isLeaf = true,
                        value = false,
                    )
                },
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1),
                    intArrayOf(1, 1),
                ),
                QuadTreeNode(
                    isLeaf = true,
                    value = true,
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0),
                ),
                QuadTreeNode(
                    isLeaf = true,
                    value = false,
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1),
                ),
                QuadTreeNode(
                    isLeaf = true,
                    value = true,
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `construct test`(grid: Array<IntArray>, expected: QuadTreeNode) {
        val actual = strategy.construct(grid)
        assertThat(actual?.value).isEqualTo(expected.value)
        assertThat(actual?.isLeaf).isEqualTo(expected.isLeaf)
    }
}

class ConstructQuadTreeRecursiveTest : ConstructQuadTreeTest<ConstructQuadTree>(ConstructQuadTreeRecursive())
