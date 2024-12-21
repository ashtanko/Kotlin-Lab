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

package dev.shtanko.algorithms.search

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

@Suppress("ArrayPrimitive")
internal abstract class AbstractSearchTest<out T : AbstractSearchStrategy<Int>>(private val strategy: T) {

    private class InputIntArrayArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(arrayOf<Int>(), 1, -1),
            Arguments.of(arrayOf(1), 1, 0),
            Arguments.of(arrayOf(1), 2, -1),
            Arguments.of(arrayOf(4, 8), 4, 0),
            Arguments.of(arrayOf(4, 8), 8, 1),
            Arguments.of(arrayOf(4, 8), 9, -1),
            Arguments.of(arrayOf(4, 8, 15, 16, 23, 42), 4, 0),
            Arguments.of(arrayOf(4, 8, 15, 16, 23, 42), 8, 1),
            Arguments.of(arrayOf(4, 8, 15, 16, 23, 42), 15, 2),
            Arguments.of(arrayOf(4, 8, 15, 16, 23, 42), 16, 3),
            Arguments.of(arrayOf(4, 8, 15, 16, 23, 42), 23, 4),
            Arguments.of(arrayOf(4, 8, 15, 16, 23, 42), 42, 5),
            Arguments.of(arrayOf(4, 8, 15, 16, 23, 42), 43, -1),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputIntArrayArgumentsProvider::class)
    internal fun `int array test`(arr: Array<Int>, element: Int, expected: Int) {
        val actual = strategy.perform(arr, element)
        assertEquals(expected, actual)
    }
}
