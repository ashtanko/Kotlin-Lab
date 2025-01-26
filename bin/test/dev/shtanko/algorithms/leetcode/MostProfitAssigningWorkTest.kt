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

abstract class MostProfitAssigningWorkTest<out T : MostProfitAssigningWork>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(2, 4, 6, 8, 10),
                intArrayOf(10, 20, 30, 40, 50),
                intArrayOf(4, 5, 6, 7),
                100,
            ),
            Arguments.of(
                intArrayOf(85, 47, 57),
                intArrayOf(24, 66, 99),
                intArrayOf(40, 25, 25),
                0,
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun maxProfitAssignmentTest(difficulty: IntArray, profit: IntArray, worker: IntArray, expected: Int) {
        val actual = strategy(difficulty, profit, worker)
        assertThat(actual).isEqualTo(expected)
    }
}

class MostProfitAssigningWorkBSGreedyTest :
    MostProfitAssigningWorkTest<MostProfitAssigningWork>(MostProfitAssigningWorkByDifficulty())

class MostProfitAssigningWorkByProfitTest :
    MostProfitAssigningWorkTest<MostProfitAssigningWork>(MostProfitAssigningWorkByProfit())

class MostProfitAssigningWorkTwoPointersTest :
    MostProfitAssigningWorkTest<MostProfitAssigningWork>(MostProfitAssigningWorkTwoPointers())

class MostProfitAssigningWorkMemoizationTest :
    MostProfitAssigningWorkTest<MostProfitAssigningWork>(MostProfitAssigningWorkMemoization())
