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

class IntegerToEnglishWordsTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                "Zero",
            ),
            Arguments.of(
                1,
                "One",
            ),
            Arguments.of(
                22,
                "Twenty Two",
            ),
            Arguments.of(
                123,
                "One Hundred Twenty Three",
            ),
            Arguments.of(
                444,
                "Four Hundred Forty Four",
            ),
            Arguments.of(
                12_345,
                "Twelve Thousand Three Hundred Forty Five",
            ),
            Arguments.of(
                1_234_567,
                "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven",
            ),
            Arguments.of(
                1_234_567_891,
                "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `integer to English words test`(num: Int, expected: String) {
        val actual = IntegerToEnglishWords().numberToWords(num)
        assertEquals(expected, actual)
    }
}
