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

abstract class StrCopiesTest<out T : StrCopies>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "catcowcat",
                "cat",
                2,
                true,
            ),
            Arguments.of(
                "catcowcat",
                "cow",
                2,
                false,
            ),
            Arguments.of(
                "catcowcat",
                "cow",
                1,
                true,
            ),
            Arguments.of(
                "",
                "",
                0,
                true,
            ),
            Arguments.of(
                "iiijjj",
                "i",
                3,
                true,
            ),
            Arguments.of(
                "iiijjj",
                "i",
                4,
                false,
            ),
            Arguments.of(
                "iiijjj",
                "ii",
                2,
                true,
            ),
            Arguments.of(
                "iiijjj",
                "ii",
                3,
                false,
            ),
            Arguments.of(
                "iiijjj",
                "x",
                3,
                false,
            ),
            Arguments.of(
                "iiijjj",
                "x",
                0,
                true,
            ),
            Arguments.of(
                "iiiiij",
                "iii",
                3,
                true,
            ),
            Arguments.of(
                "iiiiij",
                "iii",
                4,
                false,
            ),
            Arguments.of(
                "ijiiiiij",
                "iiii",
                2,
                true,
            ),
            Arguments.of(
                "ijiiiiij",
                "iiii",
                3,
                false,
            ),
            Arguments.of(
                "dogcatdogcat",
                "dog",
                2,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `strCopies test`(str: String, sub: String, times: Int, expected: Boolean) {
        val actual = strategy(str, sub, times)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class StrCopiesIterativeTest : StrCopiesTest<StrCopies>(StrCopiesIterative())
class StrCopiesRecursiveTest : StrCopiesTest<StrCopies>(StrCopiesRecursive())
