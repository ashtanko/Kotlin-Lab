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

abstract class WordBreakTest<out T : WordBreak>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "leetcode",
                listOf("leet", "code"),
                true,
            ),
            Arguments.of(
                "applepenapple",
                listOf("apple", "pen"),
                true,
            ),
            Arguments.of(
                "catsandog",
                listOf("cats", "dog", "sand", "and", "cat"),
                false,
            ),
            Arguments.of(
                "cars",
                listOf("car", "ca", "rs"),
                true,
            ),
            Arguments.of(
                "aaaaaaa",
                listOf("aaaa", "aaa"),
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `word break test`(s: String, wordDict: List<String>, expected: Boolean) {
        val actual = strategy.invoke(s, wordDict)
        assertThat(actual).isEqualTo(expected)
    }
}

class WordBreakBruteForceTest : WordBreakTest<WordBreak>(WordBreakBruteForce())
class WordBreakDPTest : WordBreakTest<WordBreak>(WordBreakDP())
class WordBreakDFSTest : WordBreakTest<WordBreak>(WordBreakDFS())
class WordBreakBFSTest : WordBreakTest<WordBreak>(WordBreakBFS())
class WordBreakDP2Test : WordBreakTest<WordBreak>(WordBreakDP2())
