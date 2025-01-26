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

abstract class PowerfulIntegersTest<out T : PowerfulIntegers>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2,
                3,
                10,
                listOf(2, 3, 4, 5, 7, 9, 10),
            ),
            Arguments.of(
                3,
                5,
                15,
                listOf(2, 4, 6, 8, 10, 14),
            ),
            Arguments.of(
                0,
                0,
                0,
                emptyList<Int>(),
            ),
            Arguments.of(
                1,
                0,
                0,
                emptyList<Int>(),
            ),
            Arguments.of(
                1,
                1,
                0,
                emptyList<Int>(),
            ),
            Arguments.of(
                1,
                1,
                1,
                emptyList<Int>(),
            ),
            Arguments.of(
                34,
                100,
                10,
                listOf(2),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max product test`(x: Int, y: Int, bound: Int, expected: List<Int>) {
        val actual = strategy.invoke(x, y, bound)
        assertThat(actual).isEqualTo(expected)
    }
}

class LogarithmicBoundsTest : PowerfulIntegersTest<LogarithmicBounds>(LogarithmicBounds())
