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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class AdvantageShuffleTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1),
                intArrayOf(1),
                intArrayOf(1),
            ),
            Arguments.of(
                intArrayOf(2),
                intArrayOf(1),
                intArrayOf(2),
            ),
            Arguments.of(
                intArrayOf(2),
                intArrayOf(2),
                intArrayOf(2),
            ),
            Arguments.of(
                intArrayOf(2, 7, 11, 15),
                intArrayOf(1, 10, 4, 11),
                intArrayOf(2, 11, 7, 15),
            ),
            Arguments.of(
                intArrayOf(12, 24, 8, 32),
                intArrayOf(13, 25, 32, 11),
                intArrayOf(24, 32, 8, 12),
            ),
            Arguments.of(
                intArrayOf(2, 0, 4, 1, 2),
                intArrayOf(1, 3, 0, 0, 2),
                intArrayOf(2, 0, 1, 2, 4),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `simple test`(a: IntArray, b: IntArray, expected: IntArray) {
        val actual = advantageCount(a, b)
        assertThat(actual, equalTo(expected))
    }
}
