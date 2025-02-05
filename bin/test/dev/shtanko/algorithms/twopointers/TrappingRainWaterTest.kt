/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.twopointers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TrappingRainWaterTest {

    @Test
    fun `test trapping rain water with a simple example`() {
        val height = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
        val expected = 6

        val trappedWater = trap(height)

        assertEquals(expected, trappedWater)
    }

    @Test
    fun `test trapping rain water with an empty array`() {
        val height = intArrayOf()
        val expected = 0

        val trappedWater = trap(height)

        assertEquals(expected, trappedWater)
    }

    @Test
    fun `test trapping rain water with no elevation differences`() {
        val height = intArrayOf(5, 5, 5, 5)
        val expected = 0

        val trappedWater = trap(height)

        assertEquals(expected, trappedWater)
    }

    @Test
    fun `test trapping rain water with increasing elevations`() {
        val height = intArrayOf(1, 2, 3, 4, 5)
        val expected = 0

        val trappedWater = trap(height)

        assertEquals(expected, trappedWater)
    }
}
