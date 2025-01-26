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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class PacificAtlanticWaterFlowTest<out T : PacificAtlanticWaterFlow>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 2, 3, 5),
                    intArrayOf(3, 2, 3, 4, 4),
                    intArrayOf(2, 4, 5, 3, 1),
                    intArrayOf(6, 7, 1, 4, 5),
                    intArrayOf(5, 1, 1, 2, 4),
                ),
                listOf(
                    listOf(0, 4),
                    listOf(1, 3),
                    listOf(1, 4),
                    listOf(2, 2),
                    listOf(3, 0),
                    listOf(3, 1),
                    listOf(4, 0),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `pacific atlantic test`(matrix: Array<IntArray>, expected: List<List<Int>>) {
        val actual = strategy.invoke(matrix)
        assertThat(actual).isEqualTo(expected)
    }
}

class PacificAtlanticBFSTest : PacificAtlanticWaterFlowTest<PacificAtlanticBFS>(PacificAtlanticBFS())
class PacificAtlanticDFSTest : PacificAtlanticWaterFlowTest<PacificAtlanticDFS>(PacificAtlanticDFS())
