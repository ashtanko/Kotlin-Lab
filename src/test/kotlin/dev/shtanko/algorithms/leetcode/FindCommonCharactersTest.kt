/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.extensions.randomString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

private const val RANDOM_STRING_LENGTH = 6
private const val RANDOM_ARRAY_SIZE = 100_000

internal class FindCommonCharactersTest {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(arrayOf<String>(), listOf<String>()),
            Arguments.of(arrayOf("bella", "label", "roller"), listOf("e", "l", "l")),
            Arguments.of(arrayOf("cool", "lock", "cook"), listOf("c", "o")),
            Arguments.of(arrayOf("a", "b", "c"), listOf<String>()),
            Arguments.of(arrayOf("far", "bar", "rar"), listOf("a", "r")),
            Arguments.of(
                Array(RANDOM_ARRAY_SIZE) { ('a'..'z').randomString(RANDOM_STRING_LENGTH) + "q" },
                listOf("q")
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `name test`(arr: Array<String>, expected: List<String>) {
        val actual = arr.commonChars()
        assertThat(actual).isEqualTo(expected)
    }
}
