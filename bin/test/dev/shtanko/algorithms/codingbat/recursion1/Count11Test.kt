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

abstract class Count11Test<out T : Count11>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "11abc11",
                2,
            ),
            Arguments.of(
                "abc11x11x11",
                3,
            ),
            Arguments.of(
                "111",
                1,
            ),
            Arguments.of(
                "",
                0,
            ),
            Arguments.of(
                "1",
                0,
            ),
            Arguments.of(
                "11",
                1,
            ),
            Arguments.of(
                "hi",
                0,
            ),
            Arguments.of(
                "11x111x1111",
                4,
            ),
            Arguments.of(
                "1x111",
                1,
            ),
            Arguments.of(
                "1Hello1",
                0,
            ),
            Arguments.of(
                "Hello",
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count 11 test`(str: String, expected: Int) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class Count11IterativeTest : Count11Test<Count11>(Count11Iterative())
class Count11RecursiveTest : Count11Test<Count11>(Count11Recursive())
