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

class BaseballGameTest {
    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(arrayOf<String>(), 0),
            Arguments.of(arrayOf(""), 0),
            Arguments.of(arrayOf("1"), 1),
            Arguments.of(arrayOf("2"), 2),
            Arguments.of(arrayOf("99"), 99),
            Arguments.of(arrayOf("2", "2", "+"), 8),
            Arguments.of(arrayOf("1", "1", "+"), 4),
            Arguments.of(arrayOf("1", "", "+"), 0),
            Arguments.of(arrayOf("5", "2", "C", "D", "+"), 30),
            Arguments.of(arrayOf("5", "-2", "4", "C", "D", "9", "+", "+"), 27),
            Arguments.of(arrayOf("5", "2", "C", "D", "+"), 30),
            Arguments.of(arrayOf("5", "2", "C", "D", "+", "C"), 15),
            Arguments.of(arrayOf("5", "2", "C", "D", "+", "C", "D"), 35),
            Arguments.of(arrayOf("5", "2", "C", "D", "+", "C", "D", "D"), 75),
            Arguments.of(arrayOf("5", "2", "C", "D", "+", "C", "D", "D", "D"), 155),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `calculate points`(ops: Array<String>, expected: Int) {
        val actual = BaseballGame().invoke(ops)
        assertEquals(expected, actual)
    }
}
