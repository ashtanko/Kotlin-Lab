/*
 * Copyright 2024 Oleksii Shtanko
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

abstract class RemoveKDigitsTest<out T : RemoveKDigits>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "1432219",
                3,
                "1219",
            ),
            Arguments.of(
                "10200",
                1,
                "200",
            ),
            Arguments.of(
                "10",
                2,
                "0",
            ),
            Arguments.of(
                "9",
                1,
                "0",
            ),
            Arguments.of(
                "112",
                1,
                "11",
            ),
            Arguments.of(
                "1234567890",
                9,
                "0",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun removeKDigitsSolutionsTest(num: String, k: Int, expected: String) {
        val actual = strategy(num, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class RemoveKDigitsStackTest : RemoveKDigitsTest<RemoveKDigitsStack>(RemoveKDigitsStack())
