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

package dev.shtanko.algorithms.exercises

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal abstract class UniqueCharactersTest<out T : UniqueCharacters>(private val strategy: T) {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                false
            ),
            Arguments.of(
                "a",
                true
            ),
            Arguments.of(
                "abc",
                true
            ),
            Arguments.of(
                "aab",
                false
            ),
            Arguments.of(
                "abcda",
                false
            )
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `find unique characters test`(str: String, expected: Boolean) {
        val actual = strategy.perform(str)
        assertEquals(expected, actual)
    }
}

internal class UniqueCharactersSetTest : UniqueCharactersTest<UniqueCharactersSet>(UniqueCharactersSet())
internal class UniqueCharactersSortTest :
    UniqueCharactersTest<UniqueCharactersSort>(UniqueCharactersSort())

internal class UniqueCharactersStreamTest :
    UniqueCharactersTest<UniqueCharactersStream>(UniqueCharactersStream())

internal class UniqueCharactersBruteForceTest :
    UniqueCharactersTest<UniqueCharactersBruteForce>(UniqueCharactersBruteForce())
