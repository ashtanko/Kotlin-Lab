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

abstract class MinAbsoluteSumDiffTest<out T : MinAbsoluteSumDiff>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 7, 5),
                intArrayOf(2, 3, 5),
                3,
            ),
            Arguments.of(
                intArrayOf(2, 4, 6, 8, 10),
                intArrayOf(2, 4, 6, 8, 10),
                0,
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                intArrayOf(1),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 10, 4, 4, 2, 7),
                intArrayOf(9, 3, 5, 1, 7, 4),
                20,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min absolute sum diff test`(nums1: IntArray, nums2: IntArray, expected: Int) {
        val actual = strategy.invoke(nums1, nums2)
        assertThat(actual).isEqualTo(expected)
    }
}

class MinAbsoluteSumDiffBinarySearchTest : MinAbsoluteSumDiffTest<MinAbsoluteSumDiff>(MinAbsoluteSumDiffBinarySearch())
class MinAbsoluteSumDiffSimpleTest : MinAbsoluteSumDiffTest<MinAbsoluteSumDiff>(MinAbsoluteSumDiffSimple())
class MinAbsoluteSumDiffBinarySearch2Test :
    MinAbsoluteSumDiffTest<MinAbsoluteSumDiff>(MinAbsoluteSumDiffBinarySearch2())
