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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class ElementPositionTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                intArrayOf(),
                intArrayOf(-1, -1),
            ),
            Arguments.of(
                8,
                intArrayOf(5, 7, 7, 8, 8, 10),
                intArrayOf(3, 4),
            ),
            Arguments.of(
                6,
                intArrayOf(5, 7, 7, 8, 8, 10),
                intArrayOf(-1, -1),
            ),
            Arguments.of(
                10,
                intArrayOf(5, 7, 7, 8, 8, 10),
                intArrayOf(5, 5),
            ),
            Arguments.of(
                5,
                intArrayOf(5, 7, 7, 8, 8, 10),
                intArrayOf(0, 0),
            ),
            Arguments.of(
                7,
                intArrayOf(5, 7, 7, 8, 8, 10),
                intArrayOf(1, 2),
            ),
            Arguments.of(
                8,
                intArrayOf(5, 7, 7, 8, 8, 10),
                intArrayOf(3, 4),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `search range test`(target: Int, arr: IntArray, expected: IntArray) {
        val actual = arr.searchRange(target)
        assertThat(actual).isEqualTo(expected)
    }
}
