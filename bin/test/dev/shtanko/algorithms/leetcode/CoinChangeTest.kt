/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class CoinChangeTest<out T : CoinChange>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 5),
                11,
                3,
            ),
            Arguments.of(
                intArrayOf(2),
                3,
                -1,
            ),
            Arguments.of(
                intArrayOf(1),
                0,
                0,
            ),
            Arguments.of(
                intArrayOf(),
                0,
                0,
            ),
            Arguments.of(
                intArrayOf(100, 22, 456),
                34,
                -1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 5),
                100,
                20,
            ),
            Arguments.of(
                intArrayOf(1, 2, 5),
                1000,
                200,
            ),
            Arguments.of(
                intArrayOf(1, 2, 5),
                10000,
                2000,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `coin change test`(coins: IntArray, amount: Int, expected: Int) {
        val actual = strategy.invoke(coins, amount)
        assertThat(actual).isEqualTo(expected)
    }
}

class CoinChangeDPTest : CoinChangeTest<CoinChange>(CoinChangeDP())
