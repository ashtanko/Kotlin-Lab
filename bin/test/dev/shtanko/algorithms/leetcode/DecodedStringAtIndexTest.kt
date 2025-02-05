/*
 * Copyright 2023 Oleksii Shtanko
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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class DecodedStringAtIndexTest<out T : DecodedStringAtIndex>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "leet2code3",
                10,
                "o",
            ),
            Arguments.of(
                "ha22",
                5,
                "h",
            ),
            Arguments.of(
                "a2345678999999999999999",
                1,
                "a",
            ),
            Arguments.of(
                "a2b3c4d5e6f7g8h9",
                10,
                "c",
            ),
            Arguments.of(
                "a2b3c4d5e6f7g8h9",
                11,
                "a",
            ),
            Arguments.of(
                "a2b3c4d5e6f7g8h9",
                12,
                "a",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `decode at index test`(str: String, k: Int, expected: String) {
        val actual = strategy(str, k)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class DecodedStringAtIndexSolutionTest : DecodedStringAtIndexTest<DecodedStringAtIndex>(DecodedStringAtIndexSolution())
