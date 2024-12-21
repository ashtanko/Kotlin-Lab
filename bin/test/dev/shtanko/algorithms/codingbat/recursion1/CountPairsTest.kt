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

package dev.shtanko.algorithms.codingbat.recursion1

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal abstract class CountPairsTest<out T : CountPairs>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "axa",
                1,
            ),
            Arguments.of(
                "axax",
                2,
            ),
            Arguments.of(
                "axbx",
                1,
            ),
            Arguments.of(
                "",
                0,
            ),
            Arguments.of(
                "hi",
                0,
            ),
            Arguments.of(
                "hihih",
                3,
            ),
            Arguments.of(
                "hihih3hi",
                4,
            ),
            Arguments.of(
                "hah",
                1,
            ),
            Arguments.of(
                "hahi",
                1,
            ),
            Arguments.of(
                "ihihhh",
                3,
            ),
            Arguments.of(
                "ihjxhh",
                0,
            ),
            Arguments.of(
                "a",
                0,
            ),
            Arguments.of(
                "aa",
                0,
            ),
            Arguments.of(
                "qqq",
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count pairs test`(str: String, expected: Int) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

internal class CountPairsIterativeTest : CountPairsTest<CountPairs>(CountPairsIterative())
internal class CountPairsRecursiveTest : CountPairsTest<CountPairs>(CountPairsRecursive())
