/*
 * Copyright 2021 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

abstract class FindAndReplacePatternTest<out T : FindAndReplacePattern>(private val strategy: T) {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("abc", "deq", "mee", "aqq", "dkd", "ccc"),
                "abb",
                listOf("mee", "aqq")
            ),
            Arguments.of(
                arrayOf("a", "b", "c"),
                "a",
                listOf("a", "b", "c")
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `findAndReplacePattern test`(words: Array<String>, pattern: String, expected: List<String>) {
        val actual = strategy.perform(words, pattern)
        assertThat(actual).containsAll(expected)
    }
}

internal class FRPTwoMapsTest : FindAndReplacePatternTest<FRPTwoMaps>(FRPTwoMaps())
internal class FRPOneMapTest : FindAndReplacePatternTest<FRPOneMap>(FRPOneMap())
