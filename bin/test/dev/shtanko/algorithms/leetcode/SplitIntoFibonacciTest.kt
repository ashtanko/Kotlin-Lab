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

abstract class SplitIntoFibonacciTest<out T : SplitIntoFibonacci>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "1101111",
                listOf(11, 0, 11, 11),
            ),
            Arguments.of(
                "112358130",
                listOf<Int>(),
            ),
            Arguments.of(
                "0123",
                listOf<Int>(),
            ),
            Arguments.of(
                "",
                listOf<Int>(),
            ),
            Arguments.of(
                "123456579",
                listOf(123, 456, 579),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `split into fibonacci test`(num: String, expected: List<Int>) {
        val actual = strategy.invoke(num)
        assertThat(actual).containsAll(expected)
    }
}

class SplitIntoFibonacciBruteForceTest : SplitIntoFibonacciTest<SplitIntoFibonacci>(SplitIntoFibonacciBruteForce())
class SplitIntoFibonacciBacktrackingTest : SplitIntoFibonacciTest<SplitIntoFibonacci>(SplitIntoFibonacciBacktracking())
