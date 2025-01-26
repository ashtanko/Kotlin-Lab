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

internal abstract class Count8Test<out T : Count8>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                8,
                1,
            ),
            Arguments.of(
                818,
                2,
            ),
            Arguments.of(
                8818,
                4,
            ),
            Arguments.of(
                7,
                0,
            ),
            Arguments.of(
                888188,
                8,
            ),
            Arguments.of(
                88888,
                9,
            ),
            Arguments.of(
                8088,
                4,
            ),
            Arguments.of(
                81238,
                2,
            ),
            Arguments.of(
                88788,
                6,
            ),
            Arguments.of(
                1818188,
                5,
            ),
            Arguments.of(
                8818181,
                5,
            ),
            Arguments.of(
                78,
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count8 test`(num: Int, expected: Int) {
        val actual = strategy(num)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

internal class Count8IterativeTest : Count8Test<Count8>(Count8Iterative())
internal class Count8RecursiveTest : Count8Test<Count8>(Count8Recursive())
