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

package dev.shtanko.algorithms.dp

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class MinCoinsTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            // 11 = 5 + 5 + 1
            Arguments.of(
                intArrayOf(1, 2, 5),
                11,
                3,
            ),
            // 7 = 5 + 2
            Arguments.of(
                intArrayOf(1, 2, 5),
                7,
                2,
            ),
            // 3 = 2 + 1
            Arguments.of(
                intArrayOf(1, 2, 5),
                3,
                2,
            ),
            // No coins needed for amount 0
            Arguments.of(
                intArrayOf(1, 2, 5),
                0,
                0,
            ),
            // No coins needed for amount 0
            Arguments.of(
                intArrayOf(),
                0,
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min coins test`(coins: IntArray, amount: Int, expected: Int) {
        val actual = minCoins(coins, amount)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
