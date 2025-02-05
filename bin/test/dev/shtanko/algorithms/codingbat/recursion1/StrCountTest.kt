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

abstract class StrCountTest<out T : StrCount>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "catcowcat",
                "cat",
                2,
            ),
            Arguments.of(
                "catcowcat",
                "cow",
                1,
            ),
            Arguments.of(
                "catcowcat",
                "dog",
                0,
            ),
            Arguments.of(
                "",
                "dog",
                0,
            ),
            Arguments.of(
                "",
                "",
                0,
            ),
            Arguments.of(
                "abc",
                "dog",
                0,
            ),
            Arguments.of(
                "cacatcowcat",
                "cat",
                2,
            ),
            Arguments.of(
                "xyx",
                "x",
                2,
            ),
            Arguments.of(
                "iiiijj",
                "i",
                4,
            ),
            Arguments.of(
                "iiiijj",
                "ii",
                2,
            ),
            Arguments.of(
                "iiiijj",
                "iii",
                1,
            ),
            Arguments.of(
                "iiiijj",
                "j",
                2,
            ),
            Arguments.of(
                "iiiijj",
                "jj",
                1,
            ),
            Arguments.of(
                "aaabababab",
                "ab",
                4,
            ),
            Arguments.of(
                "aaabababab",
                "aa",
                1,
            ),
            Arguments.of(
                "aaabababab",
                "a",
                6,
            ),
            Arguments.of(
                "aaabababab",
                "b",
                4,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `strCount test`(str: String, sub: String, expected: Int) {
        val actual = strategy(str, sub)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class StrCountIterativeTest : StrCountTest<StrCount>(StrCountIterative())
class StrCountRecursiveTest : StrCountTest<StrCount>(StrCountRecursive())
