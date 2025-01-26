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

abstract class CoinChange2Test<out T : CoinChange2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                5,
                intArrayOf(1, 2, 5),
                4,
            ),
            Arguments.of(
                3,
                intArrayOf(2),
                0,
            ),
            Arguments.of(
                10,
                intArrayOf(10),
                1,
            ),
            Arguments.of(
                10,
                intArrayOf(9, 1),
                2,
            ),
            Arguments.of(
                500,
                intArrayOf(3, 5, 7, 8, 9, 10, 11),
                35502874,
            ),
            Arguments.of(
                500,
                intArrayOf(1, 2, 5),
                12701,
            ),
            Arguments.of(
                500,
                intArrayOf(1, 2, 5, 10, 20, 50, 100),
                5824071,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `coin change test`(amount: Int, coins: IntArray, expected: Int) {
        val actual = strategy.change(amount, coins)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class CoinChange2TopDownTest : CoinChange2Test<CoinChange2>(CoinChange2TopDown())
class CoinChange2BottomUpTest : CoinChange2Test<CoinChange2>(CoinChange2BottomUp())
class CoinChange2SpaceOptTest : CoinChange2Test<CoinChange2>(CoinChange2SpaceOpt())
