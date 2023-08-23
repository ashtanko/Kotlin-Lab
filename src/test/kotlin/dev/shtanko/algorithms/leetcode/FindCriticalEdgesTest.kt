/*
 * Copyright 2023 Oleksii Shtanko
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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FindCriticalEdgesTest<out T : FindCriticalEdges>(
    private val strategy: T,
) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1, 1),
                    intArrayOf(1, 2, 1),
                    intArrayOf(2, 3, 2),
                    intArrayOf(0, 3, 2),
                    intArrayOf(0, 4, 3),
                    intArrayOf(3, 4, 3),
                    intArrayOf(1, 4, 6),
                ),
                listOf(
                    listOf(0, 1),
                    listOf(2, 3, 4, 5),
                ),
            ),
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(0, 1, 1),
                    intArrayOf(1, 2, 1),
                    intArrayOf(2, 3, 1),
                    intArrayOf(0, 3, 1),
                ),
                listOf(
                    listOf(),
                    listOf(0, 1, 2, 3),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find critical and pseudo critical edges test`(n: Int, edges: Array<IntArray>, expected: List<List<Int>>) {
        val actual = strategy(n, edges)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class FindCriticalEdgesKruskalTest : FindCriticalEdgesTest<FindCriticalEdges>(FindCriticalEdgesKruskal())
