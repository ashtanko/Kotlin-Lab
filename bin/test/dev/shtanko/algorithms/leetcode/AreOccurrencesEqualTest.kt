/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class AreOccurrencesEqualTest<out T : AreOccurrencesEqual>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                true,
            ),
            Arguments.of(
                "a",
                true,
            ),
            Arguments.of(
                "aa",
                true,
            ),
            Arguments.of(
                "abacbc",
                true,
            ),
            Arguments.of(
                "aaabb",
                false,
            ),
            Arguments.of(
                "aaabbb",
                true,
            ),
            Arguments.of(
                "ab",
                true,
            ),
            Arguments.of(
                "abababababababababababababababababababababab",
                true,
            ),
            Arguments.of(
                "abccbabacabc",
                true,
            ),
            Arguments.of(
                "vvvvvvvvvvvvvvvvvvv",
                true,
            ),
            Arguments.of(
                "fhojjkontbncdhwxbnexplclvjyexzsvqyyhpfpnvhdskuhkuoihiqgalklqketjikdlgrawhfo",
                false,
            ),
            Arguments.of(
                "tveixwaeoezcf",
                false,
            ),
            Arguments.of(
                "jfdntzwmzrsurunnoezrybmtm",
                false,
            ),
            Arguments.of(
                "z",
                true,
            ),
            Arguments.of(
                "zz",
                true,
            ),
            Arguments.of(
                "zzz",
                true,
            ),
            Arguments.of(
                "zzzz",
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `are occurrences equal test`(str: String, expected: Boolean) {
        val actual = strategy.invoke(str)
        assertThat(actual).isEqualTo(expected)
    }
}

class AreOccurrencesEqualBFTest : AreOccurrencesEqualTest<AreOccurrencesEqual>(AreOccurrencesEqualBF())
class AreOccurrencesEqualKotlinTest : AreOccurrencesEqualTest<AreOccurrencesEqual>(AreOccurrencesEqualKotlin())
