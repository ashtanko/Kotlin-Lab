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

class ContainerWithMostWaterTest {

    @Test
    fun `test maximum water container area`() {
        val heights = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
        val expectedArea = 49

        val area = maxArea(heights)

        assertEquals(expectedArea, area)
    }

    @Test
    fun `test maximum water container area with empty array`() {
        val heights = intArrayOf()
        val expectedArea = 0

        val area = maxArea(heights)

        assertEquals(expectedArea, area)
    }

    @Test
    fun `test maximum water container area with small array`() {
        val heights = intArrayOf(1, 1)
        val expectedArea = 1

        val area = maxArea(heights)

        assertEquals(expectedArea, area)
    }
}
