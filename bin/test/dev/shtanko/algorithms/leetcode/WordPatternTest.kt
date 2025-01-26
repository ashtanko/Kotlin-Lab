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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class WordPatternTest<out T : WordPattern>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "abba",
                "dog cat cat dog",
                true,
            ),
            Arguments.of(
                "abba",
                "dog cat cat fish",
                false,
            ),
            Arguments.of(
                "aaaa",
                "dog cat cat dog",
                false,
            ),
            Arguments.of(
                "abba",
                "dog dog dog dog",
                false,
            ),
            Arguments.of(
                "ab",
                "dog dog",
                false,
            ),
            Arguments.of(
                "",
                "",
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `word pattern test`(pattern: String, s: String, expected: Boolean) {
        val actual = strategy.invoke(pattern, s)
        assertThat(actual).isEqualTo(expected)
    }
}

class WordPatternSimpleTest : WordPatternTest<WordPattern>(WordPatternSimple())
