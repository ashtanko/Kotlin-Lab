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

abstract class ContainerWithMostWaterStrategyTest<out T : ContainerWithMostWaterStrategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7), 49),
            Arguments.of(intArrayOf(4, 8, 15, 16, 23, 42), 45),
            Arguments.of(intArrayOf(), 0),
            Arguments.of(intArrayOf(1), 0),
            Arguments.of(intArrayOf(1, 1), 1),
            Arguments.of(intArrayOf(1, 1, 3), 2),
            Arguments.of(intArrayOf(1, 2, 1), 2),
            Arguments.of(intArrayOf(1, 2, 3), 2),
            Arguments.of(intArrayOf(1, 2, 3, 4), 4),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5), 6),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `container with most water strategy test`(arr: IntArray, expected: Int) {
        val actual = strategy.maxArea(arr)
        assertEquals(expected, actual)
    }
}

class ContainerWithMostWaterBruteForceTest :
    ContainerWithMostWaterStrategyTest<ContainerWithMostWaterBruteForce>(ContainerWithMostWaterBruteForce())

class ContainerWithMostWaterTwoPointerTest :
    ContainerWithMostWaterStrategyTest<ContainerWithMostWaterTwoPointer>(ContainerWithMostWaterTwoPointer())
