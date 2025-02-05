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

abstract class LetterCasePermutationTest<out T : LetterCasePermutation>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "a1b2",
                listOf("a1b2", "a1B2", "A1b2", "A1B2"),
            ),
            Arguments.of(
                "3z4",
                listOf("3z4", "3Z4"),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `letter case permutation test`(s: String, expected: List<String>) {
        val actual = strategy.invoke(s)
        assertThat(actual).containsAll(expected)
    }
}

class LetterCasePermutationBFSTest : LetterCasePermutationTest<LetterCasePermutation>(LetterCasePermutationBFS())
class LetterCasePermutationDFSTest : LetterCasePermutationTest<LetterCasePermutation>(LetterCasePermutationDFS())
