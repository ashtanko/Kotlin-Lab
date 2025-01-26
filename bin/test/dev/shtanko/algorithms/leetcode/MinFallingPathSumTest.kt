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

abstract class MinFallingPathSumTest<out T : MinFallingPathSum>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 1, 3),
                    intArrayOf(6, 5, 4),
                    intArrayOf(7, 8, 9),
                ),
                13,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(-19, 57),
                    intArrayOf(-40, -5),
                ),
                -59,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(-19, -1, -96, 48, -94, 36, 16, 55, -42, 37, -59, 6, -32, 96, 95, -58, 13, -34, 94, 85),
                    intArrayOf(17, 44, 36, -29, 84, 80, -34, 50, -99, 64, 13, 91, -27, 25, -36, 57, 20, 98, -100, -72),
                    intArrayOf(-92, -75, 86, 90, -4, 90, 64, 56, 50, -63, 10, -15, 90, -66, -66, 32, -69, -78, 1, 60),
                    intArrayOf(21, 51, -47, -43, -14, 99, 44, 90, 8, 11, 99, -62, 57, 59, 69, 50, -69, 32, 85, 13),
                    intArrayOf(-28, 90, 12, -18, 23, 61, -55, -97, 6, 89, 36, 26, 26, -1, 46, -50, 79, -45, 89, 86),
                    intArrayOf(-85, -10, 49, -10, 2, 62, 41, 92, -67, 85, 86, 27, 89, -50, 77, 55, 22, -82, -94, -98),
                    intArrayOf(-62, -78, 8, -92, 86, 69, 90, -37, 81, 97, 53, -45, 34, 19, -19, -39, -88, -75, -74, -4),
                    intArrayOf(29, 53, -91, 65, -92, 11, 49, 26, 90, -31, 17, -84, 12, 63, -60, -48, 40, -49, -48, 88),
                    intArrayOf(100, -69, 80, 11, -93, 17, 28, -94, 52, 64, -86, 30, -9, -53, -8, -68, -33, 31, -5, 11),
                    intArrayOf(9, 64, -31, 63, -84, -15, -30, -10, 67, 2, 98, 73, -77, -37, -96, 47, -97, 78, -62, -17),
                    intArrayOf(66, -33, 91, -6, -94, 82, 25, -43, -93, -25, -69, 10, -71, -65, 85, 28, -52, 76, 25, 90),
                    intArrayOf(-97, 65, 2, -93, 56, -78, 22, 56, 35, -24, -95, -13, 83, -34, -51, -73, 2, 7, -86, -19),
                    intArrayOf(60, 93, -79, 76, -91, 43, -95, -16, 74, -21, 85, 43, 21, -68, -32, -18, 18, 100, -43, 1),
                    intArrayOf(87, -31, 26, 53, 26, 51, -61, 92, -65, 17, -41, 27, -42, -14, 37, -46, 46, -31, -74, 23),
                    intArrayOf(-67, -14, -20, -85, 42, 36, 56, 9, 11, -66, -59, -55, 5, 64, -29, 77, 47, 44, -33, -77),
                ),
                -989,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min falling path sum test`(matrix: Array<IntArray>, expected: Int) {
        val actual = strategy.invoke(matrix)
        assertThat(actual).isEqualTo(expected)
    }
}

class MinFallingPathSumTopDownTest : MinFallingPathSumTest<MinFallingPathSum>(MinFallingPathSumTopDown())
class MinFallingPathSumDPMemoTest : MinFallingPathSumTest<MinFallingPathSum>(MinFallingPathSumDPMemo())
class MinFallingPathSumBottomUpTest : MinFallingPathSumTest<MinFallingPathSum>(MinFallingPathSumBottomUp())
