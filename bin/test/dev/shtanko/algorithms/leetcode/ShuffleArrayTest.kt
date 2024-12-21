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

class ShuffleArrayTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `shuffle test`(arr: IntArray, num: Int, expected: IntArray) {
        val actual = arr.shuffle(num)
        assertThat(actual).isEqualTo(expected)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(2, 5, 1, 3, 4, 7),
                3,
                intArrayOf(2, 3, 5, 4, 1, 7),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 4, 3, 2, 1),
                4,
                intArrayOf(1, 4, 2, 3, 3, 2, 4, 1),
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2),
                2,
                intArrayOf(1, 2, 1, 2),
            ),
            Arguments.of(
                intArrayOf(),
                0,
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(),
                1,
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1),
                0,
                intArrayOf(1),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                0,
                intArrayOf(1, 2),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                1,
                intArrayOf(1, 2),
            ),
        )
    }
}
