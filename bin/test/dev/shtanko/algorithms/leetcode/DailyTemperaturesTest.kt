/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class DailyTemperaturesTest<out T : DailyTemperatures>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(73, 74, 75, 71, 69, 72, 76, 73),
                intArrayOf(1, 1, 4, 2, 1, 1, 0, 0),
            ),
            Arguments.of(
                intArrayOf(30, 40, 50, 60),
                intArrayOf(1, 1, 1, 0),
            ),
            Arguments.of(
                intArrayOf(30, 60, 90),
                intArrayOf(1, 1, 0),
            ),
            Arguments.of(
                intArrayOf(1),
                intArrayOf(0),
            ),
            Arguments.of(
                intArrayOf(30, 60),
                intArrayOf(1, 0),
            ),
            Arguments.of(
                intArrayOf(30, 60, 30),
                intArrayOf(1, 0, 0),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `daily temperatures test`(temperatures: IntArray, expected: IntArray) {
        val actual = strategy.invoke(temperatures)
        assertThat(actual).isEqualTo(expected)
    }
}

class DailyTemperaturesDequeTest : DailyTemperaturesTest<DailyTemperatures>(DailyTemperaturesDeque())
class DailyTemperaturesStackTest : DailyTemperaturesTest<DailyTemperatures>(DailyTemperaturesStack())
class DailyTemperaturesArrTest : DailyTemperaturesTest<DailyTemperatures>(DailyTemperaturesArr())
