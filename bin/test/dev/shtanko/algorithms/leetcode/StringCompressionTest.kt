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

abstract class StringCompressionTest<out T : StringCompression>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c'),
                6,
            ),
            Arguments.of(
                charArrayOf('a'),
                1,
            ),
            Arguments.of(
                charArrayOf('a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'),
                4,
            ),
            Arguments.of(
                charArrayOf('a', 'a', 'a', 'b', 'b', 'a', 'a'),
                6,
            ),
            Arguments.of(
                charArrayOf('a', 'b', 'c'),
                3,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `compress test`(chars: CharArray, expected: Int) {
        val actual = strategy.invoke(chars)
        assertThat(actual).isEqualTo(expected)
    }
}

class StringCompressionSimpleTest : StringCompressionTest<StringCompression>(StringCompressionSimple())
