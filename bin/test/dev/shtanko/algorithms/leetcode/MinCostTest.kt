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

abstract class MinCostTest<out T : MinCost>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                30,
                arrayOf(
                    intArrayOf(0, 1, 10),
                    intArrayOf(1, 2, 10),
                    intArrayOf(2, 5, 10),
                    intArrayOf(0, 3, 1),
                    intArrayOf(3, 4, 10),
                    intArrayOf(4, 5, 15),
                ),
                intArrayOf(5, 1, 2, 20, 20, 3),
                11,
            ),
            Arguments.of(
                29,
                arrayOf(
                    intArrayOf(0, 1, 10),
                    intArrayOf(1, 2, 10),
                    intArrayOf(2, 5, 10),
                    intArrayOf(0, 3, 1),
                    intArrayOf(3, 4, 10),
                    intArrayOf(4, 5, 15),
                ),
                intArrayOf(5, 1, 2, 20, 20, 3),
                48,
            ),
            Arguments.of(
                25,
                arrayOf(
                    intArrayOf(0, 1, 10),
                    intArrayOf(1, 2, 10),
                    intArrayOf(2, 5, 10),
                    intArrayOf(0, 3, 1),
                    intArrayOf(3, 4, 10),
                    intArrayOf(4, 5, 15),
                ),
                intArrayOf(5, 1, 2, 20, 20, 3),
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min cost test`(maxTime: Int, edges: Array<IntArray>, passingFees: IntArray, expected: Int) {
        val actual = strategy.invoke(maxTime, edges, passingFees)
        assertThat(actual).isEqualTo(expected)
    }
}

class MinCostQueueTest : MinCostTest<MinCost>(MinCostQueue())
