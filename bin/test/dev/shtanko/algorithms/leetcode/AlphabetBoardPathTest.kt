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

abstract class AlphabetBoardPathTest<out T : AlphabetBoardPath>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "leet",
                "DDR!UURRR!!DDD!",
            ),
            Arguments.of(
                "code",
                "RR!DDRR!UUL!R!",
            ),
            Arguments.of(
                "",
                "",
            ),
            Arguments.of(
                "zdz",
                "DDDDD!UUUUURRR!DDDDLLLD!",
            ),
            Arguments.of(
                "zb",
                "DDDDD!UUUUUR!",
            ),
            Arguments.of(
                "zdz",
                "DDDDD!UUUUURRR!DDDDLLLD!",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `alphabet board path test`(target: String, expected: String) {
        val actual = strategy.invoke(target).toCharArray()
        assertThat(actual).containsExactlyInAnyOrder(*expected.toCharArray())
    }
}

class AlphabetBoardPathSBTest : AlphabetBoardPathTest<AlphabetBoardPath>(AlphabetBoardPathSB())
