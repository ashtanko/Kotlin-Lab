/*
 * Copyright 2020 Alexey Shtanko
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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal abstract class TrappingRainWaterTest<out T : RainWaterStrategy>(private val strategy: T) {

    companion object {
        @JvmStatic
        fun dataProvider(): List<Pair<IntArray, Int>> {
            return listOf(
                intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1) to 6,
                intArrayOf() to 0,
                intArrayOf(1) to 0,
                intArrayOf(0, 1) to 0,
                intArrayOf(0, 1, 2, 1, 0, 3, 2) to 3
            )
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    internal fun `trapping rain water test`(testCase: Pair<IntArray, Int>) {
        val (arr, expected) = testCase
        val actual = strategy.perform(arr)
        assertEquals(expected, actual)
    }
}

internal class RainWaterStraightForwardTest :
    TrappingRainWaterTest<RainWaterStraightForward>(RainWaterStraightForward())

internal class RainWaterStackTest : TrappingRainWaterTest<RainWaterStack>(RainWaterStack())
