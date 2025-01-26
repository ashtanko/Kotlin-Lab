/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class FurthestBuildingTest<out T : FurthestBuilding>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 2, 7, 6, 9, 14, 12),
                5,
                1,
                4,
            ),
            Arguments.of(
                intArrayOf(4, 12, 2, 7, 3, 18, 20, 3, 19),
                10,
                2,
                7,
            ),
            Arguments.of(
                intArrayOf(14, 3, 19, 3),
                17,
                0,
                3,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                5,
                2,
                3,
            ),
            Arguments.of(
                intArrayOf(4, 2, 7, 6, 9, 14, 12),
                0,
                0,
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `furthest building test`(heights: IntArray, bricks: Int, ladders: Int, expected: Int) {
        val actual = strategy.invoke(heights, bricks, ladders)
        assertThat(actual).isEqualTo(expected)
    }
}

class FurthestBuildingMinHeapTest : FurthestBuildingTest<FurthestBuildingMinHeap>(FurthestBuildingMinHeap())
class FurthestBuildingMaxHeapTest : FurthestBuildingTest<FurthestBuildingMaxHeap>(FurthestBuildingMaxHeap())
class FinalReachableBuildingTest : FurthestBuildingTest<FinalReachableBuilding>(FinalReachableBuilding())
class ImprovedFinalReachableBuildingTest :
    FurthestBuildingTest<ImprovedFinalReachableBuilding>(ImprovedFinalReachableBuilding())

class BSThresholdTest : FurthestBuildingTest<BSThreshold>(BSThreshold())
