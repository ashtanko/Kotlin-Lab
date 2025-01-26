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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ConsecutiveCharactersTest<out T : ConsecutiveCharactersStrategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("leetcode", 2),
            Arguments.of("abbcccddddeeeeedcba", 5),
            Arguments.of("triplepillooooow", 5),
            Arguments.of("hooraaaaaaaaaaay", 11),
            Arguments.of("tourist", 1),
            Arguments.of("", 0),
            Arguments.of("a", 1),
            Arguments.of("aa", 2),
            Arguments.of("aaa", 3),
            Arguments.of("aaaa", 4),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `consecutive characters test`(s: String, expected: Int) {
        val actual = strategy.invoke(s)
        assertEquals(expected, actual)
    }
}

class MaxPower1Test : ConsecutiveCharactersTest<MaxPower1>(MaxPower1())
class MaxPower2Test : ConsecutiveCharactersTest<MaxPower2>(MaxPower2())
