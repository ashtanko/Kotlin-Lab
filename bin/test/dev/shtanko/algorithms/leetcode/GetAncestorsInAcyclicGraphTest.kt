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

abstract class GetAncestorsInAcyclicGraphTest<out T : GetAncestorsInAcyclicGraph>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                8,
                arrayOf(
                    intArrayOf(0, 3),
                    intArrayOf(0, 4),
                    intArrayOf(1, 3),
                    intArrayOf(2, 4),
                    intArrayOf(2, 7),
                    intArrayOf(3, 5),
                    intArrayOf(3, 6),
                    intArrayOf(3, 7),
                    intArrayOf(4, 6),
                ),
                listOf(
                    listOf(),
                    listOf(),
                    listOf(),
                    listOf(0, 1),
                    listOf(0, 2),
                    listOf(0, 1, 3),
                    listOf(0, 1, 2, 3, 4),
                    listOf(0, 1, 2, 3),
                ),
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(0, 3),
                    intArrayOf(0, 4),
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 4),
                    intArrayOf(2, 3),
                    intArrayOf(2, 4),
                    intArrayOf(3, 4),
                ),
                listOf(
                    listOf(),
                    listOf(0),
                    listOf(0, 1),
                    listOf(0, 1, 2),
                    listOf(0, 1, 2, 3),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun getAncestorsTest(n: Int, edges: Array<IntArray>, expected: List<List<Int>>) {
        val actual = strategy(n, edges)
        assertThat(actual).isEqualTo(expected)
    }
}

class GetAncestorsInAcyclicGraphDFSIterativeTest : GetAncestorsInAcyclicGraphTest<GetAncestorsInAcyclicGraph>(
    GetAncestorsInAcyclicGraphDFS(),
)

class GetAncestorsInAcyclicGraphDFSOptimizedTest : GetAncestorsInAcyclicGraphTest<GetAncestorsInAcyclicGraph>(
    GetAncestorsInAcyclicGraphDFSOpt(),
)

class GetAncestorsInAcyclicGraphSortTest : GetAncestorsInAcyclicGraphTest<GetAncestorsInAcyclicGraph>(
    GetAncestorsInAcyclicGraphSort(),
)
