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
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class TwoSum2Test {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(2, 7, 11, 15),
                9,
                intArrayOf(1, 2),
            ),
            Arguments.of(
                intArrayOf(4, 8, 15, 16, 23, 42),
                16,
                intArrayOf(2, 2),
            ),
            Arguments.of(
                intArrayOf(3, 3),
                6,
                intArrayOf(1, 2),
            ),
            Arguments.of(
                intArrayOf(3, 2, 4),
                6,
                intArrayOf(2, 2),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `two sum 2 test`(arr: IntArray, target: Int, expected: IntArray) {
        val actual = arr.twoSum2(target)
        assertThat(actual).isEqualTo(expected)
        assertArrayEquals(expected, actual)
    }
}
