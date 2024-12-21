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

abstract class ChangePiTest<out T : ChangePi>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "xpix",
                "x3.14x",
            ),
            Arguments.of(
                "pipi",
                "3.143.14",
            ),
            Arguments.of(
                "pip",
                "3.14p",
            ),
            Arguments.of(
                "p",
                "p",
            ),
            Arguments.of(
                "",
                "",
            ),
            Arguments.of(
                "x",
                "x",
            ),
            Arguments.of(
                "pixx",
                "3.14xx",
            ),
            Arguments.of(
                "pi",
                "3.14",
            ),
            Arguments.of(
                "pI",
                "3.14",
            ),
            Arguments.of(
                "Pi",
                "3.14",
            ),
            Arguments.of(
                "PI",
                "3.14",
            ),
            Arguments.of(
                "Pixx",
                "3.14xx",
            ),
            Arguments.of(
                "pIxx",
                "3.14xx",
            ),
            Arguments.of(
                "PIxx",
                "3.14xx",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `change pi test`(str: String, expected: String) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ChangePiIterativeTest : ChangePiTest<ChangePi>(ChangePiIterative())
class ChangePiRecursiveTest : ChangePiTest<ChangePi>(ChangePiRecursive())
