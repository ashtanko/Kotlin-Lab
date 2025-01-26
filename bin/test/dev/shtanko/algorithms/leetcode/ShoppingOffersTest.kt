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

abstract class ShoppingOffersTest<out T : ShoppingOffers>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(2, 5),
                listOf(
                    listOf(3, 0, 5),
                    listOf(1, 2, 10),
                ),
                listOf(3, 2),
                14,
            ),
            Arguments.of(
                listOf(2, 3, 4),
                listOf(
                    listOf(1, 1, 0, 4),
                    listOf(2, 2, 1, 9),
                ),
                listOf(1, 2, 1),
                11,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `shopping offers test`(price: List<Int>, special: List<List<Int>>, needs: List<Int>, expected: Int) {
        val actual = strategy.invoke(price, special, needs)
        assertThat(actual).isEqualTo(expected)
    }
}

class ShoppingOffersRecursiveTest : ShoppingOffersTest<ShoppingOffers>(ShoppingOffersRecursive())
