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

abstract class AbstractFindSubstringTest<T : AbstractFindSubstring>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                arrayOf("foo", "bar"),
                emptyList<Int>(),
            ),
            Arguments.of(
                "barfoothefoobarman",
                arrayOf("foo", "bar"),
                listOf(0, 9),
            ),
            Arguments.of(
                "wordgoodgoodgoodbestword",
                arrayOf("word", "good", "best", "word"),
                emptyList<Int>(),
            ),
            Arguments.of(
                "barfoofoobarthefoobarman",
                arrayOf("bar", "foo", "the"),
                listOf(6, 9, 12),
            ),
            Arguments.of(
                "lingmindraboofooowingdingbarrwingmonkeypoundcake",
                arrayOf("fooo", "barr", "wing", "ding", "wing"),
                listOf(13),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find substring test`(str: String, words: Array<String>, expected: List<Int>) {
        val actual = strategy.invoke(str, words)
        assertEquals(expected, actual)
    }
}

class FindSubstringTest : AbstractFindSubstringTest<FindSubstring>(FindSubstring())
