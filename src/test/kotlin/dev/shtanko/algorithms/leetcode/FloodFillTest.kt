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

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal data class FloodFillTestCase(
    val image: List<IntArray>,
    val sr: Int,
    val sc: Int,
    val newColor: Int,
    val output: List<IntArray>
)

internal class FloodFillTest {

    companion object {
        @JvmStatic
        fun dataProvider(): List<FloodFillTestCase> {
            return listOf(
                FloodFillTestCase(
                    listOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1)),
                    1,
                    1,
                    2,
                    listOf(intArrayOf(2, 2, 2), intArrayOf(2, 2, 0), intArrayOf(2, 0, 1))
                ),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    internal fun `flood fill test`(testCase: FloodFillTestCase) {
        val (image, sr, sc, newColor, output) = testCase
        val actual = FloodFill.perform(image.toTypedArray(), sr, sc, newColor)
        val expected = output.toTypedArray()
        assertArrayEquals(expected, actual)
    }
}
