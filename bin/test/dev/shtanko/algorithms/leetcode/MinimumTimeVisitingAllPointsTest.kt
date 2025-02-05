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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MinimumTimeVisitingAllPointsTest<out T : MinTimeToVisitAllPoints>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1),
                    intArrayOf(3, 4),
                    intArrayOf(-1, 0),
                ),
                7,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2),
                    intArrayOf(-2, 2),
                ),
                5,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(45, 65),
                    intArrayOf(456, 5),
                    intArrayOf(6, 1),
                    intArrayOf(99, 19),
                    intArrayOf(6, 87),
                ),
                1047,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(4, 8, 15, 16, 23, 42),
                    intArrayOf(567, 111, 89, 34, 67, 1),
                    intArrayOf(9, 345, 12, 457, 87, 67),
                ),
                1121,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(20, 67, 161, 196, 317, 390, 459, 592, 698, 717, 720, 746, 764, 880, 955),
                    intArrayOf(38, 48, 158, 199, 244, 300, 315, 424, 592, 624, 635, 719, 791, 927, 938),
                    intArrayOf(3, 7, 26, 182, 209, 245, 426, 435, 715, 724, 757, 767, 776, 897, 914),
                    intArrayOf(2, 8, 25, 203, 235, 238, 328, 402, 583, 614, 676, 696, 826, 830, 833),
                    intArrayOf(4, 6, 151, 176, 213, 273, 344, 428, 434, 451, 471, 609, 733, 781, 784),
                    intArrayOf(243, 245, 341, 438, 480, 568, 589, 606, 648, 709, 721, 741, 827, 932, 958),
                    intArrayOf(31, 42, 79, 88, 104, 174, 478, 594, 598, 745, 764, 783, 834, 887, 997),
                    intArrayOf(52, 148, 214, 318, 454, 486, 491, 498, 583, 593, 661, 679, 816, 900, 931),
                    intArrayOf(164, 165, 226, 268, 273, 285, 322, 581, 583, 653, 803, 836, 920, 943, 997),
                    intArrayOf(94, 148, 389, 409, 550, 585, 647, 671, 715, 821, 847, 860, 887, 898, 937),
                ),
                802,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min time to visit all points test`(points: Array<IntArray>, expected: Int) {
        val actual = strategy(points)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MinTimeToVisitAllPointsMoveDiagonallyTest :
    MinimumTimeVisitingAllPointsTest<MinTimeToVisitAllPoints>(MinTimeToVisitAllPointsMoveDiagonally())
