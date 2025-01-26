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

abstract class CountHiTest<out T : CountHi>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "xxhixx",
                1,
            ),
            Arguments.of(
                "xhixhix",
                2,
            ),
            Arguments.of(
                "hi",
                1,
            ),
            Arguments.of(
                "0",
                0,
            ),
            Arguments.of(
                "h",
                0,
            ),
            Arguments.of(
                "",
                0,
            ),
            Arguments.of(
                "xhixhxihihhhih",
                3,
            ),
            Arguments.of(
                "hiAAhi12hi",
                3,
            ),
            Arguments.of(
                "ship",
                1,
            ),
            Arguments.of(
                "ihihihihihi",
                5,
            ),
            Arguments.of(
                "ihihihihih",
                4,
            ),
            Arguments.of(
                "Hi",
                1,
            ),
            Arguments.of(
                "HI",
                1,
            ),
            Arguments.of(
                "hi",
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count hi test`(str: String, expected: Int) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class CountHiIterativeTest : CountHiTest<CountHi>(CountHiIterative())
class CountHiRecursiveTest : CountHiTest<CountHi>(CountHiRecursive())
class CountHiRecursiveSimplifiedTest : CountHiTest<CountHi>(CountHiRecursiveSimplified())
class CountHiMemoTest : CountHiTest<CountHi>(CountHiMemo())
