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

abstract class RingsAndRodsTest<out T : RingsAndRods>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "B0B6G0R6R0R6G9",
                1,
            ),
            Arguments.of(
                "B0R0G0R9R0B0G0",
                1,
            ),
            Arguments.of(
                "G4",
                0,
            ),
            Arguments.of(
                "R0G0B0R1G1B1",
                2,
            ),
            Arguments.of(
                "R0G0B0R1G1B1R2G2B2",
                3,
            ),
            Arguments.of(
                "R0G0B0R1G1B1R2G2B2R3G3B3",
                4,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count points test`(rings: String, expected: Int) {
        val actual = strategy.countPoints(rings)
        assertThat(actual).isEqualTo(expected)
    }
}

class RingsAndRodsBFTest : RingsAndRodsTest<RingsAndRods>(RingsAndRodsBF())
class RingsAndRodsBitmaskTest : RingsAndRodsTest<RingsAndRods>(RingsAndRodsBitmask())
class RingsAndRodsArrayTest : RingsAndRodsTest<RingsAndRods>(RingsAndRodsArray())
