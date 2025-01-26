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
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class RankTransformArrayTest<out T : RankTransformOfArray>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `array rank transform test`(arr: IntArray, expected: IntArray) {
        val actual = strategy(arr)
        assertArrayEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(40, 10, 20, 30),
                intArrayOf(4, 1, 2, 3),
            ),
            Arguments.of(
                intArrayOf(100, 100, 100),
                intArrayOf(1, 1, 1),
            ),
            Arguments.of(
                intArrayOf(37, 12, 28, 9, 100, 56, 80, 5, 12),
                intArrayOf(5, 3, 4, 2, 8, 6, 7, 1, 3),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
        )
    }
}

class RankTransformOfArraySortingTest : RankTransformArrayTest<RankTransformOfArray>(RankTransformOfArraySort())
class RankTransformOfArraySetTest : RankTransformArrayTest<RankTransformOfArray>(RankTransformOfArraySet())
class RankTransformOfArrayTreeMapTest : RankTransformArrayTest<RankTransformOfArray>(RankTransformOfArrayTree())
