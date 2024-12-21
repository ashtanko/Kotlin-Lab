/*
 * Copyright 2020 Oleksii Shtanko
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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class BuddyStringsTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "ab",
                "ba",
                true,
            ),
            Arguments.of(
                "ab",
                "ab",
                false,
            ),
            Arguments.of(
                "aa",
                "aa",
                true,
            ),
            Arguments.of(
                "aaaaaaabc",
                "aaaaaaacb",
                true,
            ),
            Arguments.of(
                "",
                "aa",
                false,
            ),
            Arguments.of(
                "",
                "",
                false,
            ),
            Arguments.of(
                "a",
                "a",
                false,
            ),
            Arguments.of(
                "a",
                "a",
                false,
            ),
            Arguments.of(
                "",
                "a",
                false,
            ),
            Arguments.of(
                "ab",
                "ac",
                false,
            ),
            Arguments.of(
                "ab",
                "ca",
                false,
            ),
            Arguments.of(
                "ab",
                "cd",
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `buddy strings test`(s1: String, s2: String, expected: Boolean) {
        val actual = (s1 to s2).buddyStrings()
        assertThat(actual).isEqualTo(expected)
    }
}
