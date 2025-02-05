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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FindArrayDifferenceTest<out T : FindArrayDifference>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3),
                intArrayOf(2, 4, 6),
                listOf(
                    listOf(1, 3),
                    listOf(4, 6),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 3),
                intArrayOf(1, 1, 2, 2),
                listOf(
                    listOf(3),
                    listOf(),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(1, 2, 3, 4),
                listOf(
                    listOf(),
                    listOf<Int>(),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(1, 2, 3, 4, 5),
                listOf(
                    listOf(),
                    listOf(5),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(1, 2, 3, 4),
                listOf(
                    listOf(5),
                    listOf(),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(1, 2, 3, 4, 5),
                listOf(
                    listOf<Int>(),
                    listOf(),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find difference test`(nums1: IntArray, nums2: IntArray, expected: List<List<Int>>) {
        val actual = strategy.findDifference(nums1, nums2)
        assertThat(actual).isEqualTo(expected)
    }
}

class FindArrayDifferenceBFTest : FindArrayDifferenceTest<FindArrayDifference>(FindArrayDifferenceBF())
class FindArrayDifferenceHashSetTest : FindArrayDifferenceTest<FindArrayDifference>(FindArrayDifferenceHashSet())
