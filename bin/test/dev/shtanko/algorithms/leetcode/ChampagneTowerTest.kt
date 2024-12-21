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

abstract class ChampagneTowerTest<out T : ChampagneTower>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                1,
                1,
                0.00000,
            ),
            Arguments.of(
                2,
                1,
                1,
                0.50000,
            ),
            Arguments.of(
                100000009,
                33,
                17,
                1.00000,
            ),
            Arguments.of(
                0,
                0,
                0,
                0,
            ),
            Arguments.of(
                1,
                2,
                1,
                0,
            ),
            Arguments.of(
                2,
                2,
                1,
                0.0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `champagne tower test`(poured: Int, queryRow: Int, queryGlass: Int, expected: Double) {
        val actual = strategy.invoke(poured, queryRow, queryGlass)
        assertThat(actual).isEqualTo(expected)
    }
}

class ChampagneTowerSimulationTest : ChampagneTowerTest<ChampagneTower>(ChampagneTowerSimulation())
