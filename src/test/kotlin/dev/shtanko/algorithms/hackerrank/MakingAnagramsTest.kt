/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.algorithms.hackerrank

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class MakingAnagramsTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                "",
                0,
            ),
            Arguments.of(
                "a",
                "",
                1,
            ),
            Arguments.of(
                "",
                "b",
                1,
            ),
            Arguments.of(
                "a",
                "a",
                0,
            ),
            Arguments.of(
                "cd",
                "dc",
                0,
            ),
            Arguments.of(
                "rat",
                "tars",
                1,
            ),
            Arguments.of(
                "cde",
                "abc",
                4,
            ),
            Arguments.of(
                "ab",
                "cd",
                4,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `making anagrams test`(a: String, b: String, expected: Int) {
        val actual = MakingAnagrams(a, b)
        assertThat(actual).isEqualTo(expected)
    }
}
