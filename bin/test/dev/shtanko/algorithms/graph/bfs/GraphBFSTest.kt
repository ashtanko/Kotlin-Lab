/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.algorithms.graph.bfs

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class GraphBFSTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                listOf(
                    0 to 1,
                    0 to 2,
                    1 to 2,
                    2 to 0,
                    2 to 3,
                    3 to 3,
                ),
                2,
                listOf(2, 0, 3, 1),
            ),
            Arguments.of(
                4,
                listOf(
                    0 to 1,
                    0 to 2,
                    1 to 2,
                    2 to 0,
                    2 to 3,
                    3 to 3,
                ),
                0,
                listOf(0, 1, 2, 3),
            ),
            Arguments.of(
                4,
                listOf(
                    0 to 1,
                    0 to 2,
                    1 to 2,
                    2 to 0,
                    2 to 3,
                    3 to 3,
                ),
                3,
                listOf(3),
            ),
            Arguments.of(
                6,
                listOf(
                    0 to 1,
                    1 to 2,
                    2 to 3,
                    3 to 1,
                    3 to 4,
                    4 to 5,
                    5 to 3,
                ),
                3,
                listOf(3, 1, 4, 2, 5),
            ),
            Arguments.of(
                10,
                listOf(
                    0 to 1,
                    0 to 3,
                    1 to 3,
                    3 to 2,
                    2 to 3,
                    2 to 4,
                    4 to 5,
                    4 to 6,
                    5 to 7,
                    5 to 6,
                    6 to 8,
                    6 to 9,
                    8 to 9,
                ),
                0,
                listOf(0, 1, 3, 2, 4, 5, 6, 7, 8, 9),
            ),
            Arguments.of(
                10,
                listOf(
                    0 to 1,
                    0 to 3,
                    1 to 3,
                    3 to 2,
                    2 to 3,
                    2 to 4,
                    4 to 5,
                    4 to 6,
                    5 to 7,
                    5 to 6,
                    6 to 8,
                    6 to 9,
                    8 to 9,
                ),
                3,
                listOf(3, 2, 4, 5, 6, 7, 8, 9),
            ),
            Arguments.of(
                10,
                listOf(
                    0 to 1,
                    0 to 3,
                    1 to 3,
                    3 to 2,
                    2 to 3,
                    2 to 4,
                    4 to 5,
                    4 to 6,
                    5 to 7,
                    5 to 6,
                    6 to 8,
                    6 to 9,
                    8 to 9,
                ),
                5,
                listOf(5, 7, 6, 8, 9),
            ),
            Arguments.of(
                10,
                listOf(
                    0 to 1,
                    0 to 3,
                    1 to 3,
                    3 to 2,
                    2 to 3,
                    2 to 4,
                    4 to 5,
                    4 to 6,
                    5 to 7,
                    5 to 6,
                    6 to 8,
                    6 to 9,
                    8 to 9,
                    9 to 8,
                    9 to 7,
                ),
                9,
                listOf(9, 8, 7),
            ),
            Arguments.of(
                13,
                listOf(
                    0 to 7,
                    0 to 9,
                    0 to 11,
                    1 to 8,
                    1 to 10,
                    2 to 3,
                    2 to 12,
                    3 to 2,
                    3 to 4,
                    4 to 3,
                    5 to 6,
                    6 to 7,
                    6 to 5,
                    7 to 0,
                    7 to 3,
                    7 to 6,
                    7 to 11,
                    8 to 1,
                    8 to 9,
                    8 to 12,
                    9 to 8,
                    9 to 10,
                    10 to 1,
                    10 to 9,
                    11 to 0,
                    11 to 7,
                    12 to 2,
                    12 to 8,
                ),
                0,
                listOf(0, 7, 9, 11, 3, 6, 8, 10, 2, 4, 5, 1, 12),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `BFS traversal test`(vertex: Int, edges: List<Pair<Int, Int>>, start: Int, expected: List<Int>) {
        val graph = GraphBFS(vertex)
        edges.forEach {
            val (v, w) = it
            graph.addEdge(v, w)
        }
        val actual = graph.bfs(start)
        assertThat(actual).isEqualTo(expected)
    }
}
