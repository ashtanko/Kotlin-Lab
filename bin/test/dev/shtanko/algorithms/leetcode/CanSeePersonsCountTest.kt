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
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class CanSeePersonsCountTest<out T : CanSeePersonsCount>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(10, 6, 8, 5, 11, 9),
                intArrayOf(3, 1, 2, 1, 1, 0),
            ),
            Arguments.of(
                intArrayOf(5, 1, 2, 3, 10),
                intArrayOf(4, 1, 1, 1, 0),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1),
                intArrayOf(0),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                intArrayOf(1, 0),
            ),
            Arguments.of(
                intArrayOf(2, 1),
                intArrayOf(1, 0),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(1, 1, 1, 1, 0),
            ),
            Arguments.of(
                intArrayOf(5, 4, 3, 2, 1),
                intArrayOf(1, 1, 1, 1, 0),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 0),
            ),
            Arguments.of(
                intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 0),
            ),

        )
    }

    @DisplayName("Number of Visible People in a Queue Test")
    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun canSeePersonsCount(heights: IntArray, expected: IntArray) {
        val actual = strategy.invoke(heights)
        assertThat(actual).isEqualTo(expected)
    }
}

class CanSeePersonsCountStackTest : CanSeePersonsCountTest<CanSeePersonsCount>(CanSeePersonsCountStack())
