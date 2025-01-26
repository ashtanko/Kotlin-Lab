/*
 * Copyright 2024 Oleksii Shtanko
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

abstract class MinCommonValTest<out T : MinCommonVal>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3),
                intArrayOf(2, 4),
                2,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 6),
                intArrayOf(2, 3, 4, 5),
                2,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun getCommonTest(nums1: IntArray, nums2: IntArray, expected: Int) {
        val actual = strategy(nums1, nums2)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MinCommonValHashSetTest : MinCommonValTest<MinCommonVal>(MinCommonValHashSet())
class MinCommonValTwoPointersTest : MinCommonValTest<MinCommonVal>(MinCommonValTwoPointers())
class MinCommonValBinarySearchTest : MinCommonValTest<MinCommonVal>(MinCommonValBinarySearch())
