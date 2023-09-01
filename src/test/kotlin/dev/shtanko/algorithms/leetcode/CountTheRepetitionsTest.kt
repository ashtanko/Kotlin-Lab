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

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class CountTheRepetitionsTest<out T : CountTheRepetitionsStrategy>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("", 0, "", 0, 0),
            Arguments.of("", 0, "", 1, 0),
            Arguments.of("acb", 4, "ab", 2, 2),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count the repetitions test`(s1: String, n1: Int, s2: String, n2: Int, expected: Int) {
        val actual = strategy.invoke(s1, n1, s2, n2)
        assertEquals(expected, actual)
    }
}

class CountTheRepetitionsBruteForceTest :
    CountTheRepetitionsTest<CountTheRepetitionsBruteForce>(CountTheRepetitionsBruteForce())

class CountTheRepetitionsBetterBruteForceTest :
    CountTheRepetitionsTest<CountTheRepetitionsBetterBruteForce>(CountTheRepetitionsBetterBruteForce())
