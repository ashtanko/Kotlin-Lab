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

abstract class SteppingNumbersTest<out T : SteppingNumbers>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                0,
                listOf(0),
            ),
            Arguments.of(
                0,
                21,
                listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21),
            ),
            Arguments.of(
                10,
                15,
                listOf(10, 12),
            ),
            Arguments.of(
                0,
                100,
                listOf(
                    0,
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10,
                    12,
                    21,
                    23,
                    32,
                    34,
                    43,
                    45,
                    54,
                    56,
                    65,
                    67,
                    76,
                    78,
                    87,
                    89,
                    98,
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `stepping numbers test`(low: Int, high: Int, expected: List<Int>) {
        val actual = strategy.invoke(low, high)
        assertThat(actual).isEqualTo(expected)
    }
}

class SteppingNumbersBFSTest : SteppingNumbersTest<SteppingNumbersBFS>(SteppingNumbersBFS())
