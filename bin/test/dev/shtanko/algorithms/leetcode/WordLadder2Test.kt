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

abstract class AbstractWordLadder2StrategyTest<out T : AbstractWordLadder2Strategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "hit",
                "cog",
                listOf("hot", "dot", "dog", "lot", "log", "cog"),
                listOf(
                    listOf("hit", "hot", "dot", "dog", "cog"),
                    listOf("hit", "hot", "lot", "log", "cog"),
                ),
            ),
            Arguments.of(
                "hit",
                "cog",
                listOf("hot", "dot", "dog", "lot", "log"),
                emptyList<List<String>>(),
            ),
            Arguments.of(
                "a",
                "c",
                listOf("a", "b", "c"),
                listOf(listOf("a", "c")),
            ),
            Arguments.of(
                "hot",
                "dog",
                listOf("hot", "dog"),
                emptyList<List<String>>(),
            ),
            Arguments.of(
                "",
                "",
                emptyList<List<String>>(),
                emptyList<List<String>>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `word ladder 2 test`(
        beginWord: String,
        endWord: String,
        wordList: List<String>,
        expected: List<List<String>>,
    ) {
        val actual = strategy.invoke(beginWord, endWord, wordList)
        assertEquals(expected, actual)
    }
}

class WordLadder2Test : AbstractWordLadder2StrategyTest<WordLadder2>(WordLadder2())
