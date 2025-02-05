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

abstract class StrDistTest<out T : StrDist>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "catcowcat",
                "cat",
                9,
            ),
            Arguments.of(
                "catcowcat",
                "cow",
                3,
            ),
            Arguments.of(
                "cccatcowcatxx",
                "cat",
                9,
            ),
            Arguments.of(
                "abccatcowcatcatxyz",
                "cat",
                12,
            ),
            Arguments.of(
                "xyx",
                "x",
                3,
            ),
            Arguments.of(
                "xyx",
                "y",
                1,
            ),
            Arguments.of(
                "xyx",
                "z",
                0,
            ),
            Arguments.of(
                "x",
                "x",
                1,
            ),
            Arguments.of(
                "x",
                "y",
                0,
            ),
            Arguments.of(
                "",
                "y",
                0,
            ),
            Arguments.of(
                "",
                "",
                0,
            ),
            Arguments.of(
                "hiHellohihihi",
                "hi",
                13,
            ),
            Arguments.of(
                "hiHellohihihi",
                "hih",
                5,
            ),
            Arguments.of(
                "hiHellohihihi",
                "o",
                1,
            ),
            Arguments.of(
                "hiHellohihihi",
                "ll",
                2,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `strDist test`(str: String, sub: String, expected: Int) {
        val actual = strategy(str, sub)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class StrDistRecursiveTest : StrDistTest<StrDist>(StrDistRecursive())
