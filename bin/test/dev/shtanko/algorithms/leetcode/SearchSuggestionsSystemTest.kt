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

abstract class SearchSuggestionsSystemTest<out T : SearchSuggestionsSystem>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("mobile", "mouse", "moneypot", "monitor", "mousepad"),
                "mouse",
                listOf(
                    listOf("mobile", "moneypot", "monitor"),
                    listOf("mobile", "moneypot", "monitor"),
                    listOf("mouse", "mousepad"),
                    listOf("mouse", "mousepad"),
                    listOf("mouse", "mousepad"),
                ),
            ),
            Arguments.of(
                arrayOf("havana"),
                "havana",
                listOf(
                    listOf("havana"),
                    listOf("havana"),
                    listOf("havana"),
                    listOf("havana"),
                    listOf("havana"),
                    listOf("havana"),
                ),
            ),
            Arguments.of(
                arrayOf("bags", "baggage", "banner", "box", "cloths"),
                "bags",
                listOf(
                    listOf("baggage", "bags", "banner"),
                    listOf("baggage", "bags", "banner"),
                    listOf("baggage", "bags"),
                    listOf("bags"),
                ),
            ),
            Arguments.of(
                arrayOf("havana"),
                "tatiana",
                listOf(
                    emptyList<String>(),
                    emptyList<String>(),
                    emptyList<String>(),
                    emptyList<String>(),
                    emptyList<String>(),
                    emptyList<String>(),
                    emptyList<String>(),
                ),
            ),
            Arguments.of(
                arrayOf<String>(),
                "",
                listOf<List<String>>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `suggested products test`(products: Array<String>, searchWord: String, expected: List<List<String>>) {
        val actual = strategy.invoke(products, searchWord)
        assertThat(actual).containsAll(expected)
    }
}

class SSSTrieTest : SearchSuggestionsSystemTest<SearchSuggestionsSystem>(SearchSuggestionsTrie())
class SSSBinarySearchTest : SearchSuggestionsSystemTest<SearchSuggestionsSystem>(SearchSuggestionsBinarySearch())
