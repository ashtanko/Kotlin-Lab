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

class DivisorGameTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2,
                true,
            ),
            Arguments.of(
                3,
                false,
            ),
            Arguments.of(
                48,
                true,
            ),
            Arguments.of(
                1000,
                true,
            ),
            Arguments.of(
                1001,
                false,
            ),
            Arguments.of(
                1002,
                true,
            ),
            Arguments.of(
                1003,
                false,
            ),
            Arguments.of(
                1004,
                true,
            ),
            Arguments.of(
                1005,
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `divisor game test`(num: Int, expected: Boolean) {
        val actual = num.divisorGame()
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `divisor game naive test`(num: Int, expected: Boolean) {
        val actual = num.divisorGameNaive()
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `divisor game brute force test`(num: Int, expected: Boolean) {
        val actual = num.divisorGameBruteForce()
        assertEquals(expected, actual)
    }
}
