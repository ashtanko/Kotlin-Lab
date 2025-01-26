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

abstract class WordBreak2Test<out T : WordBreak2>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "catsanddog",
                listOf("cat", "cats", "and", "sand", "dog"),
                listOf("cats and dog", "cat sand dog"),
            ),
            Arguments.of(
                "pineapplepenapple",
                listOf("apple", "pen", "applepen", "pine", "pineapple"),
                listOf("pine apple pen apple", "pineapple pen apple", "pine applepen apple"),
            ),
            Arguments.of(
                "catsandog",
                listOf("cats", "dog", "sand", "and", "cat"),
                listOf<String>(),
            ),
            Arguments.of(
                "a",
                listOf("a"),
                listOf("a"),
            ),
            Arguments.of(
                "cars",
                listOf("car", "ca", "rs"),
                listOf("ca rs"),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `word break test`(s: String, wordDict: List<String>, expected: List<String>) {
        val actual = strategy.invoke(s, wordDict)
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected)
    }
}

class WordBreak2DFSTest : WordBreak2Test<WordBreak2>(WordBreak2DFS())
class WordBreak2DPDFSTest : WordBreak2Test<WordBreak2>(WordBreak2DPDFS())
class WordBreak2BacktrackingTest : WordBreak2Test<WordBreak2>(WordBreak2Backtracking())
