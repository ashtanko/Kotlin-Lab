/*
 * Copyright 2024 Oleksii Shtanko
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

abstract class RevealCardsInIncreasingOrderTest<out T : RevealCardsInIncreasingOrder>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(17, 13, 11, 2, 3, 5, 7),
                intArrayOf(2, 13, 3, 11, 5, 17, 7),
            ),
            Arguments.of(
                intArrayOf(1, 1000),
                intArrayOf(1, 1000),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun deckRevealedIncreasingTest(deck: IntArray, expected: IntArray) {
        val actual = strategy(deck)
        assertThat(actual).isEqualTo(expected)
    }
}

class RevealCardsInIncreasingOrderTwoPointersTest :
    RevealCardsInIncreasingOrderTest<RevealCardsInIncreasingOrderTwoPointers>(RevealCardsInIncreasingOrderTwoPointers())

class RevealCardsInIncreasingOrderQueueTest :
    RevealCardsInIncreasingOrderTest<RevealCardsInIncreasingOrderQueue>(RevealCardsInIncreasingOrderQueue())
