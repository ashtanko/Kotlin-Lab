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

package dev.shtanko.algorithms.graph

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class AdjacencyMapTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    0 to 1,
                    0 to 4,
                    1 to 0,
                    1 to 2,
                    1 to 3,
                    1 to 4,
                    2 to 1,
                    2 to 3,
                    3 to 1,
                    3 to 2,
                    3 to 4,
                    4 to 0,
                    4 to 1,
                    4 to 3,
                ),
                listOf(
                    listOf(1, 4),
                    listOf(0, 2, 3, 4),
                    listOf(1, 3),
                    listOf(1, 2, 4),
                    listOf(0, 1, 3),
                ),
                """
                 0 -> [1, 4]
                 1 -> [0, 2, 3, 4]
                 2 -> [1, 3]
                 3 -> [1, 2, 4]
                 4 -> [0, 1, 3]

                """.trimIndent(),
            ),
        )
    }

    private class InputStringArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    "A" to "B",
                    "A" to "D",
                    "A" to "C",
                    "B" to "E",
                    "B" to "A",
                    "C" to "A",
                    "C" to "F",
                    "C" to "G",
                    "D" to "A",
                    "E" to "B",
                    "E" to "H",
                    "E" to "F",
                    "F" to "E",
                    "F" to "C",
                    "F" to "G",
                ),
                listOf(
                    listOf("B", "C", "D"),
                    listOf("A", "E"),
                    listOf("A", "F", "G"),
                    listOf("A"),
                    listOf("B", "F", "H"),
                    listOf("C", "E", "G"),
                    listOf("C", "F"),
                    listOf("E"),
                ),
                """
                 A -> [B, C, D]
                 B -> [A, E]
                 C -> [A, F, G]
                 D -> [A]
                 E -> [B, F, H]
                 F -> [C, E, G]
                 G -> [C, F]
                 H -> [E]

                """.trimIndent(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `create adjacency map of int test`(
        pairs: List<Pair<Int, Int>>,
        expectedList: List<List<Int>>,
        expectedString: String,
    ) {
        assertGraph(pairs, expectedList, expectedString)
    }

    @ParameterizedTest
    @ArgumentsSource(InputStringArgumentsProvider::class)
    fun `create adjacency map of string test`(
        pairs: List<Pair<String, String>>,
        expectedList: List<List<String>>,
        expectedString: String,
    ) {
        assertGraph(pairs, expectedList, expectedString)
    }

    private fun <T> assertGraph(
        pairs: List<Pair<T, T>>,
        expectedList: List<List<T>>,
        expectedString: String,
    ) {
        val graph = AdjacencyMap<T>()
        pairs.forEach {
            val (sourceVertex, destinationVertex) = it
            graph.addEdge(sourceVertex, destinationVertex)
        }
        assertThat(graph.toString()).isEqualTo(expectedString)
        assertThat(graph.toList()).isEqualTo(expectedList)
    }
}
