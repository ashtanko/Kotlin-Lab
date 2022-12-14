/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

internal class StringToIntegerTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                0,
            ),
            Arguments.of(
                " ",
                0,
            ),
            Arguments.of(
                " 1",
                1,
            ),
            Arguments.of(
                "1 ",
                1,
            ),
            Arguments.of(
                "0",
                0,
            ),
            Arguments.of(
                "4",
                4,
            ),
            Arguments.of(
                "-3",
                -3,
            ),
            Arguments.of(
                "435",
                435,
            ),
            Arguments.of(
                "${Int.MAX_VALUE}",
                Int.MAX_VALUE,
            ),
            Arguments.of(
                "${Int.MIN_VALUE}",
                Int.MIN_VALUE,
            ),
            Arguments.of(
                "A",
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `string to int test`(s: String, expected: Int) {
        val actual = s.atoi()
        assertEquals(expected, actual)
    }
}
