/*
 * Copyright 2023 Oleksii Shtanko
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

abstract class TruncateSentenceTest<out T : TruncateSentence>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "Hello how are you Contestant",
                4,
                "Hello how are you",
            ),
            Arguments.of(
                "What is the solution to this problem",
                4,
                "What is the solution",
            ),
            Arguments.of(
                "chopper is not a tanuki",
                5,
                "chopper is not a tanuki",
            ),
            Arguments.of(
                "chopper is not a tanuki",
                7,
                "chopper is not a tanuki",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `truncate sentence test`(str: String, k: Int, expected: String) {
        val actual = strategy.invoke(str, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class TruncateSentenceBruteForceTest : TruncateSentenceTest<TruncateSentence>(TruncateSentenceBruteForce())
