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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class RunningSum1DTest<out T : RunningSum>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(1, 3, 6, 10),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1, 1),
                intArrayOf(1, 2, 3, 4, 5),
            ),
            Arguments.of(
                intArrayOf(3, 1, 2, 10, 1),
                intArrayOf(3, 4, 6, 16, 17),
            ),
            Arguments.of(
                intArrayOf(-3, 1, -2, 10, -1),
                intArrayOf(-3, -2, -4, 6, 5),
            ),
            Arguments.of(
                intArrayOf(1),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
            ),
            Arguments.of(
                intArrayOf(-1, -2),
                intArrayOf(-1, -3),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `running sum naive test`(arr: IntArray, expected: IntArray) {
        val actual = strategy.invoke(arr)
        assertThat(actual).isEqualTo(expected)
        assertArrayEquals(expected, actual)
    }
}

class RunningSumNaiveTest : RunningSum1DTest<RunningSum>(RunningSumNaive())
