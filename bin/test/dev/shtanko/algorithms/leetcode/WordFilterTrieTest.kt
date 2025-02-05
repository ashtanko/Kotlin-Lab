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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class WordFilterTrieTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                WordFilterTrie(arrayOf()),
                "",
                "",
                -1,
            ),
            Arguments.of(
                WordFilterTrie(arrayOf()),
                "c",
                "d",
                -1,
            ),
            Arguments.of(
                WordFilterTrie(arrayOf("")),
                "",
                "",
                0,
            ),
            Arguments.of(
                WordFilterTrie(arrayOf("can")),
                "a",
                "n",
                -1,
            ),
            Arguments.of(
                WordFilterTrie(arrayOf("apple")),
                "app",
                "le",
                0,
            ),
            Arguments.of(
                WordFilterTrie(arrayOf("ae")),
                "a",
                "e",
                0,
            ),
            Arguments.of(
                WordFilterTrie(arrayOf("aaaaaaaaaaaaaeeeeeee")),
                "a",
                "e",
                0,
            ),
            Arguments.of(
                WordFilterTrie(arrayOf("better")),
                "a",
                "e",
                -1,
            ),
            Arguments.of(
                WordFilterTrie(arrayOf("apple")),
                "a",
                "e",
                0,
            ),
            Arguments.of(
                WordFilterTrie(arrayOf("applies")),
                "a",
                "e",
                -1,
            ),
            Arguments.of(
                WordFilterWrappedWords(arrayOf()),
                "",
                "",
                -1,
            ),
            Arguments.of(
                WordFilterWrappedWords(arrayOf("")),
                "",
                "",
                0,
            ),
            Arguments.of(
                WordFilterWrappedWords(arrayOf("apple")),
                "a",
                "e",
                0,
            ),
            Arguments.of(
                WordFilterWrappedWords(arrayOf("applies")),
                "a",
                "e",
                -1,
            ),
            Arguments.of(
                WordFilterWrappedWords(arrayOf("apple")),
                "app",
                "le",
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `word filter test`(strategy: WordFilter, prefix: String, suffix: String, expected: Int) {
        val actual = strategy.invoke(prefix, suffix)
        assertThat(actual).isEqualTo(expected)
    }
}
