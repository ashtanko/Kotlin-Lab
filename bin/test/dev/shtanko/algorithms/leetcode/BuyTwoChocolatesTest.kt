/*
 * Copyright 2023 Oleksii Shtanko
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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class BuyTwoChocolatesTest<out T : BuyTwoChocolates>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 2),
                3,
                0,
            ),
            Arguments.of(
                intArrayOf(3, 2, 3),
                3,
                3,
            ),
            Arguments.of(
                intArrayOf(1, 1),
                1,
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 1, 2),
                2,
                0,
            ),
            Arguments.of(
                intArrayOf(5, 5, 8, 3, 4, 5, 6, 8, 9, 76, 6, 6, 7, 5, 50, 6, 45, 6, 45, 34),
                90,
                83,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                10,
                7,
            ),
            Arguments.of(
                intArrayOf(),
                0,
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
                100,
                97,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun buyChocoTest(prices: IntArray, money: Int, expected: Int) {
        val actual = strategy(prices, money)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class CheckEveryPairOfChocolateTest : BuyTwoChocolatesTest<BuyTwoChocolates>(BuyTwoChocolates.CheckEveryPairOfChocolate)
class BuyTwoChocolatesGreedyTest : BuyTwoChocolatesTest<BuyTwoChocolates>(BuyTwoChocolates.Greedy)
class BuyTwoChocolatesCountingSortTest : BuyTwoChocolatesTest<BuyTwoChocolates>(BuyTwoChocolates.CountingSort)
class BuyTwoChocolatesTwoPassesTest : BuyTwoChocolatesTest<BuyTwoChocolates>(BuyTwoChocolates.TwoPasses)
class BuyTwoChocolatesOnePassTest : BuyTwoChocolatesTest<BuyTwoChocolates>(BuyTwoChocolates.OnePass)
class BuyTwoChocolatesSimpleTest : BuyTwoChocolatesTest<BuyTwoChocolates>(BuyTwoChocolates.Simple)
