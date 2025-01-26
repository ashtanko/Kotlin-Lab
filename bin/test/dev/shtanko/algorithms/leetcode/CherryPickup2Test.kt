/*
 * Copyright 2020 Oleksii Shtanko
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class CherryPickup2Test<out T : CherryPickup2Strategy>(private val strategy: T) {

    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 1, 1),
                    intArrayOf(2, 5, 1),
                    intArrayOf(1, 5, 5),
                    intArrayOf(2, 1, 1),
                ),
                24,
            ),
            Arguments.of(
                arrayOf<IntArray>(),
                0,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0),
                ),
                0,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1),
                ),
                1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(0, 0),
                ),
                1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1),
                    intArrayOf(1, 1),
                ),
                4,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 1, 1),
                ),
                6,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 1, 1),
                ),
                8,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `cherry pickup test`(grid: Array<IntArray>, expected: Int) {
        val actual = strategy.invoke(grid)
        assertEquals(expected, actual)
    }
}

class CherryPickup2DPTopDownTest : CherryPickup2Test<CherryPickup2DPTopDown>(CherryPickup2DPTopDown())

class CherryPickup2DPBottomUpTest : CherryPickup2Test<CherryPickup2DPBottomUp>(CherryPickup2DPBottomUp())
