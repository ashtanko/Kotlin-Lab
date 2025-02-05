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

abstract class SpiralMatrix3Test<out T : SpiralMatrix3>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                4,
                0,
                0,
                arrayOf(
                    intArrayOf(0, 0),
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(0, 3),
                ),
            ),
            Arguments.of(
                5,
                6,
                1,
                4,
                arrayOf(
                    intArrayOf(1, 4), intArrayOf(1, 5), intArrayOf(2, 5), intArrayOf(2, 4),
                    intArrayOf(2, 3), intArrayOf(1, 3), intArrayOf(0, 3), intArrayOf(0, 4),
                    intArrayOf(0, 5), intArrayOf(3, 5), intArrayOf(3, 4), intArrayOf(3, 3),
                    intArrayOf(3, 2), intArrayOf(2, 2), intArrayOf(1, 2), intArrayOf(0, 2),
                    intArrayOf(4, 5), intArrayOf(4, 4), intArrayOf(4, 3), intArrayOf(4, 2),
                    intArrayOf(4, 1), intArrayOf(3, 1), intArrayOf(2, 1), intArrayOf(1, 1),
                    intArrayOf(0, 1), intArrayOf(4, 0), intArrayOf(3, 0), intArrayOf(2, 0),
                    intArrayOf(1, 0), intArrayOf(0, 0),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun spiralMatrixIIITest(rows: Int, cols: Int, rStart: Int, cStart: Int, expected: Array<IntArray>) {
        val actual = strategy(rows, cols, rStart, cStart)
        assertThat(actual).isEqualTo(expected)
    }
}

class SpiralMatrix3SimulationTest : SpiralMatrix3Test<SpiralMatrix3Simulation>(SpiralMatrix3Simulation())
