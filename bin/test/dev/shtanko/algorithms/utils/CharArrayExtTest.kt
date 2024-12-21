/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.algorithms.utils

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class CharArrayExtTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                charArrayOf(),
                0,
                0,
                charArrayOf(),
            ),
            Arguments.of(
                charArrayOf('a', 'b', 'c', 'd'),
                0,
                0,
                charArrayOf('a', 'b', 'c', 'd'),
            ),
            Arguments.of(
                charArrayOf('a', 'b'),
                0,
                1,
                charArrayOf('b', 'a'),
            ),
            Arguments.of(
                charArrayOf('a', 'b'),
                1,
                0,
                charArrayOf('a', 'b'),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `reverse char array test`(arr: CharArray, left: Int, right: Int, expected: CharArray) {
        arr.reverse(left, right)
        assertArrayEquals(expected, arr)
    }
}
