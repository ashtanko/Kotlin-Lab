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

abstract class DigitCountTest<out T : DigitCount>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "1210",
                true,
            ),
            Arguments.of(
                "030",
                false,
            ),
            Arguments.of(
                "",
                true,
            ),
            Arguments.of(
                "1",
                false,
            ),
            Arguments.of(
                "12",
                false,
            ),
            Arguments.of(
                "11",
                false,
            ),
            Arguments.of(
                "10",
                false,
            ),
            Arguments.of(
                "101",
                false,
            ),
            Arguments.of(
                "1010",
                false,
            ),
            Arguments.of(
                "10101",
                false,
            ),
            Arguments.of(
                "101010",
                false,
            ),
            Arguments.of(
                "1010101",
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `digit count test`(num: String, expected: Boolean) {
        val actual = strategy.invoke(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class DigitCountFreqArrayTest : DigitCountTest<DigitCount>(DigitCountFreqArray())
