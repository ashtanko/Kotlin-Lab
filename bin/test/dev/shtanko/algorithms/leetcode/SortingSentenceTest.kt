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
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class SortingSentenceTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "is2 sentence4 This1 a3",
                "This is a sentence",
            ),
            Arguments.of(
                "Myself2 Me1 I4 and3",
                "Me Myself and I",
            ),
            Arguments.of(
                "Me1",
                "Me",
            ),
        )
    }

    @TestFactory
    fun `sort sentence tree test`() = testData().map { (input, expected) ->
        DynamicTest.dynamicTest("when sort using tree $input output is: $expected") {
            assertSortSentence(::sortSentenceTree, input, expected)
        }
    }

    @TestFactory
    fun `sort sentence test`() = testData().map { (input, expected) ->
        DynamicTest.dynamicTest("when sort $input output is: $expected") {
            assertSortSentence(::sortSentence, input, expected)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sort sentence`(input: String, expected: String) {
        val actual = sortSentence(input)
        assertThat(actual).isEqualTo(expected)
    }

    private fun assertSortSentence(strategy: (s: String) -> String, sentence: String, expected: String) {
        val actual = strategy.invoke(sentence)
        assertThat(actual).isEqualTo(expected)
    }

    private fun testData() = listOf(
        "is2 sentence4 This1 a3" to "This is a sentence",
        "Myself2 Me1 I4 and3" to "Me Myself and I",
        "Me1" to "Me",
    )
}
