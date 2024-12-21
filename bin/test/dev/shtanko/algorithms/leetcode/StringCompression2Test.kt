/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class StringCompression2Test<out T : StringCompression2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "aaabcccd",
                2,
                4,
            ),
            Arguments.of(
                "aabbaa",
                2,
                2,
            ),
            Arguments.of(
                "aaaaaaaaaaa",
                0,
                3,
            ),
            Arguments.of(
                "",
                0,
                0,
            ),
            Arguments.of(
                "a",
                0,
                1,
            ),
            Arguments.of(
                "ab",
                0,
                2,
            ),
            Arguments.of(
                "ab",
                1,
                1,
            ),
            Arguments.of(
                "ab",
                2,
                0,
            ),
            Arguments.of(
                "ab",
                3,
                2,
            ),
            Arguments.of(
                "ab",
                4,
                2,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `get length of optimal compression test`(str: String, k: Int, expected: Int) {
        val actual = strategy.invoke(str, k)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class StringCompression2DPTest : StringCompression2Test<StringCompression2>(StringCompression2DP())
