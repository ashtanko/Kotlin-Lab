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

abstract class AverageWaitingTimeTest<out T : AverageWaitingTime>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 5),
                    intArrayOf(4, 3),
                ),
                5,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(5, 2),
                    intArrayOf(5, 4),
                    intArrayOf(10, 3),
                    intArrayOf(20, 1),
                ),
                3.25,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `average waiting time test`(customers: Array<IntArray>, expected: Double) {
        val actual = strategy(customers)
        assertThat(actual).isEqualTo(expected)
    }
}

class AverageWaitingTimeIterativeTest : AverageWaitingTimeTest<AverageWaitingTime>(AverageWaitingTimeIterative())
