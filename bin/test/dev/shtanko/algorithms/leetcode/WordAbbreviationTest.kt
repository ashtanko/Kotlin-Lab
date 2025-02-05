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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class WordAbbreviationTest<out T : WordAbbreviation>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                emptyList<String>(),
                emptyList<String>(),
            ),
            Arguments.of(
                listOf(
                    "like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion",
                ),
                listOf(
                    "l2e", "god", "internal", "me", "i6t", "interval", "inte4n", "f2e", "intr4n",
                ),
            ),
            Arguments.of(
                listOf("applicable", "law", "language", "distributed"),
                listOf("a8e", "law", "l6e", "d9d"),
            ),
            Arguments.of(
                listOf(""),
                listOf(""),
            ),
            Arguments.of(
                listOf("a", "b", "c", "D", "E"),
                listOf("a", "b", "c", "D", "E"),
            ),
            Arguments.of(
                listOf("aa", "aaa"),
                listOf("aa", "aaa"),
            ),
            Arguments.of(
                listOf("a", "aa", "aaa"),
                listOf("a", "aa", "aaa"),
            ),
            Arguments.of(
                listOf("a", "aa", "aaa", "aaaa"),
                listOf("a", "aa", "aaa", "a2a"),
            ),
            Arguments.of(
                listOf("a", "aa", "aaa", "aaaaa"),
                listOf("a", "aa", "aaa", "a3a"),
            ),
            Arguments.of(
                listOf("a", "aa", "aaa", "aaaaaa"),
                listOf("a", "aa", "aaa", "a4a"),
            ),
            Arguments.of(
                listOf("", ""),
                listOf("", ""),
            ),
            Arguments.of(
                listOf("", "", "a", "", " "),
                listOf("", "", "a", "", " "),
            ),
            Arguments.of(
                listOf("", "aaa"),
                listOf("", "aaa"),
            ),
            Arguments.of(
                listOf("a", "", "human"),
                listOf("a", "", "h3n"),
            ),
            Arguments.of(
                listOf("consanguineous"),
                listOf("c12s"),
            ),
            Arguments.of(
                listOf("Pneumonoultramicroscopicsilicovolcanoconiosis"),
                listOf("P43s"),
            ),
            Arguments.of(
                listOf("Supercalifragilisticexpialidocious"),
                listOf("S32s"),
            ),
            Arguments.of(
                listOf("Alex", "Max", "Oleksii", "Vitalii", "Ival"),
                listOf("A2x", "Max", "O5i", "V5i", "I2l"),
            ),
            Arguments.of(
                listOf("Alastair", "Atticus", "Augustine", "Benjamin", "Cameron", "Constantine"),
                listOf("A6r", "A5s", "A7e", "B6n", "C5n", "C9e"),
            ),
            Arguments.of(
                getList(),
                listOf("l3m", "I3m", "is", "s4y", "d3y", "t2t", "of", "the", "and", "w2n", "an", "u5n", "p5r", "t2k"),
            ),
        )

        private fun getList() =
            "lorem Ipsum is simply dummy text of the and when an unknown printer took"
                .trim()
                .splitToSequence(' ')
                .filter { it.isNotEmpty() }
                .toList()
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `words abbreviation test`(dict: List<String>, expected: List<String>) {
        val actual = strategy.invoke(dict)
        assertThat(actual, equalTo(expected))
    }
}

class WordAbbreviationGreedyTest : WordAbbreviationTest<WordAbbreviationGreedy>(WordAbbreviationGreedy())
class WordAbbreviationLCPTest : WordAbbreviationTest<WordAbbreviationLCP>(WordAbbreviationLCP())
class WordAbbreviationTrieTest : WordAbbreviationTest<WordAbbreviationTrie>(WordAbbreviationTrie())
