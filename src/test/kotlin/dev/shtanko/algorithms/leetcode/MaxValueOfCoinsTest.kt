/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

abstract class MaxValueOfCoinsTest<out T : MaxValueOfCoins>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    listOf(1, 100, 3),
                    listOf(7, 8, 9),
                ),
                2,
                101,
            ),
            Arguments.of(
                listOf(
                    listOf(100),
                    listOf(100),
                    listOf(100),
                    listOf(100),
                    listOf(100),
                    listOf(100),
                    listOf(1, 1, 1, 1, 1, 1, 700),
                ),
                7,
                706,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max value of coins test`(piles: List<List<Int>>, k: Int, expected: Int) {
        val actual = strategy.invoke(piles, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxValueOfCoinsTopDownTest : MaxValueOfCoinsTest<MaxValueOfCoins>(MaxValueOfCoinsTopDown())
