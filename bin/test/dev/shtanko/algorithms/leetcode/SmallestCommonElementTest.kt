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

abstract class SmallestCommonElementTest<out T : SmallestCommonElement>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(intArrayOf()),
                -1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1),
                    intArrayOf(1),
                    intArrayOf(1),
                    intArrayOf(1),
                ),
                1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(1, 4, 5),
                    intArrayOf(1, 6, 7),
                    intArrayOf(1, 8, 9),
                ),
                1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3, 4, 5),
                    intArrayOf(2, 4, 5, 8, 10),
                    intArrayOf(3, 5, 7, 9, 11),
                    intArrayOf(1, 3, 5, 7, 9),
                ),
                5,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `Find Smallest Common Element in All Rows Test`(param: Array<IntArray>, expected: Int) {
        val actual = strategy.invoke(param)
        assertThat(actual).isEqualTo(expected)
    }
}

class SCECountElementsTest : SmallestCommonElementTest<SCECountElements>(SCECountElements())
class SCECountElementsImprovedTest :
    SmallestCommonElementTest<SCECountElementsImproved>(SCECountElementsImproved())

class SCEBinarySearchTest : SmallestCommonElementTest<SCEBinarySearch>(SCEBinarySearch())
class SCEBinarySearchImprovedTest :
    SmallestCommonElementTest<SCEBinarySearchImproved>(SCEBinarySearchImproved())

class SCERowPositionsTest : SmallestCommonElementTest<SCERowPositions>(SCERowPositions())
class SCERowPositionsImprovedTest :
    SmallestCommonElementTest<SCERowPositionsImproved>(SCERowPositionsImproved())
