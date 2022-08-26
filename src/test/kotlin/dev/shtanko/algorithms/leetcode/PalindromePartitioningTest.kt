/*
 * Copyright 2022 Oleksii Shtanko
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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class PalindromePartitioningTest<out T : PalindromePartitioning>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                listOf<List<String>>(),
            ),
            Arguments.of(
                "aab",
                listOf(
                    listOf("a", "a", "b"),
                    listOf("aa", "b"),
                ),
            ),
            Arguments.of(
                "a",
                listOf(
                    listOf("a"),
                ),
            ),
            Arguments.of(
                "qqqe",
                listOf(
                    listOf("q", "q", "q", "e"),
                    listOf("q", "qq", "e"),
                    listOf("qq", "q", "e"),
                    listOf("qqq", "e"),
                ),
            ),
            Arguments.of(
                "leetcode",
                listOf(
                    listOf("l", "e", "e", "t", "c", "o", "d", "e"),
                    listOf("l", "ee", "t", "c", "o", "d", "e"),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `partition test`(s: String, expected: List<List<String>>) {
        val actual = strategy.perform(s)
        assertThat(actual).containsAll(expected)
    }
}

class PPBacktrackingTest : PalindromePartitioningTest<PalindromePartitioning>(PPBacktracking())
class PPBacktrackingDPTest : PalindromePartitioningTest<PalindromePartitioning>(PPBacktrackingDP())
