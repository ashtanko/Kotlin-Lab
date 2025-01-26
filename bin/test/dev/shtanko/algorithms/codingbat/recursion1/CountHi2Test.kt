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

abstract class CountHi2Test<out T : CountHi2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "ahixhi",
                1,
            ),
            Arguments.of(
                "ahibhi",
                2,
            ),
            Arguments.of(
                "xhixhi",
                0,
            ),
            Arguments.of(
                "",
                0,
            ),
            Arguments.of(
                "hixhi",
                1,
            ),
            Arguments.of(
                "hixhhi",
                2,
            ),
            Arguments.of(
                "hihihi",
                3,
            ),
            Arguments.of(
                "hihihix",
                3,
            ),
            Arguments.of(
                "xhihihix",
                2,
            ),
            Arguments.of(
                "xxhi",
                0,
            ),
            Arguments.of(
                "hixxhi",
                1,
            ),
            Arguments.of(
                "xxxx",
                0,
            ),
            Arguments.of(
                "h",
                0,
            ),
            Arguments.of(
                "x",
                0,
            ),
            Arguments.of(
                "Hellohi",
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count hi 2 test`(str: String, expected: Int) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class CountHi2IterativeTest : CountHi2Test<CountHi2>(CountHi2Iterative())
class CountHi2RecursiveTest : CountHi2Test<CountHi2>(CountHi2Recursive())
