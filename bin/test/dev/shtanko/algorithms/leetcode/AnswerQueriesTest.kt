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

abstract class AnswerQueriesTest<out T : AnswerQueries>(private val strategy: T) {
    private class InputArgumentsProvide : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 5, 2, 1),
                intArrayOf(3, 10, 21),
                intArrayOf(2, 3, 4),
            ),
            Arguments.of(
                intArrayOf(2, 3, 4, 5),
                intArrayOf(1),
                intArrayOf(0),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(0, 1, 2, 3),
                intArrayOf(0, 1, 1, 2),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(0, 1, 2, 3, 4),
                intArrayOf(0, 1, 1, 2, 2),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvide::class)
    fun `answer queries test`(nums: IntArray, queries: IntArray, expected: IntArray) {
        val actual = strategy.invoke(nums, queries)
        assertThat(actual).isEqualTo(expected)
    }
}

class AnswerQueriesBinarySearchTest : AnswerQueriesTest<AnswerQueries>(AnswerQueriesBinarySearch())
