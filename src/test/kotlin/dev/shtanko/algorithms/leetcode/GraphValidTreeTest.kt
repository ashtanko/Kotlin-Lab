/*
 * Copyright 2021 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

internal abstract class GraphValidTreeTest<out T : GraphValidTree>(private val strategy: T) {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(0, 3),
                    intArrayOf(1, 4),
                ),
                true,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(1, 3),
                    intArrayOf(1, 4),
                ),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `graph valid tree test`(n: Int, edges: Array<IntArray>, expected: Boolean) {
        val actual = strategy.validTree(n, edges)
        assertThat(actual).isEqualTo(expected)
    }
}

internal class GVTSimpleIterativeDFSTest : GraphValidTreeTest<GVTSimpleIterativeDFS>(GVTSimpleIterativeDFS())
internal class GVTAdvancedIterativeDFSTest : GraphValidTreeTest<GVTAdvancedIterativeDFS>(GVTAdvancedIterativeDFS())
internal class GVTAdvancedUnionFindTest : GraphValidTreeTest<GVTAdvancedUnionFind>(GVTAdvancedUnionFind())
