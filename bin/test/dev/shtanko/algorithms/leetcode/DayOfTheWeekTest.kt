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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class DayOfTheWeekTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                Triple(28, 2, 2019),
                "Thursday",
            ),
            Arguments.of(
                Triple(31, 8, 2019),
                "Saturday",
            ),
            Arguments.of(
                Triple(18, 7, 1999),
                "Sunday",
            ),
            Arguments.of(
                Triple(15, 8, 1993),
                "Sunday",
            ),
            Arguments.of(
                Triple(23, 4, 1994),
                "Saturday",
            ),
            Arguments.of(
                Triple(27, 10, 1999),
                "Wednesday",
            ),
            Arguments.of(
                Triple(31, 12, 2000),
                "Sunday",
            ),
            Arguments.of(
                Triple(1, 1, 2001),
                "Monday",
            ),
            Arguments.of(
                Triple(1, 1, 1971),
                "Friday",
            ),
            Arguments.of(
                Triple(1, 1, 1972),
                "Saturday",
            ),
            Arguments.of(
                Triple(1, 1, 1973),
                "Monday",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `day of the week test`(date: Triple<Int, Int, Int>, expected: String) {
        val actual = date.dayOfTheWeek()
        assertEquals(expected, actual)
    }
}
