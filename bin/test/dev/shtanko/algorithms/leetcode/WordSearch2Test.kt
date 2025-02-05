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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class WordSearch2Test<out T : WordSearch2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    charArrayOf('o', 'a', 'a', 'n'),
                    charArrayOf('e', 't', 'a', 'e'),
                    charArrayOf('i', 'h', 'k', 'r'),
                    charArrayOf('i', 'f', 'l', 'v'),
                ),
                arrayOf("oath", "pea", "eat", "rain"),
                listOf("eat", "oath"),
            ),
            Arguments.of(
                arrayOf(
                    charArrayOf('a', 'b'),
                    charArrayOf('c', 'd'),
                ),
                arrayOf("abcb"),
                emptyList<String>(),
            ),
            Arguments.of(
                arrayOf(
                    charArrayOf('a', 'b', 'c', 'e'),
                    charArrayOf('s', 'f', 'c', 's'),
                    charArrayOf('a', 'd', 'e', 'e'),
                ),
                arrayOf("abcced", "see", "abcb"),
                listOf("abcced", "see"),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `get length of optimal compression test`(
        board: Array<CharArray>,
        words: Array<String>,
        expected: List<String>,
    ) {
        val actual = strategy.invoke(board, words)
        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(expected)
    }
}

class WordSearch2TrieTest : WordSearch2Test<WordSearch2>(WordSearch2Trie())
