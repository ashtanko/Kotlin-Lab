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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class CountGoodTripletsTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
                0,
                0,
                0,
            ),
            Arguments.of(
                intArrayOf(3, 0, 1, 1, 9, 7),
                7,
                2,
                3,
                4,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 3),
                0,
                0,
                1,
                0,
            ),
            Arguments.of(
                intArrayOf(7, 3, 7, 3, 12, 1, 12, 2, 3),
                5,
                8,
                1,
                12,
            ),
            Arguments.of(
                intArrayOf(3, 3, 2, 2, 3, 2, 2, 1),
                6,
                5,
                15,
                56,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count good triplets test`(arr: IntArray, a: Int, b: Int, c: Int, expected: Int) {
        val actual = countGoodTriplets(arr, a, b, c)
        assertEquals(expected, actual)
    }
}
