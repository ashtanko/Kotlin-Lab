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

abstract class ReplaceWordsTest<out T : ReplaceWords>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf("cat", "bat", "rat"),
                "the cattle was rattled by the battery",
                "the cat was rat by the bat",
            ),
            Arguments.of(
                listOf("a", "b", "c"),
                "aadsfasf absbs bbab cadsfafs",
                "a a b c",
            ),
            Arguments.of(
                listOf("a", "aa", "aaa", "aaaa"),
                "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa",
                "a a a a a a a a bbb baba a",
            ),
            Arguments.of(
                listOf("catt", "cat", "bat", "rat"),
                "the cattle was rattled by the battery",
                "the cat was rat by the bat",
            ),
            Arguments.of(
                listOf<String>(),
                "",
                "",
            ),
            Arguments.of(
                listOf("a"),
                "a",
                "a",
            ),
            Arguments.of(
                listOf("a"),
                "a a a a",
                "a a a a",
            ),
            Arguments.of(
                listOf("a"),
                "b",
                "b",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `replace words test`(dictionary: List<String>, sentence: String, expected: String) {
        val actual = strategy.invoke(dictionary, sentence)
        assertThat(actual).isEqualTo(expected)
    }
}

class PrefixHashTest : ReplaceWordsTest<ReplaceWords>(PrefixHash())
class ReplaceWordsTrieTest : ReplaceWordsTest<ReplaceWords>(ReplaceWordsTrie())
