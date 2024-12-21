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

abstract class FindAnagramsTest<out T : FindAnagrams>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "cbaebabacd",
                "abc",
                listOf(0, 6),
            ),
            Arguments.of(
                "abab",
                "ab",
                listOf(0, 1, 2),
            ),
            Arguments.of(
                "aaaaaaaaaa",
                "aaaaaaaaaaaaa",
                emptyList<Int>(),
            ),
            Arguments.of(
                "aaaaaaaaaa",
                "aaaaaaaaaa",
                listOf(0),
            ),
            Arguments.of(
                "aaaaaaaaaa",
                "aaaaaaaaa",
                listOf(0, 1),
            ),
            Arguments.of(
                "aaaaaaaaaa",
                "aaaaaaaa",
                listOf(0, 1, 2),
            ),
            Arguments.of(
                "",
                "",
                listOf(0),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find anagrams test`(str: String, p: String, expected: List<Int>) {
        val actual = strategy.invoke(str, p)
        assertThat(actual).isEqualTo(expected)
    }
}

class FindAnagramsHashTableTest : FindAnagramsTest<FindAnagrams>(FindAnagramsHashTable())
