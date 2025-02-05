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

abstract class MaxScoreWordsTest<out T : MaxScoreWords>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("dog", "cat", "dad", "good"),
                charArrayOf('a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'),
                intArrayOf(1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                23,
            ),
            Arguments.of(
                arrayOf("xxxz", "ax", "bx", "cx"),
                charArrayOf('z', 'a', 'b', 'c', 'x', 'x', 'x'),
                intArrayOf(4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10),
                27,
            ),
            Arguments.of(
                arrayOf("leetcode"),
                charArrayOf('l', 'e', 't', 'c', 'o', 'd'),
                intArrayOf(0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0),
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max score words test`(words: Array<String>, letters: CharArray, score: IntArray, expected: Int) {
        val actual = strategy.invoke(words, letters, score)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxScoreWordsBacktrackingTest : MaxScoreWordsTest<MaxScoreWords>(MaxScoreWordsBacktracking())
class MaxScoreWordsDFSTest : MaxScoreWordsTest<MaxScoreWords>(MaxScoreWordsDFS())
