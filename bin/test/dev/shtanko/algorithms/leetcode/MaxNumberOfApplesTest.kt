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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MaxNumberOfApplesTest<out T : MaxNumberOfApples>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(100, 200, 150, 1000),
                4,
            ),
            Arguments.of(
                intArrayOf(900, 950, 800, 1000, 700, 800),
                5,
            ),
            Arguments.of(
                intArrayOf(
                    988,
                    641,
                    984,
                    635,
                    461,
                    527,
                    491,
                    610,
                    274,
                    104,
                    348,
                    468,
                    220,
                    837,
                    126,
                    111,
                    536,
                    368,
                    89,
                    201,
                    589,
                    481,
                    849,
                    708,
                    258,
                    853,
                    563,
                    868,
                    92,
                    76,
                    566,
                    398,
                    272,
                    697,
                    584,
                    851,
                    302,
                    766,
                    88,
                    428,
                    276,
                    797,
                    460,
                    244,
                    950,
                    134,
                    838,
                    161,
                    15,
                    330,
                ),
                23,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max number of apples test`(arr: IntArray, expected: Int) {
        val actual = strategy.invoke(arr)
        assertThat(actual, equalTo(expected))
    }
}

class MaxNumberOfApplesSortTest :
    MaxNumberOfApplesTest<MaxNumberOfApplesSort>(MaxNumberOfApplesSort())

class MaxNumberOfApplesFurthestBuildingMinHeapTest :
    MaxNumberOfApplesTest<MaxNumberOfApplesMinHeap>(MaxNumberOfApplesMinHeap())

class MaxNumberOfApplesBucketSortTest :
    MaxNumberOfApplesTest<MaxNumberOfApplesBucketSort>(MaxNumberOfApplesBucketSort())
