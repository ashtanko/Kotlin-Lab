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

abstract class SumOfAllOddLengthSubArraysTest<out T : SumOfAllOddLengthSubArrays>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sum of all odd length sub arrays test`(arr: IntArray, expected: Int) {
        val actual = strategy.invoke(arr)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(intArrayOf(), 0),
            Arguments.of(intArrayOf(1), 1),
            Arguments.of(intArrayOf(1, 4, 2, 5, 3), 58),
            Arguments.of(intArrayOf(1, 2), 3),
            Arguments.of(intArrayOf(10, 11, 12), 66),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 605),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 876),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 1183),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13), 1617),
        )
    }
}

class SumOfAllOddLengthSubArraysSFTest :
    SumOfAllOddLengthSubArraysTest<SumOfAllOddLengthSubArrays>(SumOfAllOddLengthSubArraysSF())
