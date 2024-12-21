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
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class CorporateFlightBookingsTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 10),
                    intArrayOf(2, 3, 20),
                    intArrayOf(2, 5, 25),
                ),
                5,
                intArrayOf(10, 55, 45, 25, 25),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 10),
                    intArrayOf(2, 2, 15),
                ),
                2,
                intArrayOf(10, 25),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 10),
                    intArrayOf(2, 2, 15),
                    intArrayOf(2, 3, 20),
                    intArrayOf(3, 3, 25),
                ),
                3,
                intArrayOf(10, 45, 45),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 10),
                    intArrayOf(2, 2, 15),
                    intArrayOf(2, 3, 20),
                    intArrayOf(3, 3, 25),
                    intArrayOf(3, 4, 30),
                    intArrayOf(4, 4, 35),
                ),
                4,
                intArrayOf(10, 45, 75, 65),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `corporate flight bookings test`(bookings: Array<IntArray>, num: Int, expected: IntArray) {
        val actual = corpFlightBookings(bookings, num)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}
