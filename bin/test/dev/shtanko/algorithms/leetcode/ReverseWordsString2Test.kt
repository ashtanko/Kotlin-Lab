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

import dev.shtanko.algorithms.utils.reverse
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class ReverseWordsString2Test {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                charArrayOf('t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'),
                charArrayOf('b', 'l', 'u', 'e', ' ', 'i', 's', ' ', 's', 'k', 'y', ' ', 't', 'h', 'e'),
            ),
        )
    }

    class InputToReverseArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                charArrayOf(),
                0,
                0,
                charArrayOf(),
            ),
            Arguments.of(
                charArrayOf('t', 'h', 'e'),
                0,
                2,
                charArrayOf('e', 'h', 't'),
            ),
        )
    }

    class InputToReverseWordArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                charArrayOf(),
                charArrayOf(),
            ),
            Arguments.of(
                charArrayOf('t', 'h', 'e'),
                charArrayOf('e', 'h', 't'),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `test`(s: CharArray, expected: CharArray) {
        ReverseWordsInString2().invoke(s)
        assertArrayEquals(expected, s)
    }

    @ParameterizedTest
    @ArgumentsSource(InputToReverseArgumentsProvider::class)
    fun `reverse whole string test`(s: CharArray, left: Int, right: Int, expected: CharArray) {
        s.reverse(left, right)
        assertArrayEquals(expected, s)
    }

    @ParameterizedTest
    @ArgumentsSource(InputToReverseWordArgumentsProvider::class)
    fun `reverse each word test`(s: CharArray, expected: CharArray) {
        ReverseWordsInString2().reverseEachWord(s)
        assertArrayEquals(expected, s)
    }
}
