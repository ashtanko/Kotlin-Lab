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

abstract class CarPoolingTest<out T : CarPooling>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 1, 5),
                    intArrayOf(3, 3, 7),
                ),
                4,
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 1, 5),
                    intArrayOf(3, 3, 7),
                ),
                5,
                true,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(),
                ),
                0,
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 1, 5),
                    intArrayOf(3, 5, 7),
                ),
                3,
                true,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2, 7),
                    intArrayOf(3, 7, 9),
                    intArrayOf(8, 3, 9),
                ),
                11,
                true,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2, 7),
                    intArrayOf(3, 7, 9),
                    intArrayOf(8, 3, 9),
                ),
                12,
                true,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2, 7),
                    intArrayOf(3, 7, 9),
                    intArrayOf(8, 3, 9),
                ),
                10,
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2, 7),
                    intArrayOf(3, 7, 9),
                    intArrayOf(8, 3, 9),
                ),
                9,
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2, 7),
                    intArrayOf(3, 7, 9),
                    intArrayOf(8, 3, 9),
                ),
                8,
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2, 7),
                    intArrayOf(3, 7, 9),
                    intArrayOf(8, 3, 9),
                ),
                7,
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2, 7),
                    intArrayOf(3, 7, 9),
                    intArrayOf(8, 3, 9),
                ),
                6,
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `car pooling test`(trips: Array<IntArray>, capacity: Int, expected: Boolean) {
        val actual = strategy.invoke(trips, capacity)
        assertThat(actual).isEqualTo(expected)
    }
}

class ThousandOneStopsTest : CarPoolingTest<CarPooling>(ThousandOneStops())
class CarPoolingMeetingRoomTest : CarPoolingTest<CarPooling>(CarPoolingMeetingRoom())
class CarPoolingIntervalTest : CarPoolingTest<CarPooling>(CarPoolingInterval())
class CarPoolingStreamTest : CarPoolingTest<CarPooling>(CarPoolingStream())
