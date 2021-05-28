/*
 * Copyright 2021 Alexey Shtanko
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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal abstract class MaximumErasureValueTest<out T : MaximumErasureValue>(private val strategy: T) {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0
            ),
            Arguments.of(
                intArrayOf(4, 2, 4, 5, 6),
                17
            ),
            Arguments.of(
                intArrayOf(5, 2, 1, 2, 5, 2, 1, 2, 5),
                8
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `maximum unique subarray test`(nums: IntArray, expected: Int) {
        val actual = strategy.maximumUniqueSubarray(nums)
        assertThat(actual).isEqualTo(expected)
    }
}

internal class MEVBruteForceTest : MaximumErasureValueTest<MEVBruteForce>(MEVBruteForce())
internal class MEVTwoPointerSetTest : MaximumErasureValueTest<MEVTwoPointerSet>(MEVTwoPointerSet())
internal class MEVTwoPointerBooleanArrayTest :
    MaximumErasureValueTest<MEVTwoPointerBooleanArray>(MEVTwoPointerBooleanArray())

internal class MEVTwoPointerCountMapTest : MaximumErasureValueTest<MEVTwoPointerCountMap>(MEVTwoPointerCountMap())
internal class MEVPrefixSumTest : MaximumErasureValueTest<MEVPrefixSum>(MEVPrefixSum())
internal class MEVPrefixSumCountArrayTest : MaximumErasureValueTest<MEVPrefixSumCountArray>(MEVPrefixSumCountArray())
