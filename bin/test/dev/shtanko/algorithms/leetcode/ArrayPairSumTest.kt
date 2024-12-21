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

abstract class ArrayPairSumTest<out T : PairSum>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 4, 3, 2),
                4,
            ),
            Arguments.of(
                intArrayOf(1),
                1,
            ),
            Arguments.of(
                intArrayOf(-1),
                -1,
            ),
            Arguments.of(
                intArrayOf(1, 2),
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                4,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                4,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                9,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6),
                9,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7),
                16,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `array pair sum test`(arr: IntArray, expected: Int) {
        val actual = strategy.invoke(arr)
        assertThat(actual, equalTo(expected))
    }
}

class PairSumSort1Test : ArrayPairSumTest<PairSumSort1>(PairSumSort1())

class PairSumSort2Test : ArrayPairSumTest<PairSumSort2>(PairSumSort2())

class PairSumSort3Test : ArrayPairSumTest<PairSumSort3>(PairSumSort3())

class PairSumOddTest : ArrayPairSumTest<PairSumOdd>(PairSumOdd())
